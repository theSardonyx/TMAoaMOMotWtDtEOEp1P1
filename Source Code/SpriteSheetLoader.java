import java.util.HashMap;

public class SpriteSheetLoader {
	private static SpriteSheetLoader spriteSheetLoader;
	private HashMap<String, SpriteSheet> hash;
	
	private SpriteSheetLoader() {
		hash = new HashMap<String, SpriteSheet>();
	}
	
	public SpriteSheetLoader getInstance() {
		if(this.spriteSheetLoader == null) {
			this.spriteSheetLoader = new SpriteSheetLoader();
		}
		return spriteSheetLoader;
	}
	
	public SpriteSheet getSpriteSheet(String s, int cellWidth, int cellHeight) {
		if(!hash.containsKey(s)) {
			SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.getInstance().getFile(s), cellWidth, cellHeight);
			hash.put(s, spriteSheet);
		}
		return hash.get(s);
	}
}
