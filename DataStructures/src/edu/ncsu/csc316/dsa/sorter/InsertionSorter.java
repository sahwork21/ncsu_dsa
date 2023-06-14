package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * Subclass of AbstractComparisonSorter which defines how objects should be sorted
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 * @param <E> the generic type for data
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Constructor for InsertionSorter where the client can specify which
	 * comparator to use instead
	 * of the natural order
	 * Sets using setComparator
	 * @param comparator the comparator to be used as the natural order
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Constructor for InsertionSorter that allows client to access its sort method
	 * Uses the natural order to compare
	 */
	public InsertionSorter() {
		this(null);
	}
	
	
	/**
	 * Sorts an array of items into order by using insertion sort
	 * @param items the items to be put in sorted order by insertion sort
	 */
	@Override
	public void sort(E[] items) {
		
		//Runs from the second element forward
		
		for(int i = 1; i <= items.length - 1; i++) {
			
			E x = items[i];
			int j = i - 1;
			
			//Keeps shifting elements right until insertion is reached.
			while(j >= 0 && compare(items[j], x) > 0) {
				items[j + 1] = items[j];
				j = j - 1;
			}
			
			items[j + 1] = x;
		}
		
		
	}
	
	

	

	
	
}
