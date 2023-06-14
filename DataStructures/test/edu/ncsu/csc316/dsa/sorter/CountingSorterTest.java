package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.manager.StudentManager;

/**
 * Tests the CountingSorter
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 *
 */
public class CountingSorterTest {
	/**Student One*/
	private Student sOne;
	/**Student Two*/
	private Student sTwo;
	/**Student Three*/
	private Student sThree;
	/**Student Four*/
	private Student sFour;
	/**Student Five*/
	private Student sFive;
	/**Object that does the sorting*/
	private CountingSorter<Student> sorter;
	
	/**
	 * Initializes all the students and sorter
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new CountingSorter<Student>();
	}
	
	/**
	 * Test the CountingSorter's sort method
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
		
		original = new Student[]{sTwo, sOne, sFour, sFive, sThree, new Student("s6", "six", 99, 5, 5.0, "ssix")};
		
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
		assertEquals(99, original[5].getId());
		
		
		StudentManager sm = new StudentManager("input/student_randomOrder.csv", new CountingSorter<Student>());
		
		
		Student[] contents = sm.sort();
		assertEquals("first=Amber, last=Michael, id=1, creditHours=10, gpa=1.1, unityID=michaea", contents[0].toString());
		assertEquals("first=Ara, last=Marsh, id=3, creditHours=11, gpa=2.25, unityID=marsha", contents[1].toString());
		assertEquals("first=Lacie, last=Mott, id=4, creditHours=18, gpa=2.94, unityID=mottl", contents[2].toString());
		assertEquals("first=Idalia, last=Pease, id=5, creditHours=9, gpa=2.72, unityID=peasei", contents[3].toString());
		assertEquals("first=Evelin, last=Seibert, id=8, creditHours=13, gpa=0.6, unityID=seibere", contents[4].toString());
		assertEquals("first=Lewis, last=Matheson, id=9, creditHours=9, gpa=0.4, unityID=mathesl", contents[5].toString());
		assertEquals("first=Alicia, last=Terrell, id=10, creditHours=10, gpa=3.49, unityID=terrela", contents[6].toString());
		assertEquals("first=Tyree, last=Runyon, id=14, creditHours=10, gpa=0.62, unityID=runyont", contents[7].toString());
		assertEquals("first=Loise, last=Woodbury, id=17, creditHours=13, gpa=1.57, unityID=woodbul", contents[8].toString());
		assertEquals("first=Roxann, last=Carrion, id=19, creditHours=13, gpa=0.9, unityID=carrior", contents[9].toString());
		assertEquals("first=Nichole, last=Worth, id=23, creditHours=11, gpa=3.63, unityID=worthn", contents[10].toString());
		assertEquals("first=Charlene, last=Mclendon, id=24, creditHours=14, gpa=3.34, unityID=mclendc", contents[11].toString());
		assertEquals("first=Shanti, last=Dick, id=26, creditHours=17, gpa=0.56, unityID=dicks", contents[12].toString());
		assertEquals("first=Cristine, last=Greco, id=28, creditHours=11, gpa=3.11, unityID=grecoc", contents[13].toString());
		assertEquals("first=Tanner, last=Bauman, id=30, creditHours=16, gpa=1.23, unityID=baumant", contents[14].toString());
		assertEquals("first=Dante, last=Falcon, id=31, creditHours=16, gpa=2.95, unityID=falcond", contents[15].toString());
	}
	
	

}
