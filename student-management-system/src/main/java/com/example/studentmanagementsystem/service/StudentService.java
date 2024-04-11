package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.entity.Student;
import com.example.studentmanagementsystem.exception.ResourceNotFoundException;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findById(Long studentId) throws ResourceNotFoundException;

    List<Student> findByCourseId(Long courseId);

    Student create(Student student);

    Student update(Student student, Long studentId) throws ResourceNotFoundException;

    void delete(Long studentId);

    Student courseEnrollment(Long courseId, Long studentId, boolean enroll) throws ResourceNotFoundException;
}
