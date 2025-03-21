package com.example.demo.repository;

import com.example.demo.model.dto.request.CourseRequest;
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


    @Select("""
            INSERT INTO courses(course_name, description, instructor_id)
            VALUES(#{course.courseName}, #{course.description}, #{course.instructorId})
            RETURNING *
            """)
    @ResultMap("courseMapping")
    Course insertCourse(@Param("course") CourseRequest courseRequest);


    @Select("""
            UPDATE courses
            SET course_name = #{course.courseName},
                description = #{course.description},
                instructor_id = #{course.instructorId}
            WHERE course_id = #{id}
            RETURNING *
            """)
    @ResultMap("courseMapping")
    Course updateCourseById(Integer id, @Param("course") CourseRequest courseRequest);


    @Select("""
            DELETE FROM courses
            WHERE course_id = #{id}
            RETURNING *
            """)
    @ResultMap("courseMapping")
    Course deleteCourseById(Integer id);

}