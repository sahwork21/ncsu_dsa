package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList. Checks the expected outputs of the List
 * abstract data type behaviors when using an array-based list data structure
 *
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 */
public class ArrayBasedListTest {
	/** The List that is going to be tested */
	private List<String> list;

	/**
	 * Create a new instance of an array-based list before each test case executes
	 */
	@Before
	public void setUp() {
		list = new ArrayBasedList<String>();
	}

	/**
	 * Test the output of the add(index, e) behavior, including expected exceptions
	 */
	@Test
	public void testAddIndex() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		// Use the statements above to help guide your test cases
		// for data structures: Start with an empty data structure, then
		// add an element and check the accessor method return values.
		// Then add another element and check again. Continue to keep checking
		// for special cases. For example, for an array-based list, you should
		// continue adding until you trigger a resize operation to make sure
		// the resize operation worked as expected.

		try {
			list.add(15, "fifteen");
			fail("An IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		try {
			list.add(-1, "fifteen");
			fail("An IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}

	}

	/**
	 * Test the output of the addLast behavior
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.addLast("one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		list.addLast("two");
		assertEquals(2, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());
	}

	/**
	 * Test the output of the last() behavior, including expected exceptions
	 */
	@Test
	public void testLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		try {
			list.last();
			fail("Should throw and IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Successfully caught IndexOutOfBoundsException
		}

		list.addLast("one");
		assertEquals(1, list.size());
		assertEquals("one", list.last());
		assertFalse(list.isEmpty());

		list.addLast("two");
		assertEquals(2, list.size());
		assertEquals("two", list.last());

		assertFalse(list.isEmpty());
	}

	/**
	 * Test the output of the addFirst behavior
	 */
	@Test
	public void testAddFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals("one", list.first());
		assertFalse(list.isEmpty());

		list.addFirst("two");
		assertEquals(2, list.size());

		assertEquals("two", list.first());
		assertEquals("one", list.get(1));
	}

	/**
	 * Test the output of the first() behavior, including expected exceptions
	 */
	@Test
	public void testFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		try {
			list.first();
			fail("Should throw and IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Successfully caught IndexOutOfBoundsException
		}

		list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals("one", list.first());
		assertFalse(list.isEmpty());

		list.addFirst("two");
		assertEquals(2, list.size());

		assertEquals("two", list.first());
		assertEquals("one", list.get(1));
	}

	/**
	 * Test the iterator behaviors, including expected exceptions
	 */
	@Test
	public void testIterator() {
		// Start with an empty list
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Create an iterator for the empty list
		Iterator<String> it = list.iterator();

		// Try different operations to make sure they work
		// as expected for an empty list (at this point)
		try {
			it.remove();

			fail("An IllegalStateException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		assertFalse(it.hasNext());

		// Now add an element
		list.addLast("one");

		// Use accessor methods to check that the list is correct
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals("one", list.get(0));

		// Create an iterator for the list that has 1 element
		it = list.iterator();

		// Try different iterator operations to make sure they work
		// as expected for a list that contains 1 element (at this point)
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertFalse(it.hasNext());
		try {
			it.next();
			fail("A NoSuchElementException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
		
		//Removes an element
		it.remove();
		
		assertEquals(0, list.size());
		//Try removing again
		try {
			it.remove();

			fail("An IllegalStateException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		
		//Remove from multiple elements
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		assertEquals(3, list.size());
		it = list.iterator();
		
		assertEquals("one", it.next());
		assertEquals("two", it.next());
		it.remove();
		
		assertEquals("one", list.first());
		assertEquals("three", list.last());
		assertEquals(2, list.size());
		try {
			it.remove();

			fail("An IllegalStateException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		assertEquals("three", it.next());
		
	}

	/**
	 * Test the output of the remove(index) behavior, including expected exceptions
	 */
	@Test
	public void testRemoveIndex() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());
		list.add(1, "two");
		list.add(2, "three");
		list.addLast("four");

		assertEquals("one", list.first());
		assertEquals("two", list.get(1));
		assertEquals("three", list.get(2));
		assertEquals("four", list.last());
		assertEquals(4, list.size());

		assertEquals("two", list.remove(1));
		assertEquals("one", list.first());
		assertEquals("three", list.get(1));
		assertEquals("four", list.last());

		assertEquals(3, list.size());

		assertEquals("one", list.remove(0));
		assertEquals("three", list.get(0));
		assertEquals("four", list.get(1));

		assertEquals(2, list.size());

		try {
			list.remove(99);
			fail("Should throw IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Threw an exception
		}

		assertEquals("four", list.remove(1));

		assertEquals("three", list.get(0));

		assertEquals(1, list.size());

		assertEquals("three", list.remove(0));
		assertEquals(0, list.size());

		try {
			list.remove(0);
			fail("Should throw IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Threw an exception
		}

	}

	/**
	 * Test the output of the removeFirst() behavior, including expected exceptions
	 */
	@Test
	public void testRemoveFirst() {
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		
		assertEquals("one", list.removeFirst());
		assertEquals(2, list.size());
		assertEquals("two", list.get(0));
		assertEquals("three", list.get(1));
		
		assertEquals("two", list.removeFirst());
		assertEquals(1, list.size());
		assertEquals("three", list.get(0));
		
		assertEquals("three", list.removeFirst());
		assertEquals(0, list.size());
		
	}

	/**
	 * Test the output of the removeLast() behavior, including expected exceptions
	 */
	@Test
	public void testRemoveLast() {
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		
		assertEquals("three", list.removeLast());
		assertEquals(2, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		
		assertEquals("two", list.removeLast());
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		
		
		assertEquals("one", list.removeLast());
		assertEquals(0, list.size());
		
	}

	/**
	 * Test the output of the set(index, e) behavior, including expected exceptions
	 */
	@Test
	public void testSet() {
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		
		assertEquals("two", list.set(1, "four"));
		
		assertEquals(3, list.size());
		assertEquals("one", list.get(0));
		assertEquals("four", list.get(1));
		assertEquals("three", list.get(2));
		
	}
}