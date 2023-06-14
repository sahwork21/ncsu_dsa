package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 *
 */
public class StudentIDComparator implements Comparator<Student> {
	
	/**
	 * Empty constructor to allow for the comparator to compare two students
	 */
	public StudentIDComparator() {
		//Empty since no parameters or fields are required to use compare
	}
	
	/**
	 * Compares students based on id number in ascending order
	 * @return -1 if one goes before two, 1 if two goes before one, 0 if the ids are the same
	 */
	@Override
	public int compare(Student one, Student two) {
		if(one.getId() < two.getId()) {
			return -1;
		}
		else if(one.getId() > two.getId()) {
			return 1;
		}
		return 0;
	}

}
