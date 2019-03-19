package innocv.general.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Component
public class SubErrorMessage {
	
	private String errorField;
	private String errorMessage;
	
	public SubErrorMessage() {}
}
