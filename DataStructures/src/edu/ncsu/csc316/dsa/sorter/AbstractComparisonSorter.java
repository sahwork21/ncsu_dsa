package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * AbstractComparisonSorter defines the Natural Order of sorting for all sorters.
 * It also sets which comparator to use in sorting if one is provided by the client.
 * Abstract class so subclasses can define how they sort objects
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 *
 * @param <E> the type to be sorted
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {
	/**Comparator to define the order of sorting*/
	private Comparator<E> comparator;
	/**
	 * Empty constructor that sets no comparator
	 */
	public AbstractComparisonSorter() {
		this(null);
	}
	/**
	 * Constructor where the client can define the order they want objects to be compared in.
	 * @param comparator the comparator to be used when comparing objects during sorting
	 */
	public AbstractComparisonSorter(Comparator<E> comparator) {
		setComparator(comparator);
	}
	
	/**
	 * Sets the comparator to the provided comparator and if no comparator is provided defaults to Natural Order.
	 * @param comparator the comparator that will define what order to sort in
	 */
	private void setComparator(Comparator<E> comparator) {
		if(comparator == null) {
			this.comparator = new NaturalOrder();
		}
		else {
			this.comparator = comparator;
		}
	}
	
	/**
	 * Compare method allows subclasses to access the comparator compare method
	 * Decides which objects should go before each other
	 * @param one the object that will be compared to see if it goes before or after second
	 * @param two the object being compared to see where one should be for correct order
	 * @return -1 if one goes before two, 1 if one goes after two, and 0 if they are the same in order
	 */
	public int compare(E one, E two) {
        return comparator.compare(one,  two);
    }
	
	/**
	 * Natural Order is comparator that defines how objects should be sorted
	 * when no comparator is provided during construction
	 * @author Dr.King
	 * @author Sean Hinton (sahinto2)
	 *
	 */
	private class NaturalOrder implements Comparator<E> {
		/**
		 * Constructor to initialize the Natural Order
		 */
		private NaturalOrder() {
			//Empty since there are no parameters
		}
		
		/**
		 * Compares objects using their natural compareTo method
		 * @author Dr. King
		 * from the workshop 1 guide
		 */
		@Override
		public int compare(E one, E two) {
			return ((Comparable<E>) one).compareTo(two);
		}
		
		
	}
	
	
}
