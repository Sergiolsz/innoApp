package innocv.general.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Component
public class SubErrorMessage {
	
	private String errorField;
	private String errorMessage;
	
}
