package com.mitocode.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mitocode.model.Student;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.IStudentRepo;
import com.mitocode.service.IStudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDImpl<Student, Integer> implements IStudentService {

	// @Autowired
	private final IStudentRepo repo;

	@Override
	protected IGenericRepo<Student, Integer> getRepo() {
		return repo;
	}

	@Override
	public List<Student> findStudentsbyAge() {
	    return repo.findAll()  
	            .stream()                  
	            .sorted((s1, s2) -> Integer.compare(s2.getAge(), s1.getAge())) 
	            .collect(Collectors.toList());
	}

}
