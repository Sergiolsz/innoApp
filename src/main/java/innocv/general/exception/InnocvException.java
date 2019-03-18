package innocv.general.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ControllerAdvice
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public SubErrorMessage handleValidationError(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		FieldError fieldError = bindingResult.getFieldError();
		String defaultMessage = fieldError.getDefaultMessage();
		return new SubErrorMessage("VALIDATION_FAILED", defaultMessage);
	}
}
