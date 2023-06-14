package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for BFS and DFS graph traversal
 * @author sahin
 *
 */
public class GraphTraversalUtilTest {

	/**
	 * Graph for testing
	 */
	private Graph<String, Integer> graph;
	/**Vertex for testing*/
	private Vertex<String> a;
	/**Vertex for testing*/
	private Vertex<String> b;
	/**Vertex for testing*/
	private Vertex<String> c;
	/**Vertex for testing*/
	private Vertex<String> d;
	/**Vertex for testing*/
	private Vertex<String> e;
	/**Vertex for testing*/
	private Vertex<String> f;
	/**Vertex for testing*/
	private Vertex<String> g;
	/**
	 * Creates a new graph before each test
	 * 
	 * A -> B -> C ->D
	 * |   / | \      ^
	 * V  7   V   7   |
	 * E  ->  F   -> G
	 */
	@Before
	public void setUp() {
		graph = new AdjacencyListGraph<String, Integer>(true);
		
		a = graph.insertVertex("A");
		b = graph.insertVertex("B");
		c = graph.insertVertex("C");
		d = graph.insertVertex("D");
		e = graph.insertVertex("E");
		f = graph.insertVertex("F");
		g = graph.insertVertex("G");
		
		graph.insertEdge(a, b, 1);
		graph.insertEdge(a, e, 2);
		graph.insertEdge(b, e, 3);
		graph.insertEdge(b, f, 4);
		graph.insertEdge(b, g, 5);
		graph.insertEdge(b, d, 6);
		graph.insertEdge(f, g, 7);
		graph.insertEdge(g, d, 7);
		graph.insertEdge(c, d, 8);
		graph.insertEdge(e, f, 9);
		graph.insertEdge(b, c, 10);
		
		
		
		
		
	}
	
	/**
	 * Tests BFS traversal
	 */
	@Test
	public void testBFS() {
		Map<Vertex<String>, Edge<Integer>> result = GraphTraversalUtil.breadthFirstSearch(graph, a);
		
		//Now check that it has the right edges
		assertEquals(6, result.size());
		assertEquals(1, result.get(b).getElement().intValue());
		assertEquals(2, result.get(e).getElement().intValue());
		
		//Now we are on edge b
		assertEquals(4, result.get(f).getElement().intValue());
		assertEquals(5, result.get(g).getElement().intValue());
		assertEquals(6, result.get(d).getElement().intValue());
		assertEquals(10, result.get(c).getElement().intValue());
		
		
		//Now check from a different edge
		result = GraphTraversalUtil.breadthFirstSearch(graph, b);
		assertEquals(5, result.size());
		assertEquals(3, result.get(e).getElement().intValue());
		assertEquals(4, result.get(f).getElement().intValue());
		assertEquals(5, result.get(g).getElement().intValue());
		assertEquals(6, result.get(d).getElement().intValue());
		assertEquals(10, result.get(c).getElement().intValue());
		
		
	}
	
	/**
	 * Tests DFS traversal
	 */
	@Test
	public void testDFS() {
		Map<Vertex<String>, Edge<Integer>> result = GraphTraversalUtil.depthFirstSearch(graph, a);
		
		//Edges are a to b, b to c, c to d. Go back to b. b to e, e to f, f to g
		assertEquals(6, result.size());
		assertEquals(1, result.get(b).getElement().intValue());
		assertEquals(10, result.get(c).getElement().intValue());
		assertEquals(7, result.get(d).getElement().intValue());
		assertEquals(3, result.get(e).getElement().intValue());
		assertEquals(9, result.get(f).getElement().intValue());
		assertEquals(7, result.get(g).getElement().intValue());
		
	}
	
	

}
