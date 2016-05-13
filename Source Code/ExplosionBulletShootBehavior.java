
public class ExplosionBulletShootBehavior extends UpgradableShootBehavior {

	private Vector velocity, acceleration;
	
	public ExplosionBulletShootBehavior(Entity subject, BulletStage stage) {
		super(subject, stage);
		
		
		this.fireRate = 3;
		
		this.acceleration = Vector.zero();
	}

	@Override
	public void update() {
		//damage
		//radius
		this.velocity = new Vector(0, -50 - (this.getLevel() * 25));
	}

	@Override
	public Entity[] getBullets() {
		ExplosionBullet projectile = new ExplosionBullet(this.subject.position, this.stage);
		AccelerateMoveBehavior mb = new AccelerateMoveBehavior(projectile, this.velocity.clone(), this.acceleration.clone());
		projectile.setMoveBehavior(mb);
		
		Entity[] bullets = new Entity[1];
		bullets[0] = projectile;
		return bullets;
	}

}
