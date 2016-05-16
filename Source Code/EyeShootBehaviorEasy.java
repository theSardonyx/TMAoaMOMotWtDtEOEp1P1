import java.awt.Color;

public class EyeShootBehaviorEasy extends ShootBehavior{

	private Color color;
	private Entity target;
	private double speed;
	private int numBullets;
	
	public EyeShootBehaviorEasy(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);

		this.fireRate = 2;
		
		this.color = color;
		this.target = target;
		this.speed = 300;
		this.numBullets = 3;
	}
	
	public EyeShootBehaviorEasy(Entity subject, Entity target, BulletStage stage, Color color, double expireTime) {
		super(subject, stage, expireTime);

		this.fireRate = 2;
		
		this.color = color;
		this.target = target;
		this.speed = 300;
		this.numBullets = 3;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[this.numBullets];
		
		Vector direction = this.target.getPosition().subtract( this.subject.getPosition() ).normalize();
		for(int i=0; i<this.numBullets; i++)
		{
			EyeBullet projectile = new EyeBullet(subject.getPosition(), stage, color);
			AccelerateMoveBehavior mb = new AccelerateMoveBehavior(projectile, direction.scalarMult((Math.random() + 0.5) * this.speed), Vector.zero());
			projectile.setMoveBehavior(mb);
			bullets[i] = projectile;
		}
		
		return bullets;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setNumBullets(int numBullets) {
		this.numBullets = numBullets;
	}
}
