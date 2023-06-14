package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Tests the methods of the SkipListMap
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 *
 */
public class SkipListMapTest {
	/**
	 * The map being tested
	 */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SkipListMap<Integer, String>();
    }
    
    /**
     * Test the put method
     */
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        
        assertEquals(1, map.size());

        assertFalse(map.isEmpty());
        assertNull(map.put(1, "s1"));
        
        assertEquals(2, map.size());
        
        assertEquals("string3", map.put(3, "new"));
        
        assertEquals(2, map.size());
    }
    /**
     * test get method
     */
    @Test
    public void testGet() {
    	assertNull(map.get(1));
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        
        
        assertEquals("string1", map.get(1));
       
        
        assertEquals("string5", map.get(5));
        
        assertEquals("string2", map.get(2));
        
        assertNotNull(map.get(3));
        assertNull(map.get(6));
        
    }
    
    /**
     * Test the remove method
     */
    @Test
    public void testRemove() {
    	
    	assertNull(map.remove(1));
        assertTrue(map.isEmpty());
        
        assertNull(map.put(5, "5"));
        
        assertEquals("5", map.remove(5));
        assertNull(map.put(5, "5"));
        assertNull(map.put(4, "4"));
        assertNull(map.put(1, "1"));
        assertNull(map.put(2, "2"));
        assertNull(map.put(3, "3"));
        assertFalse(map.isEmpty());
        assertEquals(5, map.size());
        
        assertNull(map.remove(6));
        
        assertEquals("2", map.remove(2));
        
        assertEquals("3", map.remove(3));
       
        assertEquals("1", map.remove(1));
        
        assertEquals("5", map.remove(5));
        
        assertEquals("4", map.remove(4));
       
        assertTrue(map.isEmpty());
        
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(12, "twelve");
        map.put(5, "five");
        assertEquals(4, map.size());
    }
    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterable<Entry<Integer, String>> itble = map.entrySet();
        
        Iterator<Entry<Integer, String>> it = itble.iterator();
        
        assertTrue(it.hasNext());
        assertEquals(1, it.next().getKey().intValue());
        
        assertTrue(it.hasNext());
        assertEquals("string2", it.next().getValue());
        try {
        	it.remove();
        	fail("No exception caught");
        } catch(UnsupportedOperationException e) {
        	//Successful catch
        	assertEquals("The remove operation is not supported yet.", e.getMessage());
        }
        
        assertTrue(it.hasNext());
        assertEquals(3, it.next().getKey().intValue());
        assertTrue(it.hasNext());
        assertEquals("string4", it.next().getValue());
        assertTrue(it.hasNext());
        assertEquals(5, it.next().getKey().intValue());
        assertFalse(it.hasNext());
        try {
        	it.next();
        	fail("No exception caught");
        } catch(NoSuchElementException e) {
        	//Successful catch
        }
     }
    
    /**
     * words
     */
    @Test
    public void projectTest() {
    	Map<String, Integer> mp = new SkipListMap<String, Integer>();
    	mp.put("One", 1);
    	String fish1 = "fish";
    	String fish2 = "fish";
    	mp.put(fish1, 2);
    	mp.put("Two", 3);
    	
    	assertEquals(mp.get(fish2).intValue(), 2);
    	
    	
    }
}
