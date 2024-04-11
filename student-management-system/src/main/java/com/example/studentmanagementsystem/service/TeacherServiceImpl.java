package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.entity.Teacher;
import com.example.studentmanagementsystem.exception.ResourceNotFoundException;
import com.example.studentmanagementsystem.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(Long teacherId) throws ResourceNotFoundException {
        return teacherRepository.findById(teacherId).orElseThrow(
                () -> new ResourceNotFoundException("teacher not found"));
    }

    @Override
    public Teacher create(Teacher teacher) {
        teacher.setId(0L);
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher update(Teacher teacher, Long teacherId) throws ResourceNotFoundException {
        Teacher oldTeacher = findById(teacherId);
        teacher.setId(teacherId);
        teacher.setCourses(oldTeacher.getCourses());
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }
}
