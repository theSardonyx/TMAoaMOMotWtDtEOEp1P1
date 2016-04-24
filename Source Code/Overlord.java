import java.awt.Color;
import java.awt.image.BufferedImage;

public class Overlord extends Entity {

	public Overlord(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		visual = new Sprite(position, dimension, new BufferedImage[] {
				ss.get(0, 0), ss.get(2, 0)}, 
			new BufferedImage[] {
				ss.get(1, 0), ss.get(3, 0)}, Color.BLACK);
		((Sprite) visual).setStateRate(1);
	}

	@Override
	public void updateHook(double delta) {
		((Sprite) visual).update(delta);
	}

}
