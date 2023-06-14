package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;


import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.manager.StudentManager;

/**
 * Tests the Bubble Sorter's ability to sort objects
 * @author Sean Hinton (sahinto2)
 *
 */
public class BubbleSorterTest {

	/**Bubble sorter where sort will be tested*/
	AbstractComparisonSorter<Student> bubbleSorter;
	
	/**
	 * Tests the BubbleSorter's sort method with the natural order
	 */
	@Test
	public void testSortNaturalOrder() {
		
		bubbleSorter = new BubbleSorter<Student>();
		
		Student s1 = new Student("afirst", "alast", 1, 1, 1.0, "aunity");
		Student s2 = new Student("bfirst", "blast", 1, 1, 1.0, "bunity");
		Student s3 = new Student("cfirst", "clast", 1, 1, 1.0, "cunity");
		Student s4 = new Student("dfirst", "dlast", 1, 1, 1.0, "dunity");
		Student s5 = new Student("efirst", "elast", 1, 1, 1.0, "eunity");
		Student s6 = new Student("efirst", "elast", 8, 1, 1.0, "eunity");
		Student s7 = new Student("zfirst", "elast", 1, 1, 1.0, "zunity");
		
		Student[] students = new Student[] {s1, s2, s3, s4, s5, s6, s7};
		
		bubbleSorter.sort(students);
		assertEquals("first=afirst, last=alast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[0].toString());
		assertEquals("first=bfirst, last=blast, id=1, creditHours=1, gpa=1.0, unityID=bunity", students[1].toString());
		assertEquals("first=cfirst, last=clast, id=1, creditHours=1, gpa=1.0, unityID=cunity", students[2].toString());
		assertEquals("first=dfirst, last=dlast, id=1, creditHours=1, gpa=1.0, unityID=dunity", students[3].toString());
		assertEquals("first=efirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=eunity", students[4].toString());
		assertEquals("first=efirst, last=elast, id=8, creditHours=1, gpa=1.0, unityID=eunity", students[5].toString());
		assertEquals("first=zfirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=zunity", students[6].toString());
		
		students = new Student[] {s3, s4, s2, s7, s1, s6, s5};
		bubbleSorter.sort(students);
		assertEquals("first=afirst, last=alast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[0].toString());
		assertEquals("first=bfirst, last=blast, id=1, creditHours=1, gpa=1.0, unityID=bunity", students[1].toString());
		assertEquals("first=cfirst, last=clast, id=1, creditHours=1, gpa=1.0, unityID=cunity", students[2].toString());
		assertEquals("first=dfirst, last=dlast, id=1, creditHours=1, gpa=1.0, unityID=dunity", students[3].toString());
		assertEquals("first=efirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=eunity", students[4].toString());
		assertEquals("first=efirst, last=elast, id=8, creditHours=1, gpa=1.0, unityID=eunity", students[5].toString());
		assertEquals("first=zfirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=zunity", students[6].toString());
	}
	
	/**
	 * Tests the BubbleSorter's sort method with different comparators
	 */
	@Test
	public void testSortComparator() {
		bubbleSorter = new BubbleSorter<Student>(new StudentIDComparator());
		
		Student s1 = new Student("zfirst", "zlast", 1, 1, 1.0, "aunity");
		Student s2 = new Student("yfirst", "ylast", 2, 1, 1.5, "bunity");
		Student s3 = new Student("xfirst", "xlast", 3, 1, 4.0, "cunity");
		Student s4 = new Student("wfirst", "wlast", 4, 1, 3.5, "dunity");
		Student s5 = new Student("vfirst", "vlast", 5, 1, 6.7, "eunity");
		Student s6 = new Student("ufirst", "ulast", 6, 1, 6.2, "eunity");
		Student s7 = new Student("tfirst", "tlast", 7, 1, 4.2, "zunity");
		
		Student[] students = new Student[] {s1, s2, s3, s4, s5, s6, s7};
		
		bubbleSorter.sort(students);
		assertEquals("first=zfirst, last=zlast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[0].toString());
		assertEquals("first=yfirst, last=ylast, id=2, creditHours=1, gpa=1.5, unityID=bunity", students[1].toString());
		assertEquals("first=xfirst, last=xlast, id=3, creditHours=1, gpa=4.0, unityID=cunity", students[2].toString());
		assertEquals("first=wfirst, last=wlast, id=4, creditHours=1, gpa=3.5, unityID=dunity", students[3].toString());
		assertEquals("first=vfirst, last=vlast, id=5, creditHours=1, gpa=6.7, unityID=eunity", students[4].toString());
		assertEquals("first=ufirst, last=ulast, id=6, creditHours=1, gpa=6.2, unityID=eunity", students[5].toString());
		assertEquals("first=tfirst, last=tlast, id=7, creditHours=1, gpa=4.2, unityID=zunity", students[6].toString());
		
		students = new Student[] {s3, s4, s2, s7, s1, s6, s5};
		
		bubbleSorter.sort(students);
		assertEquals("first=zfirst, last=zlast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[0].toString());
		assertEquals("first=yfirst, last=ylast, id=2, creditHours=1, gpa=1.5, unityID=bunity", students[1].toString());
		assertEquals("first=xfirst, last=xlast, id=3, creditHours=1, gpa=4.0, unityID=cunity", students[2].toString());
		assertEquals("first=wfirst, last=wlast, id=4, creditHours=1, gpa=3.5, unityID=dunity", students[3].toString());
		assertEquals("first=vfirst, last=vlast, id=5, creditHours=1, gpa=6.7, unityID=eunity", students[4].toString());
		assertEquals("first=ufirst, last=ulast, id=6, creditHours=1, gpa=6.2, unityID=eunity", students[5].toString());
		assertEquals("first=tfirst, last=tlast, id=7, creditHours=1, gpa=4.2, unityID=zunity", students[6].toString());
		
		bubbleSorter = new BubbleSorter<Student>(new StudentGPAComparator());
		students = new Student[] {s3, s4, s2, s7, s1, s6, s5};
		bubbleSorter.sort(students);
		assertEquals("first=vfirst, last=vlast, id=5, creditHours=1, gpa=6.7, unityID=eunity", students[0].toString());
		assertEquals("first=ufirst, last=ulast, id=6, creditHours=1, gpa=6.2, unityID=eunity", students[1].toString());
		assertEquals("first=tfirst, last=tlast, id=7, creditHours=1, gpa=4.2, unityID=zunity", students[2].toString());
		assertEquals("first=xfirst, last=xlast, id=3, creditHours=1, gpa=4.0, unityID=cunity", students[3].toString());
		assertEquals("first=wfirst, last=wlast, id=4, creditHours=1, gpa=3.5, unityID=dunity", students[4].toString());
		assertEquals("first=yfirst, last=ylast, id=2, creditHours=1, gpa=1.5, unityID=bunity", students[5].toString());
		assertEquals("first=zfirst, last=zlast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[6].toString());
		
		
		students = new Student[] {s5, s6, s7, s3, s4, s2, s1};
		bubbleSorter.sort(students);
		assertEquals("first=vfirst, last=vlast, id=5, creditHours=1, gpa=6.7, unityID=eunity", students[0].toString());
		assertEquals("first=ufirst, last=ulast, id=6, creditHours=1, gpa=6.2, unityID=eunity", students[1].toString());
		assertEquals("first=tfirst, last=tlast, id=7, creditHours=1, gpa=4.2, unityID=zunity", students[2].toString());
		assertEquals("first=xfirst, last=xlast, id=3, creditHours=1, gpa=4.0, unityID=cunity", students[3].toString());
		assertEquals("first=wfirst, last=wlast, id=4, creditHours=1, gpa=3.5, unityID=dunity", students[4].toString());
		assertEquals("first=yfirst, last=ylast, id=2, creditHours=1, gpa=1.5, unityID=bunity", students[5].toString());
		assertEquals("first=zfirst, last=zlast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[6].toString());
	}
	
	/**
	 * Tests the BubbleSorter's sort method with the StudentManager
	 */
	@Test
	public void testSortStudentManager() {
		
		StudentManager sm = new StudentManager("input/student_ascendingID.csv", new BubbleSorter<Student>());
		
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
		
		sm = new StudentManager("input/student_descendingID.csv", new BubbleSorter<Student>());
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
		
		sm = new StudentManager("input/student_randomOrder.csv", new BubbleSorter<Student>());
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
}
