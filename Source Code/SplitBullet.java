
public class SplitBullet extends Entity {

	public SplitBullet(Vector position, BulletStage stage) {
		super(position, new Vector(32, 32), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeBulletMSheet.png", 32, 32);
		visual = new DrawableImage(position, dimension, ss.get(0, 5));
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}

}
