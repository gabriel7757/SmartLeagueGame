package com.example.smartleague;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class PopUpWindow extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.popupwindow);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		//getActionBar().hide();
		int width=dm.widthPixels;
		int height =dm.heightPixels;
		getWindow().setLayout((int)(width*.97),(int)(height*.61));
		
		
	}

}
