package roadgraph;

import geography.GeographicPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public abstract class GraphSearch {
	public abstract List<GeographicPoint> search();
	
	public List<GeographicPoint> path(MapGraph graph,GeographicPoint start,GeographicPoint end,Map<GeographicPoint,GeographicPoint> pathTo ){
		List<GeographicPoint> list=new ArrayList<GeographicPoint>();
		GeographicPoint gp=end;
		if(!pathTo.containsKey(gp)) return null;
		
		while(!gp.equals(start)){
			list.add(0,gp);
			gp=pathTo.get(gp);
		}
		list.add(start);
		Collections.reverse(list);
		
		return list;
	}

}
