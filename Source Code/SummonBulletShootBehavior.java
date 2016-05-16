
public class SummonBulletShootBehavior extends UpgradableShootBehavior {

	private Vector velocity, acceleration, radius;
	private int numSummons;
	
	public SummonBulletShootBehavior(Entity subject, BulletStage stage) {
		super(subject, stage);

		this.fireRate = 0.1;
		
		this.velocity = new Vector(0, -500);
		this.acceleration = Vector.zero();
		this.radius = new Vector(0, 50);
	}
	
	public SummonBulletShootBehavior(Entity subject, BulletStage stage, double expireTime) {
		super(subject, stage, expireTime);

		this.fireRate = 0.1;
		
		this.velocity = new Vector(0, -500);
		this.acceleration = Vector.zero();
		this.radius = new Vector(0, 50);
	}

	@Override
	public void update() {
		this.numSummons = 1 + (this.getLevel() / 3);
		//firerate
		//damage
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[numSummons];
		for(int i = 0; numSummons > i; i++) {
			Vector random = radius.scalarMult(Math.random()).rotate(Math.random() * (2 * Math.PI));
			SummonBullet projectile = new SummonBullet(this.subject.position.add(random), this.stage);
			AccelerateMoveBehavior mb;
			if(((PlayerMoveBehavior) this.subject.getMoveBehavior()).getFocus()) {
				mb = new AccelerateMoveBehavior(projectile, this.velocity.rotate((Math.random() * (Math.PI / 2.0)) - (Math.PI / 4.0)), this.acceleration.clone());
			} else {
				mb = new AccelerateMoveBehavior(projectile, this.velocity.rotate(Math.random() * (2 * Math.PI)), this.acceleration.clone());
			}
			projectile.setMoveBehavior(mb);
			
			bullets[i] = projectile;
		}
		return bullets;
	}
}
