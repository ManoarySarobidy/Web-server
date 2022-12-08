package file;
import java.io.*;
import java.nio.file.*;
public class FileHandler{
	String defaultPath ;
	String defaultFileName;
	String NOT_FOUND ;
	String requestFile;
	File script;
	String datas;

	String defaultDirectory;

	public FileHandler(){
		this.setDefaultPath();
		this.setNotFound();
		this.setRequestFile(this.getNotFound());
		this.setDefaultDirectory();
	}
	public FileHandler( String file ){
		this.setDefaultPath();
		this.setNotFound();
		this.setScript();
		this.setRequestFile( this.getDefaultPath() + file );
		this.setDefaultDirectory();
	}

	// read simple html file
	
	public String readFile( ) throws Exception{
		FileInputStream file = null;
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
			return;
		}
		if( file2.exists() ){
			this.setRequestFile( this.getRequestFile() + "index.php" );	
			return;
		}
	}

	public void addScript() throws Exception{
		String temporaryFile = "./temp/tempFile.tmp";
		File tempScript = this.getScript();
		File requested = new File(this.getRequestFile());
		this.createTempFile(temporaryFile);
		File tempFile = new File(temporaryFile);
		try{
			this.writeTo( tempFile , script );
			this.writeTo( tempFile , requested );
			this.setRequestFile(temporaryFile);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeTo( File toWrite , File toRead) throws Exception{
		FileOutputStream wri = new FileOutputStream(toWrite , true);
		FileInputStream inp = new FileInputStream(toRead);
		PrintWriter writter = new PrintWriter( wri );
		InputStreamReader read = new InputStreamReader(inp );
		BufferedReader reader = new BufferedReader(read);
		BufferedWriter writer = new BufferedWriter(writter);
		String line = null;
		while( (line = reader.readLine()) != null ){
			writer.write( line );
		}
		reader.close();
		writer.close();
	}

	public String checkExist() throws Exception{
		try{
			File file = new File( this.getRequestFile() );
			return checkExtension();
		}catch( Exception files ){
			files.printStackTrace();
			this.setRequestFile( this.getNotFound() );
			return readFile();
		}
	}

	public void createTempFile( String path ) throws Exception{
		File temp = new File(path);
		if( !Files.exists(temp.toPath()) ){
			Files.createFile(temp.toPath());
			// return;
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
			this.addScript();
			return readPhpFile();
			
		}else if( extension.equalsIgnoreCase("html") ){
			return readFile();
		}else if( extension.equalsIgnoreCase("ico") ){
			return readFile();
		}
		throw new IOException("Not a valid file : " + this.getRequestFile());
	}

	/**
	 * Read php file from cli
	 * */

	 // ito mbola mila parsena ilay requestFile azahoana ilay liste d'argument raha sendra ka GET ilay methode

	public String readPhpFile() throws Exception{
		Process process = Runtime.getRuntime().exec("php " + this.getRequestFile() + " " + this.getData());
		InputStream stream = process.getInputStream();
		String valiny = this.read(stream); 
		this.deleteTempFile();
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

	void deleteTempFile() throws Exception{
		String temp = "./temp/tempFile.tmp";
		File file = new File(temp);
		Files.deleteIfExists(file.toPath());
	}

	public void setDatas( String datas ){
		this.datas = datas;
	}

	String getData(){
		return this.datas;
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
	void setScript(){
		this.script = new File( "./script.php" );
	}
	File getScript(){
		return this.script;
	}

	void setDefaultDirectory(){
		this.defaultDirectory = "./php";
	}
	String getDefaultDirectory(){
		return this.defaultDirectory;
	}

}