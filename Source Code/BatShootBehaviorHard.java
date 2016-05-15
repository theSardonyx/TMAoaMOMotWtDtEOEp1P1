import java.awt.Color;

public class BatShootBehaviorHard extends ShootBehavior {

	private Color color;
	private Vector base;
	private double height, period;
	
	public BatShootBehaviorHard(Entity subject, BulletStage stage, Color color) {
		super(subject, stage);

		this.fireRate = 2;
		
		this.color = color;
		this.height = 30;
		this.period = 1;
		this.base = new Vector(100, 0).rotate(Math.PI/4);
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[4];
		Vector spawnPoint = this.subject.getPosition();
		
		for(int i=0; i<4; i++)
		{
			BatBullet projectile = new BatBullet(spawnPoint, this.stage, this.color);
			Vector targetPosition = spawnPoint.add(base.rotate( i*Math.PI/2 ));
			boolean orientToRight = (i % 2 == 0);
			projectile.setMoveBehavior(new QueueMoveBehavior(projectile, new MoveBehavior[] {
					new TimedGlideMoveBehavior(projectile, targetPosition, 0.5, 0.5),
					new AccelerateMoveBehavior(projectile, Vector.zero(), Vector.zero(), 0.5),
					new WaveMoveBehavior(projectile, new Vector(0, 150), height, period, orientToRight)
			}));
			
			bullets[i] = projectile;
		}
		
		return bullets;
	}

}
