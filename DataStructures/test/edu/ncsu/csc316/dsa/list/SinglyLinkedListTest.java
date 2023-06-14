package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods for a SinglyLinkedList
 * 
 * @author Sean Hinton (sahinto2)
 *
 */
public class SinglyLinkedListTest {

	/** The SinglyLinkedList that will be tested */
	private AbstractList<Integer> list;

	/**
	 * Sets list to be a new list before every test
	 */
	@Before
	public void setUp() {
		list = new SinglyLinkedList<Integer>();

		assertEquals(0, list.size());
	}

	/**
	 * Tests the add methods
	 */
	@Test
	public void testAddAndGet() {
		// Tests adding
		try {
			list.add(1, 1);
			fail("IndexOutOfBounds expected");
		} catch (IndexOutOfBoundsException e) {
			// Successful catch of correct exception
		}

		try {
			list.add(-1, 1);
			fail("IndexOutOfBounds expected");
		} catch (IndexOutOfBoundsException e) {
			// Successful catch of correct exception
		}

		try {
			list.last();
			fail("IndexOutOfBounds expected");
		} catch (IndexOutOfBoundsException e) {
			// Successful catch of correct exception
		}

		try {
			list.first();
			fail("IndexOutOfBounds expected");
		} catch (IndexOutOfBoundsException e) {
			// Successful catch of correct exception
		}
		assertEquals(0, list.size());

		list.add(0, 1);
		list.add(1, 2);
		list.add(2, 4);
		list.add(2, 3);
		assertEquals(4, list.last().intValue());
		assertEquals(1, list.first().intValue());
		assertEquals(1, list.get(0).intValue());
		assertEquals(2, list.get(1).intValue());
		assertEquals(3, list.get(2).intValue());
		assertEquals(4, list.get(3).intValue());
		assertEquals(4, list.size());

		list.addFirst(0);
		list.addLast(5);
		assertEquals(5, list.last().intValue());
		assertEquals(0, list.first().intValue());
		assertEquals(0, list.get(0).intValue());
		assertEquals(1, list.get(1).intValue());
		assertEquals(2, list.get(2).intValue());
		assertEquals(3, list.get(3).intValue());
		assertEquals(4, list.get(4).intValue());
		assertEquals(5, list.get(5).intValue());
		assertEquals(6, list.size());

		list.add(3, 33);
		assertEquals(2, list.get(2).intValue());
		assertEquals(33, list.get(3).intValue());
		assertEquals(3, list.get(4).intValue());
		assertEquals(7, list.size());

		try {
			list.get(7);
			fail("IndexOutOfBounds expected");
		} catch (IndexOutOfBoundsException e) {
			// Successful catch of correct exception
		}
		try {
			list.get(-1);
			fail("IndexOutOfBounds expected");
		} catch (IndexOutOfBoundsException e) {
			// Successful catch of correct exception
		}
	}

	/**
	 * Tests remove
	 */
	@Test
	public void testRemove() {
		// Tests adding
		try {
			list.remove(0);
			fail("IndexOutOfBounds expected");
		} catch (IndexOutOfBoundsException e) {
			// Successful catch of correct exception
		}

		try {
			list.remove(-1);
			fail("IndexOutOfBounds expected");
		} catch (IndexOutOfBoundsException e) {
			// Successful catch of correct exception
		}

		list.add(0, 0);
		list.add(1, 1);
		list.add(2, 2);
		list.add(3, 3);
		list.add(4, 4);
		list.add(5, 5);
		list.add(6, 6);
		assertEquals(7, list.size());

		// Remove from the end
		assertEquals(6, list.remove(6).intValue());
		assertEquals(6, list.size());
		assertEquals(5, list.last().intValue());

		assertEquals(5, list.removeLast().intValue());
		assertEquals(5, list.size());
		assertEquals(4, list.last().intValue());

		// Remove from front
		assertEquals(0, list.remove(0).intValue());
		assertEquals(4, list.size());
		assertEquals(1, list.first().intValue());

		assertEquals(1, list.removeFirst().intValue());
		assertEquals(3, list.size());
		assertEquals(2, list.first().intValue());

		// Remove from middle
		assertEquals(3, list.remove(1).intValue());
		assertEquals(2, list.size());
		assertEquals(2, list.get(0).intValue());
		assertEquals(4, list.get(1).intValue());

		assertEquals(2, list.remove(0).intValue());
		assertEquals(4, list.remove(0).intValue());

		assertEquals(0, list.size());

	}

