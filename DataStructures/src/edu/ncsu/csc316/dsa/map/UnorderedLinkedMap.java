package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 * @author Sean Hinton(sahinto2)
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {
	/**The underlying data structure containing Entries*/
    private PositionalList<Entry<K, V>> list;
    /**Default constructor for initializing the PositionalList*/
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    /**
     * Searches for a position with the corresponding key and moves to front if found
     * @param key the key to look for
     * @return a Position in the list
     */
    private Position<Entry<K, V>> lookUp(K key)
    {
//    	Position<Entry<K, V>> p = list.first();
//    	for(int i = 0; i < list.size(); i++) {
//    		if(p.getElement().getKey() == key) {
//    			return p;
//    		}
//    		p = list.after(p);
//    	}
    	Iterable<Position<Entry<K, V>>> it = list.positions();
    	for(Position<Entry<K, V>> p: it) {
//    		if(p.getElement().getKey() == key) {
//    			return p;
//    		}
    		if(p.getElement().getKey().equals(key)) {
    			return p;
    		}
    	}
    	return null;
    	
    }

    @Override
    public V get(K key) {
    	
        Position<Entry<K, V>> p = lookUp(key);
//        if(p != null && p.getElement().getKey() == key) {
//        	moveToFront(p);
//        	return p.getElement().getValue();
//        	
//        }
//        
        if(p != null && p.getElement().getKey().equals(key)) {
        	moveToFront(p);
        	return p.getElement().getValue();
        	
        }
        
        return null;
    }
    
    /**
     * Moves the input position to the front of the list
     * Remove the entry then add it to the front
     * @param position the position to move to front to improve run time
     */
    private void moveToFront(Position<Entry<K, V>> position) {
    	Entry<K, V> toFront = list.remove(position);
    	list.addFirst(toFront);
    }

    @Override
    public V put(K key, V value) {
    	
        Position<Entry<K, V>> p = lookUp(key);
        if(p != null && p.getElement().getKey().equals(key)) {
        	//Reset the old entry then put it back in with its new value
        	
        	V ret = p.getElement().getValue();
        	//Make sure p is an Entry before casting
        	if(p.getElement() instanceof Entry) {
        		MapEntry<K, V> toFront = (MapEntry<K, V>) p.getElement();
        		toFront.setValue(value);
        	}
        	
        	
        	moveToFront(p);
        	
        	return ret;
        } else {
        	
        	list.addFirst(new MapEntry<K, V>(key, value));
        	
        	return null;
        }
    }
    
    @Override
    public V remove(K key) {
    
       Position<Entry<K, V>> p = lookUp(key);
       if(p != null && p.getElement().getKey().equals(key)) {
       		V ret = list.remove(p).getValue();
       		return ret;
       	
       		
       }
       return null;
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    
}