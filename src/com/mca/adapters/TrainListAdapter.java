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
import com.mca.model.TrainId;
import com.mca.routes.R;

	public class TrainListAdapter extends BaseAdapter{
		private MainActivity context;
		private ArrayList<TrainId> availableTrains;
		private String source;
		

		public TrainListAdapter(MainActivity context, ArrayList<TrainId> availableTrains, String source) {
			this.context = context;
			this.availableTrains = availableTrains;
			this.source = source;
			/*for(int i = 0; i < availableTrains.size(); i++)
			{
				int size = availableTrains.get(i).getDepartureTiming().size();
				for(int j = 0; j < size; j++)
				{
					Toast.makeText(context, "lets c"+availableTrains.get(i).getDepartureTiming().get(j), Toast.LENGTH_LONG).show();
				}
			}*/
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return availableTrains.size();
		}

		@Override
		public TrainId getItem(int arg0) {
			// TODO Auto-generated method stub
			return availableTrains.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@SuppressLint("ViewHolder")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if(convertView == null)
			{
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.train_list_screen, parent, false);
			}
			TextView arrivingTime = (TextView) convertView.findViewById(R.id.arrivingTime);
			TextView destination = (TextView) convertView.findViewById(R.id.destination);
			TextView routeDescription = (TextView) convertView.findViewById(R.id.routeDescription);
			arrivingTime.setText(availableTrains.get(position).getDepartureTiming().get(availableTrains.get(position).getHaltingStations().indexOf(source)) + "");
//			arrivingTime.setText(availableTrains.get(position).getDepartureTiming().get(position) + "");
			destination.setText(availableTrains.get(position).getDestination());
			routeDescription.setText(availableTrains.get(position).getSource()+" - "+availableTrains.get(position).getDestination());
			
			return convertView;
		}

	}


