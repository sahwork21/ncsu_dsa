package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 *
 */
public class RedBlackTreeMapTest {
	/**Tree for testing*/
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(5, "five");
        tree.put(9, "nine");
        //Fix a double red with black uncle
        tree.put(7, "seven");
        //7 is now the root of the right subtree
        assertEquals(7, (int) tree.root().getElement().getKey());
        
        tree.put(6, "six");
        //double red on 9
        tree.put(8, "eight");
        
        
        //Get a double red to propagate
        tree.put(12, "twelve");
        tree.put(13, "thirteen");
        assertEquals(7, tree.size());
        //Make sure in order is preserved
        Iterator<Entry<Integer, String>> it = tree.entrySet().iterator();
        assertEquals("five", it.next().getValue());
        assertEquals("six", it.next().getValue());
        assertEquals("seven", it.next().getValue());
        assertEquals("eight", it.next().getValue());
        assertEquals("nine", it.next().getValue());
        assertEquals("twelve", it.next().getValue());
        assertEquals("thirteen", it.next().getValue());
        assertFalse(it.hasNext());
        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
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
         tree.put(9, "nine");
         //Fix a double red with black uncle
         tree.put(7, "seven");
         //7 is now the root of the right subtree
         assertEquals(7, (int) tree.root().getElement().getKey());
         
         tree.put(6, "six");
         //double red on 9
         tree.put(8, "eight");
         
         
         //Get a double red to propagate
         tree.put(12, "twelve");
         tree.put(13, "thirteen");
         assertEquals(7, tree.size());
         assertEquals("five", tree.get(5));
         assertEquals("twelve", tree.get(12));
         assertEquals("eight", tree.put(8, "new"));
         assertEquals("new", tree.get(8));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(5, "five");
        tree.put(9, "nine");
        //Fix a double red with black uncle
        tree.put(7, "seven");
        //7 is now the root of the right subtree
        assertEquals(7, (int) tree.root().getElement().getKey());
        
        tree.put(6, "six");
        
        //Black sibling when removing 5
        tree.remove(5);
        tree.put(10, "ten");
        //Remove with a black sibling and red child
        tree.remove(6);
        assertEquals(9, (int) tree.root().getElement().getKey());
        
        tree = new RedBlackTreeMap<Integer, String>();
        tree.put(5, "five");
        tree.put(9, "nine");
        //Fix a double red with black uncle
        tree.put(7, "seven");
        //7 is now the root of the right subtree
        assertEquals(7, (int) tree.root().getElement().getKey());
        
        tree.put(6, "six");
        //double red on 9
        tree.put(8, "eight");
        
        
        //Get a double red to propagate
        tree.put(12, "twelve");
        tree.put(13, "thirteen");
        tree.put(15, "fifteen");
        tree.put(14, "fourteen");
        
        tree.remove(8);
        tree.remove(13);
        tree.remove(6);
        tree.remove(5);
        
        tree = new RedBlackTreeMap<Integer, String>();
        tree.put(5, "five");
        tree.put(9, "nine");
        //Fix a double red with black uncle
        tree.put(7, "seven");
        tree.put(9, "nine");
        tree.put(10, "ten");
        tree.put(11, "eleven");
        tree.put(12, "twelve");
        
        tree.remove(5);
        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
}