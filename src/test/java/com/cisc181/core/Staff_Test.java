package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eTitle;
import com.cisc181.exceptions.PersonException;

public class Staff_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAverageSalary() throws PersonException {
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		staffList.add(new Staff("Joseph", "Doug", "Doggett", new Date(0), "4514 Cottrill Lane", "(314)-439-1651",
				"JosephDDoggett@teleworm.us", "9 to 5", 1, 111111, new Date(0), eTitle.MR));
		staffList.add(new Staff("First", "Middle", "Last", new Date(0), "MyAddress", "(314)-439-1651",
				"myEmail@email.com", "9 to 5", 1, 12345, new Date(0), eTitle.MR));
		staffList.add(new Staff("First", "Middle", "Last", new Date(0), "MyAddress", "(314)-439-1651",
				"myEmail@email.com", "9 to 5", 1, 123456, new Date(0), eTitle.MR));
		staffList.add(new Staff("First", "Middle", "Last", new Date(0), "MyAddress", "(314)-439-1651",
				"myEmail@email.com", "9 to 5", 1, 75000, new Date(0), eTitle.MR));
		staffList.add(new Staff("First", "Middle", "Last", new Date(0), "MyAddress", "(314)-439-1651",
				"myEmail@email.com", "9 to 5", 1, 50000, new Date(0), eTitle.MR));

		double averageSalary = (staffList.get(0).getSalary() + staffList.get(1).getSalary()
				+ staffList.get(2).getSalary() + staffList.get(3).getSalary() + staffList.get(4).getSalary()) / 5;
		
		assertTrue(averageSalary == 74382.4);
	}

	@Test(expected = PersonException.class)
	public void testIncorrectPhoneNumber() throws PersonException {
		Staff testStaff = new Staff("First", "Middle", "Last", new Date(0), "MyAddress", "123-456-7890",
				"myEmail@email.com", "9 to 5", 1, 12345, new Date(0), eTitle.MR);
	}

	@Test(expected = PersonException.class)
	public void testIncorrectDOB() throws PersonException {
		Calendar testCalendar = Calendar.getInstance();
		testCalendar.set(Calendar.YEAR, 1916);
		Date DOB = testCalendar.getTime();
		
		Staff testStaff = new Staff("First", "Middle", "Last", DOB, "MyAddress", "(123)-456-7890", "myEmail@email.com",
				"9 to 5", 1, 12345, new Date(0), eTitle.MR);
	}
}
