package tobi.ch4.domain;

public class DuplicateUserIdException extends RuntimeException {
	public DuplicateUserIdException(Throwable cause) {
		super(cause);
	}
}
