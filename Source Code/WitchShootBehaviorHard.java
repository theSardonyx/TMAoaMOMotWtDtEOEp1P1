import java.awt.Color;

public class WitchShootBehaviorHard extends ShootBehavior {

	private Entity target;
	private Color color;
	private Vector baseVelocity, baseAcceleration;
	
	public WitchShootBehaviorHard(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.fireRate = 2.5;
		
		this.target = target;
		this.color = color;
		this.baseVelocity = new Vector(150, 0);
		this.baseAcceleration = Vector.zero();
	}
	
	public WitchShootBehaviorHard(Entity subject, Entity target, BulletStage stage, Color color, double expireTime) {
		super(subject, stage, expireTime);
		
		this.fireRate = 2.5;
		
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
		
		WitchBigBullet projectile = new WitchBigBullet(this.subject.getPosition(), stage, color);
		projectile.setMoveBehavior( new AccelerateMoveBehavior(projectile, velocity, acceleration) );
		projectile.setShootBehavior(new WitchBigBulletShootBehavior(projectile, velocity, this.stage, this.color));
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
