import java.awt.Color;
import java.awt.image.BufferedImage;

public class Spider extends Entity {

	public Spider(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 4), ss.get(2, 4)}, 
												new BufferedImage[] {
													ss.get(1, 4), ss.get(3, 4)}, Color.GREEN);
		((Sprite) visual).setStateRate(1);
	}

	@Override
	public void updateHook(double delta) {
		((Sprite) visual).update(delta);
	}

}