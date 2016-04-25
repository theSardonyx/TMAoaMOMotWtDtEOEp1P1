import java.awt.Color;

public class EyeBullet extends Entity {

	public EyeBullet(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/enemyBulletSheet.png", 32, 32);
		visual = new DrawableImage(position, dimension, Sprite.integrateSprites(ss.get(0, 1), ss.get(1, 1), Color.GREEN));
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}

}
