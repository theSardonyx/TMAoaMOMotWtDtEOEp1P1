import java.awt.Color;

public class EyeShootBehaviorHard extends ShootBehavior {

	private Color color;
	private Entity target;
	private double speed;
	private double radius;
	
	public EyeShootBehaviorHard(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.fireRate = 2;
		
		this.radius = 100;
		this.speed = 500;
		this.target = target;
		this.color = color;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[7];
		
		Vector base = this.target.getPosition().subtract( this.subject.getPosition() ).normalize();
		Vector baseSpawnOffset = base.scalarMult( this.radius );
		for(int i = 0; 7 > i; i++) {
			Vector spawnPoint = this.subject.getPosition().add(baseSpawnOffset.rotate( i * 2 * Math.PI / 7 ));
			double velocityMagnitude = this.speed / (( Math.random() * 3 ) + 1);
			
			EyeBullet projectile = new EyeBullet(this.subject.position, this.stage, this.color);
			QueueMoveBehavior queue = new QueueMoveBehavior(projectile, null);
			
			TimedGlideMoveBehavior mb1 = new TimedGlideMoveBehavior(projectile, spawnPoint, 0.5, 0.5);
			AccelerateMoveBehavior mb2 = new AccelerateMoveBehavior(projectile, Vector.zero(), Vector.zero(), 0.25);
			AimedMoveBehavior mb3 = new AimedMoveBehavior(projectile, this.target, velocityMagnitude, 0);
			queue.pushBack(mb1);
			queue.pushBack(mb2);
			queue.pushBack(mb3);
			projectile.setMoveBehavior(queue);
			
			bullets[i] = projectile;
		}
		
		return bullets;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}
