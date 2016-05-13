
public class AntiBulletShootBehavior extends UpgradableShootBehavior {
	
	private Vector velocity, acceleration;

	public AntiBulletShootBehavior(Entity subject, BulletStage bulletStage) {
		super(subject, bulletStage);
		
		this.fireRate = 1;
		
		this.velocity = Vector.zero();
	}

	@Override
	public Entity[] getBullets() {
		AntiBullet projectile = new AntiBullet(subject.getPosition(), this.stage);
		projectile.setMoveBehavior(new AccelerateMoveBehavior(projectile, this.velocity.clone(), this.acceleration.clone()));
		
		Entity[] bullets = new Entity[1];
		bullets[0] = projectile;
		return bullets;
	}

	@Override
	public void update() {
		this.acceleration = new Vector(0, - 400 - (200 * this.getLevel()));
		if(this.getLevel() < 6) {
			this.fireRate = 1;
		} else {
			this.fireRate = 1 - (0.1 * (this.getLevel() - 5));
		}
	}
}
