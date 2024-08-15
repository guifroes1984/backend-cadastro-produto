package br.com.guifroes1984.productbackend.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
		
		StandardError error = new StandardError();
		error.setError("Erro de validação");
		error.setMessage(exception.getMessage());
		error.setPath(request.getRequestURI());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimeStamp(Instant.now());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
