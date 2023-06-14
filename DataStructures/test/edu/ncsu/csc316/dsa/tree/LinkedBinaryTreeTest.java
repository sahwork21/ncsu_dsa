package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree Checks the expected outputs of the BinaryTree
 * abstract data type behaviors when using a linked data structure to store
 * elements
 *
 * @author Dr. King
 *
 */
public class LinkedBinaryTreeTest {
	/** Tree of Positions that are to be tested */
	private LinkedBinaryTree<String> tree;
	/** Position one in the tree */
	private Position<String> one;
	/** Position two in the tree */
	private Position<String> two;
	/** Position three in the tree */
	private Position<String> three;
	/** Position four in the tree */
	private Position<String> four;
	/** Position five in the tree */
	private Position<String> five;
	/** Position six in the tree */
	private Position<String> six;
	/** Position seven in the tree */
	private Position<String> seven;
	/** Position eight in the tree */
	private Position<String> eight;
	/** Position nine in the tree */
	private Position<String> nine;
	/** Position ten in the tree */
	private Position<String> ten;

	/**
	 * Helper class to create an invalid position to help test validate(p)
	 * 
	 * @param <E> generic type
	 */
	private class InvalidPosition<E> implements Position<E> {

		@Override
		public E getElement() {
			return null;
		}

	}

	/**
	 * Create a new instance of a linked binary tree before each test case executes
	 */
	@Before
	public void setUp() {
		tree = new LinkedBinaryTree<String>();
	}

	/**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
	private void createTree() {
		one = tree.addRoot("one");
		two = tree.addLeft(one, "two");
		three = tree.addRight(one, "three");
		six = tree.addLeft(two, "six");
		ten = tree.addRight(two, "ten");
		four = tree.addLeft(three, "four");
		seven = tree.addLeft(ten, "seven");
		five = tree.addRight(ten, "five");
		eight = tree.addLeft(four, "eight");
		nine = tree.addRight(four, "nine");
	}

	/**
	 * Test the output of the set(p,e) behavior
	 */
	@Test
	public void testSet() {
		createTree();

		assertEquals("one", tree.set(one, "set"));
		assertEquals("set", one.getElement());
		assertEquals("eight", tree.set(eight, "eighty"));
		assertEquals("eighty", eight.getElement());
		assertThrows(IllegalArgumentException.class, () -> tree.set(new InvalidPosition<String>(), "fail"));

	}

	/**
	 * Test the output of the size() behavior
	 */
	@Test
	public void testSize() {
		assertTrue(tree.isEmpty());
		createTree();
		assertEquals(10, tree.size());
		assertEquals("five", tree.remove(five));
		assertEquals(9, tree.size());

		five = tree.addRight(ten, "five");
		Position<String> eleven = tree.addLeft(five, "11");
		assertEquals(11, tree.size());
		assertEquals("11", eleven.getElement());

		assertThrows(IllegalArgumentException.class, () -> tree.remove(new InvalidPosition<String>()));

	}

	/**
	 * Test the output of the numChildren(p) behavior
	 */
	@Test
	public void testNumChildren() {
		createTree();
		assertEquals(0, tree.numChildren(seven));
		assertEquals(1, tree.numChildren(three));
		assertEquals(2, tree.numChildren(four));

		assertEquals("nine", tree.remove(nine));
		assertEquals(9, tree.size());
		assertEquals(1, tree.numChildren(four));

		assertEquals("eight", tree.remove(eight));
		assertEquals(8, tree.size());
		assertEquals(0, tree.numChildren(four));
		assertThrows(IllegalArgumentException.class, () -> tree.numChildren(new InvalidPosition<String>()));
	}

	/**
	 * Test the output of the parent(p) behavior
	 */
	@Test
	public void testParent() {
		createTree();
		assertNull(tree.parent(one));
		assertEquals(one, tree.parent(two));
		assertEquals(four, tree.parent(eight));
		assertEquals(four, tree.parent(nine));
		assertThrows(IllegalArgumentException.class, () -> tree.parent(new InvalidPosition<String>()));
	}

