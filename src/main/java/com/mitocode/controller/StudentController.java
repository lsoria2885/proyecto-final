package com.mitocode.controller;

import java.util.List;

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

import com.mitocode.dto.GenericResponse;
import com.mitocode.dto.StudentDTO;
import com.mitocode.model.Student;
import com.mitocode.service.IStudentService;
import com.mitocode.util.MapperUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	private final IStudentService service;

	private final MapperUtil mapperUtil;

	@GetMapping
	public ResponseEntity<GenericResponse<StudentDTO>> findAll() throws Exception {
		List<StudentDTO> list = mapperUtil.mapList(service.findAll(), StudentDTO.class, "studentMapper");
		return ResponseEntity.ok(new GenericResponse<>(200, "success", list));
	}

	@GetMapping("/{id}")
	public ResponseEntity<GenericResponse<StudentDTO>> findById(@PathVariable("id") Integer id) throws Exception {
		Student obj = service.findById(id);
		return ResponseEntity.ok(
				new GenericResponse<>(200, "success", List.of(mapperUtil.map(obj, StudentDTO.class, "studentMapper"))));
	}

	@PostMapping
	public ResponseEntity<StudentDTO> save(@Valid @RequestBody StudentDTO dto) throws Exception {
		Student obj = service.save(mapperUtil.map(dto, Student.class));
		return new ResponseEntity<>(mapperUtil.map(obj, StudentDTO.class, "studentMapper"), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody StudentDTO dto)
			throws Exception {
		Student obj = service.update(id, mapperUtil.map(dto, Student.class, "studentMapper"));
		return ResponseEntity.ok(mapperUtil.map(obj, StudentDTO.class, "studentMapper"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Listar estudiantes ordenados de forma descendente por edad usando
	 * programación funcional
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/by-age")
	public ResponseEntity<List<StudentDTO>> getStudentsByAge() throws Exception {
		List<StudentDTO> list = mapperUtil.mapList(service.findStudentsbyAge(), StudentDTO.class, "studentMapper");
		return ResponseEntity.ok(list);
	}
}
