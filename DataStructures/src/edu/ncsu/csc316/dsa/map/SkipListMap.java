package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

/**
 * A SkipListMap is an ordered (meaning entries are stored in a sorted order
 * based on the keys of the entries) linked-memory representation of the Map
 * abstract data type. This link-based map maintains several levels of linked
 * lists to help approximate the performance of binary search using a
 * linked-memory structure. SkipListMap ensures a O(logn) expected/average
 * runtime for lookUps, insertions, and deletions.
 *
 * The SkipListMap class is based on algorithms developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    /**
     * Coin tosses are used when inserting entries into the data structure to ensure
     * 50/50 probability that an entry will be added to the current level of the
     * skip list structure
     */
    private Random coinToss;

    /**
     * Start references the topmost, leftmost corner of the skip list. In other
     * words, start references the sentinel front node at the top level of the skip
     * list
     */
    private SkipListNode<K, V> start;

    /**
     * The number of entries stored in the map
     */
    private int size;

    /**
     * The number of levels of the skip list data structure
     */
    private int height;

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SkipListMap() {
        this(null);
    }

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on a
     * provided {@link Comparator}
     *
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */
    public SkipListMap(Comparator<K> compare) {
        super(compare);
        coinToss = new Random();
        // Create a dummy head node for the left "-INFINITY" sentinel tower
        start = new SkipListNode<K, V>(null);
        // Create a dummy tail node for the right "+INFINITY" sentinel tower
        start.setNext(new SkipListNode<K, V>(null));
        // Set the +INFINITY tower's previous to be the "start" node
        start.getNext().setPrevious(start);
        size = 0;
        height = 0;
    }

    // Helper method to determine if an entry is one of the sentinel
    // -INFINITY or +INFINITY nodes (containing a null key)
    private boolean isSentinel(SkipListNode<K, V> node) {
        return node.getEntry() == null;
    }

    private SkipListNode<K, V> lookUp(K key) {
    	SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
            while (!isSentinel(current.next) && compare(key, current.next.getEntry().getKey()) >= 0) {
                current = current.next;
            }
        }
        return current;
    }

    @Override
    public V get(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        
        if(temp.getEntry() != null && temp.getEntry().getKey().equals(key)) {
        	return temp.getEntry().getValue();
        }
        return null;
    }

    private SkipListNode<K, V> insertAfterAbove(SkipListNode<K, V> prev, SkipListNode<K, V> down, Entry<K, V> entry) {
        SkipListNode<K, V> newNode = new SkipListNode<K, V>(entry);
        
        newNode.setBelow(down);
        newNode.setPrevious(prev);
        if(prev != null) {
        	newNode.setNext(prev.getNext());
        	newNode.getPrevious().setNext(newNode);
        }
        if(newNode.getNext() != null) {
        	newNode.getNext().setPrevious(newNode);
        }
        if(down != null) {
        	down.setAbove(newNode);
        }
        return newNode;
    }

    @Override
    public V put(K key, V value) {
        SkipListNode<K, V> temp = lookUp(key);
        if(temp.getEntry() != null && temp.getEntry().getKey().equals(key)) {
        	V original = temp.getEntry().getValue();
        	while(temp != null) {
        		temp.setEntry(new MapEntry<K, V>(temp.getEntry().getKey(), value));
        		
        		temp = temp.getAbove();
        	}
        	return original;
        }
        
        
        SkipListNode<K, V> nextLevel = null;
        int currentLevel = -1;
        //Add with coin flips
        do {
        	currentLevel++;
        	if(currentLevel >= height) {
        		//Increase height if necessary
        		height++;
        		
        		SkipListNode<K, V> tail = start.next;
        		start = insertAfterAbove(null, start, null);
        		
        		insertAfterAbove(start, tail, null);
        	}
        	
        	nextLevel = insertAfterAbove(temp, nextLevel, new MapEntry<K, V>(key, value));
        	
        	while(temp.getAbove() == null) {
        		temp = temp.getPrevious();
        	}
        	temp = temp.getAbove();
        } while (coinToss.nextDouble() < .5);
        
        size++;
        return null;
    }

    @Override
    public V remove(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        if(temp.getEntry() != null && temp.getEntry().getKey().equals(key)) {
        	//Remove every node up to its highest level
        	while(temp.getAbove() != null) {
        		temp.getPrevious().setNext(temp.getNext());
        		temp.getNext().setPrevious(temp.getPrevious());
        		temp = temp.getAbove();
        	}
        	temp.getPrevious().setNext(temp.getNext());
    		temp.getNext().setPrevious(temp.getPrevious());
    		
//    		//Remove any extra levels if needed
//    		SkipListNode<K, V> currentLevel = start;
//    		
//    		while( height > 0 && currentLevel.below.getNext().getEntry() == null) {
//    			height--;
//    			if(height == 0) {
//    				currentLevel.setBelow(null);
//    				currentLevel.getNext().setBelow(null);
//    			
//    			} else {
//    				SkipListNode<K, V> tail = currentLevel.next;
//    				currentLevel.setBelow(currentLevel.below);
//    				tail.setBelow(tail.below);
//    				currentLevel = currentLevel.getNext();
//    			}
//    			
//    			
//    			
//    			
//    		}
    		
    		
			//Remove the extra skip lists
			SkipListNode<K, V> current = start;
			while(current.below != null && current.below.getNext().getEntry() == null) {
				current = current.below;
				height--;
			}
			current.setAbove(null);
			current.getNext().setAbove(null);
			start = current;
			
    		size--;
    		return temp.getEntry().getValue();
        }
        
        return null;
        
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection set = new EntryCollection();
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
        }
        current = current.next;
        while (!isSentinel(current)) {
            set.add(current.getEntry());
            current = current.next;
        }
        return set;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SkipListMap[");
        SkipListNode<K, V> cursor = start;
        while (cursor.below != null) {
            cursor = cursor.below;
        }
        cursor = cursor.next;
        while (cursor != null && !isSentinel(cursor) && cursor.getEntry().getKey() != null) {
            sb.append(cursor.getEntry().getKey());
            if (!isSentinel(cursor.next)) {
                sb.append(", ");
            }
            cursor = cursor.next;
        }
        sb.append("]");

        return sb.toString();
    }

