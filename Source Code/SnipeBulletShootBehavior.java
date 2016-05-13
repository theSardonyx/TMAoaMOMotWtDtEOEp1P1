
public class SnipeBulletShootBehavior extends UpgradableShootBehavior {
	
	public SnipeBulletShootBehavior(Entity subject, BulletStage stage) {
		super(subject, stage);

		this.fireRate = 1;
	}

	@Override
	public void update() {
		//damage
		//firerate
	}

	@Override
	public Entity[] getBullets() {
		SnipeBullet projectile = new SnipeBullet(this.subject.position, this.stage);
		//HomingMoveBehavior mb = new HomingMoveBehavior(projectile, this.stage);
		//projectile.setMoveBehavior(mb);
		
		Entity[] bullets = new Entity[1];
		bullets[0] = projectile;
		return bullets;
	}

}
