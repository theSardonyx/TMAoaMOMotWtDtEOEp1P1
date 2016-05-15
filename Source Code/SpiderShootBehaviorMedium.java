import java.awt.Color;

public class SpiderShootBehaviorMedium extends ShootBehavior {

	private  Color color;
	private double accMagnitude;
	
	public SpiderShootBehaviorMedium(Entity subject, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.fireRate = 0.5;
		
		this.color = color;
		this.accMagnitude = 150;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[1];
		
		SpiderBullet projectile = new SpiderBullet(this.subject.getPosition(), this.stage, this.color);
		projectile.setMoveBehavior(new QueueMoveBehavior(projectile, new MoveBehavior[]{
				new AccelerateMoveBehavior(projectile, Vector.zero(), Vector.zero(), 1.5),
				new AccelerateMoveBehavior(projectile, Vector.zero(), new Vector(0, this.accMagnitude))
		}));
		
		bullets[0] = projectile;
		
		return bullets;
	}

}
