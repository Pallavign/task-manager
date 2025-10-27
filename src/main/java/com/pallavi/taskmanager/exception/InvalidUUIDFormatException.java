package com.pallavi.taskmanager.exception;

/**
 * Custom exception class to handle invalid <strong> @UUID </strong> format
 * errors.
 */
public class InvalidUUIDFormatException extends RuntimeException {

	private static final long serialVersionUID = 6946666981705161746L;

	/**
	 * Constructs a new <strong> @InvalidUUIDFormatException </strong> with the
	 * specified detail message.
	 *
	 * @param message - the detail message which is saved for later retrieval by the
	 *                <strong><em> getMessage() </em></strong> method.
	 */
	public InvalidUUIDFormatException(String message) {
		super(message);
	}

	/**
	 * Constructs a new <strong> @InvalidUUIDFormatException </strong> with the
	 * specified detail message and cause.
	 *
	 * @param message - the detail message (which is saved for later retrieval by
	 *                the <strong><em> getMessage() </em></strong> method).
	 * @param cause   - the cause (which is saved for later retrieval by the
	 *                <strong><em> getCause() </em></strong> method).
	 */
	public InvalidUUIDFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new <strong> @InvalidUUIDFormatException </strong> with the
	 * specified cause.
	 *
	 * @param cause - the cause (which is saved for later retrieval by the
	 *              <strong><em> getCause() </em></strong> method).
	 */
	public InvalidUUIDFormatException(Throwable cause) {
		super(cause);
	}
}
