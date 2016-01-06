package com.threads;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.example.smartleague.SocketsActivity;


//We get the data via Socket 
public class CommsThread extends Thread {
	
	private final Socket socket;
	private final InputStream inputStream;
	private final OutputStream outputStream;
	
	
	public CommsThread(Socket sock){
		this.socket=sock;
		InputStream tmpIn=null;
		OutputStream tmpOut=null;
		
		
		try {
			tmpIn=socket.getInputStream();
			tmpOut=socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		inputStream=tmpIn;
		outputStream=tmpOut;
		
	}
	
	
	@Override
	public void run() {
		//almacen de buffer		
		byte[] buffer = new byte[1024];
		//bytes devueltos desde read()---
		int bytes;
		
		//seguimos escuchando a InputStream hasta que ocurra una excepcion
		while (true) {
			
			try {
				bytes=inputStream.read(buffer);			
				//--actulizamos la actividad principal--
				
				SocketsActivity.UIupdater.obtainMessage(0,bytes,-1,buffer).sendToTarget();
								
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
			
		}	
		
	}
	
	
	//se llama desde la actividad principal, para enviar datos al dispositivo remoto
	public void write(byte[] bytes) {
		// TODO Auto-generated method stub
		try {
			outputStream.write(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void cancel(){
		
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
