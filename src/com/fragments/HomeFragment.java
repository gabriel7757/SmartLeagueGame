package com.fragments;

import com.example.smartleague.GameQuestions;
import com.example.smartleague.R;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {
	
	
	//Default fragment when you login in the app via facebook
	 public HomeFragment(){}
     
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	  
	        View rootView = inflater.inflate(R.layout.home, container, false);
	         final Context homeContext=container.getContext();
	        Button startButton=(Button) rootView.findViewById(R.id.buttonStart);
	        
	        startButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivity(new Intent(homeContext,GameQuestions.class));
					
					
				}
			});
	        
	        
	        
	        
	        return rootView;
	    }
	

}
