
public class FragmentBullet extends Entity {

	public FragmentBullet(Vector position, BulletStage stage) {
		super(position, new Vector(16, 16), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeBulletSSheet.png", 16, 16);
		this.visual = new DrawableImage(position, dimension, ss.get(0, 0));
		
		//TODO damage pls
		this.damage = 1;
		
		this.type = Entity.ALLY_BULLET_TYPE;
		this.canCollideEnemy = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension)
				.setCollideEllipse(true);
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collideEnemy(Entity e) {
		this.despawn();
	}
}
