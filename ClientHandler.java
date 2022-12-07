package handler;
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.Vector;
import file.FileHandler;
public class ClientHandler implements Runnable{
	
	Socket connected;

	// asiana html header foana ato

	String defaultPath = "./";	
	String httpHeader;
	String httpContent;
	String serverMessage;
	
	Vector<String> inputs;
	boolean read;
	BufferedReader reader;
	InputStreamReader input;


	int index = 0;

	public static int NUMBER = 1;

	public ClientHandler(Socket connect , String message){
		this.setConnected( connect );
		this.setHttpHeader();
		this.setHttpContent();
		this.setServerMessage( message );
	}
	public ClientHandler(Socket connect) throws Exception{
		this.setHttpHeader();
		this.setHttpContent();
		this.setRead(false);
		this.setConnected( connect );
		this.setServerMessage("<b>HAHAHAHAH Sarobidy is the boss</b>");
		this.setInputStreamReader();
		this.setBufferedReader();
	}

	public void setRead(boolean b){
		this.read = b;
	}
	public boolean wasRead(){
		return this.read;
	}

	public void setInputs(Vector<String> vec){
		this.inputs = vec;
	}
	public Vector<String> getInputs(){
		return this.inputs;
	}

	public void setIndex(int index){
		this.index = index;
	}
	public int getIndex(){
		return this.index;
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

	public void setBufferedReader(  ){
		this.reader = new BufferedReader( this.getInStream() );
	}
	public BufferedReader getBuffer(){
		return this.reader;
	}

	public void setInputStreamReader() throws Exception{
		this.input = new InputStreamReader( this.getConnected().getInputStream() );
	}
	public InputStreamReader getInStream(){
		return this.input;
	}

	public void run(){
		
		try{
			if( !this.wasRead() ){
				this.traitement();
				this.writeContent();
				this.getConnected().close();
				this.setRead(true);
			}
		}catch( Exception e ){
			e.printStackTrace();
		}

	}

	public void traitement() throws Exception{
		try{
			
			String firstLine = this.getBuffer().readLine();
			String method = getHeaderMethod( firstLine );
			String url = getUrl( firstLine );
			String file = getPathFile(url);
			String attr = "";

			if( method.equals("POST") ){
				attr = getPostData();
			}else if( method.equals("GET") ){
				attr = getAttributes( url );
			}
			FileHandler handler = new FileHandler(file);
			handler.setDatas(attr);
			this.setServerMessage(handler.traitement());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPostData() throws Exception{
		String s = null;
		int count = -5;
		while( (s = this.getBuffer().readLine()) != null && !s.isEmpty() ){
			if( s.contains("Content-Length: ") ){
				count = Integer.valueOf(s.substring(s.indexOf("Content-Length:")+16,s.length()));
			}
		}
		String datas = "";
		if( count > 0 ){
			char[] data = new char[count];
			this.getBuffer().read( data , 0 , count );
			datas = new String(data);
		}
		return datas;
	}

	String getHeaderMethod( String line ) throws Exception{
		return line.split(" ")[0];
	}

	String getUrl( String line ) throws Exception{
		return line.split(" ")[1];
	}
	String getPathFile( String url ){
		return url.split("[?]")[0];
	}
	String getAttributes(String url){
		if( url.split("[?]").length > 1 )
			return url.split("[?]")[1];
		return "";
	}

	void writeContent() throws Exception{
		OutputStream out = this.getConnected().getOutputStream();
		try{
			out.write( this.getHttpHeader().getBytes("UTF-8") );
			out.write( this.getHttpContent().getBytes("UTF-8") );
			out.write( this.getMessage().getBytes("UTF-8") );
			out.flush(); 	
			// out.close();
		}catch(Exception e){
			out.close();
		}
	}
	
}