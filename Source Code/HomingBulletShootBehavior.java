
public class HomingBulletShootBehavior extends UpgradableShootBehavior {

	private Vector velocity, acceleration;
	
	public HomingBulletShootBehavior(Entity subject, BulletStage stage) {
		super(subject, stage);

		this.fireRate = 1;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity[] getBullets() {
		Entity target = null; //TODO get target
		HomingBullet projectile = new HomingBullet(this.subject.position, this.stage, target);
		this.velocity = Vector.zero(); //TODO get velocity going to target
		this.acceleration = new Vector(0, -100); //TODO get acceleration going to target
		AccelerateMoveBehavior mb = new AccelerateMoveBehavior(projectile, this.velocity.clone(), this.acceleration.clone());
		projectile.setMoveBehavior(mb);
		
		Entity[] bullets = new Entity[1];
		bullets[0] = projectile;
		return bullets;
	}
}
