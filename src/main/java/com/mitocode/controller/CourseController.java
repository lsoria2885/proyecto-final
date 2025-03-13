package com.mitocode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.CourseDTO;
import com.mitocode.model.Course;
import com.mitocode.service.ICourseService;
import com.mitocode.util.MapperUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

	private final ICourseService service;
	@Qualifier("defaultMapper")
	private final MapperUtil modelMapper;

	@GetMapping
	public ResponseEntity<List<CourseDTO>> findAll() throws Exception {
		List<CourseDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, CourseDTO.class)).toList();

		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CourseDTO> findById(@PathVariable("id") Integer id) throws Exception {
		Course obj = service.findById(id);
		return ResponseEntity.ok(modelMapper.map(obj, CourseDTO.class));
	}

	@PostMapping
	public ResponseEntity<CourseDTO> save(@Valid @RequestBody CourseDTO dto) throws Exception {
		Course obj = service.save(modelMapper.map(dto, Course.class));
		return new ResponseEntity<>(modelMapper.map(obj, CourseDTO.class), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CourseDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CourseDTO dto)
			throws Exception {
		Course obj = service.update(id, modelMapper.map(dto, Course.class));
		return ResponseEntity.ok(modelMapper.map(obj, CourseDTO.class));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	private CourseDTO convertToDto(Course obj) {
		return modelMapper.map(obj, CourseDTO.class);
	}

	private Course convertToEntity(CourseDTO dto) {
		return modelMapper.map(dto, Course.class);
	}
}
