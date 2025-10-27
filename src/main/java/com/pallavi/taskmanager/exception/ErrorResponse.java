package com.pallavi.taskmanager.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents an <strong> error response </strong> that is returned to the
 * <strong> client </strong> when an error or <strong> @Exception </strong>
 * occurs.
 */
@Data
@AllArgsConstructor
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 5737285503669998642L;

	/**
	 * The @HttpStatus status associated with the error.
	 */
	private HttpStatus httpStatus;

	/**
	 * The numeric @HttpStatus status code associated with the error.
	 */
	private Integer httpStatusCode;

	/**
	 * A descriptive error message providing details about the error.
	 */
	private String errorMessage;

	/**
	 * The timestamp - System Default, when the error occurred.
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timeStamp;

	/**
	 * @AllArgsConstructor for creating a @TaskErrorResponse with the current
	 *                     timestamp(System Default).
	 *
	 * @param httpStatus   - The Http status associated with the error.
	 * @param errorMessage - A descriptive error message.
	 */
}
