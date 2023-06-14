package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the StudentReaders ability to read a .csv file with Student information
 * @author Dr.King
 * @author Sean Hinton (sahinto2)
 *
 */
public class StudentReaderTest {
	
	/**
	 * Tests the StudentReader can read a .csv file
	 */
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}
	
	/**
	 * Tests that data was properly read in
	 */
	@Test
	public void testCorrectData() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
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
