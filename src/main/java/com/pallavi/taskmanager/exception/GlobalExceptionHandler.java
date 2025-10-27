package com.pallavi.taskmanager.exception;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler for handling various exceptions across the
 * application.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Handles <strong> @HttpRequestMethodNotSupportedException </strong>
	 * 
	 * @Exception thrown when a request handler does not support a specific request
	 *            method.
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public final ResponseEntity<Map<String, Object>> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException,
			HttpServletRequest httpServletRequest) {
		ErrorResponse response = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, HttpStatus.METHOD_NOT_ALLOWED.value(),
				httpRequestMethodNotSupportedException.getLocalizedMessage().toString(),
				LocalDateTime.now(ZoneId.systemDefault()));
		Map<String, Object> errors = new HashMap<>();
		errors.put("response", response);
		errors.put("Requested URL", httpServletRequest.getRequestURI().toString());
		errors.put("Requested URL", httpServletRequest.getRequestURL().toString());
		errors.put("Supported Methods", httpRequestMethodNotSupportedException.getSupportedHttpMethods().toString());
		return new ResponseEntity<>(errors, HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * Handles <strong> @TaskNotFoundException </strong> Custom exception class to
	 * handle situations where a task cannot be found.
	 */
	@ExceptionHandler(TaskNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleTaskNotFoundException(TaskNotFoundException notFoundException) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),
				notFoundException.getLocalizedMessage().toString(), LocalDateTime.now(ZoneId.systemDefault()));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles <strong> @InvalidUUIDFormatException </strong> Custom exception class
	 * to handle invalid <strong> @UUID </strong> format errors and it's related
	 * errors.
	 */
	@ExceptionHandler(InvalidUUIDFormatException.class)
	public final ResponseEntity<ErrorResponse> handleInvalidUUIDFormatException(
			InvalidUUIDFormatException invalidUUIDFormatException) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
				invalidUUIDFormatException.getLocalizedMessage().toString(), LocalDateTime.now(ZoneId.systemDefault()));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles <strong> @MethodArgumentNotValidException </strong> for validation
	 * errors. <strong> @Exception </strong> to be thrown when validation on an
	 * argument annotated with <strong><em> @Valid </em></strong> fails.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<Map<String, Object>> handleValidationExceptions(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("httpStatus", HttpStatus.BAD_REQUEST);
		errors.put("httpStatusCode", HttpStatus.BAD_REQUEST.value());
		errors.put("timeStamp", LocalDateTime.now(ZoneId.systemDefault()));
		for (FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}
		methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error -> {
			String defaultMessage = error.getDefaultMessage();
			errors.put("errorMessage", defaultMessage);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	/**
	 * Handles <strong> @NoResourceFoundException </strong> Raised when
	 * <strong> @ResourceHttpRequestHandler </strong> can not find a resource.
	 */
	@ExceptionHandler(NoResourceFoundException.class)
	public final ResponseEntity<Map<String, Object>> handleNoResourceFoundException(
			NoResourceFoundException noResourceFoundException, HttpServletRequest servletRequest) {
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),
				noResourceFoundException.getLocalizedMessage().toString(), LocalDateTime.now(ZoneId.systemDefault()));
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("response", response);
		errorDetails.put("message",
				"The requested Static Resource could not be found on the server. Please check the resource URL and try again");
		errorDetails.put("Requested URL", servletRequest.getRequestURI().toString());
		errorDetails.put("Requested URL", servletRequest.getRequestURL().toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles <strong> @NoHandlerFoundException </strong> Raised when the
	 * <strong> @DispatcherServlet </strong> can not find a handler for a request.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public final ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(
			NoHandlerFoundException noHandlerFoundException, HttpServletRequest servletRequest) {
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),
				noHandlerFoundException.getLocalizedMessage().toString(), LocalDateTime.now(ZoneId.systemDefault()));
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("response", response);
		errorDetails.put("message", "The requested endpoint does not exist");
		errorDetails.put("Requested URL", servletRequest.getRequestURI().toString());
		errorDetails.put("Requested URL", servletRequest.getRequestURL().toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles <strong> @HttpMessageNotReadableException </strong> Thrown by
	 * <strong> @HttpMessageConverter </strong> implementations when the
	 * <strong><em> @HttpMessageConverter.read </em></strong> method fails.
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public final ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException httpMessageNotReadableException, HttpServletRequest servletRequest) {
		ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
				"Request body is missing or malformed. Please provide Valid Input",
				LocalDateTime.now(ZoneId.systemDefault()));
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("response", response);
		errorDetails.put("Requested URL", servletRequest.getRequestURI().toString());
		errorDetails.put("Requested URL", servletRequest.getRequestURL().toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles <strong> @MissingServletRequestParameterException </strong> that
	 * indicates a missing parameter.
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public final ResponseEntity<Map<String, Object>> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException missingServletRequestParameterException,
			HttpServletRequest servletRequest) {
		ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
				missingServletRequestParameterException.getLocalizedMessage().toString(),
				LocalDateTime.now(ZoneId.systemDefault()));
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("response", response);
		errorDetails.put("Requested URL", servletRequest.getRequestURI().toString());
		errorDetails.put("Requested URL", servletRequest.getRequestURL().toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles Generic <strong> @Exception </strong> across the Application.
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Map<String, String>> handleGenericException(Exception exception) {
		logger.error("An error occurred: {} ", exception.getMessage(), exception);
		Map<String, String> error = Map.of("error", "An unexpected error occurred : " + exception.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
