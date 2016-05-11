
public class AntiBullet extends Entity {

	public AntiBullet(Vector position, BulletStage stage) {
		super(position, new Vector(32, 32), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeBulletMSheet.png", 32, 32);
		this.visual = new DrawableImage(position, dimension, ss.get(0, 7));
		
		//TODO damage pls
		this.damage = 0; 
		
		this.type = Entity.ALLY_BULLET_TYPE;
		this.canCollideEnemy = true;
		this.canCollideEnemyBullet = true;
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collideEnemy(Entity e) {
		this.despawn();
	}
	
	@Override
	public void collideEnemyBullet(Entity e) {
		e.despawn();
	}
}
