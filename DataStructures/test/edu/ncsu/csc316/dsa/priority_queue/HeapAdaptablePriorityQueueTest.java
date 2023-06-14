package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King
 *
 */
public class HeapAdaptablePriorityQueueTest {
	/**The AdaptablePriorityQueue to test*/
    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(9, heap.size());
        
        //Now move it back down
        heap.replaceKey(e0, -6);
        assertEquals(-6, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        
        //Now change a bunch of oredering
        heap.replaceKey(e3, -3);
        heap.replaceKey(e1, 0);
        heap.replaceKey(e7, 77);
        heap.replaceKey(e2, -10);
        heap.replaceKey(e5, 55);
        heap.replaceKey(e4, 99);
        heap.replaceKey(e6, -6);
        
        try {
        	Entry<Integer, String> e9 = heap.insert(9, "eight");
        	heap.remove(e9);
        	heap.replaceKey(e9, -100);
        	fail("Couldn't catch");
        } catch (IllegalArgumentException e) {
        	assertEquals("Invalid Adaptable PQ Entry.", e.getMessage());
        }
        
        assertEquals("two", heap.deleteMin().getValue());
        assertEquals("zero", heap.deleteMin().getValue());
        assertEquals("six", heap.deleteMin().getValue());
        assertEquals("eight", heap.deleteMin().getValue());
        assertEquals("three", heap.deleteMin().getValue());
        assertEquals("one", heap.deleteMin().getValue());
        assertEquals("five", heap.deleteMin().getValue());
        assertEquals("seven", heap.deleteMin().getValue());
        assertEquals("four", heap.deleteMin().getValue());
        assertTrue(heap.isEmpty());
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceValue(e8,  "EIGHT");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("EIGHT",  e8.getValue());
        heap.replaceValue(e0, "ZERO");
        assertEquals("ZERO", heap.min().getValue());
        
        try {
        	Entry<Integer, String> e9 = heap.insert(9, "eight");
        	heap.remove(e9);
        	heap.replaceValue(e9, "1");
        	fail("Couldn't catch");
        } catch (IllegalArgumentException e) {
        	assertEquals("Invalid Adaptable PQ Entry.", e.getMessage());
        }
        
        heap.replaceValue(e1, "1");
        heap.replaceValue(e2, "2");
        heap.replaceValue(e3, "3");
        heap.replaceValue(e4, "4");
        heap.replaceValue(e5, "5");
        heap.replaceValue(e6, "6");
        heap.replaceValue(e7, "7");
        assertEquals("ZERO", heap.deleteMin().getValue());
        assertEquals("1", heap.deleteMin().getValue());
        assertEquals("2", heap.deleteMin().getValue());
        assertEquals("3", heap.deleteMin().getValue());
        assertEquals("4", heap.deleteMin().getValue());
        assertEquals("5", heap.deleteMin().getValue());
        assertEquals("6", heap.deleteMin().getValue());
        assertEquals("7", heap.deleteMin().getValue());
        assertEquals("EIGHT", heap.deleteMin().getValue());
        assertTrue(heap.isEmpty());
        
        
        
        
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.remove(e0);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(8, heap.size());
        
        heap.remove(e2);
        heap.remove(e1);
        assertEquals(e3, heap.min());
        
        assertEquals(6, heap.size());
        
        heap.remove(e7);
        heap.remove(e4);
        heap.remove(e5);
        assertEquals(e3, heap.deleteMin());
        assertEquals(6, (int)heap.min().getKey());
        
        heap.replaceKey(e6, 99);
        assertEquals(8, (int)heap.min().getKey());
        assertEquals(e8, heap.deleteMin());
        assertEquals(99, (int) heap.min().getKey());
        assertEquals(1, heap.size());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        Entry<Student, String> e2 = sHeap.insert(s2, "2");
        Entry<Student, String> e5 = sHeap.insert(s5, "5");
        Entry<Student, String> e3 = sHeap.insert(s3, "3");
        Entry<Student, String> e1 = sHeap.insert(s1, "1");
        Entry<Student, String> e4 = sHeap.insert(s4, "4");
        
        sHeap.replaceValue(e2, "one");
        assertEquals(s1, sHeap.min().getKey());
        
        sHeap.replaceKey(e4, s1);
        assertEquals(e1, sHeap.deleteMin());
        assertEquals("4", sHeap.min().getValue());
        
        sHeap.remove(e2);
        sHeap.remove(e3);
        assertEquals(2, sHeap.size());
        assertEquals(s1, sHeap.deleteMin().getKey());
        assertEquals(e5, sHeap.min());
    }
}