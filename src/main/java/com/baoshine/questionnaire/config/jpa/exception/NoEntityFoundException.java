package com.baoshine.questionnaire.config.jpa.exception;

/**
 * <p>
 * This exception is thrown if specific entity with ID specified by the caller cannot be found in persistence.
 * </p>
 * <p>
 * <strong>Thread Safety: </strong> This class is not thread safe because its base class is not thread safe.
 * </p>
 */
public class NoEntityFoundException extends EntityPersistenceException {

	/**
	 * The serial version ID.
	 */
	private static final long serialVersionUID = -6870922084033271591L;

	/**
	 * Constructor with error message.
	 *
	 * @param message
	 *            the explanation of the error
	 */
	public NoEntityFoundException(String message) {
		super(message);
	}

	/**
	 * Constructor with error message and inner cause.
	 *
	 * @param message
	 *            the explanation of the error
	 * @param cause
	 *            the underlying cause of the error
	 */
	public NoEntityFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
