package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 *
 */
public class BinarySearchTreeMapTest {
	/**The tree that is being tested*/
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        tree.put(3, "three");
        assertEquals(2, tree.size());
        assertEquals(1, (int)tree.root().getElement().getKey());
        assertEquals("three", tree.get(3));
        tree.put(-1, "negative");
        assertEquals(3, tree.size());
        assertEquals("negative", tree.get(-1));
        
        assertEquals("three", tree.put(3, "new"));
        assertEquals("new", tree.get(3));
        
        
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        assertEquals("one", tree.get(1));
        tree.put(3, "three");
        assertEquals(2, tree.size());
        
        assertEquals("one", tree.get(1));
        assertEquals("three", tree.get(3));
        tree.put(-1, "negative");
        assertEquals(3, tree.size());
        assertEquals("negative", tree.get(-1));
        assertEquals("one", tree.get(1));
        assertEquals("three", tree.get(3));
        assertNull(tree.get(0));
        assertNull(tree.get(100));
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        
        tree.put(1, "one");
        tree.put(0, "zero");
        tree.put(4, "four");
        tree.put(2, "two");
        tree.put(5, "five");
        assertEquals(5, tree.size());
        assertEquals("zero", tree.get(0));
        assertEquals("one", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("four", tree.get(4));
        
        assertEquals("five", tree.get(5));
        
        //Remove root and two should become the successor to the root
        assertEquals("one", tree.remove(1));
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(4, tree.size());
        assertEquals("zero", tree.get(0));
        
        assertEquals("two", tree.get(2));
        assertEquals("four", tree.get(4));
        
        assertEquals("five", tree.get(5));
        
        //Remove four it has only a right child five
        assertEquals("five", tree.remove(5));
        assertEquals(3, tree.size());
        assertEquals("zero", tree.get(0));
        
        assertEquals("two", tree.get(2));
        assertEquals("four", tree.get(4));
        
        
        tree.put(3, "three");
        //Remove four now that it has a left child only
        assertEquals("four", tree.remove(4));
        assertEquals("zero", tree.get(0));
        assertEquals("three", tree.get(3));
        assertEquals("two", tree.get(2));
        assertNull(tree.get(4));
        
        
        // You should create tests to ensure removing works
        // in all special cases:
        //   - removing the root
        //   - removing from a node that has only a left child
        //   - removing from a node that has only a right child
        //   - removing from a node that has both children
        // etc.
    }
    
    /**
     * Tests the inorder traversal. No null nodes
     */
    @Test
    public void testEntrySet() {
    	tree.put(5, "five");
    	tree.put(1, "one");
    	tree.put(2, "two");
    	tree.put(4, "four");
    	tree.put(3, "three");
    	tree.put(7, "seven");
    	tree.put(8, "eight");
    	tree.put(9, "nine");
    	tree.put(6, "six");
    	tree.put(10, "ten");
    	
    	
    	Iterator<Entry<Integer, String>> it = tree.entrySet().iterator();
    	assertTrue(it.hasNext());
    	
    	assertEquals("one", it.next().getValue());
    	assertEquals("two", it.next().getValue());
    	assertEquals("three", it.next().getValue());
    	assertEquals("four", it.next().getValue());
    	assertEquals("five", it.next().getValue());
    	assertEquals("six", it.next().getValue());
    	assertEquals("seven", it.next().getValue());
    	assertEquals("eight", it.next().getValue());
    	assertEquals("nine", it.next().getValue());
    	assertEquals("ten", it.next().getValue());
    	
    	assertFalse(it.hasNext());
    }
}