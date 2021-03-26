package com.baoshine.questionnaire.config.jpa.exception;


import com.baoshine.common.exception.ResultCodeEnum;
import com.baoshine.questionnaire.exception.QuestionnaireException;

/**
 * <p>
 * This exception is thrown if some not expected error occurs while accessing the persistence.
 * </p>
 * <p>
 * <strong>Thread Safety: </strong> This class is not thread safe because its base class is not thread safe.
 * </p>
 */
public class EntityPersistenceException extends QuestionnaireException {

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
	public EntityPersistenceException(String message) {
		super(ResultCodeEnum.CMN_REPOSITORY_ERROR, message);
	}

	/**
	 * Constructor with error message and inner cause.
	 *
	 * @param message
	 *            the explanation of the error
	 * @param cause
	 *            the underlying cause of the error
	 */
	public EntityPersistenceException(String message, Throwable cause) {
		super(ResultCodeEnum.CMN_REPOSITORY_ERROR, message, cause);
	}
}
