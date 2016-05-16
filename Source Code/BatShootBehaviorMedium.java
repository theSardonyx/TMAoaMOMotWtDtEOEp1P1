import java.awt.Color;

public class BatShootBehaviorMedium extends ShootBehavior {

	private Entity target;
	private Color color;
	private Vector base;
	
	public BatShootBehaviorMedium(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);

		this.fireRate = 1;
		
		this.target = target;
		this.color = color;
		this.base = new Vector(200, 0);
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[4];
		double angleToTarget = this.target.position.subtract(subject.position).getAngle();
		for(int i=0; i<4; i++)
		{
			BatBullet projectile = new BatBullet(this.subject.getPosition(), this.stage, this.color);
			Vector velocity = base.rotate( angleToTarget - (Math.PI/4) + (i*Math.PI/6.0)  );
			projectile.setMoveBehavior(new AccelerateMoveBehavior(projectile, velocity, Vector.zero()));
			bullets[i] = projectile;
		}
		
		return bullets;
	}

}
