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
//			findInstructor(appDAO);
			deleteInstructor(appDAO);
//			createInstructor(appDAO);
		};

	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding Instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println(instructor);
		System.out.println("The associated instructorDetail only: " + instructor.getInstructorDetail());
		appDAO.deleteInstructorById(id);

		System.out.println("Deleted instructor with id: " + id);
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 2;
		System.out.println("Finding Instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println(instructor);
		System.out.println("The associated instructorDetail only: " + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
//		Instructor instructor = new Instructor("Chad", "Darby", "chad@email.com");
//		InstructorDetail instructorDetail = new InstructorDetail(
//				"http://www.youtube.com/abc",
//				"gaming"
//		);
//		instructor.setInstructorDetail(instructorDetail);

		Instructor instructor = new Instructor("Michjael", "Jordan", "michael@email.com");
		InstructorDetail instructorDetail = new InstructorDetail(
				"http://www.youtube.com/michael",
				"guitar"
		);
		instructor.setInstructorDetail(instructorDetail);

		// NOTE: This will also save the details object because of CascadeType.ALL.

		// Inserts the associated entity first: InstructorDetail. Then inserts: Instructor
		// Due to relationship of foreign key Instructor needs to know the id of the InstructorDetail
		appDAO.save(instructor);
		System.out.println("Saving instructor " + instructor);
	}

}
