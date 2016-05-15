import java.awt.Color;

public class PixieShootBehaviorMedium extends ShootBehavior {
	
	private Color color;
	private Entity target;
	private double speed;
	private double interval;
	
	public PixieShootBehaviorMedium(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);

		this.fireRate = 2;
		
		this.target = target;
		this.speed = 200;
		this.interval = 0.25;
		this.color = color;
	}

	@Override
	public Entity[] getBullets() {
		int basis = 3;
		Entity[] bullets = new Entity[basis*2];
		
		Vector base = target.getPosition().subtract( subject.getPosition() ).normalize().scalarMult( this.speed );
		double baseAngle = base.getAngle() + Math.PI/2;
		for(int i=0; i<basis; i++)
		{
			PixieBullet projectile = new PixieBullet(this.subject.getPosition(), this.stage, this.color);
			double rotation = baseAngle - (i * Math.PI/(basis*2-1));
			Vector velocity = base.setAngle(rotation);
			projectile.setMoveBehavior(new QueueMoveBehavior(this.subject, new MoveBehavior[] {
					new InactiveMoveBehavior(projectile, i*this.interval),
					new TeleportMoveBehavior(projectile, this.subject),
					new AccelerateMoveBehavior(projectile, velocity, Vector.zero())
			}));
			bullets[i] = projectile;
			
			projectile = new PixieBullet(this.subject.getPosition(), this.stage, this.color);
			rotation = (baseAngle + Math.PI) + (i * Math.PI/(basis*2-1));
			velocity = base.setAngle(rotation);
			projectile.setMoveBehavior(new QueueMoveBehavior(this.subject, new MoveBehavior[] {
					new InactiveMoveBehavior(projectile, i*this.interval),
					new TeleportMoveBehavior(projectile, this.subject),
					new AccelerateMoveBehavior(projectile, velocity, Vector.zero())
			}));
			bullets[basis+i] = projectile;
		}
		
		return bullets;
	}

}
