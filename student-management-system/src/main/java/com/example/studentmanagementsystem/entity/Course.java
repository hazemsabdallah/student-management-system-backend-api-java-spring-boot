package com.example.studentmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<Quiz> quizzes = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void addToQuizzes(Quiz quiz) {
        quizzes.add(quiz);
    }

    public void removeFromQuizzes(Quiz quiz) {
        quizzes.remove(quiz);
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void addToStudents(Student student) {
        students.add(student);
    }

    public void removeFromStudents(Student student) {
        students.remove(student);
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quizzes=" + quizzes +
                ", students=" + students +
                ", teacher=" + teacher +
                '}';
    }
}
