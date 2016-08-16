package com.mca.views;

import java.util.ArrayList;

import android.widget.ListView;
import android.widget.Toast;

import com.mca.activity.MainActivity;
import com.mca.adapters.TrainListAdapter;
import com.mca.model.Constants;
import com.mca.model.TrainId;
import com.mca.model.Utils;
import com.mca.routes.R;

public class TrainListScreen {
	
private MainActivity context;
private String source;
private String destination;
private int lineId;
private ArrayList<TrainId> availableTrains;
private ListView listView;
	
	public TrainListScreen(MainActivity context, Object[] args) {

		this.context = context;
		source = args[0].toString();
		destination = args[1].toString();
		lineId = (Integer) args[2];
		int size = Utils.trainIds.size();
		availableTrains = new ArrayList<TrainId>();		
		
		for(int i = 0; i < size; i++)
		{
			TrainId trainId = Utils.trainIds.get(i);
			if(trainId.getHaltingStations().contains(source) && trainId.getHaltingStations().contains(destination))
			{
				if(trainId.getHaltingStations().indexOf(source) < trainId.getHaltingStations().indexOf(destination))
				{
						availableTrains.add(trainId);
//						Toast.makeText(context, "inside bus list screen "+availableTrains.get(i).getDepartureTiming(), Toast.LENGTH_LONG).show();
				}
			}
		}
		
		show();
		TrainListAdapter adapter = new TrainListAdapter(context, availableTrains, source);
		listView = (ListView) context.findViewById(R.id.listView);
		listView.setAdapter(adapter);
	}
	
	private void show()
	{
		context.setContentView(R.layout.simple_list_view);
	}

	public void onBackPressed() {
		context.switchToScreen(Constants.TRAIN_SELECTION_SCREEN, lineId);
	}

}
