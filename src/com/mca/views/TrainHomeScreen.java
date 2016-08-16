package com.mca.views;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.mca.activity.MainActivity;
import com.mca.model.Constants;
import com.mca.routes.R;

public class TrainHomeScreen implements OnClickListener {
	
private MainActivity context;
private Button btnWestern, btnCentral, btnHarbour;

	public TrainHomeScreen(MainActivity context) {
		this.context = context;
		show();
		init();
	}

	private void init() {
		btnWestern = (Button) context.findViewById(R.id.btnWestern);
		btnCentral = (Button) context.findViewById(R.id.btnCentral);
		btnHarbour = (Button) context.findViewById(R.id.btnHarbour);
		
		btnWestern.setOnClickListener(this);
		btnCentral.setOnClickListener(this);
		btnHarbour.setOnClickListener(this);
	}

	public void show() {
		context.setContentView(R.layout.train_home_screen);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.equals(btnCentral))
		{
			context.switchToScreen(Constants.TRAIN_SELECTION_SCREEN, MainActivity.CENTRAL_LINE);
		}
		else if(arg0.equals(btnHarbour))
		{
			context.switchToScreen(Constants.TRAIN_SELECTION_SCREEN, MainActivity.HARBOUR_LINE);
		}
		else if(arg0.equals(btnWestern))
		{
			context.switchToScreen(Constants.TRAIN_SELECTION_SCREEN, MainActivity.WESTERN_LINE);
		}
	}

}
