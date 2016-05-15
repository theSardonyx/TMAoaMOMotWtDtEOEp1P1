import java.awt.Color;

public class WitchShootBehaviorEasy extends ShootBehavior {

	private Entity target;
	private Color color;
	private Vector baseVelocity;
	
	public WitchShootBehaviorEasy(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.fireRate = 0.5;
		
		this.target = target;
		this.color = color;
		this.baseVelocity = new Vector(300, 0);
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[1];
		
		double angle = this.target.getPosition().subtract( this.subject.getPosition() ).getAngle();
		Vector velocity = baseVelocity.setAngle( angle );
		
		WitchBullet projectile = new WitchBullet(this.subject.getPosition(), new Vector(32, 32), stage, color);
		projectile.setMoveBehavior( new AccelerateMoveBehavior(projectile, velocity, Vector.zero()) );
		bullets[0] = projectile;
		
		return bullets;
	}

}
