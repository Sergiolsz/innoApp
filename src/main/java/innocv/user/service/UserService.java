package innocv.user.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import innocv.general.model.DataDTO;
import innocv.user.dto.UserDTO;

public interface UserService {

	public UserDTO createUser(UserDTO user, BindingResult errors);
	
	public UserDTO getUser(int id);
	
	public List<UserDTO> getListUsers();
	
	public UserDTO updateUser(int id, UserDTO userDTO, BindingResult errors);
	
	public DataDTO<String> deleteUser(int id);
}
