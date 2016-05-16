import java.awt.Color;

public class PixieShootBehaviorMedium extends ShootBehavior {
	
	private Color color;
	private Entity target;
	private Vector baseVelocity, baseAcceleration;
	private double velocityMagnitude, accelerationMagnitude, rotationBase, rotationDelta;
	
	public PixieShootBehaviorMedium(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);

		this.fireRate = 0.1;
		
		this.target = target;
		this.velocityMagnitude = 200;
		this.accelerationMagnitude = 0;
		this.rotationDelta = Math.PI / 10;
		this.rotationBase = Math.PI / 2;
		this.color = color;
		this.baseVelocity = null;
		this.baseAcceleration = null;
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
}
