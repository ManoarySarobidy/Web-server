package file;
import java.io.*;

public class FileHandler{
	String defaultPath ;
	String defaultFileName;
	String NOT_FOUND ;
	String requestFile;

	public FileHandler(){
		this.setDefaultPath();
		this.setNotFound();
	}
	public FileHandler( String file ){
		this.setDefaultPath();
		this.setNotFound();
		this.setRequestFile( this.getDefaultPath() + file );
	}

	// read simple html file
	
	public String readFile( ) throws Exception{
		FileInputStream file = null;
		System.out.println( this.getRequestFile() );
		if( this.getRequestFile().equalsIgnoreCase("/") ){
			this.setRequestFile( this.getRequestFile() + "index.html" );
			file = new FileInputStream( this.getRequestFile() );
		}
		file = new FileInputStream( this.getRequestFile() );
		String valiny = this.read( file );
		return valiny;
	}

	public String traitement() throws Exception{
		if( this.getRequestFile().endsWith("/") ){
			this.addDefaultFile();
			return this.checkExtension();
		}else{
			return checkExist();
		}
	}

	public void addDefaultFile(){ // for a folder
		File file1 = new File( this.getRequestFile() + "index.html" );
		File file2 = new File( this.getRequestFile() + "index.php" );
		// jereko hoe miexiste ve ilay .html
		if( file1.exists() ){
			this.setRequestFile( this.getRequestFile() + "index.html" );
			System.out.println( this.getRequestFile() );	
			return;
		}
		if( file2.exists() ){
			this.setRequestFile( this.getRequestFile() + "index.php" );	
			System.out.println( this.getRequestFile() );	
			return;
		}
	}

	public String checkExist() throws Exception{
		try{
			File file = new File( this.getRequestFile() );
			return checkExtension();
		}catch( Exception files ){
			this.setRequestFile( this.getNotFound() );
			return readFile();
		}
	}

	/**
	 * 
	 * Check if the file extension is :
	 * 	- php file :
	 * 			- if yes , compile with php and read the result from the CLI
	 * 	- html file :
	 * 			- just read the entire file
	 * 	- other (  ) :
	 * 		-throw to "PAGE_NOT_FOUND"
	 * 
	 * */

	public String checkExtension() throws Exception{
		String[] splitted = this.getRequestFile().split("\\.");
		String extension = splitted[ splitted.length - 1 ];
		if( splitted.length == 1 ){
			throw new IOException("Not a valid file : " + this.getRequestFile());
		}
		if( extension.equalsIgnoreCase("php") ){
			return readPhpFile();
			
		}else if( extension.equalsIgnoreCase("html") ){
			return readFile();
		}
		throw new IOException("Not a valid file : " + this.getRequestFile());
	}

	/**
	 * Read php file from cli
	 * */

	public String readPhpFile() throws Exception{
		Process process = Runtime.getRuntime().exec("php " + this.getRequestFile());
		InputStream stream = process.getInputStream();
		String valiny = this.read(stream); 
		return valiny;
	}

	// read from inputStream

	public String read( InputStream stream ) throws Exception{
		InputStreamReader streamReader = new InputStreamReader( stream );
		BufferedReader reader = new BufferedReader( streamReader );
		String valiny = "";
		String line = null;
		while( ( line = reader.readLine()) != null ){
			valiny += line;
		}
		reader.close();
		streamReader.close();
		stream.close();
		return valiny;
	}


	void setRequestFile(String path){
		this.requestFile = path;
	}
	String getRequestFile(){
		return this.requestFile;
	}

	void setDefaultPath(){
		this.defaultPath = ".";
	}
	String getDefaultPath(){
		return this.defaultPath;
	}
	void setNotFound(){
		this.NOT_FOUND = "./PAGE_NOT_FOUND.html";
	}
	String getNotFound(){
		return this.NOT_FOUND;
	}
	// mila manao fonction readFile
}