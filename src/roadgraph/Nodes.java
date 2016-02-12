package roadgraph;
import java.util.LinkedList;
import java.util.List;

import geography.GeographicPoint;
public class Nodes implements Comparable<Object>{
	
	private GeographicPoint location;
	private List<Edges> lstEdges;
	private double distance;
	private double actualDistance;
	public Nodes(GeographicPoint location) {
		// TODO Auto-generated constructor stub
		this.setLocation(location);
		this.setLstEdges(new LinkedList<Edges>());
		distance = 0.0;
		actualDistance = 0.0;
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

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Nodes m = (Nodes)o; 
		return ((Double)this.getDistance()).compareTo((Double) m.getDistance());
		
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getActualDistance() {
		return actualDistance;
	}

	public void setActualDistance(double actualDistance) {
		this.actualDistance = actualDistance;
	}
	
}
