/*package com.auxiliar;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.sip.SipRegistrationListener;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class MainPage extends Activity implements OnTouchListener{
	
	
	
	private GestureDetector gesture;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		// TODO Auto-generated method stub	
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.mainpage);
		
		Button mybottom=(Button) findViewById(R.id.buttonTranslation);
		
		mybottom.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent subActivity = new Intent(MainPage.this,Menu.class);
				
				Bundle translateBundle = ActivityOptions.makeCustomAnimation(MainPage.this,R.anim.slide_in_left,R.anim.slide_out_left).toBundle();
				
				startActivity(subActivity, translateBundle);			
				
				
			}
		});
				
		
	}



	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}

*/
