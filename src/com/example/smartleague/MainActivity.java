package com.example.smartleague;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

//facebook libs
//import com.facebook.*;
//import com.facebook.login.LoginManager;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;
//import com.facebook.share.*;
//import com.facebook.share.model.*;
//import com.facebook.share.widget.*;
//import com.facebook.share.internal.*;
//import com.facebook.login.widget.LoginButton.*;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInstaller.Session;
import android.content.res.Configuration;
import android.net.ParseException;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
    
    private static final  int REQUEST_CHANGE_LANGUAGE = 1;   
    private LoginButton buttonFacebook;    
    private CallbackManager callbackManager;
    
    private List<String> permissions = Arrays.asList("email"); 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        
        //Inicializacion de botones Layout
        
       buttonFacebook = (LoginButton) findViewById(R.id.login_button);      
       
        	
      
        //Facebook Login      
       buttonFacebook.setReadPermissions(permissions);       
       callbackManager = CallbackManager.Factory.create();
       
       buttonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
		
		@Override
		public void onSuccess(LoginResult result) {

			final AccessToken accessToken = result.getAccessToken();
			 
			
			GraphRequestAsyncTask request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
				
				@Override
				// we get asynchronously the user dat from facebook
				public void onCompleted(JSONObject user, GraphResponse response) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(MainActivity.this, DrawerActivity.class);
					
					
					String userId=(String) user.opt("id");
					String name=(String) user.opt("name");
					intent.putExtra("userId",userId);
					//intent.putExtra("name",user.optString("name"));
					Toast.makeText(getBaseContext(), "Exitoso, nombre: "+name+" y id User: "+userId,Toast.LENGTH_LONG).show();				
					//save the data and send it to the next activity
					startActivity(intent);
				}
			}).executeAsync();
				
					
		}
		
		@Override
		public void onError(FacebookException error) {
			// TODO Auto-generated method stub
			Log.v("MainActivity","entra en error "+error);
			Toast.makeText(getApplicationContext(), "Error: "+error,Toast.LENGTH_LONG).show();
		}
		
		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			Log.v("MainActivity","entra en cancel");
			Toast.makeText(getApplicationContext(), "Entrada Cancelada: ",Toast.LENGTH_LONG).show();
//			Intent intent = new Intent(MainActivity.this, Languages.class);
//			startActivity(intent);
			
		}
	} );
     
	}
	  

	   
	   @Override
	   public void onConfigurationChanged(Configuration newConfig) {
	       super.onConfigurationChanged(newConfig);

	       getBaseContext().getResources().updateConfiguration(newConfig,null);
	       setContentView(R.layout.activity_main);
	       setTitle(R.string.app_name);
	       
	      // restartActivity();

	       // Checks the active language
//	       if (newConfig.locale == Locale.ENGLISH) {
//	           Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
//	       } else if (newConfig.locale == Locale.GERMAN){
//	           Toast.makeText(this, "French", Toast.LENGTH_SHORT).show();
//	       }
	   }
	  	
	   
	   @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        callbackManager.onActivityResult(requestCode, resultCode, data);
	        super.onActivityResult(requestCode, resultCode, data);
	    }
	   

	
	
	
	
}

