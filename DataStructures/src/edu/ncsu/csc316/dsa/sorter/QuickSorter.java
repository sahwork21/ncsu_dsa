package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	/** Keeps track of the clients selected pivot index selector */
	private PivotSelector selector;

	/**
	 * Pivot selection strategy that uses the element at the first index each time a
	 * pivot must be selected
	 */
	public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();

	/**
	 * Pivot selection strategy that uses the element at the last index each time a
	 * pivot must be selected
	 */
	public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();

	/**
	 * Pivot selection strategy that uses the element at the middle index each time
	 * a pivot must be selected
	 */
	public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();

	/**
	 * Pivot selection strategy that uses the element at a randomly-chosen index
	 * each time a pivot must be selected
	 */
	public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();

	/**
	 * Constructs a new QuickSorter with a provided custom Comparator and a
	 * specified PivotSelector strategy
	 * 
	 * @param comparator a custom comparator to use when sorting
	 * @param selector   the pivot selection strategy to use when selecting pivots
	 */
	public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
		super(comparator);
		setSelector(selector);
	}

	/**
	 * Constructs a new QuickSorter using the natural ordering of elements. Pivots
	 * are selected using the provided PivotSelector strategy
	 * 
	 * @param selector the pivot selection strategy to use when selecting pivots
	 */
	public QuickSorter(PivotSelector selector) {
		this(null, selector);
	}

	/**
	 * Constructs a new QuickSorter with a provided custom Comparator and the
	 * default random pivot selection strategy
	 * 
	 * @param comparator a custom comparator to use when sorting
	 */
	public QuickSorter(Comparator<E> comparator) {
		this(comparator, null);
	}

	/**
	 * Constructs a new QuickSorter that uses an element's natural ordering and uses
	 * the random pivot selection strategy
	 */
	public QuickSorter() {
		this(null, null);
	}

	/**
	 * Sets the value of the pivot selector that the client provided Defaults to
	 * RandomElementSelector if no selector is provided
	 * 
	 * @param selector the selector the client wants to use for the pivot index
	 */
	private void setSelector(PivotSelector selector) {
		if (selector == null) {
			this.selector = new RandomElementSelector();
		} else {
			this.selector = selector;
		}
	}

	/**
	 * Sorts using QuickSort algorithm and selects the pivot index based on the
	 * client's selection of selector Starts quickSort with low at 0 and high at
	 * length - 1
	 * 
	 * @param items the items to be sorted
	 */
	@Override
	public void sort(E[] items) {
		quickSort(items, 0, items.length - 1);
	}

	/**
	 * Recursive sorting call to quickSort a list of items and then sort the
	 * elements to the left of the pivot and right of the pivot Keeps sorting while
	 * low is less than high
	 * 
	 * @param items the arrays to sort
	 * @param low   the lowest index of the array
	 * @param high  the highest index of the array
	 */
	private void quickSort(E[] items, int low, int high) {
		if (low < high) {
			int pivotIndex = partition(items, low, high);
			quickSort(items, low, pivotIndex - 1);
			quickSort(items, pivotIndex + 1, high);

		}
	}

	/**
	 * Moves all the value less than the pivot element to the left of the pivot
	 * element and the greater elements to the right Puts the pivot element into the
	 * correct place Uses partitionHelper to move elements to their correct halves
	 * Uses swap to move the pivot element to the high index for sorting
	 * 
	 * @param items the items to sort
	 * @param low   the lowest index to sort
	 * @param high  the highes index to sort
	 * @return the index of the pivot element after sorting
	 */
	private int partition(E[] items, int low, int high) {
		int pivotIndex = selector.selectPivot(low, high);
		
		// Swap pivotIndex with high
		swap(items, pivotIndex, high);
		return partitionHelper(items, low, high);
	}

	/**
	 * Puts the pivot element into its sorted location and moves everything less
	 * than pivot to the left and everything greater to the right
	 * Compares elements to find where the pivot index belongs
	 * @param items the items to be sorted
	 * @param low the lower bound of the index
	 * @param high the upper bound of the index, where the pivot element is
	 * @return the index of the pivot element
	 */
	private int partitionHelper(E[] items, int low, int high) {
		E pivot = items[high];
		int pivotIndex = low;
		for(int j = low; j <= high - 1; j++) {
			if(compare(items[j], pivot) <= 0) {
				swap(items, j, pivotIndex);
				pivotIndex++;
			}
		}
		
		//Put the pivot element in its sorted location
		swap(items, pivotIndex, high);
		return pivotIndex;
	}

	private void swap(E[] items, int pivotIndex, int high) {
		E temp = items[pivotIndex];
		items[pivotIndex] = items[high];
		items[high] = temp;
	}

	/**
	 * Defines the behaviors of a PivotSelector
	 * 
	 * @author Dr. King
	 *
	 */
	private interface PivotSelector {
		/**
		 * Returns the index of the selected pivot element
		 * 
		 * @param low  - the lowest index to consider
		 * @param high - the highest index to consider
		 * @return the index of the selected pivot element
		 */
		int selectPivot(int low, int high);
	}

	/**
	 * FirstElementSelector chooses the first index of the array as the index of the
	 * pivot element that should be used when sorting
	 * 
	 * @author Dr. King
	 *
	 */
	public static class FirstElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return low;
		}
	}

	/**
	 * LastElementSelector picks the last index as the pivot element when sorting
	 * 
	 * @author Dr. King
	 * @author Sean Hinton (sahinto2)
	 *
	 */
	public static class LastElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return high;
		}
	}

	/**
	 * MiddleElementSelector picks the middle element of the array as the pivot
	 * index
	 * 
	 * @author Dr. King
	 * @author Sean Hinton (sahinto2)
	 *
	 */
	public static class MiddleElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return (high + low) / 2;
		}
	}

	/**
	 * RandomElementSelector chooses a random index from low to high as the pivot
	 * index High must be added by 1 as Math.random excludes the highest value
	 * 
	 * @author Dr. King
	 *
	 */
	public static class RandomElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return (int) (Math.random() * (high - low) + low);
		}
	}

}
