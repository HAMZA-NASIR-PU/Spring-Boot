package com.example.demo;

import com.example.demo.dao.AppDAO;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
//			findInstructorDetail(appDAO);
		};
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
		appDAO.save(tempInstructor);
		System.out.println("DONE!!!");
	}

}
