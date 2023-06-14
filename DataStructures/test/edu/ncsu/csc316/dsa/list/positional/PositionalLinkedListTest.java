package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList. Checks the expected outputs of the
 * Positional List abstract data type behaviors when using an doubly-linked
 * positional list data structure
 *
 * @author Dr. King
 *
 */
public class PositionalLinkedListTest {
	/** The list for testing */
	private PositionalList<String> list;

	/**
	 * Create a new instance of an positional linked list before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		list = new PositionalLinkedList<String>();

	}

	/**
	 * Test the output of the first() behavior, including expected exceptions
	 */
	@Test
	public void testFirst() {
		assertNull(list.first());
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		assertNull(list.first());

		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals(first, list.first());

		Position<String> newFirst = list.addFirst("zero");
		assertEquals(2, list.size());
		assertEquals(newFirst, list.first());

	}

	/**
	 * Test the output of the last() behavior, including expected exceptions
	 */
	@Test
	public void testLast() {
		assertNull(list.first());
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		assertNull(list.last());

		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());
		assertEquals(first, list.last());

		Position<String> two = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(two, list.last());

		assertEquals(first, list.first());
	}

	/**
	 * Test the output of the addFirst(element) behavior
	 */
	@Test
	public void testAddFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());

		Position<String> two = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(two, list.last());

		assertEquals(first, list.first());

	}

	/**
	 * Test the output of the addLast(element) behavior
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());

		Position<String> two = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(two.getElement(), list.last().getElement());
		assertEquals(first, list.first());

	}

	/**
	 * Test the output of the before(position) behavior, including expected
	 * exceptions
	 */
	@Test
	public void testBefore() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());

		Position<String> three = list.addLast("three");
		assertEquals(2, list.size());
		assertEquals(three.getElement(), list.last().getElement());
		assertEquals(first, list.first());

		Position<String> two = list.addBefore(three, "two");

		assertEquals(three, list.last());

		assertEquals(first, list.first());
		assertEquals(two, list.before(three));

		Position<String> zero = list.addBefore(first, "zero");
		assertEquals(zero, list.first());
		assertEquals(zero, list.before(first));
		assertEquals(first, list.before(two));
		assertEquals(two, list.before(three));
		assertNull(list.before(zero));
	}

	/**
	 * Test the output of the after(position) behavior, including expected
	 * exceptions
	 */
	@Test
	public void testAfter() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> one = list.addFirst("one");

		assertEquals(one, list.first());
		assertEquals(1, list.size());

		Position<String> two = list.addAfter(one, "two");
		assertEquals(two, list.after(one));
		assertEquals(one, list.first());
		assertEquals(two, list.last());
		assertNull(list.after(two));

	}

	/**
	 * Test the output of the addBefore(position, element) behavior, including
	 * expected exceptions
	 */
	@Test
	public void testAddBefore() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> two = list.addFirst("two");

		Position<String> one = list.addBefore(two, "one");

		assertEquals(2, list.size());
		assertEquals(one, list.before(two));
		Position<String> zero = list.addBefore(one, "zero");

		assertEquals(3, list.size());
		assertEquals(zero, list.before(one));
		assertEquals(zero, list.first());

	}

	/**
	 * Test the output of the addAfter(position, element) behavior, including
	 * expected exceptions
	 */
	@Test
	public void testAddAfter() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> zero = list.addFirst("zero");

		Position<String> one = list.addAfter(zero, "one");

		assertEquals(2, list.size());
		assertEquals(one, list.after(zero));
		Position<String> two = list.addAfter(one, "two");

		assertEquals(3, list.size());
		assertEquals(one, list.after(zero));
		assertEquals(two, list.after(one));
		assertEquals(zero, list.first());
		assertNull(list.after(two));
	}

	/**
	 * Test the output of the set(position, element) behavior, including expected
	 * exceptions
	 */
	@Test
	public void testSet() {
		Position<String> one = list.addFirst("one");
		Position<String> two = list.addAfter(one, "two");
		Position<String> four = list.addAfter(two, "four");
		Position<String> three = list.addBefore(four, "three");

		assertEquals(4, list.size());
		assertEquals(one, list.first());
		assertEquals(two, list.after(one));
		assertEquals(three, list.after(two));
		assertEquals(four, list.after(three));

		assertEquals("two", list.set(two, "twotwo"));

		assertEquals("twotwo", list.after(one).getElement());
	}

	/**
	 * Test the output of the remove(position) behavior, including expected
	 * exceptions
	 */
	@Test
	public void testRemove() {
		Position<String> one = list.addFirst("one");
		Position<String> two = list.addAfter(one, "two");
		Position<String> four = list.addAfter(two, "four");
		Position<String> three = list.addBefore(four, "three");

		assertEquals(4, list.size());
		assertEquals(one, list.first());
		assertEquals(two, list.after(one));
		assertEquals(three, list.after(two));
		assertEquals(four, list.after(three));

		assertEquals("four", list.remove(four));

		assertEquals(3, list.size());
		assertEquals(one, list.first());
		assertEquals(two, list.after(one));
		assertEquals(three, list.after(two));
		assertNull(list.after(three));

		assertEquals(three, list.last());

		assertEquals("one", list.remove(one));

		assertEquals(2, list.size());

		assertNull(list.before(two));
		assertEquals(three, list.after(two));
		assertNull(list.after(three));
		assertEquals(two, list.first());

		Position<String> twoB = list.addAfter(two, "twoB");
		assertEquals(3, list.size());
		assertEquals(two, list.first());
		assertEquals(twoB, list.after(two));
		assertEquals(three, list.last());

		assertEquals("twoB", list.remove(twoB));

		assertEquals(2, list.size());
		assertEquals(two, list.first());
		assertEquals(three, list.after(two));
		assertEquals(two, list.before(three));
		assertEquals(three, list.last());

	}

	/**
	 * Test the output of the iterator behavior for elements in the list, including
	 * expected exceptions
	 */
	@Test
	public void testIterator() {

		assertEquals(0, list.size());
		Position<String> one = list.addFirst("one");
		Position<String> two = list.addLast("two");
		Position<String> three = list.addLast("three");
		assertEquals(3, list.size());
	
		Iterator<String> it = list.iterator();
		assertTrue(it.hasNext());
		try {
			it.remove();
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
			// Successful catch of the exception
		}
		assertEquals(one.getElement(), it.next());
		assertEquals(two.getElement(), it.next());
		it.remove();
		assertEquals(2, list.size());
		assertEquals(three.getElement(), it.next());
		assertFalse(it.hasNext());
		try {
			it.next();
			fail("NoSuchElementException expected");
		} catch (NoSuchElementException e) {
			// Successful catch of the exception
		}
	}

	/**
	 * Test the output of the positions() behavior to iterate through positions in
	 * the list, including expected exceptions
	 */
	@Test
	public void testPositions() {
		assertEquals(0, list.size());
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());

		Iterator<Position<String>> it = list.positions().iterator();
		assertTrue(it.hasNext());
		try {
			it.remove();
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
			// Successful catch of the exception
		}
		assertEquals(first, it.next());
		assertEquals(second, it.next());
		assertEquals(third, it.next());
		try {
			it.next();
			fail("NoSuchElementException expected");
		} catch (NoSuchElementException e) {
			// Successful catch of the exception
		}
		assertFalse(it.hasNext());

		Iterator<String> eit = list.iterator();
		assertTrue(eit.hasNext());
		try {
			eit.remove();
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
			// Successful catch of the exception
		}
		assertEquals("one", eit.next());
		assertEquals("two", eit.next());
		assertEquals("three", eit.next());

		try {
			eit.next();
			fail("NoSuchElementException expected");
		} catch (NoSuchElementException e) {
			// Successful catch of the exception
		}

		assertFalse(eit.hasNext());

		eit.remove();
		assertEquals(2, list.size());
		assertEquals(first, list.first());
		assertEquals(second, list.last());
	}

}