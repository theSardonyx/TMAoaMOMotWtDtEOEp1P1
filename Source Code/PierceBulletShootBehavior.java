public class PierceBulletShootBehavior extends UpgradableShootBehavior {

	private Vector velocity, acceleration;
	
	public PierceBulletShootBehavior(Entity subject, BulletStage stage) {
		super(subject, stage);

		this.fireRate = 3;
		
		this.acceleration = new Vector(0, -300);
	}
	
	public PierceBulletShootBehavior(Entity subject, BulletStage stage, double expireTime) {
		super(subject, stage, expireTime);

		this.fireRate = 3;
		
		this.acceleration = new Vector(0, -300);
	}

	@Override
	public void update() {
		//damage
		this.velocity = new Vector(0, -250);
	}

	@Override
	public Entity[] getBullets() {
		PierceBullet projectile = new PierceBullet(this.subject.position, this.stage);
		AccelerateMoveBehavior mb = new AccelerateMoveBehavior(projectile, this.velocity.clone(), this.acceleration.clone());
		projectile.setMoveBehavior(mb);
		
		Entity[] bullets = new Entity[1];
		bullets[0] = projectile;
		return bullets;
	}
}
