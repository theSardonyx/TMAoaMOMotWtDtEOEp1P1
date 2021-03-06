import java.awt.Color;

public class BatShootBehaviorEasy extends ShootBehavior {
	
	private Color color;
	private Vector genVelocity;
	private double height, period;
	private boolean orientationToRight;
	
	public BatShootBehaviorEasy(Entity subject, BulletStage bulletStage, Color color) {
		super(subject, bulletStage);
		
		this.fireRate = 1.25;
		
		this.color = color;
		this.genVelocity = new Vector(0, 150);
		this.height = 50;
		this.period = 1;
		this.orientationToRight = false;
	}
	
	public BatShootBehaviorEasy(Entity subject, BulletStage bulletStage, Color color, double expireTime) {
		super(subject, bulletStage, expireTime);
		
		this.fireRate = 1.25;
		
		this.color = color;
		this.genVelocity = new Vector(0, 150);
		this.height = 50;
		this.period = 1;
		this.orientationToRight = false;
	}

	@Override
	public Entity[] getBullets() {
		BatBullet projectile = new BatBullet(subject.getPosition(), stage, color);
		
		this.orientationToRight = !this.orientationToRight;
		WaveMoveBehavior mb = new WaveMoveBehavior(projectile, this.genVelocity.clone(), this.height, this.period, this.orientationToRight);
		projectile.setMoveBehavior(mb);
		
		Entity[] bullets = new Entity[1];
		bullets[0] = projectile;
		return bullets;
	}
	
	public void setGenVelocity(Vector genVelocity) {
		this.genVelocity = genVelocity;
	}
	
	public void rotate(double radians) {
		this.genVelocity.rotate(radians);
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setPeriod(double period) {
		this.period = period;
	}
	
	public void setOrientationToRight(boolean orientationToRight) {
		this.orientationToRight = orientationToRight;
	}
}
