package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Constructs a new MergeSorter with a specified custom Comparator
	 * 
	 * @param comparator a custom Comparator to use when sorting
	 */
	public MergeSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Constructs a new MergeSorter with comparisons based on the element's natural
	 * ordering
	 */
	public MergeSorter() {
		this(null);
	}

	/**
	 * Recursively sorts
	 */
	@Override
	public void sort(E[] items) {
		if (items.length < 2) {
			return;
		}
		int mid = items.length / 2;
		E[] left = Arrays.copyOfRange(items, 0, mid);
		E[] right = Arrays.copyOfRange(items, mid, items.length);

		sort(left);
		sort(right);

		merge(left, right, items);

	}

	/**
	 * Merges two arrays of elements together and put the elements into a third
	 * array
	 * 
	 * @param left  the left half of the array to be merged
	 * @param right the right half of the array to be merged
	 * @param list  the container for the sorted left and right halves merged
	 *              together
	 */
	private void merge(E[] left, E[] right, E[] list) {
		int leftIndex = 0;
		int rightIndex = 0;
		while (leftIndex + rightIndex < list.length) {
			if (rightIndex == right.length
					|| (leftIndex < left.length && compare(left[leftIndex], right[rightIndex]) < 0)) {
				list[leftIndex + rightIndex] = left[leftIndex];
				leftIndex++;
			} else {
				list[leftIndex + rightIndex] = right[rightIndex];
				rightIndex++;
			}
		}
	}


}