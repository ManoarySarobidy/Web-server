package handler;
import java.io.*;
import java.net.*;
public class ClientHandler implements Runnable{
	Socket connected;
	public ClientHandler(Socket connect){
		this.setConnected( connect );
	}
	public void setConnected( Socket connect ){
		this.connected = connect;
	}
	public Socket getConnected(){
		return this.connected;
	}
	public void run(){
		
	}
}