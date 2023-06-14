package edu.ncsu.csc316.dsa.map.hashing;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put(K, V)}, {@link Map#get(K, V)}, and {@link Map#remove(K, V)}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {
	/**The underlying array of entries*/
    private TableEntry<K, V>[] table;
    /**The number of entries in the hash map*/
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    @SuppressWarnings("unchecked")
	public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        table = new TableEntry[capacity];
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(int i = 0; i < table.length; i++) {
        	//Only add non null elements and entries that are not deleted
        	if(table[i] != null && !table[i].isDeleted) {
        		collection.add(table[i]);
        	}
        }
        return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }

    private boolean isAvailable(int index) {
        return (table[index] == null || table[index].isDeleted());
    }

    @Override
    public V bucketGet(int hash, K key) {
    	int index = findBucket(hash, key);
        if(index >= 0 && !table[index].isDeleted) {
        	return table[index].getValue();
        }
        
        return null;
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        //Either the index is negative so the key does not exist or 0 and up then it exists
    	int index = findBucket(hash, key);
    	//The bucket was found and we need to change values
    	if(index >= 0) {
    		V ret = table[index].getValue();
    		table[index].setValue(value);
    		return ret;
    	}
    	
    	//Otherwise insert at the index returned
    	size++;
    	table[-(index + 1)] = new TableEntry<K, V>(key, value);
    	return null;
    }
    
    /**
     * Finds the index of the entry after using linear probing to find the correct index
     * @param index the hash of the key that is being searched for
     * @param key the key for the entry being searched for in the table
     * @return the index of a matching key or the -index of insertion + 1
     */
    private int findBucket(int index, K key) {
        int avail = -1;
        int j = index;
        do {
        	if(isAvailable(j)) {
        		avail = j;
        	}
        	if(table[j] == null) {
        		return -(avail + 1);
        	}
        	else if(table[j].getKey().equals(key)) {
        		return j;
        	}
        	//Linear probing action of adding 1 to the hash since no match is found
        	j = (j + 1) % table.length;
        } while (j != index);
        
        return -(avail + 1);
    }

    @Override
    public V bucketRemove(int hash, K key) {
        
        // Remember to set the table bucket as DELETED using setDeleted(true)
    	int index = findBucket(hash, key);
    	//Found the bucket so set deleted no removing
    	if(index >= 0) {
    		table[index].setDeleted(true);
    		size--;
    		return table[index].getValue();
    	}
    	return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }

    private static class TableEntry<K, V> extends MapEntry<K, V> {
    	/**If this value is true then skip it when looking up values*/
        private boolean isDeleted;

        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}