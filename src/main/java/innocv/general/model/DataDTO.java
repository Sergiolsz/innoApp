package innocv.general.model;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataDTO<T> implements Serializable {

	private static final long serialVersionUID = 4742901310947907051L;
	
	private T data;
	
}
