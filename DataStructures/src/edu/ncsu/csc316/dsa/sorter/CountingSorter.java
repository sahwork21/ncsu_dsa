package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Empty constructor for initialization so the user can use this sort method
	 */
	public CountingSorter() {
		//Empty since this sort uses number sorting not comparison
	}
	/**
	 * Sorts the array using Counting sort
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sort(E[] items) {
		int min = items[0].getId();
		int max = items[0].getId();
		for(int i = 0; i <= items.length - 1; i++) {
			min = Math.min(items[i].getId(), min);
			max = Math.max(items[i].getId(), max);
		}
		
		//Find the difference of the elements
		int k = max - min + 1;
		
		int[] temp = new int[k];
		for(int i = 0; i <= items.length - 1; i++) {
			temp[items[i].getId() - min] = temp[items[i].getId() - min] + 1;
		}		
		
		for(int i = 1; i <= k - 1; i++) {
			temp[i] = temp[i - 1] + temp[i];
		}
		
		
		
		E[] ret = (E[])(new Identifiable[items.length]);
		
		for(int i = ret.length - 1; i >= 0; i--) {
			ret[temp[items[i].getId() - min] - 1] = items[i];
			temp[items[i].getId() - min] = temp[items[i].getId() - min] - 1;
		}
		
		
		//Copy in the elements
		for(int i = 0; i <= items.length - 1; i++) {
			items[i] = ret[i];
		}
	}

	
	
}
