package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;


/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley &#38; Sons, 2014
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

	/** A dummy/sentinel node representing at the front of the list **/
	private PositionalNode<E> front;

	/** A dummy/sentinel node representing at the end/tail of the list **/
	private PositionalNode<E> tail;

	/** The number of elements in the list **/
	private int size;

	/**
	 * Constructs an empty positional linked list
	 */
	public PositionalLinkedList() {
		front = new PositionalNode<E>(null);
		tail = new PositionalNode<E>(null, null, front);
		front.setNext(tail);
		size = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	@Override
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

	/**
	 * Helper method for adding between two nodes and returns the PositionalNode as
	 * a Position.
	 * 
	 * @param element the element that is contained at this position
	 * @param next    the next position in the list
	 * @param prev    the previous position in the list
	 * @return the PositionalNode that is created containing the data
	 */
	private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
		size++;
		PositionalNode<E> ret = new PositionalNode<E>(element, next, prev);
		prev.setNext(ret);
		next.setPrevious(ret);
		return ret;
	}

	@Override
	public Position<E> addAfter(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);

		return addBetween(element, node.getNext(), node);

	}

	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		return addBetween(element, node, node.getPrevious());
	}

	@Override
	public Position<E> addFirst(E element) {

		return addBetween(element, front.getNext(), front);

	}

	@Override
	public Position<E> addLast(E element) {
		return addBetween(element, tail, tail.getPrevious());
	}

	@Override
	public Position<E> after(Position<E> p) {

		PositionalNode<E> node = validate(p);
		if (node.getNext() == tail) {
			return null;
		}
		return node.getNext();
	}

	@Override
	public Position<E> before(Position<E> p) {

		PositionalNode<E> node = validate(p);
		if (node.getPrevious() == front) {
			return null;
		}
		return node.getPrevious();
	}

	@Override
	public Position<E> first() {
		if (size() == 0) {
			return null;
		}
		return front.getNext();
	}

	@Override
	public boolean isEmpty() {
		if (front.next == tail) {
			return true;
		}
		return false;
	}

	@Override
	public Position<E> last() {
		if (size() == 0) {
			return null;
		}
		return tail.getPrevious();
	}

	

	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> node = validate(p);
		node.getPrevious().setNext(node.getNext());
		node.getNext().setPrevious(node.getPrevious());
		size--;
		return node.getElement();
	}

	@Override
	public E set(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		E ret = node.getElement();
		node.setElement(element);
		return ret;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Safely casts a Position, p, to be a PositionalNode.
	 * 
	 * @param p the position to cast to a PositionalNode
	 * @return a reference to the PositionalNode
	 * @throws IllegalArgumentException if p is null, or if p is not a valid
	 *                                  PositionalNode
	 */
	private PositionalNode<E> validate(Position<E> p) {
		if (p instanceof PositionalNode) {
			return (PositionalNode<E>) p;
		}
		throw new IllegalArgumentException("Position is not a valid positional list node.");
	}

	/**
	 * PositionalNode encapsulates the data that each position contains.
	 * PositionalNodes also contain pointers to the previous and next node in the
	 * list.
	 * 
	 * @author Dr. King
	 * @author Sean Hinton (sahinto2)
	 *
	 * @param <E> the generic type that this class encapsulates
	 */
	private static class PositionalNode<E> implements Position<E> {
		/** The data this node contains */
		private E element;
		/** The next node in the list */
		private PositionalNode<E> next;
		/** The previous node in the list */
		private PositionalNode<E> previous;

		/**
		 * Constructor for a PositionalNode without a next or previous node in the list.
		 * 
		 * @param value the value to be encapsulated
		 */
		public PositionalNode(E value) {
			this(value, null);
		}

		/**
		 * Constructor for a PositionalNode without a previous node in the list.
		 * 
		 * @param value the value to be encapsulated
		 * @param next  the next node in the list.
		 */
		public PositionalNode(E value, PositionalNode<E> next) {
			this(value, next, null);
		}

		/**
		 * Constructor for a PositionalNode that uses its setter methods for fields. Has
		 * a next and previos pointer
		 * 
		 * @param value the value to be encapsulated
		 * @param next  the next node in the list
		 * @param prev  the previous node in the list
		 */
		public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
			setElement(value);
			setNext(next);
			setPrevious(prev);
		}

		/**
		 * Sets the previous node in the list to the specified node
		 * 
		 * @param prev the new previous node in the list
		 */
		public void setPrevious(PositionalNode<E> prev) {
			previous = prev;
		}

		/**
		 * Gets the previous node in the list
		 * 
		 * @return previous the previous node before this position
		 */
		public PositionalNode<E> getPrevious() {
			return previous;
		}

		/**
		 * Sets the next node in the list to the specified node
		 * 
		 * @param next the new next node in the list
		 */
		public void setNext(PositionalNode<E> next) {
			this.next = next;
		}

		/**
		 * Gets the next node in the list
		 * 
		 * @return next the next node after this position
		 */
		public PositionalNode<E> getNext() {
			return next;
		}

		@Override
		public E getElement() {
			return element;
		}

		/**
		 * Sets the data that is contained by this node
		 * 
		 * @param element the data to be contained by this node
		 */
		public void setElement(E element) {
			this.element = element;
		}
	}

	/**
	 * PositionIterator is the underlying class that iterates over Positions for the
	 * ElementIterator.
	 * 
	 * @author Dr. King
	 * @author Sean Hinton (sahinto2)
	 *
	 */
	private class PositionIterator implements Iterator<Position<E>> {
		/** The current Position of this iterator on the list */
		private Position<E> current;
		/**
		 * Flag allowing removal if it is true. Becomes true if next is called before
		 * remove
		 */
		private boolean removeOK;

		public PositionIterator() {
			current = new PositionalNode<E>(null);
			current = front;
			removeOK = false;
		}

		@Override
		public boolean hasNext() {
			if(validate(current).getNext() == tail) {
				return false;
			}
			return true;
			
		}

		@Override
		public Position<E> next() {
			if (validate(current).getNext() == tail) {
				throw new NoSuchElementException();
			}
			current = validate(current).getNext();
			removeOK = true;
			return current;

		}

		@Override
		public void remove() {
			//Delegates to the PositionalLinkedList for removal
			if(!removeOK) {
				throw new IllegalStateException();
			}
			PositionalLinkedList.this.remove(current);
			removeOK = false;
		}
		

	}

	/**
	 * ElementIterator is used by the client to iterate over elements and remove
	 * elements. Delegates the next and remove method to the PositionIterator.
	 * 
	 * @author Dr. King
	 * @author Sean Hinton (sahinto2)
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		/** The PositionIterator that iterates over positions and modifies the list */
		private Iterator<Position<E>> it;

		public ElementIterator() {
			it = new PositionIterator();
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public E next() {
			return it.next().getElement();
		}

		@Override
		public void remove() {
			it.remove();
		}
	}

	/**
	 * PositionIterable allows the client to iterate over Positions rather than elements
	 * @author Dr. King
	 *
	 */
	private class PositionIterable implements Iterable<Position<E>> {

		
		private PositionIterable() {
			
		}
		
		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
		
	}

}