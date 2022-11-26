package server;
import java.io.*;
import java.net.*;
import java.util.Vector;
import handler.*;
public class Server implements Runnable {
	ServerSocket server;
	int port;
	Vector<Socket> clients;

	public Server(){
		this.setPort( 33005 );
		this.setClients( new Vector<Socket>() );
	}
	public void setPort( int port ){
		this.port = port;
	}
	public int getPort(){
		return this.port;
	}

	public void setServer( ServerSocket server ) throws Exception{
		this.server = server;
	}
	public ServerSocket getServer(){
		return this.server;
	}

	public void setClients( Vector<Socket> cli ){
		this.clients = cli;
	}
	public Vector<Socket> getClients(){
		return this.clients;
	}
	public void addClients( Socket socket ){
		this.getClients().add( socket );
	}
	public void run(){
		try{
			this.setServer( new ServerSocket( this.getPort() ) );
			this.getServer().setReuseAddress(true);
			System.out.println( "Serveur Demarer" );
			System.out.println( this.getServer().toString() );
			while( true ){
				Socket client = this.getServer().accept();
				ClientHandler handler = new ClientHandler( client );
				// this.addClients( client ); 
				// Mila atao mamorona protocol http averina any aminy
				String headerHttp = "HTTP/1.1 200  OK\r\n";
				String contentType = "Content-Type: text/html;charset=UTF-8\n\n";
				String html = "<html><head></head><body><h2>YESSS SUCCESSS </h2></body></html>";
				handler.getConnected().getOutputStream().write(headerHttp.getBytes("UTF-8") );
				handler.getConnected().getOutputStream().write(contentType.getBytes("UTF-8") );
				handler.getConnected().getOutputStream().write(html.getBytes("UTF-8") );
				handler.getConnected().getOutputStream().close();
				// System.out.println( client );
			}

		}catch( Exception e ){
			System.out.println(e);
		}
	}

}