package com.example.studentmanagementsystem.repository;

import com.example.studentmanagementsystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTeacherId(Long teacherId);

    List<Course> findByStudentsId(Long studentId);
}
