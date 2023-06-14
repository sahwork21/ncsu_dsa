package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 *
 */
public class UnorderedLinkedMapTest {
	/**
	 * The map being tested
	 */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
    	
    	
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());

        assertFalse(map.isEmpty());
        assertNull(map.put(1, "s1"));
        assertEquals("UnorderedLinkedMap[1, 3]", map.toString());
        assertEquals(2, map.size());
        
        assertEquals("string3", map.put(3, "new"));
        assertEquals("UnorderedLinkedMap[3, 1]", map.toString());
        assertEquals(2, map.size());
        
        assertEquals("new", map.get(3));
    }

    /**
     * Test the output of the get(k) behavior
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
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string5", map.get(5));
        assertEquals("UnorderedLinkedMap[5, 1, 4, 2, 3]", map.toString());
        assertEquals("string2", map.get(2));
        assertEquals("UnorderedLinkedMap[2, 5, 1, 4, 3]", map.toString());
        
        assertNull(map.get(6));
        
        assertEquals("string1", map.put(1, "1"));
        assertEquals("1", map.get(1));
        
        assertNull(map.get(0));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertNull(map.remove(6));
        
        assertEquals("string2", map.remove(2));
        assertNull(map.get(2));
        assertEquals("UnorderedLinkedMap[1, 4, 5, 3]", map.toString());
        assertEquals("string3", map.remove(3));
        assertNull(map.get(2));
        assertNull(map.get(3));
        assertEquals("UnorderedLinkedMap[1, 4, 5]", map.toString());
        assertEquals("string1", map.remove(1));
        assertNull(map.get(2));
        assertNull(map.get(3));
        assertNull(map.get(1));
        assertEquals("UnorderedLinkedMap[4, 5]", map.toString());
        assertEquals("string5", map.remove(5));
        assertNull(map.get(2));
        assertNull(map.get(3));
        assertNull(map.get(1));
        assertNull(map.get(5));
        assertEquals("UnorderedLinkedMap[4]", map.toString());
        assertEquals("string4", map.remove(4));
        assertNull(map.get(2));
        assertNull(map.get(3));
        assertNull(map.get(1));
        assertNull(map.get(5));
        assertNull(map.get(4));
        assertEquals("UnorderedLinkedMap[]", map.toString());
        assertTrue(map.isEmpty());
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));

        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, it.next().intValue());
        assertTrue(it.hasNext());
        assertEquals(4, it.next().intValue());
        try {
        	it.remove();
        	fail("No exception caught");
        } catch(UnsupportedOperationException e) {
        	//Successful catch
        	assertEquals("The remove operation is not supported yet.", e.getMessage());
        }
        
        assertTrue(it.hasNext());
        assertEquals(2, it.next().intValue());
        assertTrue(it.hasNext());
        assertEquals(5, it.next().intValue());
        assertTrue(it.hasNext());
        assertEquals(3, it.next().intValue());
        assertFalse(it.hasNext());
        try {
        	it.next();
        	fail("No exception caught");
        } catch(NoSuchElementException e) {
        	//Successful catch
        }
        
        
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
        assertEquals("string4", it.next().getValue());
        try {
        	it.remove();
        	fail("No exception caught");
        } catch(UnsupportedOperationException e) {
        	//Successful catch
        	assertEquals("The remove operation is not supported yet.", e.getMessage());
        }
        
        assertTrue(it.hasNext());
        assertEquals(2, it.next().getKey().intValue());
        assertTrue(it.hasNext());
        assertEquals("string5", it.next().getValue());
        assertTrue(it.hasNext());
        assertEquals(3, it.next().getKey().intValue());
        assertFalse(it.hasNext());
        try {
        	it.next();
        	fail("No exception caught");
        } catch(NoSuchElementException e) {
        	//Successful catch
        }
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterable<String> itble = map.values();
        
        Iterator<String> it = itble.iterator();
        assertTrue(it.hasNext());
        assertEquals("string1", it.next());
        
        assertTrue(it.hasNext());
        assertEquals("string4", it.next());
        try {
        	it.remove();
        	fail("No exception caught");
        } catch(UnsupportedOperationException e) {
        	//Successful catch
        	assertEquals("The remove operation is not supported yet.", e.getMessage());
        }
        
        assertTrue(it.hasNext());
        assertEquals("string2", it.next());
        assertTrue(it.hasNext());
        assertEquals("string5", it.next());
        assertTrue(it.hasNext());
        assertEquals("string3", it.next());
        assertFalse(it.hasNext());
        try {
        	it.next();
        	fail("No exception caught");
        } catch(NoSuchElementException e) {
        	//Successful catch
        }
        
    }
    /**
     * Test using an entry collection
     */
    @Test
    public void testStudents() {
    	UnorderedLinkedMap<Student, String> smap = new UnorderedLinkedMap<Student, String>();
    	assertTrue(smap.isEmpty());
    	Student s1 = new Student("a", "a", 1, 1, 1, "aa");
    	assertNull(smap.put(s1, "1"));
    	assertEquals("1", smap.get(s1));
    	
    	Student s2 = new Student("b", "b", 1, 1, 1, "bb");
    	assertNull(smap.put(s2, "2"));
    	assertEquals("1", smap.remove(s1));
    	assertEquals(1, smap.size());
    	assertEquals("2", smap.put(s2, "3"));
    	
    }
    
}