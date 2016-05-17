
public class HomingBullet extends Entity {
	
	private Entity target;

	public HomingBullet(Vector position, BulletStage stage, Entity target) {
		super(position, new Vector(32, 32), stage);
		this.target = target;
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeBulletMSheet.png", 32, 32);
		this.visual = new DrawableImage(position, dimension, ss.get(0, 2));
		
		//TODO damage pls
		this.damage = 1;
		
		this.type = Entity.ALLY_BULLET_TYPE;
		this.canCollideEnemy = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension.scalarMult(0.5))
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
