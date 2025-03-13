package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDetailDTO {

	@JsonBackReference
	private EnrollmentDTO enrollment;

	@NotNull
	@JsonIncludeProperties(value = { "idCourse" })
	private CourseDTO course;

	@NotNull
	@Size(min = 3, max = 25)
	private String classroom;

}
