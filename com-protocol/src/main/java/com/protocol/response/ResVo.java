package com.protocol.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.macro.common.exception.SystemError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 返回数据基类
 * @author macro  2018年1月3日下午3:38:34
 *
 */
@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResVo<T> {

	/**状态***/
	private Integer code;
	/**提示信息***/
	private String errorMsg;
	/**返回数据内容***/
	private T data;

	public static <T> ResVo<T> success(T data){
		return ResVo.<T>builder().code(0).errorMsg("SUCCESS").data(data).build();
	}

	public static <T> ResVo<T> fail(SystemError error){
		return ResVo.<T>builder().code(error.getCode()).errorMsg(error.getMessage()).build();
	}

	public static <T> ResVo<T> fail(int code, String error){
		return ResVo.<T>builder().code(code).errorMsg(error).build();
	}
}
