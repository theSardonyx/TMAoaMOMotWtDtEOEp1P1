
public class SentinelBullet extends Entity {

	public SentinelBullet(Vector position, BulletStage stage) {
		super(position, new Vector(48, 48), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeBulletLSheet.png", 64, 64);
		this.visual = new DrawableImage(position, dimension, ss.get(0, 1));
		
		//TODO damage pls
		this.damage = 1;
		
		this.type = Entity.ALLY_BULLET_TYPE;
		
		this.collideShape = new CollideShape(this.position, this.dimension)
				.setCollideEllipse(true);
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub
		
	}
}
