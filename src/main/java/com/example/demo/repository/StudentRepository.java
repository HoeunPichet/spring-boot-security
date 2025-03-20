package com.example.demo.repository;

import com.example.demo.model.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("""
            select * from students
            offset #{limit} * (#{offset} - 1) limit #{limit}
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
            select * from students
            where student_id = #{id}
            """)
    @ResultMap("studentMapping")
    Student findStudentById(Integer id);
}
