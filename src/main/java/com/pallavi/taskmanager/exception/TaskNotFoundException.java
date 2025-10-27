package com.pallavi.taskmanager.exception;


/**
 * Custom exception class to handle situations where a task cannot be found.
 */
public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8619715290471929628L;

	/**
	 * Constructs a new <strong> @TaskNotFoundException </strong> with the specified
	 * detail message.
	 *
	 * @param message - the detail message which is saved for later retrieval by the
	 *                <strong><em> getMessage() </em></strong> method.
	 */
	public TaskNotFoundException(String message) {
		super(message);
	}

	/**
	 * Constructs a new <strong> @TaskNotFoundException </strong> with the specified
	 * detail message and cause.
	 *
	 * @param message - the detail message (which is saved for later retrieval by
	 *                the <strong><em> getMessage() </em></strong> method).
	 * @param cause   - the cause (which is saved for later retrieval by the
	 *                <strong><em> getCause() </em></strong> method).
	 */
	public TaskNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new <strong> @TaskNotFoundException </strong> with the specified
	 * cause.
	 *
	 * @param cause - the cause (which is saved for later retrieval by the
	 *              <strong><em> getCause() </em></strong> method).
	 */
	public TaskNotFoundException(Throwable cause) {
		super(cause);
	}
}
