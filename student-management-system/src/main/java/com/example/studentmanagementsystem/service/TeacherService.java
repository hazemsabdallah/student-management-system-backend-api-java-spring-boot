package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.entity.Teacher;
import com.example.studentmanagementsystem.exception.ResourceNotFoundException;

import java.util.List;

public interface TeacherService {

    List<Teacher> findAll();

    Teacher findById(Long teacherId) throws ResourceNotFoundException;

    Teacher create(Teacher teacher);

    Teacher update(Teacher teacher, Long teacherId) throws ResourceNotFoundException;

    void delete(Long teacherId);
}
