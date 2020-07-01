package com.macro.user.interceptor;

import com.alibaba.fastjson.JSON;
import com.macro.common.exception.MyException;
import com.macro.common.exception.SystemError;
import com.macro.common.response.BaseResult;
import com.macro.common.utils.IpUtil;
import com.macro.user.common.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletResponseWrapper;
import javax.validation.ConstraintViolationException;
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
	    	log.info("\n ----------------接口日志开始--------------- \n"+
					 "[API]类名:{} \n"+
					 "方法名:{} \n"+
					 "请求ip地址:{} \n"+
			  		 "请求参数列表:{} \n"+
					 "返回参数列表:{} \n"+
					 "共计消耗:{} ms \n"+
					 "----------------接口日志结束--------------- \n",
					 className, methodName, ip, params, JSON.toJSONString(result),
					 (System.currentTimeMillis() - start));
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
						  	"请求参数列表:{} \n"+
		  	    		  	"方法名:{}  ,\n"+
						  	"异常连接--》",className, methodName, params, methodName, e);
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



}
