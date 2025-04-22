package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.app.domain.dto.PersonDto;

@Configuration
public class PersonDtoBeanConfig {

	@Bean
	public PersonDto person3() {
		return PersonDto.builder().age(55).username("철수").addr("울산").build();
	}

	@Bean(name="personBean")
	public PersonDto person4() {
		return new PersonDto("박효신", 40, "대구");
	}

}
