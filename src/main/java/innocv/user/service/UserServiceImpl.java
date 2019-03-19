package innocv.user.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrikaMapper orikaMapper;

	@Override
	public UserDTO createUser(UserDTO userDTO, BindingResult errors) {
		log.debug("REST request to create a user");

		UserDTO userCreate = null;

		if(InnocvUtils.validOK(errors)) {
			log.error("Request to create with validation errors");
			throw new InnocvException(HttpStatus.NOT_ACCEPTABLE, InnocvUtils.getErrorsValidation(errors));
		}

		log.debug("Request to create withouth validation errors");

		User user = orikaMapper.map(userDTO, User.class);
		user = userRepository.save(user);
		userCreate = orikaMapper.map(user, UserDTO.class);

		log.debug("Created user with id:" + user.getId());
		return userCreate;
	}

	@Override
	public UserDTO getUser(int id) {
		log.debug("REST request to get user");

		UserDTO userDTO = getOptionalUser(id);

		log.debug("Get user correctly");
		return userDTO;
	}

	@Override
	public List<UserDTO> getListUsers() {
		log.debug("REST request to get list user");

		List<UserDTO> listUsersDTO = getOptionalListUser();

		log.debug("Get list user correctly");
		return listUsersDTO;
	}

	@Override
	public UserDTO updateUser(int id, UserDTO userDTO, BindingResult errors) {
		log.debug("REST request to update user");

		if(InnocvUtils.validOK(errors)) {
			log.debug("Request to update with validation errors");
			throw new InnocvException(HttpStatus.NOT_ACCEPTABLE, InnocvUtils.getErrorsValidation(errors));
		}

		log.debug("Request to update withouth validation errors");

		
		User user = getFindUser(id);
		userDTO.setId(id);
		user = orikaMapper.map(userDTO, User.class);
		user = userRepository.save(user);
		
		log.debug("User updated correctly");
		return userDTO;
	}

	@Override
	public DataDTO<String> deleteUser(int id) {
		log.debug("REST request to delete user");
		existUser(id);

		User userDelete = orikaMapper.map(getOptionalUser(id), User.class);
		userRepository.delete(userDelete);

		log.debug("User deleted correctly");
		return new DataDTO<String>("Te user with the id: " + id + ", has been successfully deleted.");
	}
	
	/**
	 * Method to verify a user exists by id
	 * 
	 * @param id
	 * @return User
	 */
	private User getFindUser(int id) {
		log.debug("Within the method getFindUser");

		User findUser = userRepository.findById(id).orElseThrow(
				() -> new InnocvException(HttpStatus.NOT_FOUND, "The user with the "+ id +" doesn't exist"));
		
		log.debug("The user is present");
		log.debug("Leaving the method getFindUser");
		return findUser;
	}

	/**
	 * Method to obtain a user by id
	 * 
	 * @param id
	 * @return Optional of User
	 */
	private UserDTO getOptionalUser(int id) {
		log.debug("Within the method OptionalUser");

		User optUser = getFindUser(id);

		UserDTO optUserDTO = orikaMapper.map(optUser, UserDTO.class);

		log.debug("Leaving the method OptionalUser");
		return optUserDTO;
	}

	/**
	 * Method to verify that the list of users is not empty
	 * 
	 * @return User list optional
	 */
	private List<UserDTO> getOptionalListUser() {
		log.debug("Within the method OptionalListUser");

		List<User> optListUser = userRepository.findAll();

		List<UserDTO> optListUserDTO = new ArrayList<>();

		if(optListUser.isEmpty()) {
			log.error("The list user is empty");
			throw new InnocvException(HttpStatus.NOT_FOUND, "The user list is empty");
		}

		log.debug("The list user is present");
		optListUserDTO = orikaMapper.mapAsList(optListUser, UserDTO.class);

		log.debug("Leaving the method OptionalListUser");
		return optListUserDTO;
	}

	/**
	 * Method for checking that a user exists
	 * 
	 * @param id
	 * @return User existence check
	 */
	private boolean existUser(int id) {
		log.debug("Within the method existUser");

		boolean exist = userRepository.existsById(id);
		log.debug("User exist: " + exist);

		if(!exist) {
			throw new InnocvException(HttpStatus.NOT_FOUND, "The user with the "+ id +" doesn't exist");
		}

		log.debug("Leaving the method existUser");
		return true;
	}

}
