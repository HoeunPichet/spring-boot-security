package com.example.demo.repository;

import com.example.demo.model.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRepository {

    @Select("""
            SELECT * FROM courses
            OFFSET #{limit} * (#{offset} - 1) LIMIT #{limit}
            """)
    @Results(id ="courseMapping", value = {
        @Result(property = "courseId", column = "course_id"),
        @Result(property = "courseName", column = "course_name"),
        @Result(property = "courseId", column = "course_id"),
        @Result(property = "instructor", column = "instructor_id",
            one = @One(select = "com.example.demo.repository.InstructorRepository.findInstructorById")
        ),
    })
    List<Course> getAllCourses(Integer offset, Integer limit);


    @Select("""
            SELECT * FROM courses
            WHERE course_id = #{id}
            """)
    @ResultMap("courseMapping")
    Course findCourseById(Integer id);


    @Select("""
            SELECT c.* FROM student_course sc
            INNER JOIN courses c ON sc.course_id = c.course_id
            WHERE sc.student_id = #{id}
            """)
    @ResultMap("courseMapping")
    List<Course> getCoursesByStudentId(Integer id);
}