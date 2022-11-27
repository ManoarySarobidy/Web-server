package main;
import server.*;
import frame.Frame;
public class WebServer{
	public static void main(String[] args){
		try{
			// Server server = new Server();
			// Thread mainThread = new Thread(server);
			// mainThread.start();
			Frame serverFrame = new Frame();
			Thread frameThread = new Thread(serverFrame);
			frameThread.start();
		}catch(Exception exception){
			System.out.println(exception);
		}
	}
}