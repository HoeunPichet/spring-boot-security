package com.example.demo.repository;

import com.example.demo.model.dto.request.StudentRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentCourseRepository {
    @Select("""
            INSERT INTO student_course(student_id, course_id)
            VALUES (#{studentId}, #{courseId})
            """)
    void insertStudentCourse(Integer studentId, Integer courseId);


    @Select("""
            DELETE FROM student_course
            WHERE student_id = #{studentId}
            """)
    void deleteStudentCourse(Integer studentId);
}
