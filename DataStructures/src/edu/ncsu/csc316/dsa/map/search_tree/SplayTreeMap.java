package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;
import edu.ncsu.csc316.dsa.Position;

/**
 * The SplayTreeMap is implemented as a linked data structure to support
 * efficient Tree and Map abstract data type behaviors.
 * 
 * In a Splay tree, the splay operation is performed on each insertion, removal,
 * and retrieval. While the worst-case height of a splay tree is O(n), the splay
 * operation ensures a more efficient runtime over a series of operations. Over
 * a series of m U+0007B;@see Map#put}, U+0007B;@see Map#get}, and U+0007B;@see Map#remove}
 * operations, the splay tree will provide O(mlogn) amortized cost.
 * 
 * SplayTreeMap uses sentinel leaves. Every leaf node should have 2 sentinel
 * children.
 * 
 * The SplayTreeMap class is based on the implementation developed for use with
 * the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley &#38; Sons, 2014
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 * @param <K> the type of keys stored in the Splay tree
 * @param <V> the type of values associated with keys in the Splay tree
 */
public class SplayTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

    /**
     * Constructs a new Splay tree map that uses natural ordering of keys when
     * performing comparisons
     */
    public SplayTreeMap() {
        super(null);
    }

    /**
     * Constructs a new Splay tree map that uses a provided {@link Comparator} when
     * performing comparisons of keys within the tree
     * @param compare the way that keys will be compared 
     */
    public SplayTreeMap(Comparator<K> compare) {
        super(compare);
    }

    /**
     * The splay operation rotates the given position, p, to the root of the tree
     * 
     * @param p the position to splay to be the root of the tree
     */
    private void splay(Position<Entry<K, V>> p) {
        Position<Entry<K, V>> node = p;
        //Splay until x is the root
        while(!isRoot(node)) {
        	Position<Entry<K, V>> parent = parent(node);
        	Position<Entry<K, V>> grandparent = parent(parent);
        	
        	if(grandparent == null) {
        		//zig single rotation of node up
        		rotate(node);
        	}
        	else if((node.equals(left(parent)) && parent.equals(left(grandparent))) || 
        			(node.equals(right(parent)) && parent.equals(right(grandparent)))) {
        		
        		//zig zig operation
        		//Rotate parent up then node up
        		rotate(parent);
        		rotate(node);
        	}
        	else {
        		//zig zag normal trinode restructuring
        		//Just rotate node up twice
        		rotate(node);
        		rotate(node);
        	}
        }
    }

    /**
     * {@inheritDoc} For a Splay tree, we must rotate position p to be the root of
     * the tree. If the position p is a leaf (sentinel) position, we splay the
     * parent of p.
     */
    protected void actionOnAccess(Position<Entry<K, V>> p) {
    	 Position<Entry<K, V>> node = p;
        if (isLeaf(node)) {
            node = parent(node);
        }
        if (node != null) {
            splay(node);
        }
    }

    /**
     * {@inheritDoc} For a Splay tree, we must rotate position p to be the root of
     * the tree.
     */
    protected void actionOnInsert(Position<Entry<K, V>> p) {
        splay(p);
    }

    /**
     * {@inheritDoc} For a Splay tree, we must rotate the parent of position p to be
     * the root of the tree.
     */
    protected void actionOnDelete(Position<Entry<K, V>> p) {
        if (!isRoot(p)) {
            splay(parent(p));
        }
    }
}