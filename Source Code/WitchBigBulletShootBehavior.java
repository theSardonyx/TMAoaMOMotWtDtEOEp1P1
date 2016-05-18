import java.awt.Color;

public class WitchBigBulletShootBehavior extends ShootBehavior {

	private Color color;
	private Vector baseVelocity, baseAcceleration;
	
	public WitchBigBulletShootBehavior(Entity subject, Vector velocity, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.fireRate = 0.66;
		
		this.color = color;
		this.baseVelocity = velocity;
		this.baseAcceleration = Vector.zero();
	}
	
	public WitchBigBulletShootBehavior(Entity subject, Vector velocity, BulletStage stage, Color color, double expireTime) {
		super(subject, stage, expireTime);
		
		this.fireRate = 0.66;
		
		this.color = color;
		this.baseVelocity = velocity;
		this.baseAcceleration = Vector.zero();
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[2];
		
		WitchBullet shot1 = new WitchBullet(this.subject.getPosition(), this.stage, this.color);
		Vector vel1 = this.baseVelocity.rotate(Math.PI/2);
		Vector acc1 = this.baseAcceleration.rotate(Math.PI / 2);
		shot1.setMoveBehavior(new AccelerateMoveBehavior(shot1, vel1, acc1));
		bullets[0] = shot1;
		
		WitchBullet shot2 = new WitchBullet(this.subject.getPosition(), this.stage, this.color);
		Vector vel2 = this.baseVelocity.rotate(-Math.PI/2);
		Vector acc2 = this.baseAcceleration.rotate(-Math.PI / 2);
		shot2.setMoveBehavior(new AccelerateMoveBehavior(shot2, vel2, acc2));
		bullets[1] = shot2;
		
		return bullets;
	}
	
	public void setVelocity(Vector velocity) {
		this.baseVelocity = velocity;
	}
	
	public void setAcceleration(Vector acceleration) {
		this.baseAcceleration = acceleration;
	}

}
