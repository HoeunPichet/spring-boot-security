package com.example.demo.repository;

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
}
