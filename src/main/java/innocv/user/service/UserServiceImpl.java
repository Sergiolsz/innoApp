package innocv.user.service;

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

	@Autowired
	private User user;

	@Override
	public UserDTO createUser(UserDTO userDTO, BindingResult errors) {

		UserDTO userCreate = null;
		
		if(InnocvUtils.validOK(errors)) {
			throw new InnocvException(HttpStatus.NOT_ACCEPTABLE, InnocvUtils.getErrorsValidation(errors));
		}

		try {
			user = orikaMapper.map(userDTO, User.class);
			user = userRepository.save(user);
			userCreate = getUser(user.getIdUser());
		} catch (Exception e) {
			throw new InnocvException(HttpStatus.INTERNAL_SERVER_ERROR, InnocvUtils.getCatchErrors(e.getMessage()));
		}

		return userCreate;
	}

	@Override
	public UserDTO getUser(int id) {
		UserDTO userDTO = getOptionalUser(id).get();
		return userDTO;
	}

	@Override
	public List<UserDTO> getListUsers() {
		List<UserDTO> listUsersDTO = getOptionalListUser().get();
		return listUsersDTO;
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, BindingResult errors) {

		if(InnocvUtils.validOK(errors)) {
			throw new InnocvException(HttpStatus.NOT_ACCEPTABLE, InnocvUtils.getErrorsValidation(errors));
		}

		UserDTO updateUser = getUser(userDTO.getIdUser());
		
		try {
			user = orikaMapper.map(updateUser, User.class);
			user = userRepository.save(user);
		} catch (Exception e) {
			throw new InnocvException(HttpStatus.INTERNAL_SERVER_ERROR, InnocvUtils.getCatchErrors(e.getMessage()));
		}
		
		updateUser = getUser(userDTO.getIdUser());

		return updateUser;
	}

	@Override
	public DataDTO<String> deleteUser(int id) {
		
		boolean userDelete = existUser(id);
		
		if(userDelete) {
			user = orikaMapper.map(getUser(id), User.class);
			userRepository.delete(user);
		}
		
		return new DataDTO<String>("Te user with the id: " + id + ", has been successfully deleted.");
	}

	/**
	 * 
	 * @param id
	 * @return Optional of User
	 */
	private Optional<UserDTO> getOptionalUser(int id) {
		Optional<User> optUser = Optional.of(userRepository.findById(id).get());
		Optional<UserDTO> optUserDTO = Optional.empty();

		if(optUser.isPresent()) {
			optUserDTO = Optional.of(orikaMapper.map(optUser, UserDTO.class));
		} else {
			throw new InnocvException(HttpStatus.NOT_FOUND, "The user with the "+ id +" doesn't exist");
		}

		return optUserDTO;
	}

	/**
	 * 
	 * @return User list optional
	 */
	private Optional<List<UserDTO>> getOptionalListUser() {
		List<User> optListUser = Optional.of(userRepository.findAll()).get();
		Optional<List<UserDTO>> optListUserDTO = Optional.empty();

		if(optListUser.isEmpty()) {
			throw new InnocvException(HttpStatus.NOT_FOUND, "The user list is empty");
		}

		optListUserDTO = Optional.of(orikaMapper.mapAsList(optListUser, UserDTO.class));
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
