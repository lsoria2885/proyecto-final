package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Student;

public interface IStudentService extends ICRUD<Student, Integer> {

	List<Student> findStudentsbyAge();

}
