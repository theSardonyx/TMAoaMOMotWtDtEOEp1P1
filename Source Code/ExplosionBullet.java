import java.awt.Color;

public class ExplosionBullet extends Entity {

	public ExplosionBullet(Vector position, BulletStage stage) {
		super(position, new Vector(48, 48), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeBulletLSheet.png", 64, 64);
		this.visual = new DrawableImage(position, dimension, ss.get(0, 0));
		
		//TODO damage pls
		this.damage = 0;
		
		this.type = Entity.ALLY_BULLET_TYPE;
		this.canCollideEnemy = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension)
				.setCollideRectangle(true);
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void collideEnemy(Entity e) {
		//TODO spawn blast particle
		this.spawnEntity(new Explosion(e.position, new Vector(300, 300), this.stage, Color.RED));
		this.despawn();
	}
}
