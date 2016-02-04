package roadgraph;

import geography.GeographicPoint;

public class Edges {
	
	private GeographicPoint start;
	private GeographicPoint end;
	private double length;
	private String roadName;
	private String roadType;
	/*
	 * Additional attributes will be defined later
	 */
	
	public Edges(GeographicPoint start, GeographicPoint end, double length,String roadName, String roadType) {
		// TODO Auto-generated constructor stub
		this.setStart(start);
		this.setEnd(end);
		this.setLength(length);
		this.setRoadName(roadName);
		this.setRoadType(roadType);
	}
	
	
	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}


	
	public GeographicPoint getStart() {
		return start;
	}

	public void setStart(GeographicPoint start2) {
		this.start = start2;
	}

	public GeographicPoint getEnd() {
		return end;
	}

	public void setEnd(GeographicPoint end2) {
		this.end = end2;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}



}
