package handler;
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import file.FileHandler;
public class ClientHandler implements Runnable{
	Socket connected;
	// asiana html header foana ato
	String defaultPath = "./";	
	String httpHeader;
	String httpContent;
	String serverMessage;

	public ClientHandler(Socket connect , String message){
		this.setConnected( connect );
		this.setHttpHeader();
		this.setHttpContent();
		this.setServerMessage( message );
	}
	public ClientHandler(Socket connect){
		this.setHttpHeader();
		this.setHttpContent();
		this.setConnected( connect );
	}

	public void setConnected( Socket connect ){
		this.connected = connect;
	}
	public Socket getConnected(){
		return this.connected;
	}

	public void setHttpHeader(){
		this.httpHeader = "HTTP/1.1 200  OK\r\n";
	}
	public void setHttpContent(){
		this.httpContent = "Content-Type: text/html;charset=UTF-8\n\n";
	}
	public void setServerMessage(String message){
		this.serverMessage = message;
	}

	public String getHttpHeader(){
		return this.httpHeader;
	}
	public String getHttpContent(){
		return this.httpContent;
	}
	public String getMessage(){
		return this.serverMessage;
	}

	public void run(){
		try{
			InputStream inputs = this.getConnected().getInputStream();
			InputStreamReader inputReader = new InputStreamReader( inputs );
			BufferedReader reader = new BufferedReader( inputReader );
			String line = reader.readLine();
			StringTokenizer tokenizer = new StringTokenizer(line);
			String method = tokenizer.nextToken();
			String filerequired = tokenizer.nextToken();
			FileHandler file = new FileHandler( filerequired );
			System.out.println( filerequired );
			this.setServerMessage( file.traitement() );
			
			this.writeContent();			
			reader.close();
		}catch( Exception e ){
			e.printStackTrace();
		}
	}

	public void writeContent() throws Exception{
		
		OutputStream out = this.getConnected().getOutputStream();
		out.write( this.getHttpHeader().getBytes("UTF-8") );
		out.write( this.getHttpContent().getBytes("UTF-8") );
		out.write( this.getMessage().getBytes("UTF-8") );
		out.flush();
	}

	// andao amin'izay anao file gestionnaire aloha zay vao atao php ilay izy
	// manao php ve moa tsy ho mora

	
}