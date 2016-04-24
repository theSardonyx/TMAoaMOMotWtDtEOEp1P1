import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
* This class loads images and saves them in memory in case they are
* used again
* 
* @author	Aemielvin Loremia
*/
public class ImageLoader {
	/**
	* A saved instance of the ImageLoader class
	*/
	private static ImageLoader imageLoader;
	
	/**
	* Stores all the images already loaded
	*
	* @see #getFile
	*/
	private HashMap<String, BufferedImage> hash;
	
	/**
	* Instantiates a HashMap to store images in
	*/
	private ImageLoader() {
		hash = new HashMap<String, BufferedImage>();
	}
	
	/**
	* Creates or loads an instance of the ImageLoader class for
	* the entire game session
	*
	* @return	imageLoader	A new instance of ImageLoader or the
	*						existing saved instance of ImageLoader
	*
	* @see	#imageLoader
	*/
	public static ImageLoader getInstance() {
		if(imageLoader == null)
			imageLoader = new ImageLoader();
		return imageLoader;
	}
	
	/**
	* Reads and loads an image from the specified directory
	* 
	* @param	dir		File directory
	* @return	BufferedImage	A new image or an image already
	*							saved in #hash
	* 
	* @see	FileLoader#getInstance
	*/
	public BufferedImage getFile(String dir) {
		if(!hash.containsKey(dir)) 
		{
			BufferedImage image = null;
			try {
				image = ImageIO.read(FileLoader.getInstance().getFile(dir));				
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			hash.put(dir, image);
		}
		
		return hash.get(dir);
	}
}