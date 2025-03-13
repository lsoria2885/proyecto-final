package com.mitocode.service;

import java.util.List;
import java.util.Map;

import com.mitocode.model.Enrollment;

public interface IEnrollmentService extends ICRUD<Enrollment, Integer> {

	public Map<String, List<String>> getCoursesWithStudents();
}
