package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 *
 */
public class SplayTreeMapTest {
	/**The tree being tested*/
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(5, "five");
        tree.put(6, "six");
        //Check zig
        assertEquals(6, (int)tree.root().getElement().getKey());
        //Check zig zig
        tree.put(3, "three");
        assertEquals(3, (int)tree.root().getElement().getKey());
        
        //check zig zag
        tree.put(4, "four");
        assertEquals(4, (int)tree.root().getElement().getKey());
        tree.put(20, "twenty");
        tree.put(3, "e");
        assertEquals(3, (int)tree.root().getElement().getKey());
        tree.put(31, "three one");
        tree.put(15, "fifteen");
        assertEquals(15, (int)tree.root().getElement().getKey());
        
        
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
    
    /**
     * Test the output of the get(k) behavior
     */ 
    @Test
    public void testGet() {
    	assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(5, "five");
        tree.put(6, "six");
        //Check zig
        assertEquals(6, (int)tree.root().getElement().getKey());
        //Check zig zig
        tree.put(3, "three");
        assertEquals(3, (int)tree.root().getElement().getKey());
        
        //check zig zag
        tree.put(4, "four");
        assertEquals(4, (int)tree.root().getElement().getKey());
        tree.put(20, "twenty");
        tree.put(31, "three one");
        tree.put(15, "fifteen");
        assertEquals(15, (int)tree.root().getElement().getKey());
        assertEquals(7, tree.size());
        assertEquals("three", tree.get(3));
        assertEquals("four", tree.get(4));
        assertEquals("five", tree.get(5));
        assertEquals("six", tree.get(6));
        assertEquals("fifteen", tree.get(15));
        assertEquals("twenty", tree.get(20));
        assertEquals("three one", tree.get(31));
        
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        //Splay parents
    	tree.put(7, "seven");
    	tree.put(5, "five");
    	tree.put(6, "six");
    	//Check that root special case of no splays
    	tree.remove(6);
    	assertEquals(7, (int)tree.root().getElement().getKey());
    	tree.put(10, "ten");
    	tree.put(8, "eight");
    	tree.put(6, "six");
    	//Remove 8 find the inorder successor 10 splay to the top
    	tree.remove(8);
    	assertEquals(10, (int)tree.root().getElement().getKey());
    	
    	tree = null;
    	tree = new SplayTreeMap<Integer, String>();
    	//Zig zig remove
    	tree.put(5, "five");
    	tree.put(8, "eight");
    	tree.put(4, "four");
    	tree.put(10, "ten");
    	tree.remove(4);
    	assertEquals(10, (int)tree.root().getElement().getKey());
    	
    	tree.remove(1);
    	
    	
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
    
    /**
     * Test random series of operations
     */
    @Test
    public void testSeries() {
    	tree.put(10, "ten");
    	tree.put(3, "three");
    	tree.put(7, "seven");
    	tree.put(4, "four");
    	tree.remove(10);
    	tree.put(1, "one");
    	tree.put(15, "fifteen");
    	tree.remove(7);
    	
    	//Does it maintain in order 
    	Iterator<Entry<Integer, String>> it = tree.entrySet().iterator();
    	
    	assertEquals("one", it.next().getValue());
    	assertEquals("three", it.next().getValue());
    	assertEquals("four", it.next().getValue());
    	assertEquals("fifteen", it.next().getValue());
    	assertFalse(it.hasNext());
    }
    
    /**
     * Test null removes and accesses
     */
    @Test
    public void testSentinels() {
    	tree.put(5, "five");
    	tree.remove(6);
    	tree.put(8, "eight");
    	assertNull(tree.remove(9));
    	assertEquals(2, tree.size());
    	assertNull(tree.remove(4));
    	assertEquals(2, tree.size());
    	tree.put(4, "four");
    	
    	
    	assertEquals(3, tree.size());
    	tree.remove(10);
    	assertEquals(8, (int) tree.root().getElement().getKey());
    	tree.put(1, "one");
    	tree.put(3, "three");
    	tree.put(4, "four");
    	tree.put(10, "ten");
    	assertNull(tree.remove(11));
    	assertNull(tree.remove(7));
    	
    	tree = new SplayTreeMap<Integer, String>();
    	assertTrue(tree.isEmpty());
    	tree.put(4, "four");
    	tree.put(5, "five");
    	tree.put(7, "s");
    	assertEquals("four", tree.remove(4));
    	assertEquals("s", tree.remove(7));
    	assertEquals("five", tree.remove(5));
    	assertTrue(tree.isEmpty());
    	
    	
    	
    }
    
    /**
     * Test zig zig on leaf dummy nodes
     */
    @Test
    public void testZigZig() {
    	//Force a null pointer somehow
    	assertTrue(tree.isEmpty());
    	tree.put(5, "five");
    	tree.put(10, "ten");
    	assertNull(tree.remove(4));
    	assertEquals(5, (int) tree.root().getElement().getKey());
    	assertNull(tree.remove(11));
    	tree.put(15, "fifteen");
    	assertEquals("fifteen", tree.remove(15));
    	assertNull(tree.remove(15));
    	tree.put(11, "eleven");
    	tree.put(8, "eight");
    	tree.put(20, "twenty");
    	
    }
    
   
}