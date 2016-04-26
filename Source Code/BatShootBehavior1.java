import java.awt.Color;

public class BatShootBehavior1 extends ShootBehavior {
	
	private Color color;
	private double timer;
	
	public BatShootBehavior1(Entity subject, BulletStage bulletStage, double fireRate, Color color) {
		super(subject, fireRate, bulletStage);
		this.color = color;
		timer = 0;
	}

	@Override
	public void shoot(double delta) {
		timer += delta;
		if(timer>=fireRate)
		{
			timer = 0;
			BatBullet projectile = new BatBullet(subject.getPosition(), bulletStage, color);
			projectile.setMoveBehavior(new WaveMoveBehavior(projectile, new Vector(0, 150), 50, 1));
			subject.spawnEntity(projectile);
		}
	}

}
