package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.entity.Teacher;
import com.example.studentmanagementsystem.exception.ResourceNotFoundException;
import com.example.studentmanagementsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getTeachers() {
        List<Teacher> teachers = teacherService.findAll();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/teachers/{teacherId}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Long teacherId) throws ResourceNotFoundException {
        Teacher teacher = teacherService.findById(teacherId);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @PostMapping("/teachers")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(teacherService.create(teacher), HttpStatus.OK);
    }

    @PutMapping("/teachers/{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long teacherId, @RequestBody Teacher teacher) throws ResourceNotFoundException {
        return new ResponseEntity<>(teacherService.update(teacher, teacherId), HttpStatus.OK);
    }

    @DeleteMapping("/teachers/{teacherId}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long teacherId) {
        teacherService.delete(teacherId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
