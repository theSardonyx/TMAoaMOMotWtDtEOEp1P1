import java.awt.Color;

public class WitchShootBehaviorHard extends ShootBehavior {

	private Entity target;
	private Color color;
	private Vector baseVelocity;
	
	public WitchShootBehaviorHard(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.fireRate = 1.5;
		
		this.target = target;
		this.color = color;
		this.baseVelocity = new Vector(150, 0);
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[1];
		
		double angle = this.target.getPosition().subtract( this.subject.getPosition() ).getAngle();
		Vector velocity = baseVelocity.setAngle( angle );
		
		WitchBullet projectile = new WitchBullet(this.subject.getPosition(), new Vector(64, 64), stage, color);
		projectile.setMoveBehavior( new AccelerateMoveBehavior(projectile, velocity, Vector.zero()) );
		projectile.setShootBehavior(new WitchBigBulletShootBehavior(projectile, velocity, this.stage, this.color));
		bullets[0] = projectile;
		
		return bullets;
	}

}
