package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.entity.Course;
import com.example.studentmanagementsystem.entity.Student;
import com.example.studentmanagementsystem.exception.ResourceNotFoundException;
import com.example.studentmanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final CourseService courseService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long studentId) throws ResourceNotFoundException {
        return studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("student not found"));
    }

    @Override
    public List<Student> findByCourseId(Long courseId) {
        return studentRepository.findByCoursesId(courseId);
    }

    @Override
    public Student create(Student student) {
        student.setId(0L);
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student, Long studentId) throws ResourceNotFoundException {
        Student oldStudent = findById(studentId);
        student.setId(studentId);
        student.setCourses(oldStudent.getCourses());
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public Student courseEnrollment(Long courseId, Long studentId, boolean enroll) throws ResourceNotFoundException {
        Student student = findById(studentId);
        Course course = courseService.findById(courseId);

        if (enroll)
            student.addToCourses(course);
        else
            student.removeFromCourses(course);

        return studentRepository.save(student);
    }
}
