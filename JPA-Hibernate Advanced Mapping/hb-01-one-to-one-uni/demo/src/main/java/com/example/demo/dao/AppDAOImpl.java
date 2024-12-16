package com.example.demo.dao;

import com.example.demo.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImpl implements AppDAO{

    // define field for entity manager

    // inject entity manager using constructor injection

    private EntityManager entityManager;

    @Autowired // just for readability purpose.
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Instructor findInstructorById(int id) {
        // This will also retrieve the instructor details object because of default behaviour of @OneToOne fetch type is eager.
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        //This will also delete the instructorDetail object because of CascadeType.ALL
        Instructor instructor = entityManager.find(Instructor.class, id);
        entityManager.remove(instructor);
    }


    @Override
    @Transactional
    public void save(Instructor instructor) {
        // This will also save the InstructorDetail object because of CascadeType.ALL
        entityManager.persist(instructor);
    }
}
