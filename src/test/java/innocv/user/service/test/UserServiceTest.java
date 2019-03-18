package innocv.user.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import innocv.general.exception.InnocvException;
import innocv.general.mapper.OrikaMapper;
import innocv.general.model.DataDTO;
import innocv.general.utils.InnocvUtils;
import innocv.user.dto.UserDTO;
import innocv.user.entity.User;
import innocv.user.repository.UserRepository;
import innocv.user.service.UserServiceImpl;

public class UserServiceTest {

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Mock
	private UserRepository userRepository;

	@Mock
	private BindingResult bindingResult;

	@Mock
	private OrikaMapper orikaMapper;

	@Mock
	private User userMock;

	@Mock
	private UserDTO userDTOMock;

	private User user;

	private UserDTO userDTO;

	private UserDTO userDTOUpdate;


	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
		user = getUser();
		userDTO = getUserDTO();
		userDTOUpdate = getUserDTOUpdate(userDTO);
	}

	@Test
	void testCreateUser() {
		given(InnocvUtils.validOK(bindingResult)).willReturn(false);
		given(orikaMapper.map(userDTO, User.class)).willReturn(user);
		given(orikaMapper.map(user, UserDTO.class)).willReturn(userDTO);
		given(userRepository.save(user)).willReturn(user);
		
		UserDTO createUser = userServiceImpl.createUser(userDTO, bindingResult);
		
		assertNotNull(createUser);
	}

	@Test
	void testGetUser() {
		given(orikaMapper.map(user, UserDTO.class)).willReturn(userDTO);
		given(userRepository.findById(userDTO.getIdUser())).willReturn(Optional.of(user));
		
		UserDTO getUser = userServiceImpl.getUser(userDTO.getIdUser());
		
		assertNotNull(getUser);
	}

	@Test
	void testGetListUser() {
		given(userRepository.findAll()).willReturn(Arrays.asList(user));
		
		List<UserDTO> getListUser = userServiceImpl.getListUsers();
		
		assertNotNull(getListUser);
	}

	@Test
	void testUpdateUser() {
		given(orikaMapper.map(userDTOUpdate, User.class)).willReturn(user);
		given(orikaMapper.map(user, UserDTO.class)).willReturn(userDTOUpdate);
		given(userRepository.save(user)).willReturn(user);
		
		UserDTO updateUser = userServiceImpl.updateUser(userDTO, bindingResult);
		
		assertNotNull(updateUser);
	}

	@Test
	void testDeleteUser() {
		given(userRepository.findById(userDTO.getIdUser())).willReturn(Optional.of(user));
		
		DataDTO<String> deleteUser = userServiceImpl.deleteUser(userDTO.getIdUser());
		
		assertNotNull(deleteUser);
	}

	@Test
	void testValidErrors() {
		given(InnocvUtils.validOK(bindingResult)).willReturn(true);
		
		Throwable createException = assertThrows(InnocvException.class, ()-> {
			userServiceImpl.createUser(userDTO, bindingResult);
		});
		
		Throwable updateException = assertThrows(InnocvException.class, ()-> {
			userServiceImpl.updateUser(userDTO, bindingResult);
		});
		
		assertEquals(InnocvException.class, createException.getClass());
		assertEquals(InnocvException.class, updateException.getClass());
	}
	
	@Test
	void testOptionalErrors() {	
		given(userRepository.save(user)).willReturn(user);
		//given(userServiceImpl.getUser(10)).willReturn(new UserDTO());
		given(userRepository.existsById(5)).willReturn(false);
		
		/*Throwable UserException = assertThrows(InnocvException.class, ()-> {
			userServiceImpl.getUser(10);
		});*/
		Throwable listUserException = assertThrows(InnocvException.class, ()-> {
			userServiceImpl.getListUsers();
		});
		Throwable existUserException = assertThrows(InnocvException.class, ()-> {
			userServiceImpl.deleteUser(0);
		});
		
		//assertEquals(InnocvException.class, UserException.getClass());
		assertEquals(InnocvException.class, listUserException.getClass());
		assertEquals(InnocvException.class, existUserException.getClass());
	}

	private static User getUser() {
		User user = new User();
		user.setIdUser(0);
		user.setName("Sergio");
		user.setLongName("Liébanas Rodríguez");
		user.setAge(36);
		return user;
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
