package com.vol.vtg.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vol.vtg.common.Result;
import com.vol.vtg.common.ResultCode;

@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {
		 
	@ExceptionHandler
	public ResponseEntity<Result<Object>> handleExceptions(Exception e) {
		 return ResponseEntity.ok().header("error", "Global unhandle exceptions")
		            .body(new Result<>().code(ResultCode.FAILED).message(e.getStackTrace()[0].toString()));
    }   
	
}

