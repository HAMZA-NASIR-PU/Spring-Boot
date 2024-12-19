package com.example.demo.dao;

import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import com.example.demo.entity.Review;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface AppDAO {

    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int id);
    Instructor findInstructorByIdJoinFetch(int id);
    void update(Instructor instructor);
    void update(Course course) throws SQLIntegrityConstraintViolationException;
    Course findCourseById(int id);
    void associateCoursesWithInstructor(List<Integer> courseIds, int instructorId);
    void deleteCourseById(int id);
    void save(Course course);

    void addReviewOfCourse(Review review, int courseId);
}
