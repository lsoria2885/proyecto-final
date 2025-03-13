package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

	
	private Integer idStudent;
	
	@NotNull
	@Size(min = 3, max = 25)
	private String firstName;
	
	@NotNull
	@Size(min = 3, max = 25)
	private String lastName;
	
	@NotNull
	@Size(min = 3, max = 10)
	private String dni;
	
	@NotNull
	@Min(value = 1)
	private int age;

}
