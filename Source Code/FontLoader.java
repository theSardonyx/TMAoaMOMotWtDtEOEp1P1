import java.awt.*;
import java.io.*;
import java.util.*;

public class FontLoader {
	private static FontLoader fontLoader;
	private HashMap<String, Font> hash;
	
	private FontLoader() {
		hash = new HashMap<String, Font>();
	}
	
	public static FontLoader getInstance() {
		if(fontLoader == null)
			fontLoader = new FontLoader();
		return fontLoader;
	}
	
	public Font getFont(String fontName, int fontStyle, int fontSize) {
		useFont(fontName);
		return hash.get(fontName).deriveFont(fontStyle, fontSize);
	}
	
	public void useFont(String fontName) {
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
	}
	
	public double getWidth(Font font, String s) {
		return new FontMetrics(font) {}.getStringBounds(s, null).getWidth();
	}
	
	public double getHeight(Font font, String s) {
		return new FontMetrics(font) {}.getStringBounds(s, null).getHeight();
	}
}
