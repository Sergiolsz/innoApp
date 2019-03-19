package innocv.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "user")
@Entity
public class User {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "name", nullable = false, length = 32)
	private String name;

	@Column(name = "longname", nullable = false, length = 50)
	private String longName;
	
	@Column(name = "age", nullable = false, length = 3)
	private int age;
	
}