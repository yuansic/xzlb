package com.xz.vo.entity;

/**
 * 全局异常枚举
 * @author wubei
 *
 */
public enum ResultCode {

	/**
	 * code:0, message:请求服务器成功
	 */
	SUCCESS(0, "成功"),
	ERROR(1, "失败"),
	/**
	 * code:-1, message:服务器未知异常
	 */
	UNKNOWN(-1, "服务器未知异常"),
	/**
	 * code:-1, message:参数错误
	 */
	PARAM(400, "参数错误"),
	AUTH(401, "token认证失败"),
	NOTFOUND(404, "访问页面不存在");

	private int code;
	private String message;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	ResultCode(int code, String message){
		this.code = code;
		this.message = message;
	}
}
