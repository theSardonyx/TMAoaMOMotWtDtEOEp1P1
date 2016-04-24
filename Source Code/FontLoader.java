import java.awt.*;
import java.io.*;
import java.util.*;

/**
* This class loads fonts and saves them in memory in case they are
* used again
*
* @author	Ivan Martinez
*/
public class FontLoader {
	/**
	* A saved instance of the FontLoader class
	*/
	private static FontLoader fontLoader;
	
	/**
	* Stores all the fonts already loaded
	*
	* @see #useFont
	*/
	private HashMap<String, Font> hash;
	
	/**
	* Instantiates a HashMap to store fonts in
	*/
	private FontLoader() {
		hash = new HashMap<String, Font>();
	}
	
	/**
	* Creates or loads an instance of the FontLoader class for the
	* entire game session
	*
	* @return	fontLoader	A new instance of FontLoader or the
	*						existing saved instance of FontLoader
	* 
	* @see #fontLoader
	*/
	public static FontLoader getInstance() {
		if(fontLoader == null)
			fontLoader = new FontLoader();
		return fontLoader;
	}
	
	/**
	* Loads a font based on the speicified name, style, and size
	*
	* @param	fontName	Name of font to be used
	* @param	fontStyle	Style of the font to be used
	* @param	fontSize	Size of the font to be used
	* @return	Font
	*/
	public Font getFont(String fontName, int fontStyle, int fontSize) {
		if(!hash.containsKey(fontName)) {
			int index = Arrays.binarySearch(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(), fontName);
			if(index == -1) {
				try {
					GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/font/" + fontName + ".ttf")));
				} catch (FontFormatException|IOException e) {
					e.printStackTrace();
				}
			}
			hash.put(fontName, new Font(fontName, Font.PLAIN, 10));
		}
		return hash.get(fontName).deriveFont(fontStyle, fontSize);
	}
	
	/**
	* Gets the width of the String produced when drawn with the 
	* specified Font
	* 
	* @param	font	The Font to be used to draw the specified
	*					String
	* @param	s		The String to be drawn
	* @return	double	The width of the produced String
	*/
	public double getWidth(Font font, String s) {
		return new FontMetrics(font) {}.getStringBounds(s, null).getWidth();
	}
	
	/**
	* Gets the height of the String produced when drawn with the
	* specified Font
	*
	* @param	font	The Font to be used to draw the specified
	*					String
	* @param	s		The String to be drawn
	* @return	double	The height of the produced String
	*/
	public double getHeight(Font font, String s) {
		return new FontMetrics(font) {}.getStringBounds(s, null).getHeight();
	}
}
