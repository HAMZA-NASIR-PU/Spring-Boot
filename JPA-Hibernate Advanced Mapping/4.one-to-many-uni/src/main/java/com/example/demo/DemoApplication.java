package com.example.demo;

import com.example.demo.dao.AppDAO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import com.example.demo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.sql.SQLIntegrityConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			createInstructor(appDAO);

//			findInstructor(appDAO);

//			deleteInstructor(appDAO);

//			findInstructorDetail(appDAO);

//			deleteInstructorDetail(appDAO);

//			createInstructorWithCourses(appDAO);

//			findInstructorWithCourses(appDAO);

//			findCoursesForInstructor(appDAO);

//			findInstructorWithCoursesJoinFetch(appDAO);

//			updateInstructor(appDAO);

//			updateCourse(appDAO);

//			deleteInstructor(appDAO);

//			associateCoursesWithInstructor(appDAO);

//			deleteCourse(appDAO);

//			createCourseAndReview(appDAO);

			addReviewOfCourse(appDAO);
		};
	}

	private void addReviewOfCourse(AppDAO appDAO) {
		int courseId = 10;
		Review review = new Review("T 1");
		appDAO.addReviewOfCourse(review, courseId);
	}

	private void createCourseAndReview(AppDAO appDAO) {

		// create the course
		Course tempCourse = new Course("TEST 1");

		// add some reviews
		tempCourse.addReview(new Review("Rev 1"));
		tempCourse.addReview(new Review("Rev 2"));
		tempCourse.addReview(new Review("Rev 3"));

		// Save the course
		appDAO.save(tempCourse);
		System.out.println("DONE!");
	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;
		appDAO.deleteCourseById(id);
		System.out.println("DONE1");
	}

	private void associateCoursesWithInstructor(AppDAO appDAO) {
		int instructorId = 3;
		List<Integer> courseIds = new ArrayList<>();
		courseIds.add(10);
		courseIds.add(11);
		courseIds.add(12);
		appDAO.associateCoursesWithInstructor(courseIds, instructorId);

		System.out.println("DONE!");
	}

	private void updateCourse(AppDAO appDAO) {
		try{
			int id = 11;

			// Find the course
			System.out.println("Finding course id: " + id);
			Course tempCourse = appDAO.findCourseById(id);
			tempCourse.setTitle("UPDATED");

			// Update the course
			System.out.println("Updating course id: " + id);
			appDAO.update(tempCourse);
			System.out.println("DONE!");
		}catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Something went wrong with SQL Integrity constraints");
			System.out.println(e.getMessage());
		}catch (Exception e) {
			System.out.println("Something went wrong.");
			System.out.println(e.getMessage());
		}
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + id);
		Instructor tempInstructor = appDAO.findInstructorById(id);

		// update the instructor
		System.out.println("Updating the instructor id: " + id);
		tempInstructor.setLastName("UPDATED");
		appDAO.update(tempInstructor);
		System.out.println("DONE!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor = " + instructor);
		System.out.println("Associated courses = " + instructor.getCourses());
		System.out.println("DONE!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);

		// Only load the instructor, does not load the courses by default.
		Instructor tempInstructor = appDAO.findInstructorById(id);

		// find courses for instructor
		List<Course> courses = appDAO.findCoursesByInstructorId(id);

		System.out.println("The associated courses: " + courses);
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);

		// Only load the instructor, does not load the courses by default.
		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("The associated courses:  " + tempInstructor.getCourses());

		System.out.println("DONE!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor("abc", "def", "abc@email.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"http://www.abc.com",
				"abc hobby"
		);

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course course1 = new Course("Maths");
		Course course2 = new Course("Physics");
		Course course3 = new Course("Chemistry");

		tempInstructor.add(course1);
		tempInstructor.add(course2);
		tempInstructor.add(course3);

		// save the instructor

		System.out.println("Saving the instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("DONE!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 7;
		System.out.println("Deleting instructor detail id: " + id);

		appDAO.deleteInstructorDetailById(id);

		System.out.println("DONE!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 3;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

		System.out.println("InstructorDetail object = " + instructorDetail);
		System.out.println("Associated Instructor object =" + instructorDetail.getInstructor());
		System.out.println("DONE!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 2;
		System.out.println("Deleting instructor id: " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("DONE!");
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 3;
		System.out.println("Finding instructor id: " + id);

		Instructor tempInstructor = appDAO.findInstructorById(id);
		System.out.println("tempInstructor:  " + tempInstructor);
		System.out.println("Associated InstructorDetail object:  " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		//create the instructor
		Instructor tempInstructor = new Instructor("Hamza", "Nasir", "hamza@email.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"http://www.abc.com",
				"Gaming"
		);

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		// NOTE: This will also save the InstructorDetail object because of CascadeType.ALL
		//
		System.out.println("Saving the instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
//		tempInstructor.setId(0);
//		tempInstructor.getInstructorDetail().setId(0);
		//
		// NOTE: If you again save the entity without uncommenting the above two lines, you will get the following exception:
		// org.springframework.dao.InvalidDataAccessApiUsageException: detached entity passed to persist: com.example.demo.entity.Instructor
		// Caused by: org.hibernate.PersistentObjectException: detached entity passed to persist: com.example.demo.entity.Instructor
//		appDAO.save(tempInstructor);
		System.out.println("DONE!!!");
	}

}
