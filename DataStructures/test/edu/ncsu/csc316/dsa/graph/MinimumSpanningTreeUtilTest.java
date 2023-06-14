package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;

import edu.ncsu.csc316.dsa.list.positional.PositionalList;




/**
 * Tests Kruskal's and Prim's minimum spanning tree algorithm
 * @author Sean Hinton (sahinto2)
 *
 */
public class MinimumSpanningTreeUtilTest {

	/**Graph for testing*/
	private Graph<String, Path> graph;
	
	/**Vertex for testing*/
	private Vertex<String> a;
	/**Vertex for testing*/
	private Vertex<String> b;
	/**Vertex for testing*/
	private Vertex<String> c;
	/**Vertex for testing*/
	private Vertex<String> d;
	
	
	/**
	 * Sets up the graph
	 */
	@Before
	public void setUp() {
		// Make it undirected
		graph = new AdjacencyListGraph<String, Path>();
		
		a = graph.insertVertex("A");
		b = graph.insertVertex("B");
		c = graph.insertVertex("C");
		d = graph.insertVertex("D");
		graph.insertEdge(a, d, new Path(2));
		graph.insertEdge(d, b, new Path(2));
		graph.insertEdge(d, c, new Path(1));
		graph.insertEdge(a, c, new Path(99));
		graph.insertEdge(c, b, new Path(3));
		graph.insertEdge(b, c, new Path(1));
		
		
	}
	/**
	 * Tests Kruskal's algorithm
	 */
	@Test
	public void testKruskal() {
		PositionalList<Edge<Path>> result = MinimumSpanningTreeUtil.kruskal(graph);
		
		assertEquals(3, result.size());
		Iterator<Edge<Path>> it = result.iterator();
		assertEquals(1, it.next().getElement().getWeight());
		assertEquals(1, it.next().getElement().getWeight());
		assertEquals(2, it.next().getElement().getWeight());
		assertFalse(it.hasNext());
	}
	
	/**
	 * Tests Prim's algorithm
	 *
	 */
	@Test
	public void testPrims() {
		PositionalList<Edge<Path>> result = MinimumSpanningTreeUtil.primJarnik(graph);
		Iterator<Edge<Path>> it = result.iterator();
		assertEquals(2, it.next().getElement().getWeight());
		assertEquals(1, it.next().getElement().getWeight());
		assertEquals(1, it.next().getElement().getWeight());
		assertFalse(it.hasNext());
	}

	private class Path implements Weighted {

		
		/**The length of the path*/
		private int length;
		
		/**
		 * Constructor
		 * @param l the length of the path
		 */
		public Path(int l) {
			this.length = l;
		}
		
		@Override
		public int getWeight() {
			
			return getLength();
		}

		/**
		 * Gets the length of the a path
		 * @return the length of the path
		 */
		private int getLength() {
			return length;
		}
		
	}
	
	
	
}
