package innocv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages="innocv")
public class InnocvApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnocvApplication.class, args);
	}

}