	/**
	 * Test the output of the sibling behavior
	 */
	@Test
	public void testSibling() {
		createTree();
		assertNull(tree.sibling(one));
		assertEquals(six, tree.sibling(ten));
		assertEquals(ten, tree.sibling(six));
		// Remove a left sibling
		assertEquals("six", tree.remove(six));
		assertNull(tree.sibling(ten));
		assertEquals(9, tree.size());
		// Remove a right sibling
		assertEquals(eight, tree.sibling(nine));
		assertEquals(nine, tree.sibling(eight));
		assertEquals("nine", tree.remove(nine));
		assertNull(tree.sibling(eight));
		assertEquals(8, tree.size());

		assertThrows(IllegalArgumentException.class, () -> tree.sibling(new InvalidPosition<String>()));
	}

	/**
	 * Test the output of the isInternal behavior
	 */
	@Test
	public void testIsInternal() {
		createTree();

		assertFalse(tree.isInternal(eight));
		assertTrue(tree.isInternal(one));
		assertTrue(tree.isInternal(two));
		assertTrue(tree.isInternal(ten));
		assertTrue(tree.isInternal(four));
		assertFalse(tree.isInternal(five));
		assertEquals("eight", tree.remove(eight));
		assertEquals("nine", tree.remove(nine));
		assertEquals(8, tree.size());

		assertFalse(tree.isInternal(four));
		assertThrows(IllegalArgumentException.class, () -> tree.isInternal(new InvalidPosition<String>()));
	}

	/**
	 * Test the output of the isLeaf behavior
	 */
	@Test
	public void isLeaf() {
		createTree();
		assertFalse(tree.isLeaf(one));
		assertFalse(tree.isLeaf(ten));
		assertTrue(tree.isLeaf(eight));
		assertTrue(tree.isLeaf(nine));
		assertEquals("eight", tree.remove(eight));
		assertEquals("nine", tree.remove(nine));
		assertEquals(8, tree.size());
		assertFalse(tree.isInternal(four));
		assertTrue(tree.isLeaf(four));
		assertThrows(IllegalArgumentException.class, () -> tree.isLeaf(new InvalidPosition<String>()));

	}

	/**
	 * Test the output of the isRoot(p)
	 */
	@Test
	public void isRoot() {
		createTree();
		
		assertTrue(tree.isRoot(one));
		assertFalse(tree.isRoot(eight));
		tree = new LinkedBinaryTree<String>();
		ten = tree.addRoot("ten");
		assertTrue(tree.isRoot(ten));
	}

