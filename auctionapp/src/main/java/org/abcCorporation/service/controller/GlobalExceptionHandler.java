package org.abcCorporation.service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{

	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleMyException2(Exception exp){
		Map<String,String>map = new HashMap<>();map.put("exception", "General Parse Exception");
		//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
