package unsw.enrolment.test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Enrolment;
import unsw.enrolment.Grade;
import unsw.enrolment.Lecture;
import unsw.enrolment.Student;
import unsw.enrolment.Tutorial;

public class EnrolmentTest {

	public static void main(String[] args) {

		// Create courses
		Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
		Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
		comp1531.addPrereq(comp1511);
		Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
		comp2521.addPrereq(comp1511);

		CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
		CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
		CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

		// Create some sessions for the courses
		Lecture lecture1511 = new Lecture(comp1511Offering, "Rex Vowels", DayOfWeek.TUESDAY, LocalTime.of(12, 0),
				LocalTime.of(14, 0), "Andrew Taylor");
		Lecture lecture1531 = new Lecture(comp1531Offering, "CLB 5", DayOfWeek.MONDAY, LocalTime.of(9, 0),
				LocalTime.of(11, 0), "Aarthi Natarajan");
		Lecture lecture2521 = new Lecture(comp2521Offering, "Clancy Auditorium", DayOfWeek.FRIDAY, LocalTime.of(15, 0),
				LocalTime.of(17, 0), "Ashesh Mahidadia");

		Tutorial tute1531 = new Tutorial(comp1531Offering, "Quad 1041", DayOfWeek.WEDNESDAY, LocalTime.of(18, 0),
				LocalTime.of(19, 0), "Hugh Chan");

		// Create a student
		Student student = new Student("z5555555");

		// Enrol the student in COMP1511 for T1 (this should succeed)
		Enrolment comp1511Enrolment = comp1511Offering.enrol(student, lecture1511);

		if (comp1511Enrolment != null)
			System.out.println("Enrolled in COMP1511");

		Enrolment comp1531Enrolment = comp1531Offering.enrol(student, lecture1531, tute1531);

		if (comp1531Enrolment == null)
			System.out.println("Can't enrol in COMP1531");

		Grade grade;


		comp1511Enrolment.assignMark(65);

		// Assign marks for comp1511
		// TODO Give the student a passing mark for assignment 1
		grade = comp1511Enrolment.getGrade();

		Grade assignment1 = new Grade("assignment1", 51);
		grade.addComponent(assignment1);

		Grade assignment2 = grade.addAvgComponent().setName("assignment2");


		// TODO Give the student a passing mark for milestone 1 of assignment 2
		Grade milestone1 = assignment2.addComponent("milestone1", 70);


		// TODO Give the student a passing mark for milestone 2 of assignment 2
		Grade milestone2 = assignment2.addComponent("milestone2", 50);


		// TODO Give the student an assignment 2 mark which is the average of

		System.out.println(assignment2);


		Grade prac = Grade.newSumGrade(assignment1, assignment2).setName("prac");
		System.out.println(prac);

		grade = comp1511Enrolment.assignGrade(prac);
		System.out.println(grade);

		// TODO Give the student a passing fianl mark.
		Grade fianl = new Grade("fianl", 50);
		System.out.println(fianl);

		grade = comp1511Enrolment.assignGrade(prac, fianl);

		Enrolment comp2521Enrolment = comp2521Offering.enrol(student, lecture2521);

		if (comp2521Enrolment != null)
			System.out.println("Enrolled in COMP2521");
	}
}
