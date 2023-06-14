package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	/** A reference to the dummy/sentinel node at the front of the list **/
	private LinkedListNode<E> front;

	/** A reference to the last/final node in the list **/
	private LinkedListNode<E> tail;

	/** The number of elements stored in the list **/
	private int size;

	/**
	 * Constructs an empty singly-linked list
	 */
	public SinglyLinkedList() {
		front = new LinkedListNode<E>(null);
		tail = null;
		size = 0;
	}

	
	@Override
	public void add(int index, E element) {
		checkIndexForAdd(index);

		LinkedListNode<E> current = front;

		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		current.setNext(new LinkedListNode<E>(element, current.next));

		// Changes tail pointer if adding to the end
		if (index == size()) {
			tail = current.getNext();
		}
		size++;

	}

	@Override
	public E get(int index) {
		checkIndex(index);
		LinkedListNode<E> current = front;
		for (int i = 0; i <= index; i++) {
			current = current.getNext();
		}
		return current.getElement();

	}

	@Override
	public E remove(int index) {
		checkIndex(index);

		LinkedListNode<E> current = front;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		E ret = current.getNext().getElement();
		current.setNext(current.getNext().getNext());
		// Changes tail pointer if removing from the end
		if (index == size() - 1) {
			tail = current;
		}
		if(tail == front) {
			tail = null;
		}
		size--;
		return ret;

	}

	@Override
	public E set(int index, E element) {
		checkIndex(index);
		LinkedListNode<E> current = front;
		for (int i = 0; i <= index; i++) {
			current = current.getNext();
		}

		E ret = current.getElement();
		current.setElement(element);
		return ret;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
	 * runtime. Returns the tail node instead of iterating through the entire list.
	 */
	@Override
	public E last() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("The list is empty");
		}
		return tail.getElement();
	}

	/**
	 * {@inheritDoc} For this singly-linked list, addLast(element) behavior has O(1)
	 * worst-case runtime. Adds a new ListNode after the tail node and changes the
	 * tail node reference.
	 */
	@Override
	public void addLast(E element) {
		if (size() == 0) {
			
			tail = new LinkedListNode<E>(element);
			front.setNext(tail);
			size++;
		} else {
			tail.setNext(new LinkedListNode<E>(element));
			tail = tail.getNext();
			size++;
		}
	}

	/**
	 * A LinkedListNode is a node that encapsulates an element in the list. Each
	 * ListNode contains some data that can be accessed through the node. Each
	 * ListNode also has a pointer to the next node in the list or no pointer if it
	 * is the tail node.
	 * 
	 * @author Dr. King
	 * @author Sean Hinton (sahinto2)
	 *
	 * @param <E> the generic type the LinkedListNode encapsulates
	 */
	private static class LinkedListNode<E> {
		/** The data this ListNode encapsulates */
		private E data;
		/** The next ListNode in the sequence of nodes */
		private LinkedListNode<E> next;

		/**
		 * Constructor for a LinkedListNode with null next node.
		 * 
		 * @param data the data that this ListNode will encapsulate
		 */
		public LinkedListNode(E data) {
			this(data, null);
		}

		/**
		 * Constructor for a LinkedListNode with another node after it.
		 * 
		 * @param data the data that this ListNode will encapsulate
		 * @param next the next LinkedListNode in the list of nodes.
		 */
		public LinkedListNode(E data, LinkedListNode<E> next) {
			setElement(data);
			setNext(next);
		}

		/**
		 * Gets the data that this ListNode encapsulates.
		 * 
		 * @return data the data the ListNode is encapsulating.
		 */
		public E getElement() {
			return data;
		}

		/**
		 * Sets the encapsulated data by this ListNode to the provided data.
		 * 
		 * @param data the data that the ListNode will encapsulate.
		 */
		public void setElement(E data) {
			this.data = data;
		}

		/**
		 * Gets the next ListNode in the list and returns the node.
		 * 
		 * @return next the next ListNode in the list of nodes.
		 */
		public LinkedListNode<E> getNext() {
			return next;
		}

		/**
		 * Sets the next ListNode this ListNode will point to.
		 * 
		 * @param next the next ListNode in the list
		 */
		public void setNext(LinkedListNode<E> next) {
			this.next = next;
		}

	}

	/**
	 * ElementIterator is an iterator that moves forward and traverse a LinkedList
	 * of elements. ElementIterator can move forward using the next method which
	 * updates pointers in the iterator. It also can only remove an element only
	 * after calling next at least one time before each remove.
	 * 
	 * @author Dr. King
	 * @author Sean Hinton (sahinto2)
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		/**
		 * Keep track of the next node that will be processed
		 */
		private LinkedListNode<E> current;

		/**
		 * Keep track of the node that was processed on the last call to 'next'
		 */
		private LinkedListNode<E> previous;

		/**
		 * Keep track of the previous-previous node that was processed so that we can
		 * update 'next' links when removing
		 */
		private LinkedListNode<E> previousPrevious;

		/**
		 * Keep track of whether it's ok to remove an element (based on whether next()
		 * has been called immediately before remove())
		 */
		private boolean removeOK;

		/**
		 * Construct a new element iterator where the cursor is initialized to the
		 * beginning of the list.
		 */
		public ElementIterator() {
			current = front.next;
			previous = front;
			previousPrevious = front;
			removeOK = false;
		}

		@Override
		public boolean hasNext() {
			if (current == null) {
				return false;
			}
			return true;
		}

		@Override
		public E next() {
			if (current == null) {
				throw new NoSuchElementException();
			}

			current = current.getNext();
			previousPrevious = previous;
			previous = previous.getNext();

			removeOK = true;
			return previous.getElement();

		}

		@Override
		public void remove() {
			if (!removeOK) {
				throw new IllegalStateException();
			}
			
			if (previous == tail) {
				tail = previousPrevious;
			}
			if(tail == front) {
				tail = null;
			}
			
			previousPrevious.setNext(previous.getNext());
			previous = previousPrevious;
			size--;
			removeOK = false;

		}
	}

}