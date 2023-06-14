package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for SearchTableMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a sorted array-based data structure that uses binary search to locate entries
 * based on the key of the entry
 *
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 *
 */
public class SearchTableMapTest {
	/**A map to test of primitive types and a String*/
    private Map<Integer, String> map;
    /**A map to test the comparison of a comparable object*/
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SearchTableMap<Integer, String>();
        studentMap = new SearchTableMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SearchTableMap[3]", map.toString());
        assertEquals(1, map.size());
        
        
        assertEquals("string3", map.put(3, "new3"));
        assertEquals("SearchTableMap[3]", map.toString());
        assertEquals("new3", map.get(3));
        
        assertNull(map.put(1, "1"));
        assertEquals("SearchTableMap[1, 3]", map.toString());
        assertEquals(2, map.size());
        assertEquals("1", map.get(1));
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertNull(map.get(6));
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string4", map.remove(4));
        assertEquals("SearchTableMap[1, 2, 3, 5]", map.toString());
        assertEquals(4, map.size());
        assertEquals("string1", map.remove(1));
        assertEquals("SearchTableMap[2, 3, 5]", map.toString());
        assertEquals(3, map.size());
        assertNull(map.remove(4));
    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        
        // Suggestions: since search table map keys are Comparable,
        // make sure the search table works with Comparable objects like Students
        assertTrue(studentMap.isEmpty());
        assertNull(studentMap.put(s1, 4));
        assertEquals("SearchTableMap[first=J, last=K, id=1, creditHours=0, gpa=0.0, unityID=jk]", studentMap.toString());
        assertEquals(1, studentMap.size());
        assertEquals(4, studentMap.get(s1).intValue());
        assertNull(studentMap.put(s2, 1));
        assertNull(studentMap.put(s3, 1));
        assertNull(studentMap.put(s4, 1));
        assertNull(studentMap.put(s5, 1));
        assertEquals(1, studentMap.put(s5, 5).intValue());
        assertEquals("SearchTableMap[first=L, last=B, id=5, creditHours=0, gpa=0.0, unityID=lb, "
        		+ "first=S, last=H, id=3, creditHours=0, gpa=0.0, unityID=sh, "
        		+ "first=J, last=J, id=4, creditHours=0, gpa=0.0, unityID=jj, "
        		+ "first=J, last=K, id=1, creditHours=0, gpa=0.0, unityID=jk, "
        		+ "first=J, last=S, id=2, creditHours=0, gpa=0.0, unityID=js]", studentMap.toString());
        assertEquals(5, studentMap.get(s5).intValue());
        
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
        assertEquals(2, it.next().intValue());
        try {
        	it.remove();
        	fail("No exception caught");
        } catch(UnsupportedOperationException e) {
        	//Successful catch
        	assertEquals("The remove operation is not supported yet.", e.getMessage());
        }
        
        assertTrue(it.hasNext());
        assertEquals(3, it.next().intValue());
        assertTrue(it.hasNext());
        assertEquals(4, it.next().intValue());
        assertTrue(it.hasNext());
        assertEquals(5, it.next().intValue());
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
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));

        assertEquals("string2", it.next().getValue());
        try {
        	it.remove();
        	fail("No exception caught");
        } catch(UnsupportedOperationException e) {
        	//Successful catch
        	assertEquals("The remove operation is not supported yet.", e.getMessage());
        }
        assertEquals("string3", it.next().getValue());
        assertEquals("string4", it.next().getValue());
        assertEquals("string5", it.next().getValue());
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
        
        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());
        
        
        
        
        assertTrue(it.hasNext());
        assertEquals("string1", it.next());
        
        assertTrue(it.hasNext());
        assertEquals("string2", it.next());
        try {
        	it.remove();
        	fail("No exception caught");
        } catch(UnsupportedOperationException e) {
        	//Successful catch
        	assertEquals("The remove operation is not supported yet.", e.getMessage());
        }
        
        assertTrue(it.hasNext());
        assertEquals("string3", it.next());
        assertTrue(it.hasNext());
        assertEquals("string4", it.next());
        assertTrue(it.hasNext());
        assertEquals("string5", it.next());
        assertFalse(it.hasNext());
        try {
        	it.next();
        	fail("No exception caught");
        } catch(NoSuchElementException e) {
        	//Successful catch
        }
    }
    /**
     * test using comparators on a Student Map
     */
    @Test
    public void testCompartors() {
    	 studentMap = new SearchTableMap<Student, Integer>(new StudentIDComparator());
    	 Student s1 = new Student("J", "K", 1, 0, 0, "jk");
         Student s2 = new Student("J", "S", 2, 0, 0, "js");
         Student s3 = new Student("S", "H", 3, 0, 0, "sh");
         Student s4 = new Student("J", "J", 4, 0, 0, "jj");
         Student s5 = new Student("L", "B", 5, 0, 0, "lb");
         
         
         // Suggestions: since search table map keys are Comparable,
         // make sure the search table works with Comparable objects like Students
         assertTrue(studentMap.isEmpty());
         assertNull(studentMap.put(s1, 4));
         assertEquals("SearchTableMap[first=J, last=K, id=1, creditHours=0, gpa=0.0, unityID=jk]", studentMap.toString());
         assertEquals(1, studentMap.size());
         assertEquals(4, studentMap.get(s1).intValue());
         assertNull(studentMap.put(s2, 1));
         assertNull(studentMap.put(s3, 1));
         assertNull(studentMap.put(s4, 1));
         assertNull(studentMap.put(s5, 1));
         assertEquals(1, studentMap.put(s5, 5).intValue());
         
         assertEquals("SearchTableMap[first=J, last=K, id=1, creditHours=0, gpa=0.0, unityID=jk, "
          		+ "first=J, last=S, id=2, creditHours=0, gpa=0.0, unityID=js, "
          		+ "first=S, last=H, id=3, creditHours=0, gpa=0.0, unityID=sh, "
          		+ "first=J, last=J, id=4, creditHours=0, gpa=0.0, unityID=jj, "
          		+ "first=L, last=B, id=5, creditHours=0, gpa=0.0, unityID=lb]", studentMap.toString());
         assertEquals(5, studentMap.get(s5).intValue());
    }
}