package com.mca.model;

import java.util.ArrayList;

public class BusId {

	
private int busId;
private String source;
private String destination;
private ArrayList<String> busStops;
private boolean isRouteinDownDirection = false;

public boolean isRouteinDownDirection() {
	return isRouteinDownDirection;
}
public void setRouteinDownDirection(boolean isRouteinDownDirection) {
	this.isRouteinDownDirection = isRouteinDownDirection;
}
public int getBusId() {
	return busId;
}
public void setBusId(int busId) {
	this.busId = busId;
}
public String getSource() {
	return source;
}
public void setSource(String source) {
	this.source = source;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public ArrayList<String> getBusStops() {
	return busStops;
}
public void setBusStops(ArrayList<String> busStops) {
	this.busStops = busStops;
}


}
