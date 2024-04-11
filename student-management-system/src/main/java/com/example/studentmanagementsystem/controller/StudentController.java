package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.entity.Student;
import com.example.studentmanagementsystem.exception.ResourceNotFoundException;
import com.example.studentmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) throws ResourceNotFoundException {
        Student student = studentService.findById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/courses/{courseId}/students")
    public ResponseEntity<List<Student>> getStudentByCourse(@PathVariable Long courseId) {
        List<Student> students = studentService.findByCourseId(courseId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.create(student), HttpStatus.OK);
    }

    @PutMapping("/students/{studentId}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable Long studentId) throws ResourceNotFoundException {
        studentService.update(student, studentId);
        return new ResponseEntity<>(studentService.update(student, studentId), HttpStatus.OK);
    }

    @PutMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<Student> enrollStudentInCourse(@PathVariable Long courseId,
                                                         @PathVariable Long studentId,
                                                         @RequestParam(name = "enroll") boolean enroll) throws ResourceNotFoundException {
        return new ResponseEntity<>(studentService.courseEnrollment(courseId, studentId, enroll), HttpStatus.OK);
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        studentService.delete(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}