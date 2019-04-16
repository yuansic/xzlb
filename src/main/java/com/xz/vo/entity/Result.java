package com.xz.vo.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Ajax请求返回实体 全局信息{@link ResultCode}
 * @author wubei
 *
 */
public class Result<T> implements Serializable {

	private int status; // 响应请求的编码
	private String message; // 响应请求的消息
	private T data; // 响应请求的对应

	public Result() {
	}

	public Result(ResultCode resultCode) {
		this.status = resultCode.getCode();
		this.message = resultCode.getMessage();
	}

	public Result(ResultCode resultCode, T data) {
		this.status = resultCode.getCode();
		this.message = resultCode.getMessage();
		this.data = data;
	}

	public Result(int status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
