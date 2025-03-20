package com.example.demo.repository;

import com.example.demo.model.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRepository {

    @Select("""
            select * from courses
            offset #{limit} * (#{offset} - 1) limit #{limit}
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
            select * from courses
            where course_id = #{id}
            """)
    @ResultMap("courseMapping")
    Course findCourseById(Integer id);


    @Select("""
            select c.* from student_course sc
            inner join courses c on sc.course_id = c.course_id
            where sc.student_id = #{id}
            """)
    @ResultMap("courseMapping")
    List<Course> getCoursesByStudentId(Integer id);
}