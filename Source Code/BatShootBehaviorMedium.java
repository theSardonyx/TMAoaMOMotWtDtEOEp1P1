import java.awt.Color;

public class BatShootBehaviorMedium extends ShootBehavior {

	private Entity target;
	private Color color;
	private Vector velocity, acceleration;
	private double rotation, rotationDelta;
	private int numBullets;
	private boolean isTargeting;
	
	public BatShootBehaviorMedium(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);

		this.fireRate = 1;
		
		this.target = target;
		this.color = color;
		this.numBullets = 4;
		this.velocity = new Vector(200, 0);
		this.acceleration = Vector.zero();
		this.rotation = 0;
		this.rotationDelta = Math.PI / 6;
		this.isTargeting = target != null;
	}
	
	public BatShootBehaviorMedium(Entity subject, Entity target, BulletStage stage, Color color, double expireTime) {
		super(subject, stage, expireTime);

		this.fireRate = 1;
		
		this.target = target;
		this.color = color;
		this.numBullets = 4;
		this.velocity = new Vector(200, 0);
		this.acceleration = Vector.zero();
		this.rotation = 0;
		this.rotationDelta = Math.PI / 6;
		this.isTargeting = target != null;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[this.numBullets];
		
		double angleToTarget;
		if(isTargeting)
			angleToTarget = this.target.position.subtract(this.subject.position).getAngle();
		else
			angleToTarget = this.rotation;
		
		for(int i = 0; i < numBullets / 2; i++)
		{
			BatBullet projectile1 = new BatBullet(this.subject.getPosition(), this.stage, this.color);
			double angle1 = angleToTarget + (this.rotationDelta / 2) + (i * this.rotationDelta);
			Vector velocity1 = this.velocity.setAngle(angle1);
			Vector acceleration1 = this.acceleration.setAngle(angle1);
			AccelerateMoveBehavior mb1 = new AccelerateMoveBehavior(projectile1, velocity1, acceleration1);
			projectile1.setMoveBehavior(mb1);
			bullets[i * 2] = projectile1;
			
			BatBullet projectile2 = new BatBullet(this.subject.getPosition(), this.stage, this.color);
			double angle2 = angleToTarget - (this.rotationDelta / 2) - (i * this.rotationDelta);
			Vector velocity2 = this.velocity.setAngle(angle2);
			Vector acceleration2 = this.acceleration.setAngle(angle2);
			AccelerateMoveBehavior mb2 = new AccelerateMoveBehavior(projectile2, velocity2, acceleration2);
			projectile2.setMoveBehavior(mb2);
			bullets[(i * 2) + 1] = projectile2;
		}
		
		if(numBullets % 2 != 0) {
			BatBullet projectile = new BatBullet(this.subject.getPosition(), this.stage, this.color);
			Vector velocity = this.velocity.setAngle(angleToTarget);
			Vector acceleration = this.acceleration.setAngle(angleToTarget);
			AccelerateMoveBehavior mb = new AccelerateMoveBehavior(projectile, velocity, acceleration);
			projectile.setMoveBehavior(mb);
			bullets[numBullets - 1] = projectile;
		}
		
		return bullets;
	}

	public void setRotation(double radians) {
		this.rotation = radians;
	}
	
	public void setRotationDelta(double rotationDelta) {
		this.rotationDelta = rotationDelta;
	}
	
	public void setNumBullets(int numBullets) {
		this.numBullets = numBullets;
	}
	
	public void setIsTargeting(boolean isTargeting) {
		this.isTargeting = isTargeting;
	}
	
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	
	public void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}
}
