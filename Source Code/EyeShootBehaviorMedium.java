import java.awt.Color;

public class EyeShootBehaviorMedium extends ShootBehavior {

	private Color color;
	private Entity target;
	private double speed;
	private double interval;
	private double height;
	private double period;
	
	public EyeShootBehaviorMedium(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);

		this.fireRate = 3;
		
		this.color = color;
		this.target = target;
		this.speed = 450;
		this.interval = 0.2;
		this.height = 30;
		this.period = 0.75;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[6];
		
		Vector direction = this.target.getPosition().subtract( this.subject.getPosition() ).normalize();
		for(int i=0; i<3; i++)
		{
			Vector generalVelocity = direction.scalarMult( (3-i)*this.speed/3 );
			
			EyeBullet projectile = new EyeBullet(subject.getPosition(), stage, color);
			projectile.setMoveBehavior(new QueueMoveBehavior(projectile, new MoveBehavior[] {
					new InactiveMoveBehavior(projectile, i*this.interval),
					new TeleportMoveBehavior(projectile, this.subject),
					new WaveMoveBehavior(projectile, generalVelocity, this.height, this.period, true)
			}));
			bullets[i] = projectile;
			
			projectile = new EyeBullet(subject.getPosition(), stage, color);
			projectile.setMoveBehavior(new QueueMoveBehavior(projectile, new MoveBehavior[] {
					new InactiveMoveBehavior(projectile, i*this.interval),
					new TeleportMoveBehavior(projectile, this.subject),
					new WaveMoveBehavior(projectile, generalVelocity, this.height, this.period, false)
			}));
			bullets[3+i] = projectile;
		}
		
		return bullets;
	}

}
