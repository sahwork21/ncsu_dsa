package edu.ncsu.csc316.dsa.data;

import java.util.Objects;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 */
public class Student implements Comparable<Student>, Identifiable {
	/**The Student's first name*/
	String first;
	/**The Student's last name*/
	String last;
	/**The students id number*/
	int id;
	/**The number of creditHours the student has*/
	int creditHours;
	/**The Student's gpa*/
	double gpa;
	/**The unity ID for a student*/
	String unityID;
	
	/**
	 * Constructor for creating a Student object by inputting the given fields
	 * @param first The String to be assigned as the Student's first name
	 * @param last The String to be assigned as the Student's last name
	 * @param id The int to be assigned as the Student's id number
	 * @param creditHours the int to be assigned as the Student's number of credit hours
	 * @param gpa the double to be assigned as the Student's gpa
	 * @param unityID The String to be assigned as the Student's unity ID
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		super();
		this.first = first;
		this.last = last;
		this.id = id;
		this.creditHours = creditHours;
		this.gpa = gpa;
		this.unityID = unityID;
	}

	/**
	 * Gets the Students's first name
	 * @return first the first name of the Student
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Sets the Student's first name to the given parameter
	 * @param first the String that first will be assigned to
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Gets the Students's last name
	 * @return last the last name of the Student
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Sets the Student's last name to the given parameter
	 * @param last the String that last will be assigned to
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Gets the Students's id number
	 * @return id the id number of the student
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the Student's ID number to the given parameter
	 * @param id the int that id will be assigned to
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the Students's number of credit hours
	 * @return creditHours the number of credit hours the Student has
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Sets the Student's creditHours to the given parameter
	 * @param creditHours the int that creditHours will be assigned to
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	
	/**
	 * Gets the Students's gpa
	 * @return gpa the decimal number of the Student's gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Sets the Student's gpa to the given parameter
	 * @param gpa the double that gpa will be assigned to
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	/**
	 * Gets the Students's unity ID as a String
	 * @return unityID the Student's unity ID string
	 */
	public String getUnityID() {
		return unityID;
	}
	
	/**
	 * Sets the Student's unityID String to the given parameter
	 * @param unityID the String that unityID will be assigned to
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

	/**
	 * Checks for equivalence between two Students making sure they have the same hash codes for fields
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		
		return result;
	}
	/**
	 * Checks for equivalence between this Student and another Student
	 * Students are equivalent if they are both Students and have the same first name, last name,
	 * and student id number
	 */
	@Override
	public boolean equals(Object otherStudent) {
		if (otherStudent == null) {
			return false;
		}
		if (otherStudent instanceof Student) {
			Student o = (Student) otherStudent;
			return  Objects.equals(first, o.first) && Objects.equals(last, o.last) && id == o.id;
		}
		else {
			return false;
		}
		
	}

	/**
	 * Orders students by comparing this Student's fields to another Student's fields
	 * Students are ordered by last name in alphabetical order.
	 * Then first name in alphabetical order.
	 * Then student id in ascending order.
	 * Returns -1 if this Student goes before o and 1 if this Student comes after
	 * @param o the other student being compared to
	 */
	@Override
	public int compareTo(Student o) {
		if(this.last.compareTo(o.getLast()) < 0) {
			return -1;
		}
		else if(this.last.compareTo(o.getLast()) > 0) {
			return 1;
		}
		else {
			if(this.first.compareTo(o.getFirst()) < 0) {
				return -1;
			}
			else if(this.first.compareTo(o.getFirst()) > 0) {
				return 1;
			}
			else {
				if(this.id < o.getId()) {
					return -1;
				}
				else if(this.id > o.getId()) {
					return 1;
				}
			}
		}
		return 0;
	}

	@Override
	public String toString() {
		return "first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID;
	}
	
	
	
	
	
	
	
}
