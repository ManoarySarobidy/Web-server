package server;
import java.io.*;
import java.net.*;
import java.util.Vector;
import handler.*;
public class Server implements Runnable {
	ServerSocket server;
	boolean status;
	int port;
	Vector<Socket> clients;

	String defaultFile;

	public Server(){
		this.setPort( 33005 );
		this.setClients( new Vector<Socket>() );
		this.setDefaultFile();
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
	public void setDefaultFile(){
		this.defaultFile = "./index.html";
	}
	public String getDefaultFile(){
		return this.defaultFile;
	}
	public void run(){
		try{
			this.setServer( new ServerSocket( this.getPort() ) );
			this.getServer().setReuseAddress(true);
			
			while( true ){
				Socket client = null;
				try{
					client = this.getServer().accept();
				}catch(Exception e){
					throw new Exception("Can't accept client connection");
				}
				String defaultContent = readDefaultFile();
				ClientHandler handler = new ClientHandler( client );
				new Thread( handler ).start();
			}

		}catch( Exception e ){
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public String readDefaultFile() throws IOException{
		FileInputStream file = new FileInputStream( this.getDefaultFile() );
		InputStreamReader input = new InputStreamReader( file );
		BufferedReader reader = new BufferedReader( input );
		String in = null;
		String response = null;
		while( ( in = reader.readLine() ) != null ){
			response += in;
		}
		reader.close();
		input.close();
		file.close();
		return response;
	}

}