package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.entity.Course;
import com.example.studentmanagementsystem.exception.ResourceNotFoundException;

import java.util.List;

public interface CourseService {
    
    List<Course> findAll();

    Course findById(Long courseId) throws ResourceNotFoundException;

    List<Course> findByTeacherId(Long teacherId);

    List<Course> findByStudentId(Long studentId);

    Course create(Course course, Long teacherId) throws ResourceNotFoundException;

    Course update(Course course, Long courseId, Long teacherId) throws ResourceNotFoundException;

    void delete(Long courseId);
}
