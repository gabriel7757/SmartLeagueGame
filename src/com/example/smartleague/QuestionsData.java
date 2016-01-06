package com.example.smartleague;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.globaldata.Question;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

// we use this class to get the questions via JSON through Web-Socket(Rest-API) 
public class QuestionsData extends Application {
	
	private static ArrayList<Question> questionsData = new ArrayList<Question>();
	
	
    @Override
	public void onCreate() {
	  //reinitialize variable   	
    	super.onCreate();
    	new ReadJSONFeedTask().execute("http://extjs.org.cn/extjs/examples/grid/survey.html");
    	
	 }
	
	//We use this method to provisionally to show results  
	public ArrayList<Question> getGlobalVariable() {
		
		Question pregunta1 = new Question(1, 2,"Which is the largest animal?","Whale","Lion","Giraffe","Cat","Whale");
		Question pregunta2 = new Question(2, 2,"Who was the first President of The United States of America ","Benjamin Franklin","Obama","George Washington","Bush","George Washington");
		Question pregunta3 = new Question(3, 2,"Name of the Spanish King","Juan Carlos","Felipe","Luis","Roberto","Felipe");
		
		questionsData.add(pregunta1);
		questionsData.add(pregunta2);
		questionsData.add(pregunta3);
		
           return questionsData;
   }

   public void setGlobalVariable(ArrayList<Question> questions) {
           this.questionsData = questions;
   }
   
   
   public String readJSONFeed(String URL){
		
		StringBuilder stringBuilder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(URL);
		
		
		try {
			HttpResponse response = client.execute(httpGet);
			
			
			StatusLine statusLinea = response.getStatusLine();
			int statusCode=statusLinea.getStatusCode();
			if(statusCode==200){
				
				HttpEntity entity =response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while((line=reader.readLine())!=null){
					
					stringBuilder.append(line);
					
				}
				
			}
			else{
				Log.e("JSON","Error al descargar archivo Json");
			}
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Log.e("Terminando: ", stringBuilder.toString());
		
		return stringBuilder.toString();
	}  
   
   
   
	//Class to get JSON object
	private class ReadJSONFeedTask extends AsyncTask<String,Void,String>{
		
				@Override
				protected String doInBackground(String... urls) {
					// TODO Auto-generated method stub
					return  readJSONFeed(urls[0]);//Llama al metodo readJSONFeed asícronamente
				}
				
				
				
				@Override
				protected void onPostExecute(String result) {
					// TODO Auto-generated method stub
					Log.e("Iniciando: ","Entra onPostExecute");
					
					try {
						JSONArray jsonArray = new JSONArray(result);
						
						ArrayList<String> datos= new ArrayList<>();
						
						Log.e("longitud de jsonArray: ","Valor:  "+ jsonArray.length());
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject jsonObject=jsonArray.getJSONObject(i);
							Toast.makeText(getBaseContext(), jsonObject.getString("appeId")+"--"+jsonObject.getString("inputTime"),Toast.LENGTH_SHORT).show();
							Log.e("Finalizando onPostExecute: ",jsonObject.getString("appeId"));
							datos.add(jsonObject.getString("appeId"));
							
						}
						
					//setGlobalVariable(datos); A MODIFICARR
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
		
		
	}
	
	
	
}

	
	
	
	
	
	
	
	
	
	

}
