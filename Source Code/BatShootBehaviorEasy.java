import java.awt.Color;

public class BatShootBehaviorEasy extends ShootBehavior {
	
	private Color color;
	private Vector genVelocity;
	private double height, period;
	private int shootCount;
	
	public BatShootBehaviorEasy(Entity subject, BulletStage bulletStage, Color color) {
		super(subject, bulletStage);
		
		this.fireRate = 1.25;
		
		this.color = color;
		this.genVelocity = new Vector(0, 150);
		this.height = 50;
		this.period = 1;
		this.shootCount = 0;
	}

	@Override
	public Entity[] getBullets() {
		
		BatBullet projectile = new BatBullet(subject.getPosition(), stage, color);
		this.shootCount++;
		
		boolean orientationToRight = (this.shootCount % 2 == 0);
		projectile.setMoveBehavior(new WaveMoveBehavior(projectile, this.genVelocity.clone(), this.height, this.period, orientationToRight));
		
		Entity[] bullets = new Entity[1];
		bullets[0] = projectile;
		return bullets;
	}
	
	public void setGenVelocity(Vector genVelocity) {
		this.genVelocity = genVelocity;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setPeriod(double period) {
		this.period = period;
	}
}
