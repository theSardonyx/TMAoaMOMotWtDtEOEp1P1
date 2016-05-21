
public class HomingBulletShootBehavior extends UpgradableShootBehavior {

	private double speed, acceleration, angularInterval;
	private int numBullets;
	
	public HomingBulletShootBehavior(Entity subject, BulletStage stage) {
		super(subject, stage);

		this.fireRate = 3;
		
		this.speed = 200;
		this.acceleration = 0;
		this.numBullets = 4;
		
		this.angularInterval = Math.PI / 18;
	}
	
	public HomingBulletShootBehavior(Entity subject, BulletStage stage, double expireTime) {
		super(subject, stage, expireTime);

		this.fireRate = 1;
		
		this.speed = 200;
		this.acceleration = 0;
		this.numBullets = 4;

		this.angularInterval = Math.PI / 18;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[numBullets];
		double baseInterval = this.angularInterval / 2.0;
		for(int i = 0; numBullets / 2 > i; i++) {
			double rot1 = baseInterval + (this.angularInterval * i);
			HomingBullet projectile1 = new HomingBullet(this.subject.position, this.stage);
			((DrawableImage) projectile1.visual).setRotation(rot1);
			HomingMoveBehavior mb1 = new HomingMoveBehavior(projectile1, this.stage.getRandomType(Entity.ENEMY_TYPE), new Vector(0, -speed).rotate(rot1), 2 * Math.PI, 0);
			projectile1.setMoveBehavior(mb1);
			
			double rot2 = - baseInterval - (this.angularInterval * i);
			HomingBullet projectile2 = new HomingBullet(this.subject.position, this.stage);
			((DrawableImage) projectile2.visual).setRotation(rot2);
			HomingMoveBehavior mb2 = new HomingMoveBehavior(projectile2, this.stage.getRandomType(Entity.ENEMY_TYPE), new Vector(0, -speed).rotate(rot2), 2 * Math.PI, 0);
			projectile2.setMoveBehavior(mb2);
			
			bullets[(i * 2)] = projectile1;
			bullets[(i * 2) + 1] = projectile2;
		}
		return bullets;
	}
	
	
}
