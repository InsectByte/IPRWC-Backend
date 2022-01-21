package com.insectbyte.iprwc;

import com.insectbyte.iprwc.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class IprwcApplication {

	public static void main(String[] args) {
		SpringApplication.run(IprwcApplication.class, args);
	}

}
