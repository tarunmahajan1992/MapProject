package roadgraph;

import geography.GeographicPoint;

/*
 * @author Tarun Kumar
 * Represent Edge or join of two vertices in graph
 * Class Edge implements comparable interface to compare edges with
 * respect to each other if needed.
 */
public class Edge implements Comparable<Edge>{
	private GeographicPoint to;
	private GeographicPoint from;
	private double length;
	private String name;
	private String roadType;
	
	public Edge(){
		
	}
	
	public Edge(GeographicPoint from,GeographicPoint to,double length,String name,String type){
		this.from=from;
		this.to=to;
		this.length=length;
		this.name=name;
		this.roadType=type;
	}
	
	public GeographicPoint to(){
		return this.to;
	}
	
	public GeographicPoint from(){
		return this.from;
	}
	
	public double EdgeLength(){
		return this.length;
	}
	public String name(){
		return this.name;
	}
	public String roadtype(){
		return roadType;
	}

	@Override
	public int compareTo(Edge that) {
		if(this.length>that.length) return 1;
		else if(this.length<that.length) return -1;
		else return 0;
	}
	
	@Override
	/*
	 * Overriden hashCode function so that we can use edge in
	 * java HashSet and HashMap classes.
	 */
	public int hashCode(){
		int hash = 17;
		hash = 31 * hash + to.hashCode();
		hash = 31 * hash + from.hashCode();
		hash = 31 * hash + roadType.hashCode();
		hash = 31 * hash + name.hashCode() + (int)length*31;
		return hash;
	}

}
