import java.awt.Color;
import java.awt.image.BufferedImage;

public class Pixie extends Entity {

	public Pixie(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 2), ss.get(2, 2)}, 
												new BufferedImage[] {
													ss.get(1, 2), ss.get(3, 2)}, Color.GREEN);
		((Sprite) visual).setStateRate(1);
	}

	@Override
	public void updateHook(double delta) {
		((Sprite) visual).update(delta);
	}

}
