
public class SummonBullet extends Entity {

	public SummonBullet(Vector position, BulletStage stage) {
		super(position, new Vector(16, 16), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeBulletSSheet.png", 16, 16);
		visual = new DrawableImage(position, dimension, ss.get(0, 1));
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}

}
