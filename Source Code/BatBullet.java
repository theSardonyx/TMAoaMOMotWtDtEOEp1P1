import java.awt.Color;
import java.awt.image.BufferedImage;

public class BatBullet extends Entity{

	public BatBullet(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(32, 32), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/enemyBulletSheet.PNG", 32, 32);
		visual = new DrawableImage(position, dimension, Sprite.integrateSprites(ss.get(0, 0), ss.get(1, 0), color));
	}

	@Override
	public void updateHook(double delta) {
		
	}

}
