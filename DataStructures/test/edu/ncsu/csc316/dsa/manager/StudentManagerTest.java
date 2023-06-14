package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;

/**
 * Tests the StudentManager class' ability to read a csv of Students and sort them in different ways
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 *
 */
public class StudentManagerTest {
	/**The Student Manager that is responsible for containing a collection of students and sorting*/
	private StudentManager sm;
	
	/**Initializes the StudentManager object*/
	@Before
	public void setUp() {
		sm = new StudentManager("input/student_ascendingID.csv");
	}
	
	/**
	 * Tests sorting without specifying the comparator order
	 */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
		
		sm = new StudentManager("input/student_descendingID.csv");
		sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
		
		sm = new StudentManager("input/student_randomOrder.csv");
		sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
		
	}
	
	
	// Suggestions:
	// -> Test that custom comparators sort the data correctly
	/**
	 * Tests the sorter with gpa as the specified order
	 */
	@Test
	public void testSortGpa() {
		sm = new StudentManager("input/student_ascendingID.csv", new InsertionSorter<Student>(new StudentGPAComparator()));
		Student[] sorted = sm.sort();
		assertTrue(3.63 == sorted[0].getGpa());
		assertTrue(3.49 == sorted[1].getGpa());
		assertTrue(3.34 == sorted[2].getGpa());
		assertTrue(3.11 == sorted[3].getGpa());
		assertTrue(2.95 == sorted[4].getGpa());
		assertTrue(2.94 == sorted[5].getGpa());
		assertTrue(2.72 == sorted[6].getGpa());
		assertTrue(2.25 == sorted[7].getGpa());
		assertTrue(1.57 == sorted[8].getGpa());
		assertTrue(1.23 == sorted[9].getGpa());
		assertTrue(1.1 == sorted[10].getGpa());
		assertTrue(0.9 == sorted[11].getGpa());
		assertTrue(0.62 == sorted[12].getGpa());
		assertTrue(0.6 == sorted[13].getGpa());
		assertTrue(0.56 == sorted[14].getGpa());
		assertTrue(.4 == sorted[15].getGpa());
		
		sm = new StudentManager("input/student_descendingID.csv", new InsertionSorter<Student>(new StudentGPAComparator()));
		sorted = sm.sort();
		assertTrue(3.63 == sorted[0].getGpa());
		assertTrue(3.49 == sorted[1].getGpa());
		assertTrue(3.34 == sorted[2].getGpa());
		assertTrue(3.11 == sorted[3].getGpa());
		assertTrue(2.95 == sorted[4].getGpa());
		assertTrue(2.94 == sorted[5].getGpa());
		assertTrue(2.72 == sorted[6].getGpa());
		assertTrue(2.25 == sorted[7].getGpa());
		assertTrue(1.57 == sorted[8].getGpa());
		assertTrue(1.23 == sorted[9].getGpa());
		assertTrue(1.1 == sorted[10].getGpa());
		assertTrue(0.9 == sorted[11].getGpa());
		assertTrue(0.62 == sorted[12].getGpa());
		assertTrue(0.6 == sorted[13].getGpa());
		assertTrue(0.56 == sorted[14].getGpa());
		assertTrue(.4 == sorted[15].getGpa());
		
		sm = new StudentManager("input/student_randomOrder.csv", new InsertionSorter<Student>(new StudentGPAComparator()));
		sorted = sm.sort();
		assertTrue(3.63 == sorted[0].getGpa());
		assertTrue(3.49 == sorted[1].getGpa());
		assertTrue(3.34 == sorted[2].getGpa());
		assertTrue(3.11 == sorted[3].getGpa());
		assertTrue(2.95 == sorted[4].getGpa());
		assertTrue(2.94 == sorted[5].getGpa());
		assertTrue(2.72 == sorted[6].getGpa());
		assertTrue(2.25 == sorted[7].getGpa());
		assertTrue(1.57 == sorted[8].getGpa());
		assertTrue(1.23 == sorted[9].getGpa());
		assertTrue(1.1 == sorted[10].getGpa());
		assertTrue(0.9 == sorted[11].getGpa());
		assertTrue(0.62 == sorted[12].getGpa());
		assertTrue(0.6 == sorted[13].getGpa());
		assertTrue(0.56 == sorted[14].getGpa());
		assertTrue(.4 == sorted[15].getGpa());
	}
	
	/**
	 * Tests the sorter with id as the specified order
	 */
	@Test
	public void testSortId() {
		
		sm = new StudentManager("input/student_ascendingID.csv", new InsertionSorter<Student>(new StudentIDComparator()));
		Student[] sorted = sm.sort();
		assertEquals(1, sorted[0].getId());
		assertEquals(3, sorted[1].getId());
		assertEquals(4, sorted[2].getId());
		assertEquals(5, sorted[3].getId());
		assertEquals(8, sorted[4].getId());
		assertEquals(9, sorted[5].getId());
		assertEquals(10, sorted[6].getId());
		assertEquals(14, sorted[7].getId());
		assertEquals(17, sorted[8].getId());
		assertEquals(19, sorted[9].getId());
		assertEquals(23, sorted[10].getId());
		assertEquals(24, sorted[11].getId());
		assertEquals(26, sorted[12].getId());
		assertEquals(28, sorted[13].getId());
		assertEquals(30, sorted[14].getId());
		assertEquals(31, sorted[15].getId());
		
		sm = new StudentManager("input/student_descendingID.csv", new InsertionSorter<Student>(new StudentIDComparator()));
		sorted = sm.sort();
		assertEquals(1, sorted[0].getId());
		assertEquals(3, sorted[1].getId());
		assertEquals(4, sorted[2].getId());
		assertEquals(5, sorted[3].getId());
		assertEquals(8, sorted[4].getId());
		assertEquals(9, sorted[5].getId());
		assertEquals(10, sorted[6].getId());
		assertEquals(14, sorted[7].getId());
		assertEquals(17, sorted[8].getId());
		assertEquals(19, sorted[9].getId());
		assertEquals(23, sorted[10].getId());
		assertEquals(24, sorted[11].getId());
		assertEquals(26, sorted[12].getId());
		assertEquals(28, sorted[13].getId());
		assertEquals(30, sorted[14].getId());
		assertEquals(31, sorted[15].getId());
		
		sm = new StudentManager("input/student_randomOrder.csv", new InsertionSorter<Student>(new StudentIDComparator()));
		sorted = sm.sort();
		assertEquals(1, sorted[0].getId());
		assertEquals(3, sorted[1].getId());
		assertEquals(4, sorted[2].getId());
		assertEquals(5, sorted[3].getId());
		assertEquals(8, sorted[4].getId());
		assertEquals(9, sorted[5].getId());
		assertEquals(10, sorted[6].getId());
		assertEquals(14, sorted[7].getId());
		assertEquals(17, sorted[8].getId());
		assertEquals(19, sorted[9].getId());
		assertEquals(23, sorted[10].getId());
		assertEquals(24, sorted[11].getId());
		assertEquals(26, sorted[12].getId());
		assertEquals(28, sorted[13].getId());
		assertEquals(30, sorted[14].getId());
		assertEquals(31, sorted[15].getId());
	}

}
