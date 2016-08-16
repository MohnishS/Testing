package com.mca.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mca.model.BusId;
import com.mca.model.Constants;
import com.mca.model.TrainId;
import com.mca.model.Utils;
import com.mca.routes.R;
import com.mca.views.BusHomeScreen;
import com.mca.views.BusListScreen;
import com.mca.views.BusStopsListScreen;
import com.mca.views.TrainHomeScreen;
import com.mca.views.TrainListScreen;
import com.mca.views.TrainSelectionScreen;

public class MainActivity extends Activity implements Constants{
	
	private LinearLayout trainLayout;
	private LinearLayout busLayout;
	private BusHomeScreen busHomeScreen;
	private BusListScreen busListScreen;
	private BusStopsListScreen busStopsListScreen;
	private TrainHomeScreen trainHomeScreen;
	private TrainSelectionScreen trainSelectionScreen;
	private TrainListScreen trainListScreen;
	private int currentScreenId;
	public static final int WESTERN_LINE = 1;
	public static final int CENTRAL_LINE = 2;
	public static final int HARBOUR_LINE = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		readFiles();
		setContentView(R.layout.activity_main);
		
		setLayoutListners();
	}

	private void setLayoutListners() {
		// TODO Auto-generated method stub
		trainLayout = (LinearLayout) findViewById(R.id.train);
		busLayout = (LinearLayout) findViewById(R.id.bus);
		
		trainLayout.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switchToScreen(SCREEN_TRAIN_HOME);
			}
		});
		
		busLayout.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				switchToScreen(SCREEN_BUS_HOME);
			}
		});
	}

	private void readFiles()
	{
		readBusInfo();
		readTrainInfo();
	}
	
	private void readBusInfo() {
		String json = null;
	    try {

	        InputStream is = getAssets().open("busNo.json");

	        int size = is.available();

	        byte[] buffer = new byte[size];

	        is.read(buffer);

	        is.close();

	        json = new String(buffer, "UTF-8");
	        JSONObject mainJsonObj = new JSONObject(json);
	        JSONArray mainJsonArray = mainJsonObj.getJSONArray("buses");
	        int length = mainJsonArray.length();
	        Utils.busIds.clear();
	        for(int i = 0; i < length; i++)
	        {
	        	BusId busId = new BusId();
	        	JSONObject busObj = mainJsonArray.getJSONObject(i);
	        	busId.setBusId(busObj.getInt("busNo"));
	        	busId.setSource(busObj.getString("source"));
	        	busId.setDestination(busObj.getString("destination"));
	        	JSONArray stopsArray = busObj.getJSONArray("stops");
	        	int noOfStops = stopsArray.length();
	        	ArrayList<String> busStops = new ArrayList<String>();
	        	for(int j = 0; j < noOfStops; j++)
	        	{
	        		String busStop = stopsArray.getString(j);
	        		busStops.add(busStop);
	        		Utils.addBusStops(busStop);
	        	}
	        	busId.setBusStops(busStops);
	        	Utils.busIds.add(busId);
	        }


	    } catch (IOException ex) {
	        ex.printStackTrace();
	        
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readTrainInfo() {
		String json = null;
	    try {

	        InputStream is = getAssets().open("train.json");
	        int size = is.available();
	        byte[] buffer = new byte[size];
	        is.read(buffer);
	        is.close();

	        json = new String(buffer, "UTF-8");
	        JSONObject mainJsonObj = new JSONObject(json);
	        JSONArray centralTrainArray = mainJsonObj.getJSONArray("central");
	        int length = centralTrainArray.length();
	        Utils.trainIds.clear();
	        for(int i = 0; i < length; i++)
	        {
	        	TrainId trainId = new TrainId();
	        	JSONObject trainObj = centralTrainArray.getJSONObject(i);
	        	trainId.setTrainId(trainObj.getString("trainId"));
	        	trainId.setSource(trainObj.getString("source"));
	        	trainId.setDestination(trainObj.getString("destination"));
	        	int lineId = trainObj.getInt("lineId");
	        	trainId.setLineId(lineId);
	        	trainId.setFast(trainObj.getBoolean("isFast"));
	        	JSONArray stationsArray = trainObj.getJSONArray("stops");
	        	JSONArray timeTakenArray = trainObj.getJSONArray("timeTaken");
	        	JSONArray platformNoArray = trainObj.getJSONArray("platformNo");
	        	int noOfStops = stationsArray.length();
	        	ArrayList<String> haltingStations = new ArrayList<String>();
	        	ArrayList<String> arrivesOnPlatformNo = new ArrayList<String>();
	        	ArrayList<Integer> timeTakenFromSource = new ArrayList<Integer>();
	        	for(int j = 0; j < noOfStops; j++)
	        	{
	        		String stationName = stationsArray.getString(j);
	        		haltingStations.add(stationName);
	        		if(lineId == WESTERN_LINE)
	        		{
	        			Utils.addStationNameWestern(stationName);
	        		}
	        		else if(lineId == CENTRAL_LINE)
	        		{
	        			Utils.addStationNameCentral(stationName);
	        		}
	        		else if(lineId == HARBOUR_LINE)
	        		{
	        			Utils.addStationNameHarbour(stationName);
	        		}
	        		arrivesOnPlatformNo.add(platformNoArray.getString(j));
	        		timeTakenFromSource.add(timeTakenArray.getInt(j));
	        	}
	        	trainId.setHaltingStations(haltingStations);
	        	trainId.setArrivesOnPlatformNo(arrivesOnPlatformNo);
	        	trainId.setTimeTakenFromSource(timeTakenFromSource);
	        	
	        	//Train Timings
	        	/*JSONArray trainTimingArray = trainObj.getJSONArray("trainTiming");
	        	ArrayList<ArrayList<String>> depTimingArrayList = new ArrayList<ArrayList<String>>();
	        	int noOfTrips = trainTimingArray.length();
	        	for (int j = 0; j < noOfTrips; j++) {
	        		ArrayList<String> departureTimings = new ArrayList<String>();
					JSONArray arr = trainTimingArray.getJSONArray(j);
					for(int k = 0; k < arr.length(); k++)
					{
						JSONObject obj = arr.getJSONObject(k);
						JSONArray arr1 = obj.getJSONArray((k+1)+"");
						for(int l = 0; l < arr1.length(); l++)
						{
							departureTimings.add(arr1.getString(l));
						}
					}
					depTimingArrayList.add(departureTimings);
				}
	        	trainId.setDepartureTiming(depTimingArrayList);*/
	        	JSONArray trainTimingArray = trainObj.getJSONArray("trainTiming");
	        	int noOfTrips = trainTimingArray.length();
	        	
	        	for(int j = 0; j < noOfTrips; j++)
	        	{
	        		TrainId trainIdTemp = new TrainId();
	        		trainIdTemp.setDestination(trainId.getDestination());
	        		trainIdTemp.setFast(trainId.isFast());
	        		trainIdTemp.setHaltingStations(trainId.getHaltingStations());
	        		trainIdTemp.setArrivesOnPlatformNo(trainId.getArrivesOnPlatformNo());
	        		trainIdTemp.setLineId(trainId.getLineId());
	        		trainIdTemp.setSource(trainId.getSource());
	        		trainIdTemp.setTimeTakenFromSource(trainId.getTimeTakenFromSource());
	        		trainIdTemp.setTrainId(trainId.getTrainId());
	        		
	        		JSONObject obj = trainTimingArray.getJSONObject(j);
	        		JSONArray arr = obj.getJSONArray(""+(j+1));
	        		ArrayList<String> deptTimings = new ArrayList<String>();
	        		for(int k = 0; k < arr.length(); k++)
	        		{
	        			deptTimings.add(arr.getString(k));
	        		}
	        		trainIdTemp.setDepartureTiming(deptTimings);
	        		Utils.trainIds.add(trainIdTemp);
//	        		Toast.makeText(this, "departure timing "+trainId.getDepartureTiming(), Toast.LENGTH_LONG).show();
	        	}
	        	
	        	
	        	/*Log.i("Mohnish", "Mohnish trainId "+trainId.getTrainId());
	        	Log.i("Mohnish", "Mohnish src "+trainId.getSource());
	        	Log.i("Mohnish", "Mohnish dest "+trainId.getDestination());
	        	Log.i("Mohnish", "Mohnish lineId "+trainId.getLineId());
	        	Log.i("Mohnish", "Mohnish isFast "+trainId.isFast());
	        	Log.i("Mohnish", "Mohnish platform "+trainId.getArrivesOnPlatformNo());
	        	Log.i("Mohnish", "Mohnish time taken  "+trainId.getTimeTakenFromSource());
	        	Log.i("Mohnish", "Mohnish dept timing  "+trainId.getDepartureTiming());
	        	*/
	        }
	    } catch (IOException ex) {
	    	Toast.makeText(this, "IOException "+ex.getMessage(), Toast.LENGTH_LONG).show();
	        ex.printStackTrace();
	        
	    } catch (JSONException e) {
	    	Toast.makeText(this, "JSON Exception "+e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	    catch (Exception e) {
	    	Toast.makeText(this, "Exception "+e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	public void switchToScreen(int screenId, Object... args) {
		currentScreenId = screenId;
		switch (currentScreenId) {
		case SCREEN_HOME:
			setContentView(R.layout.activity_main);
			setLayoutListners();
			break;
			
		case SCREEN_BUS_HOME:
			if(busHomeScreen == null)
			{
				busHomeScreen = new BusHomeScreen(this);
			}
			else
			{
			busHomeScreen.show();
			}
			break;

		case BUS_LIST_SCREEN:
//			if(busListScreen == null)
			{
				busListScreen = new BusListScreen(this, args);
			}
			/*else
			{
				busListScreen.show();
			}*/
			break;
			
		case BUS_STOPS_LIST_SCREEN:
//			if(busStopsListScreen == null)
			{
				busStopsListScreen = new BusStopsListScreen(this, args);
			}
			/*else
			{
				busStopsListScreen.show();
			}*/
			break;
			
		case SCREEN_TRAIN_HOME:
//			if(trainHomeScreen == null)
			{
				trainHomeScreen = new TrainHomeScreen(this);
			}
			/*else
			{
				trainHomeScreen.show();
			}*/
			break;
			
		case TRAIN_SELECTION_SCREEN:
//			if(trainSelectionScreen == null)
			{
				trainSelectionScreen = new TrainSelectionScreen(this, args);
			}
			/*else
			{
				trainSelectionScreen.show();
			}*/
			break;
			
		case TRAIN_LIST_SCREEN:
			trainListScreen = new TrainListScreen(this, args);
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		switch (currentScreenId) {
		case SCREEN_HOME:
			finish();
		
		case SCREEN_BUS_HOME:
		case SCREEN_TRAIN_HOME:
			switchToScreen(SCREEN_HOME);	
			break;
			
		case BUS_LIST_SCREEN:
			switchToScreen(SCREEN_BUS_HOME);
			break;
			
		case BUS_STOPS_LIST_SCREEN:
			switchToScreen(BUS_LIST_SCREEN);
			break;
			
		case TRAIN_LIST_SCREEN:
			trainListScreen.onBackPressed();
//			switchToScreen(TRAIN_SELECTION_SCREEN);
			
		case TRAIN_SELECTION_SCREEN:
			trainSelectionScreen.onBackPressed();
//			switchToScreen(SCREEN_TRAIN_HOME);
		default:
			break;
		}
	}
}
