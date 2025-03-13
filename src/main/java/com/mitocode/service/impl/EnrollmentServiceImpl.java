package com.mitocode.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mitocode.model.Enrollment;
import com.mitocode.repository.IEnrollmentRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.IEnrollmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl extends CRUDImpl<Enrollment, Integer> implements IEnrollmentService {

	// @Autowired
	private final IEnrollmentRepo repo;

	@Override
	protected IGenericRepo<Enrollment, Integer> getRepo() {
		return repo;
	}

	@Override
	public Map<String, List<String>> getCoursesWithStudents() {
		return repo.findAll().stream()
				.flatMap(enrollment -> enrollment.getDetails().stream()
						.map(detail -> Map.entry(detail.getCourse().getName(),
								enrollment.getStudent().getFirstName() + " " + enrollment.getStudent().getLastName())))
				.collect(Collectors.groupingBy(Map.Entry::getKey,
						Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
	}
}