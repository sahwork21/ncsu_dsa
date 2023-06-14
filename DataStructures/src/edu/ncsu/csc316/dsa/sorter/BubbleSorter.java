package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;


/**
 * Sorts an array of objects using the bubble sorting algorithm
 * @author Dr. King (pseudocode for sort)
 * @author Sean Hinton (sahinto2)
 *
 * @param <E> type that will be sorted
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	

	/**
	 * Empty constructor that uses the natural order to sort objects
	 */
	public BubbleSorter() {
		this(null);
	}
	
	/**
	 * Main path of construction where the AbstractComparisonSorter sets the order of comparison
	 * @param comparator the object that decides the order of comparison by this sorter
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	
	/**
	 * Sorts an array of objects using bubble sort
	 */
	@Override
	public void sort(E[] items) {
		boolean moveFlag = true;
		while(moveFlag) {
			moveFlag = false;
			for(int i = 1; i <= items.length - 1; i++) {
				if(compare(items[i], items[i - 1]) < 0) {
					E temp = items[i - 1];
					items[i - 1] = items[i];
					items[i] = temp;
					moveFlag = true;
				}
			}
		}
	}
}
