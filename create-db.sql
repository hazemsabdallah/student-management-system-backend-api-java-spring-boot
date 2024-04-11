DROP DATABASE IF EXISTS `student-management-system`;

CREATE DATABASE `student-management-system`;
USE `student-management-system`;

-- creating teacher table
CREATE TABLE `teacher` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255),
    `phone_number` VARCHAR(255),
    `email` VARCHAR(255),
    `address` VARCHAR(255),
    PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- creating student table
CREATE TABLE `student` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255),
    `phone_number` VARCHAR(255),
    `email` VARCHAR(255),
    `address` VARCHAR(255),
    PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- creating course table
CREATE TABLE `course` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255),
    `teacher_id` BIGINT,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_teacher_course` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE SET NULL
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- creating quiz table
CREATE TABLE `quiz` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(255),
    `course_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_course_quiz` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- creating linking table between course and students
CREATE TABLE `student_course` (
    `student_id` BIGINT NOT NULL,
    `course_id` BIGINT NOT NULL,
    PRIMARY KEY (`student_id`, `course_id`),
    CONSTRAINT `fk_student_course` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_course_student` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE
)
ENGINE=InnoDB;

-- inserting dummy data

INSERT INTO `teacher` (`name`, `phone_number`, `email`, `address`) VALUES ('teacher01', '0123456789', 'teacher01@test.com', 'teacher01 address');
INSERT INTO `teacher` (`name`, `phone_number`, `email`, `address`) VALUES ('teacher02', '0123456789', 'teacher02@test.com', 'teacher02 address');

INSERT INTO `student` (`name`, `phone_number`, `email`, `address`) VALUES ('student01', '0123456789', 'student01@test.com', 'student01 address');
INSERT INTO `student` (`name`, `phone_number`, `email`, `address`) VALUES ('student02', '0123456789', 'student02@test.com', 'student02 address');

INSERT INTO `course` (`name`, `teacher_id`) VALUES ('course01', 1);
INSERT INTO `course` (`name`, `teacher_id`) VALUES ('course02', 1);
INSERT INTO `course` (`name`, `teacher_id`) VALUES ('course03', 2);
INSERT INTO `course` (`name`, `teacher_id`) VALUES ('course04', 2);


INSERT INTO `quiz` (`type`, `course_id`) VALUES ('quiz01', 1);
INSERT INTO `quiz` (`type`, `course_id`) VALUES ('quiz02', 1);
INSERT INTO `quiz` (`type`, `course_id`) VALUES ('quiz03', 2);
INSERT INTO `quiz` (`type`, `course_id`) VALUES ('quiz04', 3);
INSERT INTO `quiz` (`type`, `course_id`) VALUES ('quiz05', 4);

INSERT INTO `student_course` (`student_id`, `course_id`) VALUES (1, 1);
INSERT INTO `student_course` (`student_id`, `course_id`) VALUES (1, 2);
INSERT INTO `student_course` (`student_id`, `course_id`) VALUES (1, 3);
INSERT INTO `student_course` (`student_id`, `course_id`) VALUES (2, 2);
INSERT INTO `student_course` (`student_id`, `course_id`) VALUES (2, 3);
INSERT INTO `student_course` (`student_id`, `course_id`) VALUES (2, 4);