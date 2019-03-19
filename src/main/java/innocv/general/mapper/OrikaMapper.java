package innocv.general.mapper;

import org.springframework.stereotype.Component;

import innocv.user.dto.UserDTO;
import innocv.user.entity.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;


@Component
public class OrikaMapper extends ConfigurableMapper {

	protected void configure(MapperFactory factory) {
		factory.classMap(User.class, UserDTO.class)
		.byDefault()
		.register();
		factory.classMap(UserDTO.class, User.class)
		.byDefault()
		.register();
	}

}
