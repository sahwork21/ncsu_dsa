package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * The Linked Stack is implemented as a singly-linked list data structure to
 * support efficient, O(1) worst-case Stack abstract data type behaviors.
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 * @param <E> the type of elements stored in the stack
 */
public class LinkedStack<E> extends AbstractStack<E> {

    /** Delegate to our existing singly linked list class **/
    private SinglyLinkedList<E> list;

    /**
     * Construct a new singly-linked list to use when modeling the last-in-first-out
     * paradigm for the stack abstract data type.
     */
    public LinkedStack() {
        list = new SinglyLinkedList<E>();
    }

    /**
     * Adds a new element to the top of stack by adding to the first index of the underlying list
     * @param element the element to be added to the top of the stack
     */
	@Override
	public void push(E element) {
		list.addFirst(element);
		
	}

	/**
	 * Removes the element at the top of the stack which is at the first index of the underlying list
	 * @return the top element of the stack
	 * @throws EmptyStackException when the size of the stack is zero
	 */
	@Override
	public E pop() {
		if(size() == 0) {
			throw new EmptyStackException();
		}
		return list.removeFirst();
	}

	/**
	 * Gets the element at the top of the stack but does not remove the top element
	 * @return the top element of the stack 
	 * @throws EmptyStackException when the size of the stack is zero
	 */
	@Override
	public E top() {
		if(size() == 0) {
			throw new EmptyStackException();
		}
		return list.first();
	}

	/**
	 * Returns the number of elements in the Stack.
	 * Gets the size of the underlying SinglyLinkedList that contains the elements of the stack.
	 * @return the size of the underlying list containing the stack's elements.
	 */
	@Override
	public int size() {
		return list.size();
	}
    
    
}