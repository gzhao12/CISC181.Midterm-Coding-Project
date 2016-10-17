package com.cisc181.core;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eMajor;
import com.cisc181.exceptions.PersonException;

public class Student_Test {
	private static ArrayList<Course> courseList;
	private static ArrayList<Semester> semesterList;
	private static ArrayList<Section> sectionList;
	private static ArrayList<Student> studentList;

	@BeforeClass
	public static void setup() throws PersonException {
		courseList = new ArrayList<Course>();
		courseList.add(new Course(UUID.randomUUID(), "CISC181", 4, eMajor.COMPSI));
		courseList.add(new Course(UUID.randomUUID(), "PHYS208", 4, eMajor.PHYSICS));
		courseList.add(new Course(UUID.randomUUID(), "MATH349", 4, eMajor.BUSINESS));

		semesterList = new ArrayList<Semester>();
		// creates Fall and Spring semester
		semesterList.add(new Semester(UUID.randomUUID(), new Date(0), new Date(1000)));
		semesterList.add(new Semester(UUID.randomUUID(), new Date(2000), new Date(3000)));

		sectionList = new ArrayList<Section>();

		// Fall courses: COMPSI, PHYS, and MATH, respectively
		sectionList.add(new Section(courseList.get(0).getCourseID(), semesterList.get(0).getSemesterID(),
				UUID.randomUUID(), 205));
		sectionList.add(new Section(courseList.get(1).getCourseID(), semesterList.get(0).getSemesterID(),
				UUID.randomUUID(), 130));
		sectionList.add(new Section(courseList.get(2).getCourseID(), semesterList.get(0).getSemesterID(),
				UUID.randomUUID(), 222));

		// Spring courses: COMPSI, PHYS, and MATH, respectively
		sectionList.add(new Section(courseList.get(0).getCourseID(), semesterList.get(1).getSemesterID(),
				UUID.randomUUID(), 123));
		sectionList.add(new Section(courseList.get(1).getCourseID(), semesterList.get(1).getSemesterID(),
				UUID.randomUUID(), 131));
		sectionList.add(new Section(courseList.get(2).getCourseID(), semesterList.get(1).getSemesterID(),
				UUID.randomUUID(), 210));

		studentList = new ArrayList<Student>();
		studentList.add(new Student("First", "Middle", "Last", new Date(0), eMajor.BUSINESS, "MyAddress0",
				"(111)-111-1110", "Student0@email.com"));
		studentList.add(new Student("First", "Middle", "Last", new Date(1), eMajor.BUSINESS, "MyAddress1",
				"(111)-111-1111", "Student1@email.com"));
		studentList.add(new Student("First", "Middle", "Last", new Date(2), eMajor.COMPSI, "MyAddress2",
				"(111)-111-1112", "Student2@email.com"));
		studentList.add(new Student("First", "Middle", "Last", new Date(3), eMajor.COMPSI, "MyAddress3",
				"(111)-111-1113", "Student3@email.com"));
		studentList.add(new Student("First", "Middle", "Last", new Date(4), eMajor.CHEM, "MyAddress4", "(111)-111-1114",
				"Student4@email.com"));
		studentList.add(new Student("First", "Middle", "Last", new Date(5), eMajor.CHEM, "MyAddress5", "(111)-111-1115",
				"Student5@email.com"));
		studentList.add(new Student("First", "Middle", "Last", new Date(6), eMajor.PHYSICS, "MyAddress6",
				"(111)-111-1116", "Student6@email.com"));
		studentList.add(new Student("First", "Middle", "Last", new Date(7), eMajor.PHYSICS, "MyAddress7",
				"(111)-111-1117", "Student7@email.com"));
		studentList.add(new Student("First", "Middle", "Last", new Date(8), eMajor.NURSING, "MyAddress8",
				"(111)-111-1118", "Student8@email.com"));
		studentList.add(new Student("First", "Middle", "Last", new Date(9), eMajor.NURSING, "MyAddress9",
				"(111)-111-1119", "Student9@email.com"));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		courseList = null;
		semesterList = null;
		sectionList = null;
		studentList = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStudentGPA() {
		ArrayList<Enrollment> enrollmentList = new ArrayList<Enrollment>();
		ArrayList<Double> studentGPAList = new ArrayList<Double>();


		for (int counter1 = 0; counter1 < studentList.size(); counter1++) {
			Student student = studentList.get(counter1);

			for (int counter2 = 0; counter2 < sectionList.size(); counter2++) {
				Section section = sectionList.get(counter2);
				Enrollment e = new Enrollment(section.getSectionID(), student.getStudentID());
				e.setGrade((counter1 * 10) + 10);
				enrollmentList.add(e);
			}
		}

		for (int counter1 = 0; counter1 < enrollmentList.size(); counter1 += 6) {
			double GPA = 0;
			for (int counter2 = 0; counter2 < 6; counter2++) {
				int element = counter1 + counter2;
				GPA += enrollmentList.get(element).getGrade();
			}
			GPA = GPA / 6;
			studentGPAList.add(GPA);
		}

		assertTrue(studentGPAList.get(0).doubleValue() == 10);
		assertTrue(studentGPAList.get(1).doubleValue() == 20);
		assertTrue(studentGPAList.get(2).doubleValue() == 30);
		assertTrue(studentGPAList.get(3).doubleValue() == 40);
		assertTrue(studentGPAList.get(4).doubleValue() == 50);
		assertTrue(studentGPAList.get(5).doubleValue() == 60);
		assertTrue(studentGPAList.get(6).doubleValue() == 70);
		assertTrue(studentGPAList.get(7).doubleValue() == 80);
		assertTrue(studentGPAList.get(8).doubleValue() == 90);
		assertTrue(studentGPAList.get(9).doubleValue() == 100);
	}

	@Test
	public void testCourseGPA() {
		ArrayList<Enrollment> enrollmentList = new ArrayList<Enrollment>();
		ArrayList<Double> sectionGPAList = new ArrayList<Double>();
		
		for (int counter1 = 0; counter1 < studentList.size(); counter1++) {
			Student student = studentList.get(counter1);

			for (int counter2 = 0; counter2 < sectionList.size(); counter2++) {
				Section section = sectionList.get(counter2);
				Enrollment e = new Enrollment(section.getSectionID(), student.getStudentID());
				e.setGrade((counter1 * 10) + 10);
				enrollmentList.add(e);
			}
		}
		
		for (int counter1 = 0; counter1 < 6; counter1++) {
			double classGPA = 0;
			for (int counter2 = 0; counter2 < enrollmentList.size(); counter2 += 6) {
				int element = counter1 + counter2;
				classGPA += enrollmentList.get(element).getGrade();
			}
			classGPA = classGPA / 10;
			sectionGPAList.add(classGPA);
		}
		
		assertTrue(sectionGPAList.get(0).doubleValue() == 55);
		assertTrue(sectionGPAList.get(1).doubleValue() == 55);
		assertTrue(sectionGPAList.get(2).doubleValue() == 55);
		assertTrue(sectionGPAList.get(3).doubleValue() == 55);
		assertTrue(sectionGPAList.get(4).doubleValue() == 55);
		assertTrue(sectionGPAList.get(5).doubleValue() == 55);
	}
	
	@Test
	public void testChangeMajor() {
		studentList.get(0).setMajor(eMajor.CHEM);
		assertEquals(studentList.get(0).getMajor(), eMajor.CHEM);
	}
}