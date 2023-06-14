package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the StudentIDComparator to compare two student's ids
 * @author Dr.King
 * @author Sean Hinton (sahinto2)
 *
 */
public class StudentIDComparatorTest {
	/**First student*/
	private Student sOne;
	/**Second student*/
	private Student sTwo;
	/**Third student*/
	private Student sThree;
	/**Fourth student*/
	private Student sFour;
	/**Fifth student*/
	private Student sFive;
	/**Sixth student equal to sOne*/
	private Student sSix;
	/**The comparator that will compare two students*/
	private StudentIDComparator comparator;

	/**
	 * Initializes the students and comparators.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		sSix = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * Tests that the comparator returns the correct values
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);
		assertEquals(0, comparator.compare(sOne, sOne));
		assertEquals(0, comparator.compare(sOne, sSix));
		assertTrue(comparator.compare(sThree, sFour) < 0);
		assertTrue(comparator.compare(sFive, sFour) > 0);

		
		
	}


}
