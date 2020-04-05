package com.rohit.springjpql;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.Assert;

import com.rohit.springjpql.entity.Student;
import com.rohit.springjpql.repo.StudentRepo;

@SpringBootTest
class SpringbootjpqlApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private StudentRepo studentRepo;

	@Test
	void testStudentCreate() {

		Student student = new Student();
		student.setFname("rohit");
		student.setLname("chavan");
		student.setScore(90);

		Student student1 = new Student();
		student1.setFname("urmila");
		student1.setLname("kadam");
		student1.setScore(100);

		Student student2 = new Student();
		student2.setFname("amol");
		student2.setLname("gade");
		student2.setScore(95);

		Student student3 = new Student();
		student3.setFname("john");
		student3.setLname("cena");
		student3.setScore(85);

		Student student4 = new Student();
		student4.setFname("randy");
		student4.setLname("ortan");
		student4.setScore(90);

		studentRepo.saveAll(Arrays.asList(student, student1, student2, student3, student4));
	}

	@Test
	void testFindAllStudent() {
		studentRepo.findAll().forEach(i -> {
			System.out.println(i.getFname());
		});
	}

	@Test
	void testFindAllStudentByFname() {

		studentRepo.findByFname("rohit").forEach(i -> {
			System.out.println("fname=" + i.getFname() + " lname=" + i.getLname() + " score =" + i.getScore());
		});
		;
	}

	@Test
	void testFindByFnameAndLname() {
		studentRepo.getFnameAndLname().forEach(i -> {
			System.out.println("fname =" + i.get("fname") + " lname =" + i.get("lname"));
		});
	}

	@Test
	void testInsertJpql() {
		int oldCount = studentRepo.getCount();
		studentRepo.insertStudent("brock", "takachi", 99);
		int newCount = studentRepo.getCount();
		System.out.println("new Count="+newCount+" old count ="+oldCount);
		Assert.isTrue(newCount > oldCount, "Insert Operation  Error");
	}
	
	@Test
	void testDeleteOperation() {
		int oldCount = studentRepo.getCount();
		studentRepo.deleteByFname("brock");
		int newCount = studentRepo.getCount();
		System.out.println("new Count="+newCount+" old count ="+oldCount);
		Assert.isTrue(newCount < oldCount, "Delete  Operation Error");
	}

}
