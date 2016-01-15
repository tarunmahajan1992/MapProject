package roadgraph;

import geography.GeographicPoint;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Consumer;

public class DijkstraSearch extends GraphSearch {
	private MapGraph graph;
	private GeographicPoint start;
	private GeographicPoint end;
	private Queue<GeographicPoint> pq;
	private Map<GeographicPoint, Double> distance;  //Store distance from start for vertices
	private Map<GeographicPoint,GeographicPoint> pathTo;  //Store parent of that node also visited if exists in map
	
	
    public DijkstraSearch(MapGraph graph,GeographicPoint start,GeographicPoint end, Consumer<GeographicPoint> nodeSearched) {
    	this.graph=graph;
		this.start=start;
		this.end=end;
		distance=new HashMap<GeographicPoint, Double>();    
		pathTo=new HashMap<GeographicPoint, GeographicPoint>();
		pq=new PriorityQueue<GeographicPoint>(new Comparator<GeographicPoint>() {

			@Override
			public int compare(GeographicPoint o1, GeographicPoint o2) {
				if(distance.get(o1)>distance.get(o2)) return 1;
				else if(distance.get(o1)<distance.get(o2)) return -1;
				else return 0;
			}
		
		});
		for(GeographicPoint node:graph.getVertices()){
			distance.put(node, Double.POSITIVE_INFINITY);
		}
		distance.put(start, 0.0);
		pq.add(start);
		int count=0;
		while(!pq.isEmpty()){
			count++;
			GeographicPoint node=pq.remove();
			if(node.equals(end)) 
				break;
			nodeSearched.accept(node);
			relax(node);
		}
		System.out.println(count);
		
	}
	

	@Override
	public List<GeographicPoint> search() {
		
		return path(graph,start,end,pathTo);
	}
	
	private void relax(GeographicPoint node){
		for(Edge e:graph.adjNode(node)){
			GeographicPoint to=e.to();
			Double temp=distance.get(node)+e.EdgeLength();
			if(distance.get(to)>temp){
				distance.put(to, temp);
				pathTo.put(to, node);
				if(pq.contains(to)){
					changePQ(to);
				}
				else
					pq.add(to);
				
			}
		}
	}
	/*
	 * Method to change priority queue state when distance of node changes.
	 * We need to notify Queue about this.
	 * @Param GeographicPoint
	 */
	
	private void changePQ(GeographicPoint node){
		pq.remove(node);
		pq.add(node);
		
	}

}
