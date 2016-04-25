
public class SentinelBullet extends Entity {

	public SentinelBullet(Vector position, BulletStage stage) {
		super(position, new Vector(64, 64), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeBulletLSheet.png", 64, 64);
		visual = new DrawableImage(position, dimension, ss.get(0, 1));
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub
		
	}

}
