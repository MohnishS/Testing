package com.mca.model;

import java.util.ArrayList;

public class TrainId {

	private String trainId;
	private String source;
	private String destination;
	private ArrayList<String> haltingStations;
	private ArrayList<Integer> timeTakenFromSource;
	private ArrayList<String> arrivesOnPlatformNo;
	private ArrayList<String> departureTiming;
	private boolean isFast;
	private int lineId;
	
	
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
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
	public ArrayList<String> getHaltingStations() {
		return haltingStations;
	}
	public void setHaltingStations(ArrayList<String> haltingStations) {
		this.haltingStations = haltingStations;
	}
	public ArrayList<Integer> getTimeTakenFromSource() {
		return timeTakenFromSource;
	}
	public void setTimeTakenFromSource(ArrayList<Integer> timeTakenFromSource) {
		this.timeTakenFromSource = timeTakenFromSource;
	}
	public ArrayList<String> getArrivesOnPlatformNo() {
		return arrivesOnPlatformNo;
	}
	public void setArrivesOnPlatformNo(ArrayList<String> arrivesOnPlatformNo) {
		this.arrivesOnPlatformNo = arrivesOnPlatformNo;
	}
	public ArrayList<String> getDepartureTiming() {
		return departureTiming;
	}
	public void setDepartureTiming(ArrayList<String> deptTiming) {
		this.departureTiming = deptTiming;
	}
	public boolean isFast() {
		return isFast;
	}
	public void setFast(boolean isFast) {
		this.isFast = isFast;
	}
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
}
