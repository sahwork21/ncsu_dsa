package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * Tests Dijkstra's algorithm and shortest spanning tree algorithm
 * @author Sean Hinton (sahinto2)
 *
 */
public class ShortestPathUtilTest {

	
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
	/**Costs from start to a given vertex*/
	private Map<Vertex<String>, Integer> costs;
	
	/**
	 * Sets up the graph
	 */
	@Before
	public void setUp() {
		
		graph = new AdjacencyListGraph<String, Path>(true);
		costs = new LinearProbingHashMap<Vertex<String>, Integer>();
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
		
		costs.put(d, 2);
		costs.put(a, 0);
		costs.put(b, Integer.MAX_VALUE);
		costs.put(c, 99);
	}
	
	/**
	 * Test Dijkstra's algorithm
	 */
	@Test
	public void testDijkstras() {
		Map<Vertex<String>, Integer> results = ShortestPathUtil.dijkstra(graph, a);
		
		assertEquals(4, results.size());
		assertEquals(0, results.get(a).intValue());
		assertEquals(2, results.get(d).intValue());
		assertEquals(3, results.get(c).intValue());
		assertEquals(4, results.get(b).intValue());
	}
	
	/**
	 * Tests that the algorithm returns the shortest spanning tree
	 */
	@Test
	public void testShortestPath() {
		Map<Vertex<String>, Edge<Path>> results = ShortestPathUtil.shortestPathTree(graph, a, costs);
		assertEquals(2, results.size());
		assertEquals(2, results.get(d).getElement().getWeight());
		assertEquals(99, results.get(c).getElement().getWeight());
		
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
