package com.myProject.myProject;

import com.myProject.myProject.user.User;
import com.myProject.myProject.user.userRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyProjectApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(userRepository userRepository){
		return args -> {
			User maria = new User(
					"maria@gmail.com",
					"1234",
					"maria",
					"ramos"
			);

			userRepository.save(maria);
		};

	}
}
