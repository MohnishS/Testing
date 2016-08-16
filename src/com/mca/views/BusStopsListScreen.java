package com.mca.views;

import java.util.ArrayList;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mca.activity.MainActivity;
import com.mca.model.BusId;
import com.mca.routes.R;

public class BusStopsListScreen {
	private MainActivity context;
	private BusId busId;
	private ArrayAdapter<String> arrayAdapter;
	private String[] busStops;
	private ListView listView;
	
	public BusStopsListScreen(MainActivity context, Object[] args) {
		this.context = context;
		this.busId = (BusId) args[0];
		show();
		listView = (ListView) context.findViewById(R.id.listView);
		busStops = new String[busId.getBusStops().size()];

		for(int i = 0; i < busStops.length; i++)
		{
			if(!busId.isRouteinDownDirection())
			{
				busStops[i] = busId.getBusStops().get(i);
			}
			else
			{
				busStops[i] = busId.getBusStops().get(busStops.length - 1 - i);
			}
		}
		Toast.makeText(context, "bus stops length "+busStops.length, Toast.LENGTH_LONG).show();
		arrayAdapter = new ArrayAdapter<String>(context, R.layout.simple_text_view, busStops);
		listView.setAdapter(arrayAdapter);

	}
	
	public void show() {
		context.setContentView(R.layout.simple_list_view);
	}

}
