package com.mca.adapters;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.mca.activity.MainActivity;
import com.mca.model.BusId;
import com.mca.routes.R;

public class BusListAdapter extends BaseAdapter{
	private MainActivity context;
	private ArrayList<BusId> availableBuses;
	

	public BusListAdapter(MainActivity context, ArrayList<BusId> availableBuses) {
		this.context = context;
		this.availableBuses = availableBuses;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return availableBuses.size();
	}

	@Override
	public BusId getItem(int arg0) {
		// TODO Auto-generated method stub
		return availableBuses.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		Toast.makeText(context, "inside getview "+availableBuses.size(), Toast.LENGTH_LONG).show();

		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.bus_list_screen, parent, false);
		}
		TextView busNo = (TextView) convertView.findViewById(R.id.busNo);
		TextView routeDescription = (TextView) convertView.findViewById(R.id.routeDescription);
		busNo.setText(availableBuses.get(position).getBusId() + "");
		if(availableBuses.get(position).isRouteinDownDirection())
		{
			routeDescription.setText(availableBuses.get(position).getDestination() + " - " + availableBuses.get(position).getSource());
		}
		else
		{
			routeDescription.setText(availableBuses.get(position).getSource() + " - " + availableBuses.get(position).getDestination());
		}
		return convertView;
	}

}
