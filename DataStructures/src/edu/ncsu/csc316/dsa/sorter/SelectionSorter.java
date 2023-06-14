package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * Subclass of AbstractComparisonSorter which defines how objects should be sorted
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Constructor where the client can specify if they want data sorted by id or gpa
	 * @param comparator the specified order objects should be sorted
	 */
	public SelectionSorter(Comparator<E> comparator) {
		super(comparator);
	}
	/**
	 * Empty constructor that uses the Natural Order for sorting
	 */
	public SelectionSorter() {
		this(null);
	}
	
	
	@Override
	public void sort(E[] items) {
		// TODO Auto-generated method stub
		for(int i = 0; i <= items.length - 1; i++) {
			int min = i;
			for(int j = i + 1; j <= items.length - 1; j++) {
				if(compare(items[j], items[min]) < 0) {
					min = j;
				}
			}
			
			if(i != min) {
				E temp = items[i];
				items[i] = items[min];
				items[min] = temp;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
}
