import java.awt.Color;

public class EyeShootBehaviorEasy extends ShootBehavior{

	private Color color;
	private Entity target;
	private double speed;
	
	public EyeShootBehaviorEasy(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);

		this.fireRate = 3;
		
		this.color = color;
		this.target = target;
		this.speed = 450;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[3];
		
		Vector direction = this.target.getPosition().subtract( this.subject.getPosition() ).normalize();
		for(int i=0; i<3; i++)
		{
			EyeBullet projectile = new EyeBullet(subject.getPosition(), stage, color);
			projectile.setMoveBehavior(new AccelerateMoveBehavior(projectile, direction.scalarMult( (i+1)*speed/3 ), Vector.zero()));
			bullets[i] = projectile;
		}
		
		return bullets;
	}

}
