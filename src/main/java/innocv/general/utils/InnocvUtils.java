package innocv.general.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;

import innocv.general.exception.ErrorMessage;
import innocv.general.exception.SubErrorMessage;

/**
 * Class of utilities
 */
public class InnocvUtils {
	private static Log log = LogFactory.getLog(InnocvUtils.class);
	
	
	/**
	 * Method to check for validation errors
	 * 
	 * @param errors
	 * @return boolean - errors
	 */
	public static final boolean validOK(BindingResult errors) {
		log.debug("Verification of validation errors");
		boolean haveErrors = errors.hasErrors() ?  true :  false;

		return haveErrors;
	}
	
	/**
	 * Method to list validation errors
	 * 
	 * @param errors
	 * @return ErrorMessage - list validation errors 
	 */
	public static final ErrorMessage getErrorsValidation(BindingResult errors) {
		log.debug("The request has errors");
		List<SubErrorMessage> listErrors = errors.getAllErrors()
				.stream()
				.map(x -> new SubErrorMessage(x.getObjectName().toString(), x.getDefaultMessage().toString()))
				.collect(Collectors.toList());
		
		log.error("List erros: " + listErrors);
		ErrorMessage error = new ErrorMessage(listErrors);
		
		return error;
	}
	
}
