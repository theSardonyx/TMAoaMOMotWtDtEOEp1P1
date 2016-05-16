import java.awt.Color;

public class PixieShootBehaviorEasy extends ShootBehavior {

	private Color color;
	private Vector baseVelocity, baseAcceleration;
	private double velocityMagnitude, accelerationMagnitude, rotationDelta, bounceBase, bounceCap;
	private Entity target;
	private boolean isBounce;
	
	public PixieShootBehaviorEasy(Entity subject, Entity target, BulletStage stage, boolean orientationToRight, Color color) {
		super(subject, stage);
		
		this.fireRate = 0.2;
		
		this.velocityMagnitude = 200;
		this.accelerationMagnitude = 0;
		this.rotationDelta = Math.PI / 10;
		this.target = target;
		this.color = color;
		if(orientationToRight)
			this.rotationDelta = - Math.abs(rotationDelta);
		else
			this.rotationDelta = Math.abs(rotationDelta);
		this.baseVelocity = null;
		this.baseAcceleration = null;
		this.isBounce = false;
		this.bounceBase = 0;
		this.bounceCap = 2 * Math.PI;
	}

	@Override
	public Entity[] getBullets() {
		if(this.baseVelocity == null || this.baseAcceleration == null) {
			Vector diff = this.target.getPosition()
					.subtract(this.subject.getPosition())
					.normalize();
			if(this.baseVelocity == null)
				this.baseVelocity = diff.scalarMult(this.velocityMagnitude);
			if(this.baseAcceleration == null)
				this.baseAcceleration = diff.scalarMult(this.accelerationMagnitude);
		}
		else {
			this.baseVelocity = this.baseVelocity.rotate(rotationDelta);
			this.baseAcceleration = this.baseAcceleration.rotate(rotationDelta);
			this.bounceBase += rotationDelta;
			if(Math.abs(this.bounceBase) >= this.bounceCap) {
				this.bounceBase = 0;
				if(this.isBounce)
					this.rotationDelta *= -1;
			}
		}
		
		PixieBullet projectile = new PixieBullet(this.subject.position, this.stage, this.color);
		AccelerateMoveBehavior mb = new AccelerateMoveBehavior(projectile, this.baseVelocity, this.baseAcceleration);
		projectile.setMoveBehavior(mb);
		
		Entity[] bullets = new Entity[1];
		bullets[0] = projectile;
		return bullets;
	}
	
	public void setBounce(boolean isBounce) {
		this.isBounce = isBounce;
	}
	
	public void setBounceCap(double radians) {
		this.bounceCap = radians;
	}
	
	public void setRotation(double radians) {
		this.baseVelocity = new Vector(0, this.velocityMagnitude).setAngle(radians);
		this.baseAcceleration = new Vector(0, this.accelerationMagnitude).setAngle(radians);
	}
	
	public void rotate(double radians) {
		if(this.baseVelocity != null)
			this.baseVelocity = this.baseVelocity.rotate(radians);
		if(this.baseAcceleration != null)
			this.baseAcceleration = this.baseAcceleration.rotate(radians);
	}
}
