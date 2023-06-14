package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.AVLTreeMap;

/**
 * The SeparateChainingHashMap is implemented as a hash table that uses separate
 * chaining for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@see Map#put}, {@see Map#get}, and {@see Map#remove}.
 * 
 * The secondary map that appears within each bucket (with separate chaining)
 * supports worst-case O(logn) runtime for {@see Map#put}, {@see Map#get}, and
 * {@link Map#remove} within each bucket.
 * 
 * The SeparateChainingHashMap class is based on the implementation developed
 * for use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class SeparateChainingHashMap<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {
	/**The Array of Maps that contains Entries in the table. Each array index has a different map*/
    private Map<K, V>[] table;
    /**The number of entries into the table*/
    private int size;

    /**
     * Constructs a new separate chaining hash map that uses natural ordering of
     * keys when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public SeparateChainingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new separate chaining hash map that
     * uses natural ordering of keys when performing comparisons. The created hash
     * table uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public SeparateChainingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new separate chaining hash map that uses natural ordering of
     * keys when performing comparisons. The created hash table is initialized to
     * have the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public SeparateChainingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new separate chaining hash map that
     * uses natural ordering of keys when performing comparisons. The created hash
     * table is initialized to have the provided capacity.
     * Initializes an AVLTreeMap to be the underlying Map as it provide O(log n) get, put, and remove
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    @SuppressWarnings("unchecked") 
	public SeparateChainingHashMap(int capacity, boolean isTesting) {
    	super(capacity, isTesting);
    	table = new AVLTreeMap[capacity]; 
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                // Each bucket contains a map, so include
                // all entries in the entrySet for the map
                // at the current bucket
                for (Entry<K, V> entry : table[i].entrySet()) {
                    collection.add(entry);
                }
            }
        }
        return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        // You can choose to use any EFFICIENT secondary map.
        // UnorderedLinkedMap, SearchTableMap, and BinarySearchTreeMap are NOT the most
        // efficient maps we have discussed this semester since UnorderedLinkedMap has
        // O(n) put, get, and remove; SearchTableMap has O(n) put and remove; and
        // BinarySearchTreeMap has O(n) put, get, and remove. Therefore, use a
        // SkipListMap with expected O(logn) runtime, or a balanced binary search tree
        // for guaranteed O(logn) worst-case runtime.
        table = new AVLTreeMap[capacity];
        size = 0;
    }

    @Override
    public V bucketGet(int hash, K key) {
        // Get the bucket at the specified index in the hash table
        Map<K, V> bucket = table[hash];
        // If there is no map in the bucket, then the entry does not exist
        if (bucket == null) {
            return null;
        }
        // Otherwise, delegate to the existing map's get method to return the value
        return bucket.get(key);
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        //Have to initialize the Map if it doesn't exist at the index
    	if(table[hash] == null) {
    		table[hash] = new AVLTreeMap<K, V>();
    	}
    	
    	//Check if the key already exists and replace the value if it does
    	if(table[hash].get(key) != null) {
    		return table[hash].put(key, value);
    		
    	}
    	
    	size++;
    	return table[hash].put(key, value);
    	
    }

    @Override
    public V bucketRemove(int hash, K key) {
    	//Shortcircuit needed when removing from a non existent bucket
    	if(table[hash] == null || table[hash].get(key) == null) {
    		return null;
    	}
        size--;
    	return table[hash].remove(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }
}