package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.entity.Course;
import com.example.studentmanagementsystem.exception.ResourceNotFoundException;
import com.example.studentmanagementsystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final TeacherService teacherService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long courseId) throws ResourceNotFoundException {
        return courseRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("course not found"));
    }

    @Override
    public List<Course> findByTeacherId(Long teacherId) {
        return courseRepository.findByTeacherId(teacherId);
    }

    @Override
    public List<Course> findByStudentId(Long studentId) {
        return courseRepository.findByStudentsId(studentId);
    }

    @Override
    public Course create(Course course, Long teacherId) throws ResourceNotFoundException {
        course.setId(0L);
        if (teacherId != null)
            course.setTeacher(teacherService.findById(teacherId));
        return courseRepository.save(course);
    }

    @Override
    public Course update(Course course, Long courseId, Long teacherId) throws ResourceNotFoundException {
        Course oldCourse = findById(courseId);
        course.setId(courseId);
        course.setQuizzes(oldCourse.getQuizzes());
        course.setStudents(oldCourse.getStudents());

        if (teacherId != null)
            course.setTeacher(teacherService.findById(teacherId));
        else
            course.setTeacher(oldCourse.getTeacher());

        return courseRepository.save(course);
    }

    @Override
    public void delete(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
