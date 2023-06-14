package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the sort method for the Insertion Sorter
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 */
public class InsertionSorterTest {
	/**An array that is already sorted in ascending order*/
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/**
	 * An array that is not sorted into ascending order
	 * Confirms the value check for insertion index
	*/
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/**
	 * A random array of numbers to check sorting 
	 */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	
	/**Array for testing sorting students*/
	private Student[] students;

	/**
	 * The object that calls its own sorting method to sort the arrays
	 */
	private InsertionSorter<Integer> integerSorter;
	/**
	 * The object that calls its own sorting method to sort the arrays of students
	 */
	private InsertionSorter<Student> studentSorter;

	/**
	 * Initializes the InsertionSorter class that will sort the arrays
	 */
	@Before
	public void setUp() {
		integerSorter = new InsertionSorter<Integer>();
		studentSorter = new InsertionSorter<Student>();
	}

	/**
	 * Tests insertion sort on an array of ints
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals(1, dataAscending[0].intValue());
		assertEquals(2, dataAscending[1].intValue());
		assertEquals(3, dataAscending[2].intValue());
		assertEquals(4, dataAscending[3].intValue());
		assertEquals(5, dataAscending[4].intValue());

		integerSorter.sort(dataDescending);
		assertEquals(1, dataDescending[0].intValue());
		assertEquals(2, dataDescending[1].intValue());
		assertEquals(3, dataDescending[2].intValue());
		assertEquals(4, dataDescending[3].intValue());
		assertEquals(5, dataDescending[4].intValue());

		integerSorter.sort(dataRandom);
		assertEquals(1, dataRandom[0].intValue());
		assertEquals(2, dataRandom[1].intValue());
		assertEquals(3, dataRandom[2].intValue());
		assertEquals(4, dataRandom[3].intValue());
		assertEquals(5, dataRandom[4].intValue());
	}

	/**
	 * Tests the InsertionSort of an array containing Students
	 */
	@Test
	public void testSortStudent() {
		Student s1 = new Student("afirst", "alast", 1, 1, 1.0, "aunity");
		Student s2 = new Student("bfirst", "blast", 1, 1, 1.0, "bunity");
		Student s3 = new Student("cfirst", "clast", 1, 1, 1.0, "cunity");
		Student s4 = new Student("dfirst", "dlast", 1, 1, 1.0, "dunity");
		Student s5 = new Student("efirst", "elast", 1, 1, 1.0, "eunity");
		Student s6 = new Student("efirst", "elast", 8, 1, 1.0, "eunity");
		Student s7 = new Student("zfirst", "elast", 1, 1, 1.0, "zunity");
		
		students = new Student[]{s1, s2, s3, s4, s5, s6, s7};
		
		studentSorter.sort(students);
		assertEquals("first=afirst, last=alast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[0].toString());
		assertEquals("first=bfirst, last=blast, id=1, creditHours=1, gpa=1.0, unityID=bunity", students[1].toString());
		assertEquals("first=cfirst, last=clast, id=1, creditHours=1, gpa=1.0, unityID=cunity", students[2].toString());
		assertEquals("first=dfirst, last=dlast, id=1, creditHours=1, gpa=1.0, unityID=dunity", students[3].toString());
		assertEquals("first=efirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=eunity", students[4].toString());
		assertEquals("first=efirst, last=elast, id=8, creditHours=1, gpa=1.0, unityID=eunity", students[5].toString());
		assertEquals("first=zfirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=zunity", students[6].toString());
		
		students = new Student[] {s1, s5, s3, s6, s4, s2, s7};
		studentSorter.sort(students);
		assertEquals("first=afirst, last=alast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[0].toString());
		assertEquals("first=bfirst, last=blast, id=1, creditHours=1, gpa=1.0, unityID=bunity", students[1].toString());
		assertEquals("first=cfirst, last=clast, id=1, creditHours=1, gpa=1.0, unityID=cunity", students[2].toString());
		assertEquals("first=dfirst, last=dlast, id=1, creditHours=1, gpa=1.0, unityID=dunity", students[3].toString());
		assertEquals("first=efirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=eunity", students[4].toString());
		assertEquals("first=efirst, last=elast, id=8, creditHours=1, gpa=1.0, unityID=eunity", students[5].toString());
		assertEquals("first=zfirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=zunity", students[6].toString());
		
		students = new Student[] {s7, s6, s5, s4, s3, s2, s1};
		studentSorter.sort(students);
		assertEquals("first=afirst, last=alast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[0].toString());
		assertEquals("first=bfirst, last=blast, id=1, creditHours=1, gpa=1.0, unityID=bunity", students[1].toString());
		assertEquals("first=cfirst, last=clast, id=1, creditHours=1, gpa=1.0, unityID=cunity", students[2].toString());
		assertEquals("first=dfirst, last=dlast, id=1, creditHours=1, gpa=1.0, unityID=dunity", students[3].toString());
		assertEquals("first=efirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=eunity", students[4].toString());
		assertEquals("first=efirst, last=elast, id=8, creditHours=1, gpa=1.0, unityID=eunity", students[5].toString());
		assertEquals("first=zfirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=zunity", students[6].toString());
		
		
	}
}