	/**
	 * Test the output of the preOrder traversal behavior
	 *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
	 */
	@Test
	public void testPreOrder() {
		createTree();
		Iterator<Position<String>> it = tree.preOrder().iterator();
		assertTrue(it.hasNext());
		assertEquals("one", it.next().getElement());
		assertThrows(UnsupportedOperationException.class, () -> it.remove());
		assertTrue(it.hasNext());
		assertEquals("two", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("six", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("ten", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("seven", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("five", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("three", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("four", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("eight", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("nine", it.next().getElement());
		assertFalse(it.hasNext());
		assertThrows(NoSuchElementException.class, () -> it.next());
		assertThrows(UnsupportedOperationException.class, () -> it.remove());
	}

	/**
	 * Test the output of the postOrder traversal behavior
	 */
	@Test
	public void testPostOrder() {
		createTree();
		Iterator<Position<String>> it = tree.postOrder().iterator();
		assertTrue(it.hasNext());
		
		assertEquals("six", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("seven", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("five", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("ten", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("two", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("eight", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("nine", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("four", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("three", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("one", it.next().getElement());
		assertFalse(it.hasNext());
		assertThrows(NoSuchElementException.class, () -> it.next());
		assertThrows(UnsupportedOperationException.class, () -> it.remove());
	}

	/**
	 * Test the output of the inOrder traversal behavior
	 */
	@Test
	public void testInOrder() {
		createTree();
		Iterator<Position<String>> it = tree.inOrder().iterator();
		assertTrue(it.hasNext());
		
		assertEquals("six", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("two", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("seven", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("ten", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("five", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("one", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("eight", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("four", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("nine", it.next().getElement());
		assertEquals("three", it.next().getElement());
		assertFalse(it.hasNext());
		assertThrows(NoSuchElementException.class, () -> it.next());
		assertThrows(UnsupportedOperationException.class, () -> it.remove());
		
	}

	/**
	 * Test the output of the Binary Tree ADT behaviors on an empty tree
	 */
	@Test
	public void testEmptyTree() {
		one = tree.addRoot("one");
		assertEquals(1, tree.size());
		assertNull(tree.sibling(one));
		assertEquals("one", tree.remove(one));
		assertNull(tree.root());
		assertTrue(tree.isEmpty());
		Iterator<Position<String>> it = tree.inOrder().iterator();
		assertFalse(it.hasNext());
		assertEquals("LinkedBinaryTree[\n]", tree.toString());
		
	}

	/**
	 * Test the output of the levelOrderTraversal. Breadth first search
	 *
	 *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine   
	 */
	@Test
	public void testLevelOrder() {
		createTree();
		Iterator<Position<String>> it = tree.levelOrder().iterator();
		assertTrue(it.hasNext());
		assertEquals("one", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("two", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("three", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("six", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("ten", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("four", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("seven", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("five", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("eight", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("nine", it.next().getElement());
	}

	/**
	 * Test the output of the addLeft(p,e) behavior, including expected exceptions
	 * 
	 *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine   
	 */
	@Test
	public void testAddLeft() {
		one = tree.addRoot("one");
		assertEquals("two",  tree.addLeft(one, "two").getElement());
		assertEquals("two", tree.left(one).getElement());
		assertEquals(2, tree.size());
		assertThrows(IllegalArgumentException.class, () -> tree.addLeft(one, "three"));
	}

	/**
	 * Test the output of the addRight(p,e) behavior, including expected exceptions
	 */
	@Test
	public void testAddRight() {
		one = tree.addRoot("one");
		assertEquals("two",  tree.addRight(one, "two").getElement());
		assertEquals("two", tree.right(one).getElement());
		assertEquals(2, tree.size());
		assertThrows(IllegalArgumentException.class, () -> tree.addRight(one, "three"));
	}

	/**
	 * Test the output of the remove(p) behavior, including expected exceptions
	 */
	@Test
	public void testRemove() {
		createTree();
		Exception e = assertThrows(IllegalArgumentException.class, () -> tree.remove(ten));
		assertEquals("The node has two children", e.getMessage());
		
		assertEquals("seven", tree.remove(seven));
		assertEquals(9, tree.size());
		assertNull(tree.sibling(five));
		assertEquals("ten", tree.remove(ten));
		assertEquals(six, tree.sibling(ten));
		assertEquals(8, tree.size());
		
		assertEquals("eight", tree.remove(eight));
		tree.remove(four);
		
		//Remove the whole right side
		tree = new LinkedBinaryTree<String>();
		createTree();
		tree.remove(nine);
		tree.remove(four);
		tree.remove(three);
		assertEquals(7, tree.size());
		tree.remove(eight);
		assertEquals(6, tree.size());
		assertEquals("LinkedBinaryTree[\none\n two\n  six\n  ten\n   seven\n   five\n]", tree.toString());
		
	}
	/**
	 * Tests toString
	 *         			one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine   
	 */
	@Test
	public void testToString() {
		createTree();
		assertEquals("LinkedBinaryTree[\n"
				+ "one\n two\n  six\n  ten\n   seven\n   five\n"
				+ " three\n  four\n   eight\n   nine\n]", tree.toString());
		
		tree.remove(eight);
		tree.remove(four);
		assertEquals("LinkedBinaryTree[\n"
				+ "one\n two\n  six\n  ten\n   seven\n   five\n"
				+ " three\n  nine\n]", tree.toString());
	}
}