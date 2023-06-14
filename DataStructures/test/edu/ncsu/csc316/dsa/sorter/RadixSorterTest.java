package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.manager.StudentManager;

/**
 * Tests the RadixSorter's ability to sort by id number
 * @author sahin
 *
 */
public class RadixSorterTest {
	
	/**Object that does Radix Sorting*/
	private RadixSorter<Student> sorter;
	
	/**
	 * Tests the sort method for RadixSorter
	 */
	@Test
	public void testSort() {
		sorter = new RadixSorter<Student>();
		
		Student s1 = new Student("zfirst", "zlast", 1, 1, 1.0, "aunity");
		Student s2 = new Student("yfirst", "ylast", 2, 1, 1.5, "bunity");
		Student s3 = new Student("xfirst", "xlast", 3, 1, 4.0, "cunity");
		Student s4 = new Student("wfirst", "wlast", 4, 1, 3.5, "dunity");
		Student s5 = new Student("vfirst", "vlast", 5, 1, 6.7, "eunity");
		Student s6 = new Student("ufirst", "ulast", 6, 1, 6.2, "eunity");
		Student s7 = new Student("tfirst", "tlast", 7, 1, 4.2, "zunity");
		
		Student[] students = new Student[] {s2, s3, s4, s5, s7, s6, s1};
		
		sorter.sort(students);
		
		assertEquals(s1, students[0]);
		assertEquals(s2, students[1]);
		assertEquals(s3, students[2]);
		assertEquals(s4, students[3]);
		assertEquals(s5, students[4]);
		assertEquals(s6, students[5]);
		assertEquals(s7, students[6]);
		
		
		students = new Student[] {s1, s2, s3, s4, s5, s6, s7};
		
		sorter.sort(students);
		
		assertEquals(s1, students[0]);
		assertEquals(s2, students[1]);
		assertEquals(s3, students[2]);
		assertEquals(s4, students[3]);
		assertEquals(s5, students[4]);
		assertEquals(s6, students[5]);
		assertEquals(s7, students[6]);
		
		StudentManager sm = new StudentManager("input/student_randomOrder.csv", new CountingSorter<Student>());
		
		
		students = sm.sort();
		assertEquals("first=Amber, last=Michael, id=1, creditHours=10, gpa=1.1, unityID=michaea", students[0].toString());
		assertEquals("first=Ara, last=Marsh, id=3, creditHours=11, gpa=2.25, unityID=marsha", students[1].toString());
		assertEquals("first=Lacie, last=Mott, id=4, creditHours=18, gpa=2.94, unityID=mottl", students[2].toString());
		assertEquals("first=Idalia, last=Pease, id=5, creditHours=9, gpa=2.72, unityID=peasei", students[3].toString());
		assertEquals("first=Evelin, last=Seibert, id=8, creditHours=13, gpa=0.6, unityID=seibere", students[4].toString());
		assertEquals("first=Lewis, last=Matheson, id=9, creditHours=9, gpa=0.4, unityID=mathesl", students[5].toString());
		assertEquals("first=Alicia, last=Terrell, id=10, creditHours=10, gpa=3.49, unityID=terrela", students[6].toString());
		assertEquals("first=Tyree, last=Runyon, id=14, creditHours=10, gpa=0.62, unityID=runyont", students[7].toString());
		assertEquals("first=Loise, last=Woodbury, id=17, creditHours=13, gpa=1.57, unityID=woodbul", students[8].toString());
		assertEquals("first=Roxann, last=Carrion, id=19, creditHours=13, gpa=0.9, unityID=carrior", students[9].toString());
		assertEquals("first=Nichole, last=Worth, id=23, creditHours=11, gpa=3.63, unityID=worthn", students[10].toString());
		assertEquals("first=Charlene, last=Mclendon, id=24, creditHours=14, gpa=3.34, unityID=mclendc", students[11].toString());
		assertEquals("first=Shanti, last=Dick, id=26, creditHours=17, gpa=0.56, unityID=dicks", students[12].toString());
		assertEquals("first=Cristine, last=Greco, id=28, creditHours=11, gpa=3.11, unityID=grecoc", students[13].toString());
		assertEquals("first=Tanner, last=Bauman, id=30, creditHours=16, gpa=1.23, unityID=baumant", students[14].toString());
		assertEquals("first=Dante, last=Falcon, id=31, creditHours=16, gpa=2.95, unityID=falcond", students[15].toString());
	}

}
