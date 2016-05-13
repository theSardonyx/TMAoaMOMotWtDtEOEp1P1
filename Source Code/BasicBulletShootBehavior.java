
public class BasicBulletShootBehavior extends UpgradableShootBehavior {

	private Vector velocity, acceleration;
	
	public BasicBulletShootBehavior(Entity subject, BulletStage bulletStage) {
		super(subject, bulletStage);
		
		this.fireRate = 1;
		
		this.acceleration = Vector.zero();
	}

	@Override
	public Entity[] getBullets() {
		BasicBullet projectile = new BasicBullet(subject.getPosition(), this.stage);
		projectile.setMoveBehavior(new AccelerateMoveBehavior(projectile, this.velocity.clone(), this.acceleration.clone()));

		Entity[] bullets = new Entity[1];
		bullets[0] = projectile;
		return bullets;
	}

	@Override
	public void update() {
		this.velocity = new Vector(0, -100);
	}

}
