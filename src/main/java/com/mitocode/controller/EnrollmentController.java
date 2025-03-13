package com.mitocode.controller;

import java.util.List;
import java.util.Map;

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

import com.mitocode.dto.EnrollmentDTO;
import com.mitocode.model.Enrollment;
import com.mitocode.service.IEnrollmentService;
import com.mitocode.util.MapperUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

	private final IEnrollmentService service;
	@Qualifier("defaultMapper")
	private final MapperUtil modelMapper;

	@GetMapping
	public ResponseEntity<List<EnrollmentDTO>> findAll() throws Exception {
		List<EnrollmentDTO> list = service.findAll().stream().map(this::convertToDto).toList();

		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnrollmentDTO> findById(@PathVariable("id") Integer id) throws Exception {
		Enrollment obj = service.findById(id);

		return ResponseEntity.ok(convertToDto(obj));
	}

	@PostMapping
	public ResponseEntity<EnrollmentDTO> save(@Valid @RequestBody EnrollmentDTO dto) throws Exception {
		Enrollment obj = service.save(convertToEntity(dto));

		return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EnrollmentDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody EnrollmentDTO dto)
			throws Exception {
		Enrollment obj = service.update(id, convertToEntity(dto));
		return ResponseEntity.ok(convertToDto(obj));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	/**
	 * Mostrar la relación de cursos matriculados y sus estudiantes correspondientes
	 * usando programación funcional (sugerencia, usar un Map<K,V>)
	 * 
	 * @return
	 */
	@GetMapping("/courses-with-students")
	public ResponseEntity<Map<String, List<String>>> getCoursesWithStudents() {
		return ResponseEntity.ok(service.getCoursesWithStudents());
	}

	private EnrollmentDTO convertToDto(Enrollment obj) {
		return modelMapper.map(obj, EnrollmentDTO.class);
	}

	private Enrollment convertToEntity(EnrollmentDTO dto) {
		return modelMapper.map(dto, Enrollment.class);
	}

}
