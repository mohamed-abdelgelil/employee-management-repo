package dev.me.hr.config.exception;

public class TransitionException extends RuntimeException {

	private String errorCode;
	private String errorMessage;

	public TransitionException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7763362130382979098L;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
