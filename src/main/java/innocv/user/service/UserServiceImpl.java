package innocv.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import innocv.general.exception.InnocvException;
import innocv.general.mapper.OrikaMapper;
import innocv.general.model.DataDTO;
import innocv.general.utils.InnocvUtils;
import innocv.user.dto.UserDTO;
import innocv.user.entity.User;
import innocv.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrikaMapper orikaMapper;

	@Override
	public UserDTO createUser(UserDTO userDTO, BindingResult errors) {

		UserDTO userCreate = null;

		if(InnocvUtils.validOK(errors)) {
			throw new InnocvException(HttpStatus.NOT_ACCEPTABLE, InnocvUtils.getErrorsValidation(errors));
		}

		User user = orikaMapper.map(userDTO, User.class);
		user = userRepository.save(user);
		userCreate = orikaMapper.map(user, UserDTO.class);

		return userCreate;
	}

	@Override
	public UserDTO getUser(int id) {
		UserDTO userDTO = getOptionalUser(id);
		return userDTO;
	}

	@Override
	public List<UserDTO> getListUsers() {
		List<UserDTO> listUsersDTO = getOptionalListUser();
		return listUsersDTO;
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, BindingResult errors) {

		if(InnocvUtils.validOK(errors)) {
			throw new InnocvException(HttpStatus.NOT_ACCEPTABLE, InnocvUtils.getErrorsValidation(errors));
		}

		existUser(userDTO.getIdUser());
		UserDTO userUpdate = null;

		User user = orikaMapper.map(userDTO, User.class);
		user = userRepository.save(user);
		userUpdate = orikaMapper.map(user, UserDTO.class);

		return userUpdate;
	}

	@Override
	public DataDTO<String> deleteUser(int id) {

		existUser(id);

		User user = orikaMapper.map(getOptionalUser(id), User.class);
		userRepository.delete(user);

		return new DataDTO<String>("Te user with the id: " + id + ", has been successfully deleted.");
	}

	/**
	 * Method to verify that a user exists by id
	 * 
	 * @param id
	 * @return Optional of User
	 */
	private UserDTO getOptionalUser(int id) {
		Optional<User> optUser = Optional.of(userRepository.findById(id).get());
		UserDTO optUserDTO = null;

		if(optUser.isPresent()) {
			optUserDTO = orikaMapper.map(optUser.get(), UserDTO.class);
		} else {
			throw new InnocvException(HttpStatus.NOT_FOUND, "The user with the "+ id +" doesn't exist");
		}

		return optUserDTO;
	}

	/**
	 * Method to verify that the list of users is not empty
	 * 
	 * @return User list optional
	 */
	private List<UserDTO> getOptionalListUser() {
		List<User> optListUser = Optional.of(userRepository.findAll()).get();
		List<UserDTO> optListUserDTO = new ArrayList<>();

		if(optListUser.isEmpty()) {
			throw new InnocvException(HttpStatus.NOT_FOUND, "The user list is empty");
		}

		optListUserDTO = orikaMapper.mapAsList(Arrays.asList(optListUser), UserDTO.class);
		return optListUserDTO;
	}

	/**
	 * Method for checking that a user exists
	 * 
	 * @param id
	 * @return User existence check
	 */
	private boolean existUser(int id) {
		boolean exist = false;
		Optional<Boolean> optExist = Optional.of(userRepository.existsById(id));

		if(optExist.isPresent()) {
			exist = true;
		} else {
			throw new InnocvException(HttpStatus.NOT_FOUND, "The user with the "+ id +" doesn't exist");
		}

		return exist;
	}

}
