import java.awt.Color;

public class WitchBigBulletShootBehavior extends ShootBehavior {

	private Color color;
	private Vector baseVelocity;
	
	public WitchBigBulletShootBehavior(Entity subject, Vector velocity, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.fireRate = 0.5;
		
		this.color = color;
		this.baseVelocity = velocity;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[2];
		
		WitchBullet shot1 = new WitchBullet(this.subject.getPosition(), new Vector(32, 32), this.stage, this.color);
		Vector vel1 = baseVelocity.rotate(Math.PI/2);
		shot1.setMoveBehavior(new AccelerateMoveBehavior(shot1, vel1, Vector.zero()));
		bullets[0] = shot1;
		
		WitchBullet shot2 = new WitchBullet(this.subject.getPosition(), new Vector(32, 32), this.stage, this.color);
		Vector vel2 = baseVelocity.rotate(-Math.PI/2);
		shot2.setMoveBehavior(new AccelerateMoveBehavior(shot2, vel2, Vector.zero()));
		bullets[1] = shot2;
		
		return bullets;
	}

}
