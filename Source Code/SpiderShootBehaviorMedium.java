import java.awt.Color;

public class SpiderShootBehaviorMedium extends ShootBehavior {

	private Color color;
	private double speed, acceleration, waitTime;
	
	public SpiderShootBehaviorMedium(Entity subject, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.fireRate = 0.5;
		
		this.color = color;
		this.acceleration = 150;
		this.speed = 0;
		this.waitTime = 1.5;
	}
	
	public SpiderShootBehaviorMedium(Entity subject, BulletStage stage, Color color, double expireTime) {
		super(subject, stage, expireTime);
		
		this.fireRate = 0.5;
		
		this.color = color;
		this.acceleration = 150;
		this.speed = 0;
		this.waitTime = 1.5;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[1];
		
		SpiderBullet projectile = new SpiderBullet(this.subject.getPosition(), this.stage, this.color);
		QueueMoveBehavior queue = new QueueMoveBehavior(projectile, null);
		
		AccelerateMoveBehavior mb1 = new AccelerateMoveBehavior(projectile, Vector.zero(), Vector.zero(), this.waitTime);
		AccelerateMoveBehavior mb2 = new AccelerateMoveBehavior(projectile, new Vector(0, this.speed), new Vector(0, this.acceleration));
		queue.pushBack(mb1);
		queue.pushBack(mb2);
		projectile.setMoveBehavior(queue);
		
		bullets[0] = projectile;
		
		return bullets;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	
	public void setWaitTime(double waitTime) {
		this.waitTime = waitTime;
	}
}