public class BurstBulletShootBehavior extends UpgradableShootBehavior {

	private Vector velocity, acceleration;
	private int numBullets;
	
	public BurstBulletShootBehavior(Entity subject, BulletStage bulletStage) {
		super(subject, bulletStage);
		
		this.fireRate = 1;
		
		this.acceleration = Vector.zero();
	}
	
	public BurstBulletShootBehavior(Entity subject, BulletStage bulletStage, double expireTime) {
		super(subject, bulletStage, expireTime);
		
		this.fireRate = 1;
		
		this.acceleration = Vector.zero();
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[numBullets];
		double rotation = (2 * Math.PI) / this.numBullets;
		for(int i = 0; numBullets > i; i++) {
			bullets[i] = new BurstBullet(subject.getPosition(), this.stage);
			((DrawableImage) bullets[i].visual).setRotation(rotation * i);
			AccelerateMoveBehavior mb = new AccelerateMoveBehavior(bullets[i], this.velocity.clone().rotate(i * rotation), this.acceleration.clone());
			bullets[i].setMoveBehavior(mb);
		}
		
		return bullets;
	}

	public void update() {
		//damage
		this.velocity = new Vector(0, -150);
		this.numBullets = 8 + ((this.getLevel() / 3) * 4);
	}
}
