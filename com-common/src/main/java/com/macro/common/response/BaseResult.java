package com.macro.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.macro.common.exception.SystemError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回数据基类
 * @author macro  2018年1月3日下午3:38:34
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel("数据返回")
public class BaseResult<T> {

	@ApiModelProperty(value="请求状态 0：成功",name="code")
	private Integer code;
	@ApiModelProperty(value="提示信息",name="msg")
	private String msg;
	@ApiModelProperty(value="总记录数",name="total")
	private Long total;
	@ApiModelProperty(value="返回数据内容",name="data")
	private T data;

	public BaseResult(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public BaseResult(Integer code, String msg, T data, Long total) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.total = total;
	}

	/**
	 * 成功返回结果
	 */
	public static <T> BaseResult<T> success() {
		return new BaseResult<T>(0, "SUCCESS", null);
	}

	/**
	 * 成功返回结果
	 * @param data 获取的数据
	 */
	public static <T> BaseResult<T> data(T data) {
		return new BaseResult(0, "SUCCESS", data);
	}

	/**
	 * 成功返回结果
	 * @param data 获取的数据
	 */
	public static <T> BaseResult<T> data(T data, Long total) {
		return new BaseResult(0, "SUCCESS", data, total);
	}

	/**
	 * 失败返回结果
	 */
	public static <T> BaseResult<T> fail() {
		return fail(SystemError.OPERATION_FAIL);
	}

	/**
	 * 失败返回结果
	 * @param errorCode 错误码
	 */
	public static <T> BaseResult<T> fail(SystemError errorCode) {
		return new BaseResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
	}

	/**
	 * 失败返回结果
	 * @param code 错误码
	 */
	public static <T> BaseResult<T> fail(Integer code, String msg) {
		return new BaseResult<T>(code, msg, null);
	}

}
