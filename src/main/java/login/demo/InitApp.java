package login.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class InitApp {

	private static Resource userRole;

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);
		System.out.println(userRole.name().toString());
	}

}
