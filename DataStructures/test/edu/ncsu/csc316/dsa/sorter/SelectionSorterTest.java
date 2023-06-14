package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Tests the Selection Sort algorithm
 * @author Sean Hinton (sahinto2)
 *
 */
public class SelectionSorterTest {
	/** Object that uses the selection sort algorithm*/
	SelectionSorter<Student> selectionSorter;
	
	/**
	 * Tests the selection sort method
	 */
	@Test
	public void testSort() {
		selectionSorter = new SelectionSorter<Student>();
		
		Student s1 = new Student("afirst", "alast", 1, 1, 1.0, "aunity");
		Student s2 = new Student("bfirst", "blast", 1, 1, 1.0, "bunity");
		Student s3 = new Student("cfirst", "clast", 1, 1, 1.0, "cunity");
		Student s4 = new Student("dfirst", "dlast", 1, 1, 1.0, "dunity");
		Student s5 = new Student("efirst", "elast", 1, 1, 1.0, "eunity");
		Student s6 = new Student("efirst", "elast", 8, 1, 1.0, "eunity");
		Student s7 = new Student("zfirst", "elast", 1, 1, 1.0, "zunity");
		
		Student[] students = new Student[] {s7, s6, s5, s4, s3, s2, s1}; 
		
		selectionSorter.sort(students);
		assertEquals("first=afirst, last=alast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[0].toString());
		assertEquals("first=bfirst, last=blast, id=1, creditHours=1, gpa=1.0, unityID=bunity", students[1].toString());
		assertEquals("first=cfirst, last=clast, id=1, creditHours=1, gpa=1.0, unityID=cunity", students[2].toString());
		assertEquals("first=dfirst, last=dlast, id=1, creditHours=1, gpa=1.0, unityID=dunity", students[3].toString());
		assertEquals("first=efirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=eunity", students[4].toString());
		assertEquals("first=efirst, last=elast, id=8, creditHours=1, gpa=1.0, unityID=eunity", students[5].toString());
		assertEquals("first=zfirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=zunity", students[6].toString());
		
		
		
        students = new Student[] {s1, s2, s3, s4, s5, s6, s7};
		
		selectionSorter.sort(students);
		assertEquals("first=afirst, last=alast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[0].toString());
		assertEquals("first=bfirst, last=blast, id=1, creditHours=1, gpa=1.0, unityID=bunity", students[1].toString());
		assertEquals("first=cfirst, last=clast, id=1, creditHours=1, gpa=1.0, unityID=cunity", students[2].toString());
		assertEquals("first=dfirst, last=dlast, id=1, creditHours=1, gpa=1.0, unityID=dunity", students[3].toString());
		assertEquals("first=efirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=eunity", students[4].toString());
		assertEquals("first=efirst, last=elast, id=8, creditHours=1, gpa=1.0, unityID=eunity", students[5].toString());
		assertEquals("first=zfirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=zunity", students[6].toString());
		
		students = new Student[] {s3, s4, s2, s7, s1, s6, s5};
	    selectionSorter.sort(students);
		assertEquals("first=afirst, last=alast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[0].toString());
		assertEquals("first=bfirst, last=blast, id=1, creditHours=1, gpa=1.0, unityID=bunity", students[1].toString());
		assertEquals("first=cfirst, last=clast, id=1, creditHours=1, gpa=1.0, unityID=cunity", students[2].toString());
		assertEquals("first=dfirst, last=dlast, id=1, creditHours=1, gpa=1.0, unityID=dunity", students[3].toString());
		assertEquals("first=efirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=eunity", students[4].toString());
		assertEquals("first=efirst, last=elast, id=8, creditHours=1, gpa=1.0, unityID=eunity", students[5].toString());
		assertEquals("first=zfirst, last=elast, id=1, creditHours=1, gpa=1.0, unityID=zunity", students[6].toString());
	}
	
	/**
	 * Tests selection sort with comparators
	 */
	@Test
	public void testIdGpaSort() {
		selectionSorter = new SelectionSorter<Student>(new StudentIDComparator());
		
		
		Student s1 = new Student("zfirst", "zlast", 1, 1, 1.0, "aunity");
		Student s2 = new Student("yfirst", "ylast", 2, 1, 1.5, "bunity");
		Student s3 = new Student("xfirst", "xlast", 3, 1, 4.0, "cunity");
		Student s4 = new Student("wfirst", "wlast", 4, 1, 3.5, "dunity");
		Student s5 = new Student("vfirst", "vlast", 5, 1, 6.7, "eunity");
		Student s6 = new Student("ufirst", "ulast", 6, 1, 6.2, "eunity");
		Student s7 = new Student("tfirst", "tlast", 7, 1, 4.2, "zunity");
		
		Student[] students = new Student[] {s1, s2, s3, s4, s5, s6, s7};
		
		selectionSorter.sort(students);
		assertEquals("first=zfirst, last=zlast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[0].toString());
		assertEquals("first=yfirst, last=ylast, id=2, creditHours=1, gpa=1.5, unityID=bunity", students[1].toString());
		assertEquals("first=xfirst, last=xlast, id=3, creditHours=1, gpa=4.0, unityID=cunity", students[2].toString());
		assertEquals("first=wfirst, last=wlast, id=4, creditHours=1, gpa=3.5, unityID=dunity", students[3].toString());
		assertEquals("first=vfirst, last=vlast, id=5, creditHours=1, gpa=6.7, unityID=eunity", students[4].toString());
		assertEquals("first=ufirst, last=ulast, id=6, creditHours=1, gpa=6.2, unityID=eunity", students[5].toString());
		assertEquals("first=tfirst, last=tlast, id=7, creditHours=1, gpa=4.2, unityID=zunity", students[6].toString());
		
		
		selectionSorter = new SelectionSorter<Student>(new StudentGPAComparator());
		
		
		students = new Student[] {s1, s2, s3, s4, s5, s6, s7};
		
		selectionSorter.sort(students);
		assertEquals("first=vfirst, last=vlast, id=5, creditHours=1, gpa=6.7, unityID=eunity", students[0].toString());
		assertEquals("first=ufirst, last=ulast, id=6, creditHours=1, gpa=6.2, unityID=eunity", students[1].toString());
		assertEquals("first=tfirst, last=tlast, id=7, creditHours=1, gpa=4.2, unityID=zunity", students[2].toString());
		assertEquals("first=xfirst, last=xlast, id=3, creditHours=1, gpa=4.0, unityID=cunity", students[3].toString());
		assertEquals("first=wfirst, last=wlast, id=4, creditHours=1, gpa=3.5, unityID=dunity", students[4].toString());
		assertEquals("first=yfirst, last=ylast, id=2, creditHours=1, gpa=1.5, unityID=bunity", students[5].toString());
		assertEquals("first=zfirst, last=zlast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[6].toString());
		
		
		students = new Student[] {s4, s2, s7, s6, s1, s3, s5};
		
		selectionSorter.sort(students);
		assertEquals("first=vfirst, last=vlast, id=5, creditHours=1, gpa=6.7, unityID=eunity", students[0].toString());
		assertEquals("first=ufirst, last=ulast, id=6, creditHours=1, gpa=6.2, unityID=eunity", students[1].toString());
		assertEquals("first=tfirst, last=tlast, id=7, creditHours=1, gpa=4.2, unityID=zunity", students[2].toString());
		assertEquals("first=xfirst, last=xlast, id=3, creditHours=1, gpa=4.0, unityID=cunity", students[3].toString());
		assertEquals("first=wfirst, last=wlast, id=4, creditHours=1, gpa=3.5, unityID=dunity", students[4].toString());
		assertEquals("first=yfirst, last=ylast, id=2, creditHours=1, gpa=1.5, unityID=bunity", students[5].toString());
		assertEquals("first=zfirst, last=zlast, id=1, creditHours=1, gpa=1.0, unityID=aunity", students[6].toString());
	}

}
