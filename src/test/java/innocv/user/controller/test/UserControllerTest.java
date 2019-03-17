package innocv.user.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import innocv.general.mapper.OrikaMapper;
import innocv.general.model.DataDTO;
import innocv.user.controller.UserController;
import innocv.user.dto.UserDTO;
import innocv.user.entity.User;
import innocv.user.repository.UserRepository;
import innocv.user.service.UserServiceImpl;

public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserDTO userDTOMock;

	@Mock
	private BindingResult bindingResult;
	
	@Mock
	private OrikaMapper orikaMapper;

	@Mock
	private User user;
	
	private UserDTO userDTO;
	
	private List<UserDTO> listUserDTO;


	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		userDTO = getUserDTO();
		listUserDTO = Arrays.asList(userDTO);
		
		Mockito.when(userService.createUser(userDTOMock, bindingResult)).thenReturn(userDTO);
		Mockito.when(userService.getUser(1)).thenReturn(userDTO);
		Mockito.when(userService.getListUsers()).thenReturn(listUserDTO);
		Mockito.when(userService.updateUser(userDTO, bindingResult)).thenReturn(getUserDTOUpdate(userDTO));
		Mockito.when(userService.deleteUser(1)).thenReturn(new DataDTO<String>("User deteled"));
		
	}

	@Test
	void testCreateUser() {
		ResponseEntity<DataDTO<UserDTO>> responseCreate = userController.createUser(userDTOMock, bindingResult);
		assertNotNull(responseCreate);
		assertEquals(userDTO.getClass(), responseCreate.getBody().getData().getClass());
		assertEquals(userDTO.getName(), responseCreate.getBody().getData().getName());
	}
	
	@Test
	void testGetUser() {
		ResponseEntity<DataDTO<UserDTO>> responseGetUser = userController.getUser(1);
		assertNotNull(responseGetUser);
		assertEquals(userDTO.getClass(), responseGetUser.getBody().getData().getClass());
		assertEquals(userDTO.getName(), responseGetUser.getBody().getData().getName());
	}
	
	@Test
	void testGetAllUser() {
		ResponseEntity<DataDTO<List<UserDTO>>> responseGetUserList = userController.getAllUsers();
		assertNotNull(responseGetUserList);
		assertEquals(userDTO.getClass(), responseGetUserList.getBody().getData().get(0).getClass());
	}
	
	@Test
	void testUpdateUser() {
		ResponseEntity<DataDTO<UserDTO>> responseUpdate = userController.updateUser(userDTO, bindingResult);
		assertNotNull(responseUpdate);
		assertEquals(userDTO.getClass(), responseUpdate.getBody().getData().getClass());
		assertEquals(userDTO.getName(), responseUpdate.getBody().getData().getName());
	}
	
	@Test
	void testDeleteUser() {
		ResponseEntity<DataDTO<String>> responseDelete = userController.deleteUser(1);
		assertNotNull(responseDelete);
	}

	private static UserDTO getUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setName("Sergio");
		userDTO.setLongName("Liébanas Rodríguez");
		userDTO.setAge(36);
		return userDTO;
	}
	
	private static UserDTO getUserDTOUpdate(UserDTO userDTO) {
		userDTO.setAge(37);
		return userDTO;
	}

}
