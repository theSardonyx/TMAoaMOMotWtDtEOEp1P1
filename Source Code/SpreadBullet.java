
public class SpreadBullet extends Entity {

	public SpreadBullet(Vector position, BulletStage stage) {
		super(position, new Vector(32, 32), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeBulletMSheet.png", 32, 32);
		visual = new DrawableImage(position, dimension, ss.get(0, 3));
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}

}
