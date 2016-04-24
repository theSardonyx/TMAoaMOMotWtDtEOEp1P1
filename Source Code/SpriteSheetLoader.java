/**
* This class loads a spritesheet in correspondence to a SpriteSheet object
* 
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
import java.util.HashMap;

public class SpriteSheetLoader {
	private static SpriteSheetLoader spriteSheetLoader;
	private HashMap<String, SpriteSheet> hash;
	
	/*
	Constructor for a SpriteSheetLoader
	creates a HashMap for a string key to its corresponding SpriteSheet
	*/
	private SpriteSheetLoader() {
		hash = new HashMap<String, SpriteSheet>();
	}
	/*
	Returns a single instance of a SpriteSheetLoader
	Makes sure that only one instance of a SpriteSheetLoader is used on the game
	*/
	public static SpriteSheetLoader getInstance() {
		if(spriteSheetLoader == null) {
			spriteSheetLoader = new SpriteSheetLoader();
		}
		return spriteSheetLoader;
	}
	/*
	Returns a SpriteSheet based from a String key, and loads it alongside the available cell width and height
	@param s: String key to search the SpriteSheet on the HashMap
	@param cellWidth: value to determine a spliced Sprite width
	@param cellHeight: value to determine a spliced Sprite height
	*/
	public SpriteSheet getSpriteSheet(String s, int cellWidth, int cellHeight) {
		if(!hash.containsKey(s)) {
			SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.getInstance().getFile(s), cellWidth, cellHeight);
			hash.put(s, spriteSheet);
		}
		return hash.get(s);
	}
}
