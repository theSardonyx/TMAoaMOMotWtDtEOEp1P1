import java.awt.Color;

public class PixieShootBehaviorMedium extends ShootBehavior {
	
	private Color color;
	private Entity target;
	private Vector baseVelocity, baseAcceleration;
	private double speed, acceleration, rotationBase, rotationDelta, bounceBase, bounceCap;
	private boolean isBounce;
	
	public PixieShootBehaviorMedium(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);

		this.fireRate = 0.1;
		
		this.target = target;
		this.speed = 200;
		this.acceleration = 0;
		this.rotationDelta = Math.PI / 10;
		this.rotationBase = Math.PI / 2;
		this.color = color;
		this.baseVelocity = null;
		this.baseAcceleration = null;
		
		this.isBounce = false;
		this.bounceBase = 0;
		this.bounceCap = Math.PI * 2;
	}
	
	public PixieShootBehaviorMedium(Entity subject, Entity target, BulletStage stage, Color color, double expireTime) {
		super(subject, stage, expireTime);

		this.fireRate = 0.1;
		
		this.target = target;
		this.speed = 200;
		this.acceleration = 0;
		this.rotationDelta = Math.PI / 10;
		this.rotationBase = Math.PI / 2;
		this.color = color;
		this.baseVelocity = null;
		this.baseAcceleration = null;
		
		this.isBounce = false;
		this.bounceBase = 0;
		this.bounceCap = Math.PI * 2;
	}

	@Override
	public Entity[] getBullets() {
		if(this.baseVelocity == null || this.baseAcceleration == null) {
			Vector diff = this.target.getPosition()
					.subtract(this.subject.getPosition())
					.normalize();
			if(this.baseVelocity == null)
				this.baseVelocity = diff.scalarMult(this.speed);
			if(this.baseAcceleration == null)
				this.baseAcceleration = diff.scalarMult(this.acceleration);
		}
		this.bounceBase += this.rotationDelta;
		if(Math.abs(this.bounceBase) >= this.bounceCap) {
			this.bounceBase = 0;
			if(this.isBounce)
				this.rotationDelta *= -1;
		}
		
		PixieBullet projectileLeft = new PixieBullet(this.subject.position, this.stage, this.color);
		AccelerateMoveBehavior mbLeft = new AccelerateMoveBehavior(projectileLeft, 
				this.baseVelocity.rotate(this.rotationBase), 
				this.baseAcceleration.rotate(this.rotationBase));
		projectileLeft.setMoveBehavior(mbLeft);
		PixieBullet projectileRight = new PixieBullet(this.subject.position, this.stage, this.color);
		AccelerateMoveBehavior mbRight = new AccelerateMoveBehavior(projectileRight, 
				this.baseVelocity.rotate(- this.rotationBase), 
				this.baseAcceleration.rotate(- this.rotationBase));
		projectileRight.setMoveBehavior(mbRight);
		
		this.rotationBase -= this.rotationDelta;
		
		Entity[] bullets = new Entity[2];
		bullets[0] = projectileLeft;
		bullets[1] = projectileRight;
		return bullets;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	
	public void setRotationDelta(double rotationDelta) {
		this.rotationDelta = rotationDelta;
	}
	
	public void setIsBounce(boolean isBounce) {
		this.isBounce = isBounce;
	}
	
	public void setBounceCap(double radians) {
		this.bounceCap = radians;
	}
}
