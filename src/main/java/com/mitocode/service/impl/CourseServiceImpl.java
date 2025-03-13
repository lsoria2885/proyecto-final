package com.mitocode.service.impl;

import org.springframework.stereotype.Service;

import com.mitocode.model.Course;
import com.mitocode.repository.ICourseRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.ICourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDImpl<Course, Integer> implements ICourseService {

	// @Autowired
	private final ICourseRepo repo;

	@Override
	protected IGenericRepo<Course, Integer> getRepo() {
		return repo;
	}
}