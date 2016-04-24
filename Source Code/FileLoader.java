import java.io.*;
import java.util.HashMap;

/**
* This class loads files and saves them in memory in case they are
* used again.
* 
* @author	Aemielvin Loremia
*/
public class FileLoader {
	/**
	* A saved instance of the FileLoader class
	*/
	private static FileLoader fileLoader;
	
	/**
	* Stores all the files already loaded
	*
	* @see #getFile
	*/
	private HashMap<String, File> hash;
	
	/**
	* Instantiates a HashMap to store files in
	*/
	private FileLoader() {
		hash = new HashMap<String, File>();
	}
	
	/**
	* Creates or loads an instance of the FileLoader class for the
	* entire game session
	* 
	* @return	fileLoader	A new instance of the FileLoader or the
	*						existing saved instance of FileLoader
	*
	* @see	#fileLoader
	*/
	public static FileLoader getInstance() {
		if(fileLoader == null)
			fileLoader = new FileLoader();
		return fileLoader;
	}
	
	/**
	* Reads and loads a file from the specified directory
	* 
	* @param	dir		File directory
	* @return	File	A new file or a file already saved in #hash
	*/
	public File getFile(String dir) {
		if(!hash.containsKey(dir)) 
		{
			File file = null;
			file = new File(dir);
			hash.put(dir, file);
		}
		
		return hash.get(dir);
	}
}
