package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue.AdaptablePQEntry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * ShortestPathUtil provides a collection of behaviors for computing shortest
 * path spanning trees for a given graph.
 * 
 * The ShortestPathUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 * @author Sean Hinton (sahinto2)
 */
public class ShortestPathUtil {
    
    /**
     * For a connected graph, returns a map that represents shortest path costs to
     * all vertices computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph the graph for which to compute the shortest path spanning tree
     * @param start the vertex at which to start computing the shorest path spanning
     *              tree
     * @return a map that represents the shortest path costs to all vertices in the
     *         graph
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> graph, Vertex<V> start) {
        
        //NOTE: since Dijkstra's algorithm is very similar to Prim-Jarnik's algorithm,
        //     you should review the provided Prim-Jarnik implementation in the next
        //     section of the lab on Minimum Spanning Trees
    	AdaptablePriorityQueue<Integer, Vertex<V>> order = new HeapAdaptablePriorityQueue<Integer, Vertex<V>>();
    	Map<Vertex<V>, Integer> costs = new LinearProbingHashMap<Vertex<V>, Integer>();
    	Set<Vertex<V>> found = new HashSet<Vertex<V>>();
    	Map<Vertex<V>, AdaptablePQEntry<Integer, Vertex<V>>> associatedEdge = new LinearProbingHashMap<Vertex<V>, AdaptablePQEntry<Integer, Vertex<V>>>();
    	
    	for(Vertex<V> v : graph.vertices()) {
    		if(v.equals(start)) {
    			costs.put(v, 0);
    		}
    		
    		else {
    			costs.put(v, Integer.MAX_VALUE);
    		}
    		
    		int currentCost = costs.get(v);
    		AdaptablePQEntry<Integer, Vertex<V>> entry = (AdaptablePQEntry<Integer, Vertex<V>>) order.insert(currentCost, v);
    		associatedEdge.put(v, entry);
    	}
    	
    	while(!order.isEmpty()) {
    		AdaptablePQEntry<Integer, Vertex<V>> entry = (AdaptablePQEntry<Integer, Vertex<V>>) order.deleteMin();
    		Vertex<V> u = entry.getValue();
    		found.add(u);
    		for(Edge<E> e : graph.outgoingEdges(u)) {
    			Vertex<V> z = graph.opposite(u, e);
    			if(!found.contains(z)) {
    				int r = e.getElement().getWeight() + costs.get(u);
    				
    				if(r < costs.get(z)) {
    					costs.put(z, r);
    					order.replaceKey(associatedEdge.get(z), r);
    				}
    			}
    		}
    	}
    	
    	return costs;
    }
    
    /**
     * For a connected graph, returns a map that represents shortest path spanning
     * tree edges computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>       the type of data in the graph vertices
     * @param <E>       the type of data in the graph edges
     * @param graph         the graph for which to compute the shortest path spanning
     *                  tree
     * @param start         the vertex at which to start computing the shortest path
     *                  spanning tree
     * @param costs the map of shortest path costs to reach each vertex in the
     *                  graph
     * @return a map that represents the shortest path spanning tree edges
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> graph, Vertex<V> start, Map<Vertex<V>, Integer> costs) {
        
    	Map<Vertex<V>, Edge<E>> pathCosts = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
    	for(Entry<Vertex<V>, Integer> v : costs.entrySet()) {
    		Vertex<V> current = v.getKey();
    		if(!current.equals(start)) {
    			for(Edge<E> e : graph.incomingEdges(current)) {
    				Vertex<V> oppo = graph.opposite(current, e);
    				if(costs.get(current) == costs.get(oppo) + e.getElement().getWeight()) {
    					pathCosts.put(current, e);
    				}
    			}
    		}
    	}
    	return pathCosts;
    }
}