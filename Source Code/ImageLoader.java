import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {
	private static ImageLoader imageLoader;
	private HashMap<String, BufferedImage> hash;
	
	private ImageLoader() {
		hash = new HashMap<String, BufferedImage>();
	}
	
	public static ImageLoader getInstance() {
		if(imageLoader == null)
			imageLoader = new ImageLoader();
		return imageLoader;
	}
	
	public BufferedImage getFile(String s) {
		if(!hash.containsKey(s)) 
		{
			BufferedImage image = null;
			try {
				image = ImageIO.read(FileLoader.getInstance().getFile(s));				
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			hash.put(s, image);
		}
		
		return hash.get(s);
	}
}