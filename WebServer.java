package main;
import server.*;

public class WebServer{
	public static void main(String[] args){
		try{
			Server server = new Server();
			Thread mainThread = new Thread(server);
			mainThread.start();
		}catch(Exception exception){
			System.out.println(exception);
		}
	}
}