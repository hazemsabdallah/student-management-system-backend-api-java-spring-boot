package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.entity.Course;
import com.example.studentmanagementsystem.exception.ResourceNotFoundException;
import com.example.studentmanagementsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "courses")
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = courseService.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable Long courseId) throws ResourceNotFoundException {
        Course course = courseService.findById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/teachers/{teacherId}/courses")
    public ResponseEntity<List<Course>> getCoursesByTeacher(@PathVariable Long teacherId) {
        List<Course> courses = courseService.findByTeacherId(teacherId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}/courses")
    public ResponseEntity<List<Course>> getCoursesByStudent(@PathVariable Long studentId) {
        List<Course> courses = courseService.findByStudentId(studentId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course,
                                               @RequestParam(required = false, name = "teacherId") Long teacherId) throws ResourceNotFoundException {
        return new ResponseEntity<>(courseService.create(course, teacherId), HttpStatus.OK);
    }

    @PutMapping("/courses/{courseId}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course,
                                               @PathVariable Long courseId,
                                               @RequestParam(required = false, name = "teacherId") Long teacherId) throws ResourceNotFoundException {
        return new ResponseEntity<>(courseService.update(course, courseId, teacherId), HttpStatus.OK);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        courseService.delete(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
