package com.mitocode.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mitocode.dto.StudentDTO;
import com.mitocode.model.Student;

@Configuration
public class MapperConfig {

	@Bean("defaultMapper")
	public ModelMapper defaultMapper() {
		return new ModelMapper();
	}

	@Bean("studentMapper")
	public ModelMapper studentMapper() {
		ModelMapper mapper = new ModelMapper();

		mapper.createTypeMap(Student.class, StudentDTO.class).addMapping(Student::getFirstName,
				(dest, v) -> dest.setFirstName((String) v));

		// Escritura POST PUT
		mapper.createTypeMap(StudentDTO.class, Student.class).addMapping(StudentDTO::getFirstName,
				(dest, v) -> dest.setFirstName((String) v));

		return mapper;
	}

}
