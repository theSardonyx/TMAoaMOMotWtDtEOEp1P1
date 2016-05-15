import java.awt.Color;

public class EyeShootBehaviorHard extends ShootBehavior {

	private Color color;
	private Entity target;
	private double speed;
	private double radius;
	
	public EyeShootBehaviorHard(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);

		this.fireRate = 3;
		
		this.target = target;
		this.color = color;
		this.speed = 450;
		this.radius = 100;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[7];
		
		Vector base = this.target.getPosition().subtract( this.subject.getPosition() ).normalize();
		Vector baseSpawnOffset = base.scalarMult( this.radius );
		for(int i=0; i<7; i++)
		{
			Vector spawnPoint = this.subject.getPosition().add(baseSpawnOffset.rotate( i * 2 * Math.PI / 7 ));
			Vector velocityDirection = this.target.getPosition().subtract( spawnPoint ).normalize();
			Vector velocity = velocityDirection.scalarMult( this.speed / (( Math.random() * 3 ) + 1) );
			
			EyeBullet projectile = new EyeBullet(this.subject.getPosition(), this.stage, this.color);
			projectile.setMoveBehavior( new QueueMoveBehavior(projectile, new MoveBehavior[] {
					new TimedGlideMoveBehavior(projectile, spawnPoint, 0.5, 0.5),
					new AccelerateMoveBehavior(projectile, Vector.zero(), Vector.zero(), 0.5),
					new AccelerateMoveBehavior(projectile, velocity, Vector.zero())
			} ));
			bullets[i] = projectile;
		}
		
		return bullets;
	}

}
