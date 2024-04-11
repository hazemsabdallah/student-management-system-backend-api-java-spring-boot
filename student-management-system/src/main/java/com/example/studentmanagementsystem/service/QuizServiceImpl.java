package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.entity.Course;
import com.example.studentmanagementsystem.entity.Quiz;
import com.example.studentmanagementsystem.exception.ResourceNotFoundException;
import com.example.studentmanagementsystem.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    private final CourseService courseService;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, CourseService courseService) {
        this.quizRepository = quizRepository;
        this.courseService = courseService;
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz findById(Long quizId) throws ResourceNotFoundException {
        return quizRepository.findById(quizId).orElseThrow(
                () -> new ResourceNotFoundException("quiz not found"));
    }

    @Override
    public List<Quiz> findByCourseId(Long courseId) {
        return quizRepository.findByCourseId(courseId);
    }

    @Override
    public Quiz create(Long courseId, Quiz quiz) throws ResourceNotFoundException {
        Course course =  courseService.findById(courseId);
        quiz.setId(0L);
        quiz.setCourse(course);
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz update(Quiz quiz, Long quizId) throws ResourceNotFoundException {
        Quiz oldQuiz = findById(quizId);
        quiz.setId(quizId);
        quiz.setCourse(oldQuiz.getCourse());
        return quizRepository.save(quiz);
    }

    @Override
    public void delete(Long quizId) {
        quizRepository.deleteById(quizId);
    }
}
