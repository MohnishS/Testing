package com.mca.views;

import java.util.ArrayList;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mca.activity.MainActivity;
import com.mca.adapters.BusListAdapter;
import com.mca.model.BusId;
import com.mca.model.Constants;
import com.mca.model.Utils;
import com.mca.routes.R;

public class BusListScreen {
	
	private MainActivity context;
	private String source, destination;
	private ListView listView;
	private ArrayList<BusId> availableBuses;

	public BusListScreen(final MainActivity context, Object[] args) {
		this.context = context;
		source = args[0].toString();
		destination = args[1].toString();
		int size = Utils.busIds.size();
		availableBuses = new ArrayList<BusId>();		
		
		for(int i = 0; i < size; i++)
		{
			BusId busId = Utils.busIds.get(i);
			if(busId.getBusStops().contains(source) && busId.getBusStops().contains(destination))
			{
				if(busId.getBusStops().indexOf(source) > busId.getBusStops().indexOf(destination))
				{
					busId.setRouteinDownDirection(true);
				}
				availableBuses.add(busId);
//				Toast.makeText(context, "inside bus list screen "+i, Toast.LENGTH_LONG).show();
			}
		}		
		show();
		BusListAdapter adapter = new BusListAdapter(context, availableBuses);
		listView = (ListView) context.findViewById(R.id.listView);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				context.switchToScreen(Constants.BUS_STOPS_LIST_SCREEN, availableBuses.get(position));
			}
		});
	}
	
	public void show() {
		// TODO Auto-generated method stub
		context.setContentView(R.layout.simple_list_view);
	}

}
