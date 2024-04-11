package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.entity.Quiz;
import com.example.studentmanagementsystem.exception.ResourceNotFoundException;

import java.util.List;

public interface QuizService {

    List<Quiz> findAll();

    Quiz findById(Long quizId) throws ResourceNotFoundException;

    List<Quiz> findByCourseId(Long courseId);

    Quiz create(Long courseId, Quiz quiz) throws ResourceNotFoundException;

    Quiz update(Quiz quiz, Long quizId) throws ResourceNotFoundException;

    void delete(Long quizId);
}
