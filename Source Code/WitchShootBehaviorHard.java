import java.awt.Color;

public class WitchShootBehaviorHard extends ShootBehavior {

	private Entity target;
	private Color color;
	private double speed, acceleration;
	
	public WitchShootBehaviorHard(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.fireRate = 2.5;
		
		this.target = target;
		this.color = color;
		this.speed = 150;
		this.acceleration = 0;
	}
	
	public WitchShootBehaviorHard(Entity subject, Entity target, BulletStage stage, Color color, double expireTime) {
		super(subject, stage, expireTime);
		
		this.fireRate = 2.5;
		
		this.target = target;
		this.color = color;
		this.speed = 150;
		this.acceleration = 0;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[1];
		
		WitchBigBullet projectile = new WitchBigBullet(this.subject.getPosition(), stage, color);
		AimedMoveBehavior mb = new AimedMoveBehavior(projectile, this.target, this.speed, this.acceleration);
		projectile.setMoveBehavior(mb);
		projectile.setShootBehavior(new WitchBigBulletShootBehavior(projectile, mb.getVelocity(), this.stage, this.color));
		bullets[0] = projectile;
		
		return bullets;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
}
