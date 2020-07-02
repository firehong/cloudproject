package com.macro.user.interceptor;

import com.alibaba.fastjson.JSON;
import com.macro.common.exception.MyException;
import com.macro.common.exception.SystemError;
import com.macro.common.response.BaseResult;
import com.macro.common.utils.IpUtil;
import com.macro.common.utils.JwtUtils;
import com.macro.common.utils.SignUtil;
import com.macro.common.utils.StringUtil;
import com.macro.common.vo.user.UserVO;
import com.macro.user.common.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletResponseWrapper;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther Macro
 * @date 2019-06-25 13:52
 * @description 请求日志记录
 */
@Component
@Aspect
@Slf4j
public class ApiAspectContent extends BaseService {

	@Value("${jwt.config.secret}")
	private String secret;
	@Value("${spring.profiles.active}")
	private String profiles;

	/**
	 * 拦截contoller层 操作日志
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.macro.user.api.controller.*.*(..))")
	public Object getCache(ProceedingJoinPoint jp) throws Throwable{
		//获取类名
		String className = jp.getThis().toString();
		//获取方法名
	    String methodName = jp.getSignature().getName();
	    // 请求地址
		String ip = IpUtil.getIpAddress(req);
	    //方法开始时间
	    long start = System.currentTimeMillis();
	    Object result;
		Map params = new HashMap();
	    try {
			//参数获取
			params = methodBefore(jp);
			//可以执行数据解密等工作
	    	//执行方法
	    	result = jp.proceed();

			//日志打印
			if(!"prod".equals(profiles)){
				log.info("\n ----------------接口日志开始--------------- \n"+
								"[API]类名:{} \n"+
								"方法名:{} \n"+
								"请求ip地址:{} \n"+
								"请求TOKEN:{} \n"+
								"请求参数列表:{} \n"+
								"返回参数列表:{} \n"+
								"共计消耗:{} ms \n"+
								"----------------接口日志结束--------------- \n",
						className, methodName, ip, req.getHeader("token"), params, JSON.toJSONString(result),
						(System.currentTimeMillis() - start));
			}
			if("prod".equals(profiles)){
				log.info("\n ----------------接口日志开始--------------- \n"+
								"[API]类名:{} \n"+
								"方法名:{} \n"+
								"请求ip地址:{} \n"+
								"请求TOKEN:{} \n"+
								"请求参数列表:{} \n"+
								"共计消耗:{} ms \n"+
								"----------------接口日志结束--------------- \n",
						className, methodName, ip, req.getHeader("token"), params, (System.currentTimeMillis() - start));
			}
	    	//返回数据
	    	return result;
		} catch (Exception e) {
	    	//参数校验错误
	    	if(e instanceof ConstraintViolationException){
				return BaseResult.fail(SystemError.PARAM_ERROR.getCode(), e.getMessage());
			}
			//自定义异常暂时不管
			if (e instanceof MyException) {
				return BaseResult.fail(((MyException) e).getCode(), e.getMessage());
			}else {
				//系统异常打印到日志
				log.error("\n[API]类名:{} ,\n" +
							"方法名:{} \n"+
							"请求TOKEN:{} \n"+
						  	"请求参数列表:{} \n"+
		  	    		  	"方法名:{}  ,\n"+
						  	"异常连接--》",
							className, methodName, req.getHeader("token"), params, methodName, e);
				//重复插入数据
				if(e.getCause() instanceof SQLIntegrityConstraintViolationException){
					return BaseResult.fail(SystemError.OPERATION_REPEAT);
				}
				return BaseResult.fail(SystemError.SERVER_ERROR);
			}
	    }
	}

	/**
	 * @date 2019/11/7 20:36
	 * @Description 获取请求参数
	 * @Param
	 */
	public Map methodBefore(JoinPoint joinPoint) {
		// 请求参数。
		Object[] objs = joinPoint.getArgs();
		String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名
		Map paramMap = new HashMap();
		for (int i = 0; i < objs.length; i++) {
			if (!(objs[i] instanceof ExtendedServletRequestDataBinder) && !(objs[i] instanceof HttpServletResponseWrapper)
					&& !(objs[i] instanceof MultipartFile)) {
				paramMap.put(argNames[i], objs[i]);
			}
		}
		return paramMap;
	}

	/**
	 * @date 2019/11/7 17:59
	 * @Description 参数合法性校验
	 * @Param
	 */
	public String verifData(Map params){
		//获取请求头信息
		Long timestamp = Long.getLong(req.getHeader("timestamp")==null ? "1" : req.getHeader("timestamp"));
		Integer dataType = Integer.getInteger(req.getHeader("dataType")==null ? "0" : req.getHeader("dataType"));
		String sign = req.getHeader("sign")==null ? "" : req.getHeader("sign");
		String version = req.getHeader("version")==null ? "" : req.getHeader("version");
		String uid = req.getHeader("uid")==null ? "" : req.getHeader("uid");
		String token = req.getHeader("token")==null ? "" : req.getHeader("token");
		if(timestamp ==null || sign.isEmpty() || version.isEmpty() || uid.isEmpty() || token.isEmpty()){
			return "参数缺失";
		}
		// 签名校验
		params.put("timestamp",timestamp);
		params.put("version",version);
		params.put("token",token);
		String checkSign = SignUtil.sign(params, "token");
		if(!sign.toUpperCase().equals(checkSign)){
			log.error("请求签名值：{}，系统签名值：{}", sign, checkSign);
			return "签名错误";
		}
		return "SUCCESS";
	}

	/**
	 * 鉴权处理
	 * @author Macro 2019-07-03 11:44
	 * @param
	 * @return boolean
	 */
	private boolean auth(ProceedingJoinPoint jp){
		//判断是否需要鉴权处理
		MethodSignature ms = (MethodSignature)jp.getSignature();
		Method me = ms.getMethod();
		Auth auth = me.getAnnotation(Auth.class);
		//如果方法上没有注解，获取类上面的注解
		if(auth == null){
			auth = jp.getTarget().getClass().getAnnotation(Auth.class);
		}
		//没有标记任何注解，默认不需要鉴权
		int root = 0;
		if(auth != null){
			root = auth.isAuth();
		}
		if(root == 0){
			//不需要鉴权
			return true;
		}
		//判断登录状态鉴权检验
		String token = req.getHeader("token");
		if(StringUtil.isBlank(token)){return false;}
		// 判断token有效性
		if(!JwtUtils.validate(token, secret)){
			return false;
		}
		UserVO userVO = redisUtil.get(token);
		if(userVO == null){
			return false;
		}
		UserContext.setUser(userVO);
		//判断请求方法权限
		String authCode = auth.authCode();
		if("".equals(authCode)){
			return true;
		}
//		List<String> authList = userVO.getAuthList();
//		if(authList.contains(authCode)){
//			return true;
//		}
		return false;
	}



}
