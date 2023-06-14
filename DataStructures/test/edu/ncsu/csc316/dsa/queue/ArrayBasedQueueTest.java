package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedQueue.
 * Checks the expected outputs of the Queue abstract data type behaviors when using
 * a circular array-based data structure
 *
 * @author Dr. King
 *
 */
public class ArrayBasedQueueTest {
	/**The queue being tested*/
    private Queue<String> queue;
    
    /**
     * Create a new instance of a circular array-based queue before each test case executes
     */ 
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }

    /**
     * Test the output of the enqueue(e) behavior
     */     
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("three");
        assertEquals(3, queue.size());
        
        //Ensure extension on the circle
        
        assertEquals("one", queue.dequeue());
        assertEquals(2, queue.size());
        
        queue.enqueue("four");
        assertEquals(3, queue.size());
        assertEquals("two", queue.front());
        
        queue.enqueue("five");
        assertEquals(4, queue.size());
        assertEquals("two", queue.front());
        
        assertEquals("two", queue.dequeue());
        assertEquals("three", queue.dequeue());
        assertEquals("four", queue.dequeue());
        assertEquals("five", queue.dequeue());
        assertEquals(0, queue.size());
        
    }
    
    /**
     * Test the output of the dequeue(e) behavior, including expected exceptions
     */     
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");    
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }        
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("three");
        assertEquals(3, queue.size());
        
        assertEquals("one", queue.dequeue());
        assertEquals(2, queue.size());
        
        queue.enqueue("four");
        assertEquals(3, queue.size());
        assertEquals("two", queue.dequeue());
        assertEquals(2, queue.size());
        
        queue.enqueue("five");
        assertEquals(3, queue.size());
        assertEquals("three", queue.dequeue());
        assertEquals("four", queue.dequeue());
        assertEquals("five", queue.dequeue());
        assertEquals(0, queue.size());
    }
    
    /**
     * Test the output of the front() behavior, including expected exceptions
     */     
    @Test
    public void testFront() {
        assertEquals(0, queue.size());
        try {
        	queue.front();
        	fail("No NoSuchElementException thrown");
        } catch(NoSuchElementException e) {
        	//Successful catch of NoSuchElementException
        }
        
        queue.enqueue("one");
        assertEquals("one", queue.front());
        queue.enqueue("two");
        assertEquals("one", queue.front());
        queue.enqueue("three");
        assertEquals("one", queue.front());
        assertEquals("one", queue.dequeue());
        assertEquals("two", queue.front());
        assertEquals(2, queue.size());
        assertEquals("two", queue.dequeue());
        assertEquals("three", queue.front());
        assertEquals(1, queue.size());
        
        assertEquals("three", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

}