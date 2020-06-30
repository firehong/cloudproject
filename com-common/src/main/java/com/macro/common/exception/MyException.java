package com.macro.common.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 公共异常处理类
 * 
 * @author macro  2017-7-21 下午5:52:19
 * @version share 1.0
 */
public class MyException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 1L;

    /********异常代码***********/
    @Setter
    @Getter
    private Integer code; 
    
    public MyException(String message){  
        super(message);
        this.code=SystemError.SERVER_ERROR.getCode();
    } 
    
    public MyException(Integer code, String message){
        super(message);  
        this.code = code;
    }

    public MyException(SystemError systemError){
        super(systemError.getMessage());
        this.code=systemError.getCode();
    }

    public MyException(String message, Exception e){
        super(message,e);
        this.code=SystemError.SERVER_ERROR.getCode();
    }

    public MyException() {
    }

}
