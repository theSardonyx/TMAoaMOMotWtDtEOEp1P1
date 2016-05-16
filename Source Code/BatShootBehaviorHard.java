import java.awt.Color;

public class BatShootBehaviorHard extends ShootBehavior {

	private Color color;
	private Vector base;
	private double height, period, rotation;
	private int numBullets;
	
	public BatShootBehaviorHard(Entity subject, BulletStage stage, Color color) {
		super(subject, stage);

		this.fireRate = 2;
		
		this.color = color;
		this.height = 30;
		this.period = 1;
		this.base = new Vector(100, 0);
		this.rotation = 0;
		this.numBullets = 4;
	}
	
	public BatShootBehaviorHard(Entity subject, BulletStage stage, Color color, double expireTime) {
		super(subject, stage, expireTime);

		this.fireRate = 2;
		
		this.color = color;
		this.height = 30;
		this.period = 1;
		this.base = new Vector(100, 0);
		this.rotation = 0;
		this.numBullets = 4;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[this.numBullets];
		Vector spawnPoint = this.subject.getPosition();
		
		double rotation = 0;
		double rotationDelta = (2 * Math.PI) / numBullets;
		for(int i=0; i<this.numBullets; i++)
		{
			BatBullet projectile = new BatBullet(spawnPoint, this.stage, this.color);
			Vector targetPosition = spawnPoint.add(base.setAngle(rotation));
			rotation += rotationDelta;
			boolean orientToRight = (Math.random() > 0.5);
			QueueMoveBehavior queue = new QueueMoveBehavior(projectile, null);
			
			TimedGlideMoveBehavior mb1 = new TimedGlideMoveBehavior(projectile, targetPosition, 0.5, 0.5);
			AccelerateMoveBehavior mb2 = new AccelerateMoveBehavior(projectile, Vector.zero(), Vector.zero(), 0.5);
			WaveMoveBehavior mb3 = new WaveMoveBehavior(projectile, new Vector(0, 150).rotate(this.rotation), this.height, this.period, orientToRight);
			queue.pushBack(mb1);
			queue.pushBack(mb2);
			queue.pushBack(mb3);
			
			projectile.setMoveBehavior(queue);
			
			bullets[i] = projectile;
		}
		
		return bullets;
	}

	public void setRotation(double radians) {
		this.rotation = radians;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setPeriod(double period) {
		this.period = period;
	}
	
	public void setNumBullets(int numBullets) {
		this.numBullets = numBullets;
	}
}
