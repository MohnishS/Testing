package com.mca.model;

import java.util.ArrayList;

public class Utils {
	
	public static ArrayList<BusId> busIds = new ArrayList<BusId>();
	public static ArrayList<String> busStops = new ArrayList<String>();
	public static ArrayList<TrainId> trainIds = new ArrayList<TrainId>();
	public static ArrayList<String> stationNamesWestern = new ArrayList<String>();
	public static ArrayList<String> stationNamesCentral = new ArrayList<String>();
	public static ArrayList<String> stationNamesHarbour = new ArrayList<String>();
	
	public static void addBusStops(String stop)
	{
		if(!busStops.contains(stop))
		{
			busStops.add(stop);
		}
	}
	
	public static void addStationNameWestern(String name)
	{
		if(!stationNamesWestern.contains(name))
		{
			stationNamesWestern.add(name);
		}
	}
	
	public static void addStationNameCentral(String name)
	{
		if(!stationNamesCentral.contains(name))
		{
			stationNamesCentral.add(name);
		}
	}
	
	public static void addStationNameHarbour(String name)
	{
		if(!stationNamesHarbour.contains(name))
		{
			stationNamesHarbour.add(name);
		}
	}
	

}
