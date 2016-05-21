
public class SnipeBulletShootBehavior extends UpgradableShootBehavior {
	
	private double speed;
	
	public SnipeBulletShootBehavior(Entity subject, BulletStage stage) {
		super(subject, stage);

		this.fireRate = 1;
		
		this.speed = 3000;
	}
	
	public SnipeBulletShootBehavior(Entity subject, BulletStage stage, double expireTime) {
		super(subject, stage, expireTime);

		this.fireRate = 1;
		
		this.speed = 3000;
	}

	@Override
	public void update() {
		//damage
		//firerate
	}

	@Override
	public Entity[] getBullets() {
		SnipeBullet projectile = new SnipeBullet(this.subject.position, this.stage);
		AimedMoveBehavior mb = new AimedMoveBehavior(projectile, this.stage.getRandomType(Entity.ENEMY_TYPE), this.speed, 0);
		projectile.setMoveBehavior(mb);
		
		Entity[] bullets = new Entity[1];
		bullets[0] = projectile;
		return bullets;
	}

}
