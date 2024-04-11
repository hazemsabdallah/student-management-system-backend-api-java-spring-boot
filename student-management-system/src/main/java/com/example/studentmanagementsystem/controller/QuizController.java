package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.entity.Quiz;
import com.example.studentmanagementsystem.exception.ResourceNotFoundException;
import com.example.studentmanagementsystem.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quizzes")
    public ResponseEntity<List<Quiz>> getQuizzes() {
        List<Quiz> quizzes = quizService.findAll();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/quizzes/{quizId}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long quizId) throws ResourceNotFoundException {
        Quiz quiz = quizService.findById(quizId);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @GetMapping("/courses/{courseId}/quizzes")
    public ResponseEntity<List<Quiz>> getQuizzesForCourse(@PathVariable Long courseId) {
        List<Quiz> quizzes = quizService.findByCourseId(courseId);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @PostMapping("/courses/{courseId}/quizzes")
    public ResponseEntity<Quiz> createQuizByCourse(@PathVariable Long courseId, @RequestBody Quiz quiz) throws ResourceNotFoundException {
        return new ResponseEntity<>(quizService.create(courseId, quiz), HttpStatus.OK);
    }

    @PutMapping("/quizzes/{quizId}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long quizId, @RequestBody Quiz quiz) throws ResourceNotFoundException {
        return new ResponseEntity<>(quizService.update(quiz, quizId), HttpStatus.OK);
    }

    @DeleteMapping("/quizzes/{quizId}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long quizId) {
        quizService.delete(quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
