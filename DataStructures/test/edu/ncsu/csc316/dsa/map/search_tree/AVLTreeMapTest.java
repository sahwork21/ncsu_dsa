package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 *
 */
public class AVLTreeMapTest {
	/**AVL tree being tested*/
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        //Straight line restructure going right
        tree.put(1, "one");
        tree.put(2, "two");
        assertEquals(2, tree.size());
        assertEquals(1, (int)tree.root().getElement().getKey());
        assertEquals(2, (int) tree.right(tree.root()).getElement().getKey());
        tree.put(3, "three");
        assertEquals(3, tree.size());
        
        //2 has become the root and it has 1 and 3 as children
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        
        //Create a right then left and imbalance on a = 3, b = 5, c = 4
        tree.put(5, "five");
        tree.put(4, "four");
        assertEquals(5, tree.size());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(3, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        
        tree = new AVLTreeMap<Integer, String>();
        
        //Straight line left with 1 as z
        tree.put(1, "one");
        tree.put(0, "zero");
        tree.put(-1, "negative");
        assertEquals(0, (int)tree.root().getElement().getKey());
        assertEquals(-1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.right(tree.root()).getElement().getKey());
        
        //Create a left right restructure
        tree.put(-3, "three");
        tree.put(-2, "two");
        //-2 is now the root of the left subtree
        assertEquals(-2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(-3, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(-1, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        
        tree.put(-3, "new");
        
        assertEquals(5, tree.size());
        assertEquals(0, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(-2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(-3, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(-1, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals("new", tree.get(-3));
        
        
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
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
        assertTrue(tree.isEmpty());
        tree.put(4, "four");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(8, "eight");
        tree.put(5, "five");
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(6, "six");
        
        Iterator<Entry<Integer, String>> it = tree.entrySet().iterator();
        
        assertEquals("one", it.next().getValue());
        assertEquals("two", it.next().getValue());
        assertEquals("three", it.next().getValue());
        assertEquals("four", it.next().getValue());
        assertEquals("five", it.next().getValue());
        assertEquals("six", it.next().getValue());
        assertEquals("seven", it.next().getValue());
        assertEquals("eight", it.next().getValue());
        assertFalse(it.hasNext());
        
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        //Make 3 the root
        tree.put(2, "two");
        tree.put(1, "one");
        tree.put(3, "three");
        tree.put(4, "four");
        assertEquals(4, tree.size());
        //Remove 1 to unbalance
        assertEquals("one", tree.remove(1));
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        
        //Right left restructure the right side
        tree.put(1, "one");
        tree.put(10, "ten");
        tree.put(7, "seven");
        tree.remove(2);
        //Must restructure the right 7 is now the root of the subtree
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(10, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(4, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        
        
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());     
    }
}