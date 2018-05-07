package com.dtte.portal.obiee.model;

public class Method_Response {

	public enum ResponseCodes {
		SUCCESS,
		DUPLICATE_REGISTER,
		GENERIC_ERROR
	}
	
	public Method_Response() {
		this.code = ResponseCodes.SUCCESS;
	}
	
	private String message;
	private ResponseCodes code;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ResponseCodes getCode() {
		return code;
	}
	public void setCode(ResponseCodes code) {
		this.code = code;
	}
	
	
	
}
