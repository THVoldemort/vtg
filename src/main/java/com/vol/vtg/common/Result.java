package com.vol.vtg.common;

public class Result<T> {
	private Integer code;
	private String message;
	private T value;
	public int getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public Result() {
	}
	
	public Result(Integer code, String message, T value) {
		super();
		this.code = code;
		this.message = message;
		this.value = value;
	}
	public Result(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public Result<T> code(Integer code) {
		this.code = code;
		return this;
	}
	
	public Result<T> message(String message){
		this.message = message;
		return this;
	}
	
	public Result<T> value(T value){
		this.value = value;
		return this;
	}
	
	public static Result<?> createSuccess(){
		Result<?> result = new Result<>();
		result.code(ResultCode.SUCCESS).message(ResultMessage.SUCCESS);
		return result;
	}
	
	public static Result<?> createFail(){
		Result<?> result = new Result<>();
		result.code(ResultCode.FAILED).message(ResultMessage.FAILED);
		return result;
	}
}
