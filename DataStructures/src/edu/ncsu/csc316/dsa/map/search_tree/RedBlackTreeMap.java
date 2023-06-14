package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * The RedBlackTreeMap is implemented as a linked data structure to support
 * efficient Tree and Map abstract data type behaviors.
 * 
 * A RedBlack tree must satisfy four properties: (1) the root must be black (2)
 * the children of a red node are black (3) all leaves (sentinels) are black (3)
 * the black-depth is the same for all leaves (sentinels) (i.e., every sentinel
 * has the same number of black ancestors)
 * 
 * The properties of a RedBlack tree ensure O(logn) height, and O(logn)
 * worst-case performance for U+0007B;@see Map#put}, U+0007B;@see Map#get}, and
 * U+0007B;@see Map#remove}.
 * 
 * RedBlackTreeMap uses sentinel leaves. Every leaf node should have 2 sentinel
 * children.
 * 
 * The RedBlackTreeMap class is based on the implementation developed for use
 * with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley &#38; Sons, 2014
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 * @param <K> the type of keys stored in the RedBlack tree
 * @param <V> the type of values associated with keys in the RedBlack tree
 */
public class RedBlackTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

    /**
     * Constructs a new red-black tree map that uses natural ordering of keys when
     * performing comparisons
     */
    public RedBlackTreeMap() {
        super(null);
    }

    /**
     * Constructs a new red-black tree map that uses a provided {@link Comparator}
     * when performing comparisons of keys within the tree
     * @param compare the way keys are compared in the tree
     */
    public RedBlackTreeMap(Comparator<K> compare) {
        super(compare);
    }

    /**
     * Returns true if the given position is black (it's property = 0)
     * 
     * @param p the position for which to determine if the color is black
     * @return true if the position's property/color is black
     */
    private boolean isBlack(Position<Entry<K, V>> p) {
        return getProperty(p) == 0;
    }

    /**
     * Returns true if the given position is red (it's property = 1)
     * 
     * @param p the position for which to determine if the color is red
     * @return true if the position's property/color is red
     */
    private boolean isRed(Position<Entry<K, V>> p) {
        return getProperty(p) == 1;
    }

    /**
     * Set the color of the given position to be black (property = 0)
     * 
     * @param p the position for which to make black
     */
    private void makeBlack(Position<Entry<K, V>> p) {
        setProperty(p, 0);
    }

    /**
     * Set the color of the given position to be red (property = 1)
     * 
     * @param p the position for which to make red
     */
    private void makeRed(Position<Entry<K, V>> p) {
        setProperty(p, 1);
    }

    /**
     * Resolves a double-red condition in a red-black tree where a red position has
     * a red child
     * 
     * @param p the position that may have a red parent
     */
    private void resolveRed(Position<Entry<K, V>> p) {
        Position<Entry<K, V>> node = p;
        Position<Entry<K, V>> parent = parent(node);
        if(isRed(parent)) {
        	//Double red so fix it
        	Position<Entry<K, V>> uncle = sibling(parent);
        	//Case 1 of the sibling of parent is black
        	if(isBlack(uncle)) {
        		//Do a trinode restructure to get node on top then color swap
        		Position<Entry<K, V>> middle = restructure(node);
        		//middle to black and its children to red
        		makeBlack(middle);
        		makeRed(left(middle));
        		makeRed(right(middle));
        	}
        	
        	//Case 2 of the sibling of parent is red. Do a split
        	else {
        		makeBlack(uncle);
        		makeBlack(parent);
        		//Might propagate the red red up the tree
        		Position<Entry<K, V>> grandparent = parent(parent);
        		if(!isRoot(grandparent)) {
        			makeRed(grandparent);
        			//Check if there a red child on a red node
        			resolveRed(grandparent);
        		}
        	}
        }
    }

    /**
     * Resolves the double-black condition where the black-depths of the sentinel
     * leaves are no longer equal.
     * 
     * @param p the position at which the double-black condition is located
     */
    private void remedyDoubleBlack(Position<Entry<K, V>> p) {
    	Position<Entry<K, V>> node = p;
    	Position<Entry<K, V>> parent = parent(node);
    	Position<Entry<K, V>> sibling = sibling(node);
    	//Cases 1 and 2 of black sibling
    	if(isBlack(sibling)) {
    		//Case 1 trinode restructure transfer when sibling has a red child
    		if(isRed(left(sibling)) || isRed(right(sibling))) {
    			Position<Entry<K, V>> temp = null;
    			if(isRed(left(sibling))) {
    				temp = left(sibling);
    			}
    			else {
    				temp = right(sibling);
    			}
    			Position<Entry<K, V>> middle = restructure(temp);
    			
    			if(isRed(parent)) {
    				makeRed(middle);
    			}
    			else {
    				makeBlack(middle);
    			}
    			
    			makeBlack(left(middle));
    			makeBlack(right(middle));
    		}
    		
    		else {
    			//Case 2 do recolorings
    			makeRed(sibling);
    			if(isRed(parent)) {
    				makeBlack(parent);
    			}
    			else if(!isRoot(parent)){
    				//Propagate up the double black
    				remedyDoubleBlack(parent);
    			}
    		}
    	}
    	else {
    		//Case 3 red sibling so rotate 
    		rotate(sibling);
    		makeBlack(sibling);
    		makeRed(parent);
    		remedyDoubleBlack(node);
    	}
    }

    /**
     * {@inheritDoc} For a RedBlack tree, we must check that the newly inserted
     * position has not created a double-red condition (i.e., the newly created
     * position is red and has a red parent)
     */
    protected void actionOnInsert(Position<Entry<K, V>> p) {
        if (!isRoot(p)) {
            makeRed(p);
            resolveRed(p);
        }
    }

    /**
     * {@inheritDoc} For a RedBlack tree, we must check that the removed position
     * has not created a double-black condition (i.e., a situation in which the
     * black-depth property of the tree is violated)
     */
    protected void actionOnDelete(Position<Entry<K, V>> p) {
        if (isRed(p)) {
            makeBlack(p);
        } else if (!isRoot(p)) {
            Position<Entry<K, V>> sib = sibling(p);
            if (isInternal(sib) && (isBlack(sib) || isInternal(left(sib)))) {
                remedyDoubleBlack(p);
            }
        }
    }
}