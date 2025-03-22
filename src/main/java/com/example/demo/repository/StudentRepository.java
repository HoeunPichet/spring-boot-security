package com.example.demo.repository;

import com.example.demo.model.dto.request.StudentRequest;
import com.example.demo.model.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("""
            SELECT * FROM students
            OFFSET #{limit} * (#{offset} - 1) LIMIT #{limit}
            """)
    @Results(id ="studentMapping", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "courses", column = "student_id",
                    many = @Many(select = "com.example.demo.repository.CourseRepository.getCoursesByStudentId")
            ),
    })
    List<Student> getAllStudents(Integer offset, Integer limit);


    @Select("""
            SELECT * FROM students
            WHERE student_id = #{id}
            """)
    @ResultMap("studentMapping")
    Student findStudentById(Integer id);


    @Select("""
            INSERT INTO students(student_name, email, phone_number)
            VALUES (#{student.studentName}, #{student.email}, #{student.phoneNumber})
            RETURNING student_id
            """)
    Integer insertStudent(@Param("student") StudentRequest studentRequest);


    @Select("""
            UPDATE students
            SET student_name = #{student.studentName},
                email = #{student.email},
                phone_number = #{student.phoneNumber}
            WHERE student_id = #{id}
            RETURNING student_id
            """)
    Integer updateStudent(Integer id, @Param("student") StudentRequest studentRequest);


    @Select("""
            DELETE FROM students
            WHERE student_id = #{id}
            RETURNING *
            """)
    @ResultMap("studentMapping")
    Student deleteStudentById(Integer id);
}
