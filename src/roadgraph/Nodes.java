package roadgraph;
import java.util.LinkedList;
import java.util.List;

import geography.GeographicPoint;
public class Nodes {
	
	private GeographicPoint location;
	private List<Edges> lstEdges;
	
	public Nodes(GeographicPoint location) {
		// TODO Auto-generated constructor stub
		this.setLocation(location);
		this.setLstEdges(new LinkedList<Edges>());
	}

	public List<Edges> getLstEdges() {
		return lstEdges;
	}

	public void setLstEdges(List<Edges> lstEdges) {
		this.lstEdges = lstEdges;
	}

	public GeographicPoint getLocation() {
		return location;
	}
	
	public GeographicPoint getEndLocationByIndex(int i){
		return this.getLstEdges().get(i).getEnd();
	}
	public void setLocation(GeographicPoint location) {
		this.location = location;
	}
	
	public void addEdges(Edges edge){
		lstEdges.add(edge);
	}
	
}
