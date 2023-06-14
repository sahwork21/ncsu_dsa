package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 *
 */
public class StudentGPAComparator implements Comparator<Student> {
	/**
	 * Empty constructor allows the Comparator method to be used
	 */
	public StudentGPAComparator() {
		//Empty constructor so compare method can be used
	}
	
	/**
	 * Compares students based on GPA in descending order
	 * @return -1 if one has a greater gpa than two, 1 if one has a lower gpa, and 0 if they are equal
	 */
	@Override
	public int compare(Student one, Student two) {
		if(one.getGpa() > two.getGpa()) {
			return -1;
		}
		else if(one.getGpa() < two.getGpa()) {
			return 1;
		}
		return 0;
	}

}
