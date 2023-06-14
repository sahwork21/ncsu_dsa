package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for HeapPriorityQueue
 * Checks the expected outputs of the Priorty Queue abstract data type behaviors when using
 * a min-heap data structure 
 *
 * @author Dr. King
 *
 */
public class HeapPriorityQueueTest {
	/**Heap based PriorityQueue for testing*/
    private PriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */     
    @Before
    public void setUp() {
        heap = new HeapPriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the insert(k,v) behavior
     */     
    @Test
    public void testInsert() {
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(9, "nine");
        assertEquals(8, (int)heap.min().getKey());
        heap.insert(10, "ten");
        assertEquals(8, (int)heap.min().getKey());
        assertEquals(3, heap.size());
        
        //Now add a smaller element so down heap is needed
        heap.insert(7, "seven");
        assertEquals(7, (int)heap.min().getKey());
        assertEquals(4, heap.size());
        heap.insert(4, "four");
        assertEquals(4, (int)heap.min().getKey());
        assertEquals(5, heap.size());
    }
    
    /**
     * Test the output of the min behavior
     */ 
    @Test
    public void testMin() {
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        
        assertNull(heap.min());
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(9, "nine");
        assertEquals(8, (int)heap.min().getKey());
        heap.insert(10, "ten");
        assertEquals(8, (int)heap.min().getKey());
        assertEquals(3, heap.size());
        
        //Now add a smaller element so down heap is needed
        heap.insert(7, "seven");
        assertEquals(7, (int)heap.min().getKey());
        assertEquals(4, heap.size());
        heap.insert(4, "four");
        assertEquals(4, (int)heap.min().getKey());
        assertEquals(5, heap.size());
        
        //Add a whole bunch of entries and make sure they stay the same
        heap.insert(4, "four2");
        assertEquals("four", heap.deleteMin().getValue());
        assertEquals(4, (int)heap.min().getKey());
        
        heap.insert(15, "fifteen");
        heap.insert(16, "sixteen");
        heap.insert(17, "seventeen");
        heap.insert(18, "eighteen");
        heap.insert(19, "nineteen");
        heap.insert(20, "twenty");
        heap.insert(21, "twentyone");
        assertEquals(4, (int)heap.min().getKey());
        
        heap.insert(1, "one");
        heap.insert(2, "two");
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.deleteMin().getValue());
        assertEquals(2, (int)heap.min().getKey());
        assertEquals("two", heap.deleteMin().getValue());
        assertEquals(4, (int)heap.min().getKey());
    }
    
    /**
     * Test the output of the deleteMin behavior
     */     
    @Test 
    public void deleteMin() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        assertNull(heap.deleteMin());
        heap.insert(1, "one");
        heap.insert(2, "two");
        heap.insert(3, "three");
        
        assertEquals("one", heap.deleteMin().getValue());
        assertEquals(2, heap.size());
        assertEquals(2, (int)heap.min().getKey());
        
        heap.insert(2, "two2");
        
        assertEquals("two", heap.deleteMin().getValue());
        assertEquals(2, heap.size());
        assertEquals(2, heap.min().getKey().intValue());
        
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */ 
    @Test
    public void testStudentHeap() {
        PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 5, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 4, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 2, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 1, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        sHeap.insert(s2, "2");
        sHeap.insert(s5, "5");
        sHeap.insert(s3, "3");
        sHeap.insert(s1, "1");
        sHeap.insert(s4, "4");
        assertEquals(5, sHeap.size());
        assertEquals("1", sHeap.min().getValue());
        assertEquals("jk1", sHeap.deleteMin().getKey().getUnityID());
        assertEquals(4, sHeap.size());
        assertEquals("2", sHeap.min().getValue());
        assertEquals("js2", sHeap.deleteMin().getKey().getUnityID());
        assertEquals(3, sHeap.size());
        assertEquals("3", sHeap.min().getValue());
        assertEquals("sh3", sHeap.deleteMin().getKey().getUnityID());
        assertEquals(2, sHeap.size());
        assertEquals("4", sHeap.min().getValue());
        assertEquals("jj4", sHeap.deleteMin().getKey().getUnityID());
        assertEquals(1, sHeap.size());
        assertEquals("5", sHeap.min().getValue());
        assertEquals("lb5", sHeap.deleteMin().getKey().getUnityID());
        assertEquals(0, sHeap.size());
        
        
        //Now do natural order
        sHeap = new HeapPriorityQueue<Student, String>();
        sHeap.insert(s2, "5");
        sHeap.insert(s5, "1");
        sHeap.insert(s3, "2");
        sHeap.insert(s1, "4");
        sHeap.insert(s4, "3");
        assertEquals("1", sHeap.min().getValue());
        assertEquals("B", sHeap.deleteMin().getKey().getLast());
        assertEquals(4, sHeap.size());
        assertEquals("2", sHeap.min().getValue());
        assertEquals("H", sHeap.deleteMin().getKey().getLast());
        assertEquals(3, sHeap.size());
        assertEquals("3", sHeap.min().getValue());
        assertEquals("J", sHeap.deleteMin().getKey().getLast());
        assertEquals(2, sHeap.size());
        assertEquals("4", sHeap.min().getValue());
        assertEquals("K", sHeap.deleteMin().getKey().getLast());
        assertEquals(1, sHeap.size());
        assertEquals("5", sHeap.min().getValue());
        assertEquals("S", sHeap.deleteMin().getKey().getLast());
        assertEquals(0, sHeap.size());
        
    }
}