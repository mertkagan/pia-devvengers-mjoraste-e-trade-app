package com.devvengers.mjoraste;

import com.devvengers.mjoraste.repository.AboutRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableMongoRepositories(basePackageClasses = AboutRepository.class)
@EnableJpaRepositories(excludeFilters =
@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = AboutRepository.class))
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
