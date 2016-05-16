import java.awt.Color;

public class WitchShootBehaviorMedium extends ShootBehavior {

	private Entity target;
	private Color color;
	private Vector baseVelocity, baseAcceleration;
	
	public WitchShootBehaviorMedium(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.fireRate = 1;
		
		this.target = target;
		this.color = color;
		this.baseVelocity = new Vector(150, 0);
		this.baseAcceleration = Vector.zero();
	}

	public WitchShootBehaviorMedium(Entity subject, Entity target, BulletStage stage, Color color, double expireTime) {
		super(subject, stage, expireTime);
		
		this.fireRate = 1;
		
		this.target = target;
		this.color = color;
		this.baseVelocity = new Vector(150, 0);
		this.baseAcceleration = Vector.zero();
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[1];
		
		double angle = this.target.getPosition().subtract( this.subject.getPosition() ).getAngle();
		Vector velocity = baseVelocity.setAngle( angle );
		Vector acceleration = baseAcceleration.setAngle(angle);
		
		WitchBullet projectile = new WitchBullet(this.subject.getPosition(), new Vector(64, 64), stage, color);
		projectile.setMoveBehavior( new AccelerateMoveBehavior(projectile, velocity, acceleration) );
		bullets[0] = projectile;
		
		return bullets;
	}

	public void setVelocity(Vector velocity) {
		this.baseVelocity = velocity;
	}
	
	public void setAcceleration(Vector acceleration) {
		this.baseAcceleration = acceleration;
	}
}
