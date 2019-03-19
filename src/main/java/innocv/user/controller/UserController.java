package innocv.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innocv.general.model.DataDTO;
import innocv.user.dto.UserDTO;
import innocv.user.service.UserServiceImpl;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping
	public ResponseEntity<DataDTO<UserDTO>> createUser(@Validated @RequestBody UserDTO userDTO, BindingResult errors) {
		
		DataDTO<UserDTO> dataCreate = new DataDTO<UserDTO>(userServiceImpl.createUser(userDTO, errors));
		ResponseEntity<DataDTO<UserDTO>> responseEntity = ResponseEntity.ok().body(dataCreate);
		
		return responseEntity;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DataDTO<UserDTO>> getUser(@PathVariable int id) {
		
		DataDTO<UserDTO> dataUser = new DataDTO<UserDTO>(userServiceImpl.getUser(id));
		ResponseEntity<DataDTO<UserDTO>> responseEntity = ResponseEntity.ok().body(dataUser);
		
		return responseEntity;
	}
	
	@GetMapping(value="/users")
	public ResponseEntity<DataDTO<List<UserDTO>>> getAllUsers() {
		
		DataDTO<List<UserDTO>> dataListUsers = new DataDTO<List<UserDTO>>(userServiceImpl.getListUsers());
		ResponseEntity<DataDTO<List<UserDTO>>> responseEntity = ResponseEntity.ok().body(dataListUsers);
		
		return responseEntity;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DataDTO<UserDTO>> updateUser(@PathVariable int id, @Validated @RequestBody UserDTO userDTO, BindingResult errors) {
		
		DataDTO<UserDTO> dataUpdate = new DataDTO<UserDTO>(userServiceImpl.updateUser(id, userDTO, errors));
		ResponseEntity<DataDTO<UserDTO>> responseEntity = ResponseEntity.ok().body(dataUpdate);
		
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DataDTO<String>> deleteUser(@PathVariable int id) {
		
		DataDTO<String> dataDelete = userServiceImpl.deleteUser(id);
		ResponseEntity<DataDTO<String>> responseEntity = ResponseEntity.ok().body(dataDelete);
		
		return responseEntity;
	}

}
