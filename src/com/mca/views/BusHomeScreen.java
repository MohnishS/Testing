package com.mca.views;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mca.activity.MainActivity;
import com.mca.model.BusId;
import com.mca.model.Constants;
import com.mca.model.Utils;
import com.mca.routes.R;

public class BusHomeScreen {
	
	private TextView tvSource;
	private TextView tvDest;
	private MainActivity context;
	private String[] busStops = new String[Utils.busStops.size()];
	private Button findBtn;
	private AlertDialog.Builder builder;
	
	public BusHomeScreen(MainActivity context) {
		this.context = context;
		int size = busStops.length;
		for(int i = 0; i < size; i++)
		{
			busStops[i] = Utils.busStops.get(i);
		}
		show();
	}

	public void init() {
		tvSource = (TextView) context.findViewById(R.id.source);
		tvSource.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showBusStopsDialog(true);				
			}
		});
		
		tvDest = (TextView) context.findViewById(R.id.destination);
		tvDest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showBusStopsDialog(false);
			}
		});
		
		findBtn = (Button) context.findViewById(R.id.findBtn);
		findBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String src = tvSource.getText().toString();
				String dest = tvDest.getText().toString();
				if(src.equalsIgnoreCase(dest))
				{
					Toast.makeText(context, "Source and Destination cannot be same", Toast.LENGTH_LONG).show();
				}
				else if(src.equalsIgnoreCase("") || dest.equalsIgnoreCase("")
						|| src.equalsIgnoreCase(context.getString(R.string.select_source)) || dest.equalsIgnoreCase(context.getString(R.string.select_destination)))
				{
					Toast.makeText(context, "Source and Destination should not be empty", Toast.LENGTH_LONG).show();
				}
				else
				{
					context.switchToScreen(Constants.BUS_LIST_SCREEN, src, dest);
				}
				/*int size = Utils.busIds.size();
				int count = 0;
				for(int i = 0; i < size; i++)
				{
					BusId busId = Utils.busIds.get(i);
					if(busId.getBusStops().contains(src) && busId.getBusStops().contains(dest))
					{
						count++;
					}
				}
				Toast.makeText(context, "Count "+count, Toast.LENGTH_LONG).show();*/
			}
		});
	}

	protected void showBusStopsDialog(final boolean isSource) {
		// TODO Auto-generated method stub
		if(builder == null)
		{
			builder = new AlertDialog.Builder(context);
		}
		if(isSource)
		{
			builder.setTitle(R.string.select_source);
		}
		else
		{
			builder.setTitle(R.string.select_destination);
		}
		builder.setItems(busStops, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if(isSource)
				{
					tvSource.setText(busStops[which]);
				}
				else
				{
					tvDest.setText(busStops[which]);
				}
			}
		}).create().show();
	}

	public void show() {
		context.setContentView(R.layout.bus_home_screen);
		init();
	}
}
