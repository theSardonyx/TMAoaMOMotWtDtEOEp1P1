
public class BulletDespawner extends Entity {

	public BulletDespawner(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		this.type = Entity.AMBIENT_TYPE;
		this.collideShape = new CollideShape(position, dimension).setCollideRectangle(true);
		this.canCollideAllyBullet = true;
		this.canCollideEnemyBullet = true;
	}

	@Override
	public void updateHook(double delta) {}

	@Override
	public void collideEnemyBullet(Entity e) {
		e.despawn();
	}
	
	@Override
	public void collideAllyBullet(Entity e)
	{
		e.despawn();
	}
	
}
