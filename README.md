# student-management-system-backend-api-java-spring-boot

To run the api, you must first run the `create-db.sql` in mysql. The file named `erd-db.png` contains a snapshot of the ERD schema.

A Swagger integration is available at `localhost:8080/swagger`.

#### Tech Stack

- Java Spring Boot
- Hibernate
- MySQL

#### Exposed API endpoints

1. Courses
>- GET /api/courses => read all courses
>- GET /api/courses/{courseId} => read course by id
>- GET /api/teachers/{teacherId}/courses => read courses assigned to a teacher
>- GET /api/students/{studentId}/courses => read courses enrolled by student
>- POST /api/courses => create course
>- PUT /courses/{courseId}?teacherId=(optional) => update course by id and assign teacher
>- DELETE /api/courses/{courseId} => delete course by id
2. Quizzes 
>- GET /api/quizzes => read all quizzes
>- GET /api/quizzes/{quizId} => read quiz by id
>- GET /courses/{courseId}/quizzes" => read quizzes belonging to a course
>- POST /api/courses/{courseId}/quizzes => create quiz for a course
>- PUT /api/quizzes/{quizId} => update quiz by id
>- DELETE /api/quizzes/{quizId} => delete quiz by id
3. Students
>- GET /api/students => read all students
>- GET /api/students/{studentId} => read student by id
>- GET /courses/{courseId}/students => read students enrolled in a course
>- POST /api/students => create student
>- PUT /api/students/{studentId} => update student by id
>- PUT /courses/{courseId}/students/{studentId}?enroll=1 => enroll student into course
>- PUT /courses/{courseId}/students/{studentId}?enroll=0 => disenroll student from course
>- DELETE /api/students/{studentId} => delete student by id
4. Teachers
>- GET /api/teachers => read all teachers
>- GET /api/teachers/{teacherId} => read teacher by id
>- POST /api/teachers => create teacher
>- PUT /api/teachers/{teacherId} => update teacher by id
>- DELETE /api/teachers/{teacherId} => delete teacher by id
