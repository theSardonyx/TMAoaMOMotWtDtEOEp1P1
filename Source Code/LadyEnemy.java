import java.awt.Color;
import java.awt.image.BufferedImage;

public class LadyEnemy extends Entity {

	public LadyEnemy(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 7), ss.get(2, 7)}, 
												new BufferedImage[] {
													ss.get(1, 7), ss.get(3, 7)}, Color.GREEN);
		((Sprite) visual).setStateRate(1);
	}

	@Override
	public void updateHook(double delta) {
		((Sprite) visual).update(delta);
	}

}
