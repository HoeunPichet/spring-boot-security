create DATABASE kshrd_center;

CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    student_name VARCHAR(150) NOT NULL,
    email VARCHAR(100),
    phone_number VARCHAR(100)
);

CREATE TABLE instructors (
    instructor_id SERIAL PRIMARY KEY,
    instructor_name VARCHAR(150) NOT NULL,
    email VARCHAR(100)
);

CREATE TABLE courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(150) NOT NULL,
    instructor_id INT,
    description VARCHAR(250),
    FOREIGN KEY (instructor_id) REFERENCES instructors(instructor_id) ON DELETE CASCADE
);

CREATE TABLE student_course (
    id SERIAL PRIMARY KEY,
    student_id INT,
    course_id INT,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);