package innocv.user.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserDTO {

	private int id;

	@NotEmpty(message= "The username can't be empty")
	@Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long") 
	private String name;

	@NotEmpty(message= "The longName can't be empty")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters long") 
	private String longName;

	@Min(value = 1, message = "Age should not be less than 1")
    @Max(value = 110, message = "Age should not be greater than 110")
	private int age;

}
