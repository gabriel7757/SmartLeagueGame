package com.fragments;

import com.example.smartleague.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class TypeQuestions extends Fragment {
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View myview = inflater.inflate(R.layout.questions, container, false);
        
//       Switch switchScience =  (Switch) myview.findViewById(R.id.switchquestion);
//       final TextView text=(TextView) myview.findViewById(R.id.question);
//       switchScience.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//		
//		@Override
//		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//			// TODO Auto-generated method stub
//			
//		text.setText("se ha cambiado!!");
//			
//		}
//	});
//           
//          
        return myview;
    }
    
    
    
   
	
	

	
	
	

}
