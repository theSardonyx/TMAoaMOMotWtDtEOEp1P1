import java.awt.Color;

public class SpiderShootBehaviorHard extends ShootBehavior {

	private Color color;
	private double speed, acceleration, offset, waitTime;
	private int numBullets;
	
	public SpiderShootBehaviorHard(Entity subject, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.fireRate = 1;
		
		this.color = color;
		this.speed = 0;
		this.acceleration = 200;
		this.offset = 50;
		this.waitTime = 0.75;
		this.numBullets = 3;
	}
	
	public SpiderShootBehaviorHard(Entity subject, BulletStage stage, Color color, double expireTime) {
		super(subject, stage, expireTime);
		
		this.fireRate = 1;
		
		this.color = color;
		this.speed = 0;
		this.acceleration = 200;
		this.offset = 50;
		this.waitTime = 0.75;
		this.numBullets = 3;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[this.numBullets];
		
		Vector baseExplodePosition = new Vector(offset, 0);
		for(int i=0; i<this.numBullets; i++)
		{
			Vector explodePosition = baseExplodePosition.rotate(Math.random()* 2 * Math.PI);
			SpiderBullet projectile = new SpiderBullet(this.subject.getPosition(), this.stage, this.color);
			QueueMoveBehavior queue = new QueueMoveBehavior(projectile, null);
			
			AccelerateMoveBehavior mb1 = new AccelerateMoveBehavior(projectile, Vector.zero(), Vector.zero(), this.waitTime);
			TimedGlideMoveBehavior mb2 = new TimedGlideMoveBehavior(projectile, this.subject.getPosition().add(explodePosition), .25, .25);
			AccelerateMoveBehavior mb3 = new AccelerateMoveBehavior(projectile, Vector.zero(), Vector.zero(), 0.25);
			AccelerateMoveBehavior mb4 = new AccelerateMoveBehavior(projectile, new Vector(0, this.speed), new Vector(0, this.acceleration));
			queue.pushBack(mb1);
			queue.pushBack(mb2);
			queue.pushBack(mb3);
			queue.pushBack(mb4);
			projectile.setMoveBehavior(queue);
			
			bullets[i] = projectile;
		}
		
		return bullets;
	}

	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	
	public void setOffset(double offset) {
		this.offset = offset;
	}
	
	public void setNumBullets(int numBullets) {
		this.numBullets = numBullets;
	}
	
	public void setWaitTime(double waitTime) {
		this.waitTime = waitTime;
	}
}
