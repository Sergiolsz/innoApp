package innocv.general.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ControllerAdvice
public class InnocvException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private ErrorMessage errorMessage;
	private String message;
	
	public InnocvException() {}
	
	public InnocvException(HttpStatus status, ErrorMessage errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}
	
	public InnocvException(HttpStatus httpStatus, String message) {
		status = httpStatus;
		this.message = message;
	}
	
}
