import java.awt.Color;

public class BatShootBehaviorMedium extends ShootBehavior {

	private Color color;
	private Vector base;
	
	public BatShootBehaviorMedium(Entity subject, BulletStage stage, Color color) {
		super(subject, stage);

		this.fireRate = 1;
		
		this.color = color;
		this.base = new Vector(200, 0);
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[4];
		
		for(int i=0; i<4; i++)
		{
			BatBullet projectile = new BatBullet(this.subject.getPosition(), this.stage, this.color);
			Vector velocity = base.rotate( (Math.PI/4) + (i*Math.PI/6.0)  );
			projectile.setMoveBehavior(new AccelerateMoveBehavior(projectile, velocity, Vector.zero()));
			bullets[i] = projectile;
		}
		
		return bullets;
	}

}
