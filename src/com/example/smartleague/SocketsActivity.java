package com.example.smartleague;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.threads.CommsThread;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.TextView;

public class SocketsActivity extends Activity {
	
	static final String idUser="Gabriel";
	InetAddress serverAddress;
	Socket socket;
	
	//todas las vistas--
	static TextView txtMessagesReceived;
	EditText txtMessage;
	
	
	CommsThread commsThread;
	
	
	
	public void onClickSend(String message){
		
		sendToServer(message);
		
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();		
		new CreateCommThreadTask().execute();
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
	   super.onPause();
	   
	   new CloseSocketTask().execute();
	}
	
	private void sendToServer(String message) {
		// TODO Auto-generated method stub
		// byte[] b = convertBitmapToByteArray(bitmap);
		byte[] theByteArray=message.getBytes();
		new WriteToServerTask().execute(theByteArray);	
		
		
	}
	
	public byte[] convertBitmapToByteArray(Bitmap bitmap) {
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight());
	    bitmap.compress(CompressFormat.PNG, 100, buffer);
	    return buffer.toByteArray();
	}
	
	
	
	
	public static Handler UIupdater = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			int numOfBytsReceived=msg.arg1;
			
			byte[] buffer =(byte[])msg.obj;
			
			
			
			 Bitmap bitmap = BitmapFactory.decodeByteArray(buffer , 0, buffer.length);
			 
			
			//convertimos la tabla de bytes completa a cadena
			String strReceived = new String(buffer);			
			//extraer solamente a cadena recibida
			strReceived=strReceived.substring(0,numOfBytsReceived);			
			//mostramos el texto recibido en el textView
			txtMessagesReceived.setText(txtMessagesReceived.getText().toString()+strReceived);			
			
		};
		
		
	};
	
	private class CreateCommThreadTask extends AsyncTask<Void,Integer,Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			//creamos un socket
			try {
				serverAddress=InetAddress.getByName("192.168.1.129");//cambiamos esta IpAddres para nuestro Node.JS
				socket = new Socket(serverAddress, 500);
				commsThread = new CommsThread(socket);
				commsThread.start();
				
				//ahora enviamos el nickname				
				sendToServer(idUser);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
					
			
			return null;
		}

		
		
	}
	
	private class WriteToServerTask extends AsyncTask<byte[],Void,Void>{

		@Override
		protected Void doInBackground(byte[]... data) {
			// TODO Auto-generated method stub			
			commsThread.write(data[0]);
			return null;
		}		
	}	
	
	private class CloseSocketTask extends AsyncTask<Void,Void,Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	
	
	

}
