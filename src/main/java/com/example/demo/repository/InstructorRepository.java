package com.example.demo.repository;

import com.example.demo.model.entity.Instructor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorRepository {

    @Select("""
            select * from instructors
            offset #{limit} * (#{offset} - 1) limit #{limit}
            """)
    @Results(id = "instructorMapping", value = {
        @Result(property = "instructorId", column = "instructor_id"),
        @Result(property = "instructorName", column = "instructor_name")
    })
    List<Instructor> getAllInstructors(Integer offset, Integer limit);


    @Select("""
            select * from instructors
            where instructor_id = #{id}
            """)
    @ResultMap("instructorMapping")
    Instructor findInstructorById(Integer id);
}
