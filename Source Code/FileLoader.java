import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class FileLoader {
	private static FileLoader fileLoader;
	private HashMap<String, File> hash;
	
	private FileLoader() {
		hash = new HashMap<String, File>();
	}
	
	public static FileLoader getInstance() {
		if(fileLoader == null)
			fileLoader = new FileLoader();
		return fileLoader;
	}
	
	public File getFile(String s) {
		if(!hash.containsKey(s)) 
		{
			File file = null;
			file = new File(s);
			hash.put(s, file);
		}
		
		return hash.get(s);
	}
}
