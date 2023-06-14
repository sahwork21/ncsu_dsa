package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King
 *
 */
public class LinearProbingHashMapTest {
	/**The map being tested*/
    private Map<Integer, String> map;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        map = new LinearProbingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        
        assertNull(map.put(1, "one"));
        assertNull(map.put(3, "three"));
        assertEquals(2, map.size());
        //Cause a collision with three
        assertNull(map.put(10, "ten"));
        assertEquals(3, map.size());
        assertEquals("ten", map.put(10, "newten"));
        assertEquals(3, map.size());
        assertEquals("newten", map.get(10));
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        //Put a bunch of elements and see if we can get them
        assertNull(map.put(1, "one"));
        assertNull(map.put(8, "eight"));
        assertNull(map.put(2, "two"));
        assertEquals("one", map.get(1));
        assertEquals("eight", map.get(8));
        assertEquals("two", map.get(2));
        assertNull(map.put(6, "six"));
        //Now check if things match after growing
        assertEquals("one", map.get(1));
        assertEquals("eight", map.get(8));
        assertEquals("two", map.get(2));
        assertEquals("six", map.get(6));
        assertEquals(4, map.size());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(1, "one"));
        assertNull(map.put(8, "eight"));
        assertNull(map.put(2, "two"));
        assertNull(map.put(6, "six"));
        //Remove 8 and check if 2 is still accessible
        assertEquals("eight", map.remove(8));
        assertEquals(3, map.size());
        
        assertEquals("two", map.get(2));
        assertEquals("two", map.put(2, "newtwo"));
        assertEquals("newtwo", map.get(2));
        
        //Now insert something there
        assertNull(map.put(15, "fifteen"));
        assertEquals(4, map.size());
        assertNull(map.remove(7));
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	assertTrue(map.isEmpty());
        assertNull(map.put(1, "one"));
        assertNull(map.put(8, "eight"));
        assertNull(map.put(2, "two"));
        assertNull(map.put(6, "six"));
        //Remove 8 and check if 2 is still accessible
        assertEquals("eight", map.remove(8));
        assertEquals(3, map.size());
        
        assertEquals("two", map.get(2));
        assertEquals("two", map.put(2, "newtwo"));
        assertEquals("newtwo", map.get(2));
        
        //Now insert something there
        assertNull(map.put(15, "fifteen"));
        assertEquals(4, map.size());
        //The order should be 6, 1, 2, 15
        Iterator<Integer> it = map.iterator();
        assertEquals(6, (int)it.next());
        assertEquals(1, (int)it.next());
        assertEquals(2, (int)it.next());
        assertEquals(15, (int)it.next());
        try {
        	it.next();
        	fail("No NoSuchElementException");
        } catch(NoSuchElementException e) {
        	//Successful catch
        }
        
        try {
        	it.remove();
        	fail("No UnsupportedOperationException");
        } catch(UnsupportedOperationException e) {
        	//Successful catch
        }
    }
    
    /**
     * Test the output of the entrySet() behavior
     */     
    @Test
    public void testEntrySet() {
    	assertTrue(map.isEmpty());
        assertNull(map.put(1, "one"));
        assertNull(map.put(8, "eight"));
        assertNull(map.put(2, "two"));
        assertNull(map.put(6, "six"));
        //Remove 8 and check if 2 is still accessible
        assertEquals("eight", map.remove(8));
        assertEquals(3, map.size());
        
        assertEquals("two", map.get(2));
        assertEquals("two", map.put(2, "newtwo"));
        assertEquals("newtwo", map.get(2));
        
        //Now insert something there
        assertNull(map.put(15, "fifteen"));
        assertEquals(4, map.size());
        //The order should be 6, 1, 2, 15
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();     
        assertEquals(6, (int)it.next().getKey());
        assertEquals(1, (int)it.next().getKey());
        assertEquals(2, (int)it.next().getKey());
        assertEquals(15, (int)it.next().getKey());
        try {
        	it.next();
        	fail("No NoSuchElementException");
        } catch(NoSuchElementException e) {
        	//Successful catch
        }
        
        try {
        	it.remove();
        	fail("No UnsupportedOperationException");
        } catch(UnsupportedOperationException e) {
        	//Successful catch
        }
    }
    
    /**
     * Test the output of the values() behavior
     */  
    @Test
    public void testValues() {
    	assertTrue(map.isEmpty());
        assertNull(map.put(1, "one"));
        assertNull(map.put(8, "eight"));
        assertNull(map.put(2, "two"));
        assertNull(map.put(6, "six"));
        //Remove 8 and check if 2 is still accessible
        assertEquals("eight", map.remove(8));
        assertEquals(3, map.size());
        
        assertEquals("two", map.get(2));
        assertEquals("two", map.put(2, "newtwo"));
        assertEquals("newtwo", map.get(2));
        
        //Now insert something there
        assertNull(map.put(15, "fifteen"));
        assertEquals(4, map.size());
        //The order should be 6, 1, 2, 15
        
        Iterator<String> it = map.values().iterator();
        assertEquals("six", it.next());
        assertEquals("one", it.next());
        assertEquals("newtwo", it.next());
        assertEquals("fifteen", it.next());
        try {
        	it.next();
        	fail("No NoSuchElementException");
        } catch(NoSuchElementException e) {
        	//Successful catch
        }
        
        try {
        	it.remove();
        	fail("No UnsupportedOperationException");
        } catch(UnsupportedOperationException e) {
        	//Successful catch
        }
    }
    /**
     * Test the methods on an actual hashmap with random values during MAD hashing
     */
    @Test
    public void testRandomHashMap() {
    	map = new LinearProbingHashMap<Integer, String>();
    	assertNull(map.put(1, "one"));
    	assertNull(map.put(8, "eight"));
    	assertNull(map.put(5, "five"));
    	assertEquals(3, map.size());
    	
    	assertEquals("five", map.put(5, "5"));
    	assertEquals("5", map.get(5));
    	assertEquals(3, map.size());
    	assertEquals("one", map.remove(1));
    	assertEquals(2, map.size());
    	
    	map = new LinearProbingHashMap<Integer, String>(false);
    	
    	assertNull(map.put(1, "one"));
    	assertNull(map.put(8, "eight"));
    	assertNull(map.put(5, "five"));
    	assertEquals(3, map.size());
    	
    	assertEquals("five", map.put(5, "5"));
    	assertEquals("5", map.get(5));
    	assertEquals(3, map.size());
    	assertEquals("one", map.remove(1));
    	assertEquals(2, map.size());
    	
    	
    	map = new LinearProbingHashMap<Integer, String>(7, false);
    	assertNull(map.put(1, "one"));
    	assertNull(map.put(8, "eight"));
    	assertNull(map.put(5, "five"));
    	assertEquals(3, map.size());
    	
    	assertEquals("five", map.put(5, "5"));
    	assertEquals("5", map.get(5));
    	assertEquals(3, map.size());
    	assertEquals("one", map.remove(1));
    	assertEquals(2, map.size());
    }
}