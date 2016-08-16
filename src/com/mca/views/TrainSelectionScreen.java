package com.mca.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mca.activity.MainActivity;
import com.mca.model.Constants;
import com.mca.model.Utils;
import com.mca.routes.R;

public class TrainSelectionScreen implements OnClickListener {
	
private MainActivity context;
private EditText etSource, etDest;
private Button btnFind;
private AlertDialog.Builder builder;
private int lineId, size;
private String[] stationNames;

	public TrainSelectionScreen(MainActivity context, Object[] args) {
		this.context = context;
		lineId = (Integer) args[0];
		switch (lineId) {
		case MainActivity.WESTERN_LINE:
			size = Utils.stationNamesWestern.size();
			stationNames = new String[size];
			for(int i = 0; i < size; i++)
			{
				stationNames[i] = Utils.stationNamesWestern.get(i);
			}
			break;

		case MainActivity.CENTRAL_LINE:
			size = Utils.stationNamesCentral.size();
			stationNames = new String[size];
			for(int i = 0; i < size; i++)
			{
				stationNames[i] = Utils.stationNamesCentral.get(i);
			}
			break;
			
		case MainActivity.HARBOUR_LINE:
			size = Utils.stationNamesHarbour.size();
			stationNames = new String[size];
			for(int i = 0; i < size; i++)
			{
				stationNames[i] = Utils.stationNamesHarbour.get(i);
			}
			break;
			
		default:
			break;
		}
		show();
	}

	public void init()
	{
		etSource = (EditText) context.findViewById(R.id.etSource);
		etDest = (EditText) context.findViewById(R.id.etDest);
		btnFind = (Button) context.findViewById(R.id.btnFind);
		
		etSource.setOnClickListener(this);
		etDest.setOnClickListener(this);
		btnFind.setOnClickListener(this);
	}
	
	public void show() {
		context.setContentView(R.layout.train_selection_screen);
		init();
	}

	@Override
	public void onClick(View v) {
		if(v.equals(etSource))
		{
			showStationNamesDialog(true);
		}
		else if(v.equals(etDest))
		{
			showStationNamesDialog(false);
		}
		else if(v.equals(btnFind))
		{
			String src = etSource.getText().toString();
			String dest = etDest.getText().toString();
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
				context.switchToScreen(Constants.TRAIN_LIST_SCREEN, src, dest, lineId);
			}
		}
	}
	
	protected void showStationNamesDialog(final boolean isSource) {
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
		builder.setItems(stationNames, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if(isSource)
				{
					etSource.setText(stationNames[which]);
				}
				else
				{
					etDest.setText(stationNames[which]);
				}
			}
		}).create().show();
	}

	public void onBackPressed() {
		context.switchToScreen(Constants.SCREEN_TRAIN_HOME, lineId);
	}

}
