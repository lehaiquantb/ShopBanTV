package com.shoptivi.api;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shoptivi.dto.ApiError;
import com.shoptivi.exception.NonNullIdException;
import com.shoptivi.exception.NullIdException;
import com.shoptivi.exception.NullNameException;
import com.shoptivi.exception.TooManyOptionException;

@ControllerAdvice
public class ProductExceptionController {
	@ExceptionHandler(value = TooManyOptionException.class)
	public ResponseEntity<ApiError> exception(TooManyOptionException exception) {
		return new ResponseEntity<ApiError>(
				new ApiError(HttpStatus.BAD_REQUEST, "So option toi da la 3", "loi du lieu"), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<ApiError> handleValidationError(ConstraintViolationException ex) {
		return new ResponseEntity<ApiError>(
				new ApiError(HttpStatus.BAD_REQUEST, "Du lieu khong hop le", "validate - error"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiError> exception(Exception ex) {
		return new ResponseEntity<ApiError>(
				new ApiError(HttpStatus.BAD_REQUEST, "Warning!", "Faltal Error"),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NullIdException.class)
	public ResponseEntity<ApiError> exception(NullIdException exception) {
		return new ResponseEntity<ApiError>(
				new ApiError(HttpStatus.BAD_REQUEST, "Id khong duoc la null", "loi du lieu"), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NonNullIdException.class)
	public ResponseEntity<ApiError> exception(NonNullIdException exception) {
		return new ResponseEntity<ApiError>(
				new ApiError(HttpStatus.BAD_REQUEST, "Id phai la null", "loi du lieu"), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value = NullNameException.class)
	public ResponseEntity<ApiError> exception(NullNameException exception) {
		return new ResponseEntity<ApiError>(
				new ApiError(HttpStatus.BAD_REQUEST, "Name khong duoc la null", "loi du lieu"), HttpStatus.BAD_REQUEST);
	}
}
