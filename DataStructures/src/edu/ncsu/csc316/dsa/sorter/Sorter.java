package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 * @param <E> the generic type for an array of objects
 */
public interface Sorter<E> {
	
	/**
	 * Sorts an array using various algorithms
     * to put a random order of numbers in to ascending order.
	 * @param items the items to be sorted
	 */
	void sort(E[] items);
}
