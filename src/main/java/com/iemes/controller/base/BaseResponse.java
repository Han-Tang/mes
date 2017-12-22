package com.iemes.controller.base;

/**
 * 返回信息实体类
 * @author Administrator
 *
 */
public class BaseResponse {

	private boolean result;		//状态
	
	private String message;		//成功
	
	private Object data;		//data

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "{result : "+result+",message : "+message+",data : "+data+"}";
	}
	
	
}
