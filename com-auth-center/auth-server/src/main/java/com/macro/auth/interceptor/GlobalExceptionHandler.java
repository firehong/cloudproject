package com.macro.auth.interceptor;


import com.macro.common.exception.SystemError;
import com.macro.common.response.BaseResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @auther Macro
 * @date 2019/11/8 16:14
 * @Description 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler{

    /**
     * @date 2019/11/8 18:07
     * @Description 参数校验失败异常返回
     * @Param
     */
    @ExceptionHandler(value =BindException.class)
    @ResponseBody
    public BaseResult handleBindException(BindException e){
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        FieldError fieldError = e.getFieldError();
        StringBuilder sb = new StringBuilder();
        sb.append(fieldError.getField()).append("=[").append(fieldError.getRejectedValue()).append("]")
                .append(fieldError.getDefaultMessage());
        // 生成返回结果
        return BaseResult.fail(SystemError.PARAM_ERROR.getCode(), sb.toString());
    }

    /**
     * @date 2019/11/8 18:41
     * @Description 参数校验失败异常返回
     * @Param
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = SystemError.PARAM_ERROR.getMessage();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage = fieldError.getDefaultMessage();
        }
        // 生成返回结果
        return BaseResult.fail(SystemError.PARAM_ERROR.getCode(), errorMesssage);
    }

    /**
     * @date 2019/11/11 19:26
     * @Description 缺少参数信息
     * @Param
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public BaseResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
        // 生成返回结果
        return BaseResult.fail(SystemError.PARAM_ERROR.getCode(), e.getMessage());
    }


}
