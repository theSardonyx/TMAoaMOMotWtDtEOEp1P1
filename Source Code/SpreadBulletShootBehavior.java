
public class SpreadBulletShootBehavior extends UpgradableShootBehavior {

	private Vector velocity, acceleration;
	private double angularInterval;
	private int numBullets;
	
	public SpreadBulletShootBehavior(Entity subject, BulletStage stage) {
		super(subject, stage);

		this.fireRate = 1;
		
		this.acceleration = Vector.zero();
		this.angularInterval = Math.PI / 18;
	}

	@Override
	public void update() {
		//fireRate
		this.numBullets = 2 + ((this.getLevel() - 1) / 2 * 2);
		this.velocity = new Vector(0, -200);
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[numBullets];
		double baseInterval = this.angularInterval / 2.0;
		for(int i = 0; numBullets / 2 > i; i++) {
			double rotLeft = baseInterval + (this.angularInterval * i);
			SpreadBullet projectile1 = new SpreadBullet(this.subject.position, this.stage);
			((DrawableImage) projectile1.visual).setRotation(rotLeft);
			AccelerateMoveBehavior mb1 = new AccelerateMoveBehavior(projectile1, this.velocity.rotate(rotLeft), this.acceleration.clone());
			projectile1.setMoveBehavior(mb1);
			
			double rotRight = -baseInterval - (this.angularInterval * i);
			SpreadBullet projectile2 = new SpreadBullet(this.subject.position, this.stage);
			((DrawableImage) projectile2.visual).setRotation(rotRight);
			AccelerateMoveBehavior mb2 = new AccelerateMoveBehavior(projectile2, this.velocity.rotate(rotRight), this.acceleration.clone());
			projectile2.setMoveBehavior(mb2);
			
			bullets[(i * 2)] = projectile1;
			bullets[(i * 2) + 1] = projectile2;
		}
		return bullets;
	}

	public void setAngularInterval(double radians) {
		this.angularInterval = radians;
	}
}
