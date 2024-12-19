package com.example.demo.dao;

import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import com.example.demo.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    // Define field for entity manager
    private EntityManager entityManager;

    // Inject entity manager using constructor injection.
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        // This will also save the InstructorDetail object because of CascadeType.ALL
        this.entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        Instructor instructor = this.entityManager.find(Instructor.class, id);
        return instructor;
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        // retrieve the instructor
        Instructor instructor= this.entityManager.find(Instructor.class, id);

//        TypedQuery<Instructor> query = this.entityManager.createQuery("SELECT i FROM Instructor i JOIN FETCH i.courses WHERE i.id = :id", Instructor.class);

//        query.setParameter("id", id);

//        Instructor instructor = query.getSingleResult();

        List<Course> courses = instructor.getCourses();

        for(Course course:courses) {
            course.setInstructor(null);
        }

        // We only delete the instructor and not the associated courses based on our cascade types.
        // delete the instructor and associated instructor detail object
        this.entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return this.entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        // retrieve the instructor detail
        InstructorDetail instructorDetail = this.entityManager.find(InstructorDetail.class, id);

        //
        // remove the associated object reference
        // break bi-directional link
        //
        instructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail and also delete the associated instructor
        this.entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery("SELECT c from Course c where c.instructor.id = :data", Course.class);
        query.setParameter("data", id);

        // execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        // create query
        //
        // NOTE: By adding JOIN FETCH i.instructor_detail, Hibernate can optimize the left join done between instructor and instructorDetail.
        //
        TypedQuery<Instructor> query = this.entityManager.createQuery("SELECT i FROM Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail WHERE i.id = :data", Instructor.class);
        query.setParameter("data", id);

        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        this.entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) throws SQLIntegrityConstraintViolationException {
        this.entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return this.entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void associateCoursesWithInstructor(List<Integer> courseIds, int instructorId) {
        TypedQuery<Course> query = this.entityManager.createQuery("SELECT c FROM Course c WHERE c.id IN :ids", Course.class);
        query.setParameter("ids", courseIds);
        List<Course> courses = query.getResultList();

        Instructor instructor = this.entityManager.find(Instructor.class, instructorId);

        for(Course course:courses) {
            course.setInstructor(instructor);
        }
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        // find the course
        Course tempCourse = this.entityManager.find(Course.class, id);

        // delete the course
        this.entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course course) {
        // will save the course and associated reviews due to CascadeType.ALL
        this.entityManager.persist(course);
    }

    @Override
    @Transactional
    public void addReviewOfCourse(Review review, int courseId) {
        // find the course
        TypedQuery<Course> query = this.entityManager.createQuery("SELECT c FROM Course c WHERE c.id = :id", Course.class);
        query.setParameter("id", courseId);
        Course course = query.getSingleResult();
//        course.addReview(review);  // 4 queries
        review.setCourse(course);  // Only 2 queries
        this.entityManager.persist(review);
    }
}
