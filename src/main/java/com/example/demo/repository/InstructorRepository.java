package com.example.demo.repository;

import com.example.demo.model.dto.request.InstructorRequest;
import com.example.demo.model.entity.Instructor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorRepository {

    @Select("""
            SELECT * FROM instructors
            OFFSET #{limit} * (#{offset} - 1) LIMIT #{limit}
            """)
    @Results(id = "instructorMapping", value = {
        @Result(property = "instructorId", column = "instructor_id"),
        @Result(property = "instructorName", column = "instructor_name")
    })
    List<Instructor> getAllInstructors(Integer offset, Integer limit);


    @Select("""
            SELECT * FROM instructors
            WHERE instructor_id = #{id}
            """)
    @ResultMap("instructorMapping")
    Instructor findInstructorById(Integer id);


    @Select("""
            INSERT INTO instructors(instructor_name, email)
            VALUES (#{instructor.instructorName}, #{instructor.email})
            RETURNING *
            """)
    @ResultMap("instructorMapping")
    Instructor insertInstructor(@Param("instructor") InstructorRequest instructorRequest);


    @Select("""
            UPDATE instructors
            SET instructor_name = #{instructor.instructorName},
                email = #{instructor.email}
            WHERE instructor_id = #{id}
            RETURNING *
            """)
    @ResultMap("instructorMapping")
    Instructor updateInstructor(Integer id, @Param("instructor") InstructorRequest instructorRequest);


    @Select("""
            DELETE FROM instructors
            WHERE instructor_id = #{id}
            RETURNING *
            """)
    @ResultMap("instructorMapping")
    Instructor deleteInstructorById(Integer id);
}
