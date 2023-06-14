package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
/**
 * Used for testing the methods inside the Student class
 * @author Dr. King
 * @author Sean Hinton (sainto2)
 *
 */
public class StudentTest {
	/**Instance one of a student*/
	private Student sOne;
	/**Instance two of a student*/
	private Student sTwo;
	/**Instance three of a student*/
	private Student sThree;
	/**Instance four of a student*/
	private Student sFour;
	/**Instance five of a student*/
	private Student sFive;
	/**Instance six of a student*/
	private Student sSix;
	/**Instance six of a student*/
	private Student sSeven;

	/**
	 * Initializes the Students to be tested
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("first", "last", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("first", "last", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("z", "last", 3, 5, 5.0, "fiveUnityID");
		sSix = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sSeven = new Student("OneFirst", "OneLast", 1, 100, 1.5, "oneUnityID");
	}

	/**
	 * Tests the setFirst method to check if first was set to the new value
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Tests the setLast method to check if last was set to the new value
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}
	
	/**
	 * Tests the setID method to check if id was set to the new value
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Tests the setGpa method to check if gpa was set to the new value
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Tests the setUnityID method to check if unityID was set to the new value
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}
	/**
	 * Tests the setCreditHours method to check if creditHours was set to the new value
	 */
	@Test
	public void testSetCreditHours() {
		sOne.setCreditHours(21);
		assertEquals(21, sOne.getCreditHours());
	}

	/**
	 * Tests the compareTo method to check that students come in the correct order
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertEquals(0, sOne.compareTo(sOne));
		assertEquals(0, sTwo.compareTo(sTwo));
		assertTrue(sThree.compareTo(sFour) < 0);
		assertTrue(sThree.compareTo(sFive) < 0);
		assertTrue(sFour.compareTo(sThree) > 0);
		assertTrue(sFive.compareTo(sThree) > 0);
		
		
		
		
	}
	
	
	// Suggestions:
	// -> Test .equals(), .toString(), etc.
	/**
	 * Tests the hashCode method for two Students
	 */
	@Test
	public void testHashCode() {
		assertNotEquals(sOne.hashCode(), sTwo.hashCode());
		assertNotEquals(sThree.hashCode(), sFour.hashCode());
		assertNotEquals(sThree.hashCode(), sFive.hashCode());
		assertEquals(sOne.hashCode(), sSix.hashCode());
		assertEquals(sOne.hashCode(), sSeven.hashCode());
	}
	
	/**
	 * Tests the equals method. Equal if they have the same last, first, and id
	 */
	@Test
	public void testEquals() {
		assertFalse(sOne.equals(sTwo));
		assertFalse(sThree.equals(sFour));
		assertFalse(sThree.equals(sFive));
		assertTrue(sOne.equals(sSix));
		assertTrue(sOne.equals(sSeven));
		assertFalse(sFour.equals(sFive));
		assertTrue(sSix.equals(sSix));
		
		
	}
	
	/**
	 * Tests the toString method outputs the right thing
	 */
	@Test
	public void testToString() {
		assertEquals("first=OneFirst, last=OneLast, id=1, creditHours=1, gpa=1.0, unityID=oneUnityID", sOne.toString());
	}
}
