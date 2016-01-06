package com.example.smartleague;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


public class Languages extends ListActivity {
	
	
	
	
	 String[] arrLanguages = new String[]{"English", "Spanish"};

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {

	  super.onCreate(savedInstanceState);
	  setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrLanguages));
	 }

	 
	 @Override
	 protected void onListItemClick(ListView l, View v, int position, long id) {

	  super.onListItemClick(l, v, position, id);

	  setResult(RESULT_OK, new Intent().putExtra("language", arrLanguages[position]));

	  finish();

	 }
	 
}