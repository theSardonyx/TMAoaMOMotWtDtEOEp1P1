import java.awt.Color;

public class BatShootBehavior1 extends ShootBehavior {
	
	private boolean shot;
	private Color color;
	public BatShootBehavior1(Entity subject, BulletStage bulletStage, double fireRate, Color color) {
		super(subject, fireRate, bulletStage);
		this.color = color;
		shot = false;
	}

	@Override
	public void shoot(double delta) {
		if(!shot)
		{
			shot = true;
			BatBullet projectile = new BatBullet(subject.getPosition(), bulletStage, color);
			projectile.setMoveBehavior(new WaveMoveBehavior(projectile, new Vector(0, 150), 50, 1));
			subject.spawnEntity(projectile);
		}
	}

}
