import java.awt.Color;

public class EyeShootBehaviorHard extends ShootBehavior {

	private Color color;
	private Entity target;
	private double speed, radius;
	private int numBullets;
	
	public EyeShootBehaviorHard(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.fireRate = 2;
		
		this.radius = 100;
		this.speed = 500;
		this.target = target;
		this.color = color;
		this.numBullets = 7;
	}
	
	public EyeShootBehaviorHard(Entity subject, Entity target, BulletStage stage, Color color, double expireTime) {
		super(subject, stage, expireTime);
		
		this.fireRate = 2;
		
		this.radius = 100;
		this.speed = 500;
		this.target = target;
		this.color = color;
		this.numBullets = 7;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[this.numBullets];
		
		Vector base = this.target.getPosition().subtract( this.subject.getPosition() ).normalize();
		Vector baseSpawnOffset = base.scalarMult( this.radius );
		for(int i = 0; this.numBullets > i; i++) {
			Vector spawnPoint = this.subject.getPosition().add(baseSpawnOffset.rotate( i * 2 * Math.PI / this.numBullets ));
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
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setNumBullets(int numBullets) {
		this.numBullets = numBullets;
	}
}
