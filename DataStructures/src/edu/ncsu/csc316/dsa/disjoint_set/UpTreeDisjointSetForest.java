package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * The UpTreeDisjointSetForest is implemented as a forest of linked up-trees.
 * Using balanced union, {@link DisjointSetForest#union} has worst-case runtime
 * of O(1). Using path-compression find, {@link DisjointSetForest#find} has
 * worst-case O(logn), but over time has worst-case runtime O(log*(n))
 * [log-star].
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 * @param <E> the type of elements stored in the disjoint set
 */
public class UpTreeDisjointSetForest<E> implements DisjointSetForest<E> {

    // We need a secondary map to quickly locate an entry within the forest of
    // up-trees
    // NOTE: The textbook implementation does not include this
    // functionality; instead, the textbook implementation leaves
    // the responsibility of tracking positions to the client in a
    // separate map structure
	/**Underlying Map of keys to different UpTree forests*/
    private Map<E, UpTreeNode<E>> map;

    /**
     * Constructs a new DisjointSetForest
     */
    public UpTreeDisjointSetForest() {
        // Use an efficient map!
        map = new LinearProbingHashMap<E, UpTreeNode<E>>();
    }

    /**
     * An UpTreeNode maintains an element, a reference to the node's parent, and (if
     * it's the root of an up-tree) the number of nodes stored within that up-tree.
     * 
     * @author Dr. King
     * @author Sean Hinton(sahinto2)
     * @param <E> generic type of key for a node
     */
    private static class UpTreeNode<E> implements Position<E> {
    	/**The key contained by this node*/
        private E element;
        /**The pointer to the parent node in the set. Is null if it's the root*/
        private UpTreeNode<E> parent;
        /**The number of elements in the tree. Only for roots*/
        private int count;

        /**
         * Constructs a new UpTreeNode with the given element, a reference to itself as
         * the parent, and a count of 1.
         * 
         * @param element the element to store in the new UpTreeNode
         */
        public UpTreeNode(E element) {
            setElement(element);
            setParent(this);
            setCount(1);
        }

        /**
         * Sets the element of the UpTreeNode to the given element
         * 
         * @param element the element to store in the UpTreeNode
         */
        public void setElement(E element) {
            this.element = element;
        }

        @Override
        public E getElement() {
            return element;
        }

        /**
         * Sets the parent of the UpTreeNode to the given UpTreeNode
         * 
         * @param parent the UpTreeNode to set as the current node's parent
         */
        public void setParent(UpTreeNode<E> parent) {
            this.parent = parent;
        }

        /**
         * Returns a reference to the parent of the current UpTreeNode
         * 
         * @return a reference to the parent of the current UpTreeNode
         */
        public UpTreeNode<E> getParent() {
            return parent;
        }

        /**
         * Sets the number of nodes contained in the UpTree rooted at the current
         * UpTreeNode
         * 
         * @param count the number of nodes in the tree
         */
        public void setCount(int count) {
            this.count = count;
        }

        /**
         * If the current UpTreeNode is the root of an up-tree, returns the number of
         * elements stored within the UpTree. Otherwise, if the current UpTreeNode is
         * not the root of an up-tree, count is undefined.
         * 
         * @return the number of elements stored within the UpTree, if the current
         *         UpTreeNode is the root; otherwise, count is undefined.
         */
        public int getCount() {
            return count;
        }
    }

    @Override
    public Position<E> makeSet(E value) {
    	UpTreeNode<E> root = new UpTreeNode<E>(value); 
        map.put(value, root);
        return root;
    }

    @Override
    public Position<E> find(E value) {
        // NOTE: The textbook solution requires the client to keep
        // track of the location of each position in the forest.
        // Our implementation includes a Map to handle this for the
        // client, so we should allow the client to find the set
        // that contains a node by specifying the element
    	
    	
    	//Whenever we go over a root compress it by making it point to the root.
    	//Iterate over every single forest
    	//Only have to compress if the element is in the Map
    	UpTreeNode<E> node = map.get(value);
    	if(node != null) {
    		return findHelper(node);
    		
    	}
    	
    	//The node was not found
        return node;
    }

    private UpTreeNode<E> findHelper(UpTreeNode<E> current) {
        // Implement path-compression find
        //Whenever we iterate over a node point it to the root.
    	
    	
    	//The parent is what we are looking for so recursively call again
    	if(!current.getParent().equals(current)) { //If the parent does not equal the parent recur
    		
    		current.setParent(findHelper(current.getParent()));
    		
    	}
    	
    	//We reached the top of the tree so return so that pointers get corrected.
    	return current.getParent();
    }

    @Override
    public void union(Position<E> s, Position<E> t) {
        UpTreeNode<E> a = validate(s);
        UpTreeNode<E> b = validate(t);
        
        
        //Need to get to the root for each tree
        while(!b.getParent().equals(b)) {
    		
    		b = b.getParent();
    	}
        
        while(!a.getParent().equals(a)) {
    	
    		a = a.getParent();
    	}
        
        //The smaller tree will now point to the bigger tree
        if(a.getCount() <= b.getCount()) {
        	a.setParent(b);
        	
        	
        	
        	b.setCount(b.getCount() + a.getCount());
        }
        else{
        	b.setParent(a);
        	
        	
        	a.setCount(b.getCount() + a.getCount());
        }
    }

    private UpTreeNode<E> validate(Position<E> p) {
        if (!(p instanceof UpTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid up tree node.");
        }
        return (UpTreeNode<E>) p;
    }
}