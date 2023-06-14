package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for SeparateChainingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a separate chaining hash map data structure 
 *
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 */
public class SeparateChainingHashMapTest {
	/**The map that is being tested*/
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of a separate chaining hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are TESTING.
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
        // Remember that our secondary map (an AVL tree) is a search
        // tree, which means the entries should be sorted in order within
        // that tree
        map = new SeparateChainingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));

        // Since our entrySet method returns the entries in the table
        // from left to right, we can use the entrySet to check
        // that our values are in the correct order in the hash table.
        // Alternatively, you could implement a toString() method if you
        // want to check that the exact index/map of each bucket is correct
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        
        
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        
        
        // You should create some collisions to check that entries
        // are placed in the correct buckets
        assertNull(map.put(7, "seven"));
        assertEquals(3, map.size());
        assertEquals("string3", map.put(3, "new3"));
        assertEquals(3, map.size());
        //Collision in the 1 bucket with 0, 7, and 14
        assertNull(map.put(14, "fourteen"));
        assertEquals(4, map.size());
        assertNull(map.put(0, "zero"));
        assertEquals(5, map.size());
        //Now iterate through all the elements bucket by bucket. 7, 14, 3, 4
        it = map.entrySet().iterator();
        assertEquals(0, (int)it.next().getKey());
        assertEquals(7, (int)it.next().getKey());
        assertEquals(14, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertFalse(it.hasNext());
        assertEquals("new3", map.get(3));
        assertEquals("seven", map.get(7));
        assertEquals("fourteen", map.get(14));
        assertEquals("zero", map.get(0));
        
        assertEquals("zero", map.put(0, "0"));
        assertEquals(5, map.size());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        //Put a bunch of things and see if they work
        
        assertNull(map.put(4, "four"));
        assertNull(map.put(2, "two"));
        assertNull(map.put(3, "three"));
        assertNull(map.put(5, "five"));
        assertNull(map.put(1, "one"));
        assertNull(map.put(0, "zero"));
        assertNull(map.put(6, "six"));
        assertEquals("zero", map.get(0));
        assertEquals("one", map.get(1));
        assertEquals("two", map.get(2));
        assertEquals("three", map.get(3));
        assertEquals("four", map.get(4));
        assertEquals("five", map.get(5));
        assertEquals("six", map.get(6));
        
        //Now cause a collision in every bucket
        assertNull(map.put(11, "eleven"));
        assertNull(map.put(9, "nine"));
        assertNull(map.put(10, "ten"));
        assertNull(map.put(12, "twelve"));
        assertNull(map.put(8, "eight"));
        assertNull(map.put(7, "seven"));
        assertNull(map.put(13, "thirteen"));
        assertEquals("zero", map.get(0));
        assertEquals("one", map.get(1));
        assertEquals("two", map.get(2));
        assertEquals("three", map.get(3));
        assertEquals("four", map.get(4));
        assertEquals("five", map.get(5));
        assertEquals("six", map.get(6));
        assertEquals("seven", map.get(7));
        assertEquals("eight", map.get(8));
        assertEquals("nine", map.get(9));
        assertEquals("ten", map.get(10));
        assertEquals("eleven", map.get(11));
        assertEquals("twelve", map.get(12));
        assertEquals("thirteen", map.get(13));
        assertNull(map.get(14));
        
        assertEquals(14, map.size());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(4, "four"));
        
        assertNull(map.remove(5));
        
        assertNull(map.put(2, "two"));
        assertNull(map.put(3, "three"));
        assertNull(map.put(5, "five"));
        assertNull(map.put(1, "one"));
        assertNull(map.put(0, "zero"));
        assertNull(map.put(6, "six"));
        assertNull(map.put(11, "eleven"));
        assertNull(map.put(9, "nine"));
        assertNull(map.put(10, "ten"));
        assertNull(map.put(12, "twelve"));
        assertNull(map.put(8, "eight"));
        assertNull(map.put(7, "seven"));
        assertNull(map.put(13, "thirteen"));
        assertEquals("zero", map.get(0));
        assertEquals("one", map.get(1));
        assertEquals("two", map.get(2));
        assertEquals("three", map.get(3));
        assertEquals("four", map.get(4));
        assertEquals("five", map.get(5));
        assertEquals("six", map.get(6));
        assertEquals("seven", map.get(7));
        assertEquals("eight", map.get(8));
        assertEquals("nine", map.get(9));
        assertEquals("ten", map.get(10));
        assertEquals("eleven", map.get(11));
        assertEquals("twelve", map.get(12));
        assertEquals("thirteen", map.get(13));
        //Now remove some keys
        assertEquals("seven", map.remove(7));
        assertEquals("eleven", map.remove(11));
        assertEquals("three", map.remove(3));
        
        
        assertEquals("zero", map.get(0));
        assertEquals("one", map.get(1));
        assertEquals("two", map.get(2));
        assertNull(map.get(3));
        assertEquals("four", map.get(4));
        assertEquals("five", map.get(5));
        assertEquals("six", map.get(6));
        assertNull(map.get(7));
        assertEquals("eight", map.get(8));
        assertEquals("nine", map.get(9));
        assertEquals("ten", map.get(10));
        assertNull(map.get(11));
        assertEquals("twelve", map.get(12));
        assertEquals("thirteen", map.get(13));
        assertEquals(11, map.size());
        
        assertNull(map.remove(99));
        assertEquals(11, map.size());
        
        
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */   
    @Test
    public void testIterator() {
        
        
        
        assertTrue(map.isEmpty());
        assertNull(map.put(4, "four"));
        assertNull(map.put(2, "two"));
        assertNull(map.put(3, "three"));
        assertNull(map.put(5, "five"));
        assertNull(map.put(1, "one"));
        assertNull(map.put(0, "zero"));
        assertNull(map.put(6, "six"));
        assertNull(map.put(11, "eleven"));
        assertNull(map.put(9, "nine"));
        assertNull(map.put(10, "ten"));
        assertNull(map.put(12, "twelve"));
        assertNull(map.put(8, "eight"));
        assertNull(map.put(7, "seven"));
        assertNull(map.put(13, "thirteen"));
        assertEquals("zero", map.get(0));
        assertEquals("one", map.get(1));
        assertEquals("two", map.get(2));
        assertEquals("three", map.get(3));
        assertEquals("four", map.get(4));
        assertEquals("five", map.get(5));
        assertEquals("six", map.get(6));
        assertEquals("seven", map.get(7));
        assertEquals("eight", map.get(8));
        assertEquals("nine", map.get(9));
        assertEquals("ten", map.get(10));
        assertEquals("eleven", map.get(11));
        assertEquals("twelve", map.get(12));
        assertEquals("thirteen", map.get(13));
        
        Iterator<Integer> it = map.iterator();
        //Goes bucket by bucket
        assertEquals(6, (int)it.next());
        assertEquals(13, (int)it.next());
        assertEquals(0, (int)it.next());
        assertEquals(7, (int)it.next());
        assertEquals(1, (int)it.next());
        assertEquals(8, (int)it.next());
        assertEquals(2, (int)it.next());
        assertEquals(9, (int)it.next());
        assertEquals(3, (int)it.next());
        assertEquals(10, (int)it.next());
        assertEquals(4, (int)it.next());
        assertEquals(11, (int)it.next());
        assertEquals(5, (int)it.next());
        assertEquals(12, (int)it.next());
        
        
        //Now remove some keys
        assertEquals("seven", map.remove(7));
        assertEquals("eleven", map.remove(11));
        assertEquals("three", map.remove(3));
        
        
        assertEquals("zero", map.get(0));
        assertEquals("one", map.get(1));
        assertEquals("two", map.get(2));
        assertNull(map.get(3));
        assertEquals("four", map.get(4));
        assertEquals("five", map.get(5));
        assertEquals("six", map.get(6));
        assertNull(map.get(7));
        assertEquals("eight", map.get(8));
        assertEquals("nine", map.get(9));
        assertEquals("ten", map.get(10));
        assertNull(map.get(11));
        assertEquals("twelve", map.get(12));
        assertEquals("thirteen", map.get(13));
        assertEquals(11, map.size());
        it = map.iterator();
        assertEquals(6, (int)it.next());
        assertEquals(13, (int)it.next());
        assertEquals(0, (int)it.next());
        
        assertEquals(1, (int)it.next());
        assertEquals(8, (int)it.next());
        assertEquals(2, (int)it.next());
        assertEquals(9, (int)it.next());
       
        assertEquals(10, (int)it.next());
        assertEquals(4, (int)it.next());
        
        assertEquals(5, (int)it.next());
        assertEquals(12, (int)it.next());
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
    	assertNull(map.put(4, "four"));
        assertNull(map.put(2, "two"));
        assertNull(map.put(3, "three"));
        assertNull(map.put(5, "five"));
        assertNull(map.put(1, "one"));
        assertNull(map.put(0, "zero"));
        assertNull(map.put(6, "six"));
        assertNull(map.put(11, "eleven"));
        assertNull(map.put(9, "nine"));
        assertNull(map.put(10, "ten"));
        assertNull(map.put(12, "twelve"));
        assertNull(map.put(8, "eight"));
        assertNull(map.put(7, "seven"));
        assertNull(map.put(13, "thirteen"));
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();        
        assertEquals(6, (int)it.next().getKey());
        assertEquals(13, (int)it.next().getKey());
        assertEquals(0, (int)it.next().getKey());
        assertEquals(7, (int)it.next().getKey());
        assertEquals(1, (int)it.next().getKey());
        assertEquals(8, (int)it.next().getKey());
        assertEquals(2, (int)it.next().getKey());
        assertEquals(9, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey());
        assertEquals(5, (int)it.next().getKey());
        assertEquals(12, (int)it.next().getKey());
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
    	assertNull(map.put(4, "four"));
        assertNull(map.put(2, "two"));
        assertNull(map.put(3, "three"));
        assertNull(map.put(5, "five"));
        assertNull(map.put(1, "one"));
        assertNull(map.put(0, "zero"));
        assertNull(map.put(6, "six"));
        assertNull(map.put(11, "eleven"));
        assertNull(map.put(9, "nine"));
        assertNull(map.put(10, "ten"));
        assertNull(map.put(12, "twelve"));
        assertNull(map.put(8, "eight"));
        assertNull(map.put(7, "seven"));
        assertNull(map.put(13, "thirteen"));
        Iterator<String> it = map.values().iterator();
        assertEquals("six", it.next());
        assertEquals("thirteen", it.next());
        assertEquals("zero", it.next());
        assertEquals("seven", it.next());
        assertEquals("one", it.next());
        assertEquals("eight", it.next());
        assertEquals("two", it.next());
        assertEquals("nine", it.next());
        assertEquals("three", it.next());
        assertEquals("ten", it.next());
        assertEquals("four", it.next());
        assertEquals("eleven", it.next());
        assertEquals("five", it.next());
        assertEquals("twelve", it.next());
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
    	map = new SeparateChainingHashMap<Integer, String>();
    	assertNull(map.put(1, "one"));
    	assertNull(map.put(8, "eight"));
    	assertNull(map.put(5, "five"));
    	assertEquals(3, map.size());
    	
    	assertEquals("five", map.put(5, "5"));
    	assertEquals("5", map.get(5));
    	assertEquals(3, map.size());
    	assertEquals("one", map.remove(1));
    	assertEquals(2, map.size());
    	
    	map = new SeparateChainingHashMap<Integer, String>(false);
    	
    	assertNull(map.put(1, "one"));
    	assertNull(map.put(8, "eight"));
    	assertNull(map.put(5, "five"));
    	assertEquals(3, map.size());
    	
    	assertEquals("five", map.put(5, "5"));
    	assertEquals("5", map.get(5));
    	assertEquals(3, map.size());
    	assertEquals("one", map.remove(1));
    	assertEquals(2, map.size());
    	
    	
    	map = new SeparateChainingHashMap<Integer, String>(7, false);
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