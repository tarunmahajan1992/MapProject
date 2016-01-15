package roadgraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;




import geography.GeographicPoint;

/*
 * @author Tarun Kumar
 * Class GraphBFS
 * Utility class to implement Breadth First search on Graph.
 * It hides details within it. 
 */
public class GraphBFS extends GraphSearch{
	private MapGraph graph;
	private Set<GeographicPoint> visited;
	private HashMap<GeographicPoint,GeographicPoint> pathTo;
	private GeographicPoint start;
	private GeographicPoint end;
	private Queue<GeographicPoint> queue;
	private Consumer<GeographicPoint> nodeSearched;
	
	/*
	 * constructor to initialize variables in Graph BFS class.
	 *@param graph Graph to process.
	 *@param start Starting point in bfs 
	 *@param end end point in bfs algorithm
	 *@nodeSearched hook to be used in front end Application
	 */
	public GraphBFS(MapGraph graph,GeographicPoint start,GeographicPoint end, Consumer<GeographicPoint> nodeSearched){
		this.graph=graph;
		visited=new HashSet<GeographicPoint>();
		pathTo=new HashMap<GeographicPoint, GeographicPoint>();
		this.start=start;
		this.end=end;
		queue=new LinkedList<GeographicPoint>();
		this.nodeSearched=nodeSearched;
	}
	
	/*
	 * Method implementing breadth firsh search on graph.
	 * @return List containing path vertices from start to end.
	 */
	@Override
	public List<GeographicPoint> search(){
		queue.add(start);
		GeographicPoint temp=null;
		pathTo.put(start, null);
		while(!queue.isEmpty()&&!end.equals(temp)){
			temp=queue.remove();
			bfsNodeProcess(temp);
		}
		System.out.println("success");
		return path(graph,start,end,pathTo);
	}
	
	/*
	 * Utility method to process one node in bfs algorithm.
	 * Extract neighbours and put them on queue if not visited.
	 * @param node vertex or node in graph to process
	 */
	private void bfsNodeProcess(GeographicPoint node){
		nodeSearched.accept(node);
		GeographicPoint temp;
		for(Edge e:graph.adjNode(node)){
			temp=e.to();
			if(!pathTo.containsKey(temp))   //if not visited
			{
			pathTo.put(temp, node);
			queue.add(temp);
			}
		}
		
	}
	
	/*
	 * Method to return path from start to goal includind
	 * start point and end point. Uses PathTo hashmap to extract path.
	 */
	
	
}
