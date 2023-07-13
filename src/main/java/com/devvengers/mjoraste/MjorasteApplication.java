package com.devvengers.mjoraste;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableSwagger2
public class MjorasteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MjorasteApplication.class, args);
	}



	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
