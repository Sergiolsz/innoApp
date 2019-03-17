package innocv.user.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserDTO {

private int idUser;
	
	@NotEmpty(message= "The username can't be empty")
	private String name;

	private String longName;
	
	@NotEmpty(message= "The user's age can't be empty")
	private int age;
}
