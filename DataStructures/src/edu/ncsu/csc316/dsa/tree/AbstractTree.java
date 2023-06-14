package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;

/**
 * A skeletal implementation of the Tree abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the tree
 * abstract data type.
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 *
 * @param <E> the type of elements stored in the tree
 */
public abstract class AbstractTree<E> implements Tree<E> {

	@Override
	public boolean isInternal(Position<E> p) {
		return numChildren(p) > 0;
	}

	@Override
	public boolean isLeaf(Position<E> p) {
		return numChildren(p) == 0;
	}

	@Override
	public boolean isRoot(Position<E> p) {
		return p == root();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public E set(Position<E> p, E value) {

		AbstractTreeNode<E> temp = validate(p);
		E ret = p.getElement();
		temp.setElement(value);
		return ret;

	}

	@Override
	public Iterable<Position<E>> preOrder() {
		PositionCollection traversal = new PositionCollection();
		if (!isEmpty()) {
			preOrderHelper(root(), traversal);
		}
		return traversal;
	}

	private void preOrderHelper(Position<E> p, PositionCollection traversal) {
		traversal.add(p);
		for (Position<E> c : children(p)) {
			if(c.getElement() != null) {
				preOrderHelper(c, traversal);
			}
			
		}
	}

	@Override
	public Iterable<Position<E>> postOrder() {
		PositionCollection traversal = new PositionCollection();
		
		//Not empty add the elements to the traversal
		if(!isEmpty()) {
			postOrderHelper(root(), traversal);
		}
		return traversal;
	}
	
	
	private void postOrderHelper(Position<E> p, PositionCollection traversal) {
        //Children first then root
		for(Position<E> child : children(p)) {
			
			if(child.getElement() != null) {
				postOrderHelper(child, traversal);
			}
		}
		traversal.add(p);
    }

	@Override
	public Iterable<Position<E>> levelOrder() {
		PositionCollection traversal = new PositionCollection();
		
		Queue<Position<E>> helperCalls = new ArrayBasedQueue<Position<E>>();
		
		//Not empty add the elements to the traversal
		if(!isEmpty()) {
			helperCalls.enqueue(root());
			//As soon as the node leaves put its children into the queue
			//Keep dequeuing until there are no more elements to add
			while(!helperCalls.isEmpty()) {
				Position<E> current = helperCalls.dequeue();
				for(Position<E> children : children(current)) {
					if(children.getElement() != null) {
						helperCalls.enqueue(children);
					}
					
				}
				traversal.add(current);
			}
			
		}
		
		
		return traversal;
	

	}
	
	
	
	/**
	 * Safely casts a Position, p, to be an AbstractTreeNode.
	 * 
	 * @param p the position to cast to a AbstractTreeNode
	 * @return a reference to the AbstractTreeNode
	 * @throws IllegalArgumentException if p is null, or if p is not a valid
	 *                                  AbstractTreeNode
	 */
	protected abstract AbstractTreeNode<E> validate(Position<E> p);

	protected abstract static class AbstractTreeNode<E> implements Position<E> {
		/** The element encapsulated by a tree node */
		private E element;

		public AbstractTreeNode(E element) {
			setElement(element);
		}

		@Override
		public E getElement() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
		toStringHelper(sb, "", root());
		sb.append("]");
		return sb.toString();
	}

	private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
		if (root == null) {
			return;
		}
		sb.append(indent).append(root.getElement()).append("\n");
		for (Position<E> child : children(root)) {
			toStringHelper(sb, indent + " ", child);
		}
	}

	/**
	 * PositionCollection implements the {@link Iterable} interface to allow
	 * traversing through the positions of the tree. PositionCollection does not
	 * allow removal operations
	 * 
	 * @author Dr. King
	 *
	 */
	protected class PositionCollection implements Iterable<Position<E>> {
		/** The underlying List of Positions in the Tree */
		private List<Position<E>> list;

		/**
		 * Construct a new PositionCollection
		 */
		public PositionCollection() {
			list = new SinglyLinkedList<Position<E>>();
		}

		/**
		 * Add a position to the collection. Null positions or positions with null
		 * elements are not added to the collection
		 * 
		 * @param position the position to add to the collection
		 */
		public void add(Position<E> position) {
			if (position != null && position.getElement() != null) {
				list.addLast(position);
			}
		}

		/**
		 * Return an iterator for the PositionCollection
		 * 
		 * @return An iterator over the elements in the Tree
		 */
		public Iterator<Position<E>> iterator() {
			return new PositionSetIterator();
		}

		private class PositionSetIterator implements Iterator<Position<E>> {
			/** The encapsulated list iterator that allows traversal in order */
			private Iterator<Position<E>> it;

			public PositionSetIterator() {
				it = list.iterator();
			}

			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public Position<E> next() {
				return it.next();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("The remove operation is not supported yet.");
			}
		}
	}
}