	/**
	 * Tests set
	 */
	@Test
	public void testSet() {
		

		// Tests adding
		try {
			list.set(0, 0);
			fail("IndexOutOfBounds expected");
		} catch (IndexOutOfBoundsException e) {
			// Successful catch of correct exception
		}

		try {
			list.set(-1, 0);
			fail("IndexOutOfBounds expected");
		} catch (IndexOutOfBoundsException e) {
			// Successful catch of correct exception
		}

		list.add(0, 0);
		list.add(1, 1);
		list.add(2, 2);
		list.add(3, 3);
		list.add(4, 4);
		list.add(5, 5);
		list.add(6, 6);
		
		list.set(3, 33);
		assertEquals(33, list.get(3).intValue());
		
		list.set(6, 66);
		assertEquals(66, list.get(6).intValue());

	}
	
	/**
	 * Tests iterator methods
	 */
	@Test
	public void testIterator() {
		Iterator<Integer> it = list.iterator();
		
		//Try moving on an empty list
		assertFalse(it.hasNext());
		try {
			it.next();
			fail("NoSuchElementExceptionexpected");
		} catch(NoSuchElementException e) {
			//Successful catch of the correct expression
		}
		
		list.add(0, 1);
		it = list.iterator();
		assertTrue(it.hasNext());
		assertEquals(1, it.next().intValue());
		assertFalse(it.hasNext());
		
		//Try removing
		it.remove();
		assertEquals(0, list.size());
		assertFalse(it.hasNext());
		
		//Traverse and remove multiple elements
		list.add(0, 0);
		list.add(1, 1);
		list.add(2, 2);
		list.add(3, 3);
		list.add(4, 4);
		list.add(5, 5);
		list.add(6, 6);
		
		it = list.iterator();
		//Remove from front
		assertEquals(0, it.next().intValue());
		assertEquals(0, list.first().intValue());
		it.remove();
		assertEquals(1, list.first().intValue());
		assertEquals(6, list.size());
		
		try {
			it.remove();
			fail("IllegalStateException expected");
		} catch(IllegalStateException e) {
			//Successful catch of the exception
		}
		
		//Remove a middle element
		assertEquals(1, it.next().intValue());
		assertEquals(2, it.next().intValue());
		assertEquals(3, it.next().intValue());
		assertEquals(4, it.next().intValue());
		
		it.remove();
		assertEquals(5, list.get(3).intValue());
		assertEquals(3, list.get(2).intValue());
		
		while(it.hasNext()) {
			it.next();
		}
		
		assertEquals(6, list.last().intValue());
		//Remove tail
		it.remove();
		assertEquals(5, list.last().intValue());
		
		list = new SinglyLinkedList<Integer>();
		
		it = list.iterator();
		assertFalse(it.hasNext());
		list.add(0, 1);
		it = list.iterator();
		it.next();
		it.remove();
		assertFalse(it.hasNext());
		
		list.add(0, 1);
		list.add(1, 2);
		list.add(2, 3);
		it = list.iterator();
		//Check middle
		assertEquals(1, it.next().intValue());
		assertEquals(2, it.next().intValue());
		it.remove();
		
		assertEquals(3, it.next().intValue());
		
		
		
	}
	
	/**
	 * Tests last methods
	 */
	@Test
	public void testAddLast() {
		list.addLast(1);
		assertEquals(1, list.last().intValue());
		
		assertEquals(1, list.remove(0).intValue());
		
		list.add(0, 1);
		assertEquals(1, list.get(0).intValue());
		assertEquals(1, list.size());
		
		Iterator<Integer> it = list.iterator();
		it.next();
		it.remove();
		assertFalse(it.hasNext());
		
		
		
		
	}
}
