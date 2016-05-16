
public class SplitBulletShootBehavior extends UpgradableShootBehavior {
	
	private Vector velocity, acceleration;
	private int numFragments;
	
	public SplitBulletShootBehavior(Entity subject, BulletStage stage) {
		super(subject, stage);

		this.fireRate = 1;
	}
	
	public SplitBulletShootBehavior(Entity subject, BulletStage stage, double expireTime) {
		super(subject, stage, expireTime);

		this.fireRate = 1;
	}

	@Override
	public void update() {
		this.velocity = new Vector(0, -300);
		this.acceleration = new Vector(0, -200);
		//damage
		this.numFragments = 2;
	}

	@Override
	public Entity[] getBullets() {
		SplitBullet projectile = new SplitBullet(this.subject.position, this.stage);
		projectile.setNumFragments(this.numFragments);
		AccelerateMoveBehavior mb = new AccelerateMoveBehavior(projectile, this.velocity.clone(), this.acceleration.clone());
		projectile.setMoveBehavior(mb);
		
		Entity[] bullets = new Entity[1];
		bullets[0] = projectile;
		return bullets;
	}

}
