package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests that the comparator compares gpas and students in descending order
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 *
 */
public class StudentGPAComparatorTest {
	/**Fifth student*/
	private Student sOne;
	/**Fourth student*/
	private Student sTwo;
	/**Third student*/
	private Student sThree;
	/**Second student*/
	private Student sFour;
	/**First student*/
	private Student sFive;
	
	/**Student equal to one*/
	private Student sOneEqual;
	
	/**
	 * comparator to compare students by gpa
	 */
	private StudentGPAComparator comparator;

	/**
	 * initializes all the student objects and comparartor
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		sOneEqual = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");

		comparator = new StudentGPAComparator();
	}

	/**
	 * Tests that comparator returns the descending order of student gpas
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);
		assertTrue(comparator.compare(sThree, sFour) > 0);
		assertTrue(comparator.compare(sFive, sThree) < 0);
		assertTrue(comparator.compare(sFive, sOne) < 0);
		assertEquals(0, comparator.compare(sOne, sOneEqual));
		
	}

}
