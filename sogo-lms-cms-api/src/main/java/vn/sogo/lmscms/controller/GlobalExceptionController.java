package vn.sogo.lmscms.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import vn.sogo.lmscms.model.response.ApiResponse;

@RestControllerAdvice 
public class GlobalExceptionController {
	
	private static final Logger logger = Logger.getLogger(GlobalExceptionController.class);
		
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		
		logger.error("Api Error", ex);
		
		Throwable inner = null;
        Throwable root = ex;
        while ((inner = root.getCause()) != null)
        {
            root = inner;
        }
        
        ApiResponse<String> result = new ApiResponse<String>();
		result.setSuccess(Boolean.FALSE);
		result.setMessage(root.getMessage());
		
		return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.OK);

	}
}
