package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) (new Object[capacity]);
        size = 0;
    }

    
	@Override
	public void add(int index, E element) {
		checkIndexForAdd(index);
		
		
		ensureCapacity(size() + 1);
		
		for(int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		
		data[index] = element;
		
		size++;
		
	}

	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
	
    
	@Override
	public E get(int index) {
		checkIndex(index);
		return data[index];
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		E ret = data[index];
		for(int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		
		size--;
		return ret;
	}

	@Override
	public E set(int index, E element) {
		checkIndex(index);
		E ret = data[index];
		data[index] = element;
		return ret;
	}

	@Override
	public int size() {
		return size;
	}

	
	/**
	 * Returns a new ElementIterator object to the caller
	 * @return a new ElementIterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	
	
	
    
	/**
	 * Iterates over the array of data only going forward
	 * @author Dr. King
	 * @author Sean Hinton (sahinto2)
	 *
	 */
    private class ElementIterator implements Iterator<E> {
    	/**The position of the element that next returned*/
    	private int position;
    	/**Checks if removing at position is allowed*/
    	private boolean removeOK;
    	
    	/**
    	 * Constructs a new Element Iterator with position at -1 and removeOK false
    	 */
    	public ElementIterator() {
    		this.position = -1;
    		this.removeOK = false;
    	}
    	
    	/**
    	 * If the next method has another element after position true is returned.
    	 * Otherwise false is returned.
    	 * @return true if there is another element to iterate false if not
    	 */
		@Override
		public boolean hasNext() {
			if(position + 1 >= size) {
				return false;
			}
			return true;
		}
		
		/**
		 * Moves the iterator forward one element if there is another element to move to.
		 * Returns the data at the next position and makes removeOK true.
		 * @throws NoSuchElementException if there is no next element
		 */
		@Override
		public E next() {
			if(position + 1 >= size) {
				throw new NoSuchElementException();
			}
			
			position = position + 1;
			removeOK = true;
			return data[position];
		}
		
		/**
		 * Removes the last element that next returned.
		 * Cannot be called when removeOK is false.
		 * Uses the ArrayBasedList's remove method for removal of the element at position.
		 * @throws IllegalStateException if removeOK is false because next has not been called.
		 */
		@Override
		public void remove() {
			if(!removeOK) {
				throw new IllegalStateException();
			}
			ArrayBasedList.this.remove(position);
			removeOK = false;
			position--;
		}
    	
    }
}