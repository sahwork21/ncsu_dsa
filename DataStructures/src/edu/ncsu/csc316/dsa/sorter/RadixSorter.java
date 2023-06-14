package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {
	/**
	 * Empty constructor for initialization so the user can use this sort method
	 */
	public RadixSorter() {
		//Empty since this sort uses number sorting not comparison
	}

	
	/**
	 * Sorts objects using the radix sorting algorithm
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sort(E[] items) {
		
		int k = 0;
		for(int i = 0; i <= items.length - 1; i++) {
			k = Math.max(k, items[i].getId());
		}
		
		double digits = Math.ceil(Math.log(k + 1) / Math.log(10));
		
		int p = 1;
		
		for(int j = 1; j <= digits; j++) {
			int[] binary = new int[10];
			for(int i = 0; i <= items.length - 1; i++) {
				binary[(items[i].getId() / p) % 10] = binary[(items[i].getId() / p) % 10] + 1;
			}
			
			for(int i = 1; i <= 9; i++) {
				binary[i] = binary[i - 1] + binary[i];
			}
			
			E[] ret = (E[]) new Identifiable[items.length];
			
			for(int i = ret.length - 1; i >= 0; i--) {
				ret[binary[ (items[i].getId() / p) % 10 ] - 1] = items[i];
				binary[ (items[i].getId() / p) % 10] = binary[ (items[i].getId() / p) % 10] - 1;
			}
			
			for(int i = 0; i <= ret.length - 1; i++) {
				items[i] = ret[i];
			}
			
			p = p * 10;
			
		}
		
	}

}
