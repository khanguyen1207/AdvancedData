/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 2
	
	private HashMap<GeographicPoint, Nodes> nodeList;
	Set<GeographicPoint> nodeSet;
	Set<Edges> edgesSet;
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 2
		nodeList = new HashMap<GeographicPoint, Nodes>();
		edgesSet = new HashSet<Edges>();
		nodeSet = nodeList.keySet();
		System.out.println(nodeList);
	}
	
	public HashMap<GeographicPoint, Nodes> getNodeList() {
		return nodeList;
	}

	public void setNodeList(HashMap<GeographicPoint, Nodes> nodeSet) {
		this.nodeList = nodeSet;
	}
	
	private Nodes getNodeFromPoint(GeographicPoint location){
		return nodeList.get(location);
	}
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 2
		return nodeSet.size();
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 2
		return nodeSet;
	}
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 2
		return edgesSet.size();
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 2
		if (location == null || nodeSet.contains(location))
			return false;
		
		nodeList.put(location, new Nodes(location));
		return true;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 2
		Edges temp = new Edges(from, to, length, roadName, roadType);
		nodeList.get(from).addEdges(temp);
		edgesSet.add(temp);
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 2
		//System.out.println("Con cac");
		LinkedList<GeographicPoint> result = new LinkedList<GeographicPoint>();
		//LinkedList<GeographicPoint> myStack;
		HashMap<Nodes,Nodes> hashmap = new HashMap<Nodes, Nodes>();
		HashMap<Nodes,Nodes> visitedNodes = new HashMap<Nodes, Nodes>();
		Queue<Nodes> myQueue = new LinkedList<Nodes>();
		try {
			myQueue.add(nodeList.get(start));
			//System.out.println("Con cac 1");
			
		} catch (Exception e){
			
		}
		Nodes temp;
		while (!myQueue.isEmpty()){
			temp = myQueue.poll();
			visitedNodes.put(temp, temp);
			//System.out.println(temp.getLocation());
			if (temp.equals(nodeList.get(goal))){
				result.addLast(temp.getLocation());
				while (hashmap.containsKey(temp)){
					temp = hashmap.get(temp);
					result.addFirst(temp.getLocation());
				}
				return result;
			} 
			else {
				for (int i = 0; i < temp.getLstEdges().size(); i++){
					//System.out.println(temp.getLstEdges().size() + " Size of edge list");
					if (!visitedNodes.containsKey(getNodeFromPoint(temp.getEndLocationByIndex(i)))){
						//.out.println(temp.getEndLocationByIndex(i) + " Adding new location");
						hashmap.put(getNodeFromPoint(temp.getEndLocationByIndex(i)), temp);
						myQueue.add(getNodeFromPoint(temp.getEndLocationByIndex(i)));
					}
				}
			}
			
		}
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());

		return null;
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		//Comperator<Edges>
		HashSet<Nodes> visitedSet = new HashSet<Nodes>();
		HashMap<Nodes, Nodes> parentMap = new HashMap<Nodes, Nodes>();
		Nodes goalNode = nodeList.get(goal);
		PriorityQueue<Nodes> myQueue = new PriorityQueue<Nodes>();
		myQueue.add(nodeList.get(start));
		Nodes temp;
		while (!myQueue.isEmpty()){
			temp = myQueue.remove();
			if (!visitedSet.contains(temp)){
				visitedSet.add(temp);
				if (!temp.equals(goalNode)){
					
				}
			}
		}
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		
		return null;
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		
		/* Use this code in Week 3 End of Week Quiz
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
		
	}
	
}
