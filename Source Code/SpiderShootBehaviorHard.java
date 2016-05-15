import java.awt.Color;

public class SpiderShootBehaviorHard extends ShootBehavior {

	private  Color color;
	private double accMagnitude;
	private double initialOffset;
	
	public SpiderShootBehaviorHard(Entity subject, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.fireRate = 1;
		
		this.color = color;
		this.accMagnitude = 200;
		this.initialOffset = 50;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[3];
		
		Vector baseExplodePosition = new Vector(initialOffset, 0);
		for(int i=0; i<3; i++)
		{
			Vector explodePosition = baseExplodePosition.rotate(Math.random()* 2 *Math.PI);
			SpiderBullet projectile = new SpiderBullet(this.subject.getPosition(), this.stage, this.color);
			projectile.setMoveBehavior(new QueueMoveBehavior(projectile, new MoveBehavior[]{
					new AccelerateMoveBehavior(projectile, Vector.zero(), Vector.zero(), 0.5),
					new TimedGlideMoveBehavior(projectile, this.subject.getPosition().add(explodePosition), 0.5, 0.5),
					new AccelerateMoveBehavior(projectile, Vector.zero(), Vector.zero(), 0.5),
					new AccelerateMoveBehavior(projectile, Vector.zero(), new Vector(0, 200))
			}));
			
			bullets[i] = projectile;
		}
		
		return bullets;
	}

}
