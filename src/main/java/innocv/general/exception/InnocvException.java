package innocv.general.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnocvException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private ErrorMessage errorMessage;
	
	public InnocvException(HttpStatus status, ErrorMessage errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}
	
	public InnocvException(HttpStatus httpStatus, Object object) {
		status = httpStatus;
	}
	
	public InnocvException(HttpStatus httpStatus, String message) {
		status = httpStatus;
	}
	
}