//    // This method may be useful for testing or debugging.
//    // You may comment-out this method instead of testing it, since
//    // the full string will depend on the series of random coin flips
//    // and will not have deterministic expected results.
//    /**
//     * Method for testing that returns the full skip list
//     * @return the underlying Linked Lists of Entries
//     */
//    public String toFullString() {
//        StringBuilder sb = new StringBuilder("SkipListMap[\n");
//        SkipListNode<K, V> cursor = start;
//        SkipListNode<K, V> firstInList = start;
//        while (cursor != null) {
//            firstInList = cursor;
//            sb.append("-INF -> ");
//            cursor = cursor.next;
//            while (cursor != null && !isSentinel(cursor)) {
//                sb.append(cursor.getEntry().getKey() + " -> ");
//                cursor = cursor.next;
//            }
//            sb.append("+INF\n");
//            cursor = firstInList.below;
//        }
//        sb.append("]");
//        return sb.toString();
//    }

    private static class SkipListNode<K, V> {
    	/**
    	 * The encapsulated entry of the SkipListNode
    	 */
        private Entry<K, V> entry;
        /**The node above this node*/
        private SkipListNode<K, V> above;
        /**The node below this node*/
        private SkipListNode<K, V> below;
        /**The node before this node*/
        private SkipListNode<K, V> prev;
        /**The node after this node*/
        private SkipListNode<K, V> next;

        public SkipListNode(Entry<K, V> entry) {
            setEntry(entry);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }

        public SkipListNode<K, V> getAbove() {
            return above;
        }

        public Entry<K, V> getEntry() {
            return entry;
        }

        public SkipListNode<K, V> getNext() {
            return next;
        }

        public SkipListNode<K, V> getPrevious() {
            return prev;
        }

        public void setAbove(SkipListNode<K, V> up) {
            this.above = up;
        }

        public void setBelow(SkipListNode<K, V> down) {
            this.below = down;
        }

        public void setEntry(Entry<K, V> entry) {
            this.entry = entry;
        }

        public void setNext(SkipListNode<K, V> next) {
            this.next = next;
        }

        public void setPrevious(SkipListNode<K, V> prev) {
            this.prev = prev;
        }
    }
    
}