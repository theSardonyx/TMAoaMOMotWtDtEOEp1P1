import java.awt.Color;

public class PixieShootBehaviorEasy extends ShootBehavior {

	private Color color;
	private Entity target;
	private double speed;
	private double interval;
	private int shootCounter;
	
	public PixieShootBehaviorEasy(Entity subject, Entity target, BulletStage stage, boolean startOnRight, Color color) {
		super(subject, stage);
		
		this.fireRate = 3;
		
		this.target = target;
		this.speed = 200;
		this.interval = 0.5;
		this.color = color;
		this.shootCounter = 1;
		if(startOnRight)
			shootCounter++;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[3];
		
		Vector base = target.getPosition().subtract( subject.getPosition() ).normalize().scalarMult( this.speed );
		boolean orientToRight = (shootCounter % 2 == 0);
		for(int i=0; i<3; i++)
		{
			PixieBullet projectile = new PixieBullet(this.subject.getPosition(), this.stage, this.color);
			double rotation = i*Math.PI/6;
			
			if(orientToRight)
				rotation = -rotation;
			
			Vector velocity = base.rotate(rotation);
			projectile.setMoveBehavior(new QueueMoveBehavior(this.subject, new MoveBehavior[] {
					new InactiveMoveBehavior(projectile, i*this.interval),
					new TeleportMoveBehavior(projectile, this.subject),
					new AccelerateMoveBehavior(projectile, velocity, Vector.zero())
			}));
			
			bullets[i] = projectile;
		}
		
		shootCounter++;
		return bullets;
	}

}
