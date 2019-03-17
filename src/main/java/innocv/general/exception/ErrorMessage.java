package innocv.general.exception;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Component
public class ErrorMessage {

	private List<SubErrorMessage> listMessageValidation;
	
}
