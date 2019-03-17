package innocv.general.mapper;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;


@Component
public class OrikaMapper extends ConfigurableMapper {

	protected void configure(MapperFactory factory) {
		ConverterFactory converterFactory = factory.getConverterFactory();
		converterFactory.registerConverter("dateConverter", new OrikaDateConverter("dd/MM/yyyy"));
	/*	factory.classMap(EntradaModel.class, UsuarioModel.class)
		.field("autor", "nombre")
		.byDefault()
		.register();
		factory.classMap(User.class, UserDTO.class)
		.fieldMap("fechaPublicacion", "fechaPublicacion").converter("dateConverter").add()
		.byDefault()
		.register();*/
	}

}
