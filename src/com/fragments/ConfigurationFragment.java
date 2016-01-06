package com.fragments;

import java.util.ArrayList;
import java.util.Arrays;

import com.drawer.Item_objct;
import com.example.smartleague.MultipleItemsListAdapter;

import com.example.smartleague.QuestionObject;
import com.example.smartleague.R;

import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class ConfigurationFragment extends Fragment {

	private ArrayList<QuestionObject> navQuestions;
	private String[] questionsTitle;
	private ListView listaConfig;
	 private TypedArray navSwitches;
	 private MultipleItemsListAdapter NavAdapter;
	 private LayoutInflater myInflater;
	 private LayoutInflater myInflaterHeader;
	 private Context contextFragment;
	 private ListView navList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myView = inflater.inflate(R.layout.configuration, container, false);
		 

		
		//Lista
        listaConfig = (ListView) myView.findViewById(R.id.listaConfig);
        
        //Declaramos el header el cual sera el layout de header.xml
        contextFragment=container.getContext();
        myInflaterHeader=LayoutInflater.from(contextFragment);
        View header = myInflaterHeader.inflate(R.layout.headerquestion,listaConfig,false);
        //Establecemos header
        listaConfig.addHeaderView(header);
        
        
        
		//Tomamos listado  de imgs desde drawable
        navSwitches = getResources().obtainTypedArray(R.array.navigation_iconos);			
		//Tomamos listado  de titulos desde el string-array de los recursos @string/nav_options
        questionsTitle = getResources().getStringArray(R.array.nav_questions);
        //Listado de titulos de barra de navegacion
        navQuestions = new ArrayList<QuestionObject>();
        //Agregamos objetos Item_objct al array
        //Science	      
        navQuestions.add(new QuestionObject(questionsTitle[0], navSwitches.getResourceId(0, -1)));
        //Sport
        navQuestions.add(new QuestionObject(questionsTitle[1], navSwitches.getResourceId(1, -1)));
        //music
        navQuestions.add(new QuestionObject(questionsTitle[2], navSwitches.getResourceId(2, -1)));
        //History
        navQuestions.add(new QuestionObject(questionsTitle[3], navSwitches.getResourceId(3, -1)));
        
        
        
      //Declaramos y seteamos nuestro adaptador al cual le pasamos el array con los titulos	       
        NavAdapter= new MultipleItemsListAdapter(container.getContext(),navQuestions,myInflater);
        
        listaConfig.setAdapter(NavAdapter);
        
        
      //Establecemos la accion al clickear sobre cualquier item del menu.
        //De la misma forma que hariamos en una app comun con un listview.
        listaConfig.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
            	//MostrarFragment(position);
            	
            	
            }
        });
        
        
        //Eventos
		
		
		
		return myView;
				
	}
	



	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	
}
