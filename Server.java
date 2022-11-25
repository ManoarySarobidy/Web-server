package server;
import java.io.*;
import java.net.*;
import java.util.Vector;
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
				// this.addClients( client ); 
				// Mila atao mamorona protocol http averina any aminy
				String headerHttp = "HTTP/1.1 200  OK\r\n\r\n";
				// DataOutputStream output = new DataOutputStream( client.getOutputStream() );
				// output.writeUTF( headerHttp );
				client.getOutputStream().write(headerHttp.getBytes("UTF-8") );
				System.out.println( client );
			}

		}catch( Exception e ){
			System.out.println(e);
		}
	}

}