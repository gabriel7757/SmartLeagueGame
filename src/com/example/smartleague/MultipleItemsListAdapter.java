package com.example.smartleague;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.smartleague.NavigationAdapter.Fila;
import com.example.smartleague.R.bool;
import com.fragments.ConfigurationFragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MultipleItemsListAdapter extends BaseAdapter {

	  private static Context myfragmenContext;  
		ArrayList<QuestionObject> arrayitms; 
		private LayoutInflater inflater;
		private static boolean[] onOff =new boolean[] { true, true, true, true};
		//static ArrayList<Boolean> onOff=new ArrayList<Boolean>(Arrays.asList(new Boolean[4]));
		
	
	
	public MultipleItemsListAdapter(Context context,
				ArrayList<QuestionObject> arrayitms,LayoutInflater inflate) {
			super();
			this.myfragmenContext = context;
			this.arrayitms = arrayitms;
			this.inflater=inflate;						
			inflater = LayoutInflater.from(myfragmenContext);
			 
		}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayitms.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arrayitms.get(position);
	}
	   @Override
	    public long getItemId(int position) {
	        return position;
	    }   
	    //Declaramos clase estatica la cual representa a la fila para las preguntas
	    public static class RowQuestions  
	    {  
	    		TextView title_q;
	    		Switch myswitch;
	    }
	    
	    //Declaramos clase estatica la cual representa a la fila para el separador
	    public static class RowSeparator  
	    {  
	    		
	    } 
	    
	    
	   public View getView(final int position, View convertView, ViewGroup parent) {  
	      // TODO Auto-generated method stub  
		  
		   RowSeparator rowS;
		   RowQuestions rowQ;
		   
		
		  
		   Log.e("1.....BIEN POR QUE LO HACEEE","DEspues de todo!!!!!!!!!!!!!" );
		   //LayoutInflater inflater = (LayoutInflater) myfragmenContext
             //      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);   
	       
	      if(convertView==null)  
	       {      	        
	    	  
	    	  
			        
			        	rowQ = new RowQuestions();	
			        	  //Creo objeto item y lo obtengo del array
			        	   convertView = inflater.inflate(R.layout.questions, null);
				           QuestionObject question=arrayitms.get(position);
				           //convertView = inflater.inflate(R.layout.questions, null);
				           
				           //Tipo Pregunta
				           rowQ.title_q=(TextView) convertView.findViewById(R.id.question);				           
				           //Seteo en el tipo de la pregunta correspondiente obtenido del objeto
				           rowQ.title_q.setText(question.getQuestion());
				           
				           
				         //Icono
				           rowQ.myswitch = (Switch) convertView.findViewById(R.id.myswitch); 
				           //onOff=loadArray(myfragmenContext);
				           rowQ.myswitch.setChecked(onOff[position]);//ponemos la variable switch segun en el estado que este
				           
				           convertView.setTag(rowQ);
				           
				           
				           
				           rowQ.myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
							
							@Override
							public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
								// TODO Auto-generated method stub
								onOff[position]=isChecked;
								//onOff[position]= isChecked;//seteo la variable a true o false segun la posicion del switch chequeado								
							}
						});
				           
				         //rowQ.myswitch.setChecked(onOff.get(position));//ponemos la variable switch segun en el estado que este
				           
								         
	        }  
	        else  
	        {  
	        	  Log.e("3.....BIEN POR QUE LO HACEEE","DEspues de todo!!!!!!!!!!!!!" );
	           //view = (Fila) convertView.getTag();  
	        }  
	        return convertView;  
	    }
	   
	   //PREPARAMOS PARA GUARDAR EN SHARED PREFERENCES
	   
	      
	
//	   public static boolean saveArray()
//	   {
//	       SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(myfragmenContext);
//	       SharedPreferences.Editor mEdit1 = sp.edit();
//	       mEdit1.putInt("Status_size", onOff.size()); /*  is an array */ 
//
//	       for(int i=0;i<onOff.size();i++)  
//	       {
//	           mEdit1.remove("Status_" + i);
//	           mEdit1.putBoolean("Status_"+i,onOff.get(i));
//	           
//	       }
//
//	       return mEdit1.commit();     
//	   }
	   
	   public static ArrayList<Boolean> loadArray(Context mContext)
	   {  
		   ArrayList<Boolean> switchArray =new ArrayList<Boolean>(Arrays.asList(new Boolean[4]));
	       SharedPreferences mSharedPreference1 = PreferenceManager.getDefaultSharedPreferences(mContext);
	       switchArray.clear();
	       int size = mSharedPreference1.getInt("Status_size", 0);  
	       
	       for(int i=0;i<size;i++) 
	       {
	        if (mSharedPreference1.getBoolean("Status_" + i,true)){
	            
	        	switchArray.add(true);
	        }
	        else{
	        	switchArray.add(false);
	        }
	        
	       }
	       
	       return switchArray;
	     
	   }
	
	
	
	

}
