import java.awt.Color;

public class EyeShootBehaviorMedium extends ShootBehavior {

	private Color color;
	private Entity target;
	private double speed, interval, height, period;
	private int numBursts;
	private boolean withStraight;
	
	public EyeShootBehaviorMedium(Entity subject, Entity target, BulletStage stage, Color color) {
		super(subject, stage);
		
		this.color = color;
		this.target = target;
		this.speed = 300;
		this.interval = 0.2;
		this.height = 30;
		this.period = 0.75;
		this.numBursts = 3;
		this.withStraight = false;
		
		this.fireRate = (this.interval + 0.25) * this.numBursts;
	}
	
	public EyeShootBehaviorMedium(Entity subject, Entity target, BulletStage stage, Color color, double expireTime) {
		super(subject, stage, expireTime);
		
		this.color = color;
		this.target = target;
		this.speed = 300;
		this.interval = 0.2;
		this.height = 30;
		this.period = 0.75;
		this.numBursts = 3;
		this.withStraight = false;
		
		this.fireRate = (this.interval + 0.25) * this.numBursts;
	}

	@Override
	public Entity[] getBullets() {
		int spacing;
		if(withStraight)
			spacing = 3;
		else
			spacing = 2;
		int bulletsSize = numBursts * spacing;
		Entity[] bullets = new Entity[bulletsSize];
		
		Vector direction = this.target.getPosition().subtract( this.subject.getPosition() ).normalize();
		for(int i=0; i<numBursts; i++)
		{
			Vector generalVelocity = direction.scalarMult((Math.random() + 0.5) * this.speed);
			double inactiveTime = i * this.interval;
			
			EyeBullet projectile1 = new EyeBullet(this.subject.getPosition(), this.stage, this.color);
			QueueMoveBehavior queue1 = new QueueMoveBehavior(projectile1, null);
			
			InactiveMoveBehavior mb1 = new InactiveMoveBehavior(projectile1, inactiveTime);
			TeleportMoveBehavior mb2 = new TeleportMoveBehavior(projectile1, this.subject);
			WaveMoveBehavior mb3 = new WaveMoveBehavior(projectile1, generalVelocity, this.height, this.period, true);
			queue1.pushBack(mb1);
			queue1.pushBack(mb2);
			queue1.pushBack(mb3);
			projectile1.setMoveBehavior(queue1);
			bullets[i * spacing] = projectile1;
			
			EyeBullet projectile2 = new EyeBullet(this.subject.getPosition(), this.stage, this.color);
			QueueMoveBehavior queue2 = new QueueMoveBehavior(projectile2, null);
			
			mb1 = new InactiveMoveBehavior(projectile2, inactiveTime);
			mb2 = new TeleportMoveBehavior(projectile2, this.subject);
			mb3 = new WaveMoveBehavior(projectile2, generalVelocity, this.height, this.period, false);
			queue2.pushBack(mb1);
			queue2.pushBack(mb2);
			queue2.pushBack(mb3);
			projectile2.setMoveBehavior(queue2);
			bullets[i * spacing + 1] = projectile2;
			
			if(this.withStraight) {
				EyeBullet projectile3 = new EyeBullet(this.subject.getPosition(), this.stage, this.color);
				QueueMoveBehavior queue3 = new QueueMoveBehavior(projectile3, null);
				
				mb1 = new InactiveMoveBehavior(projectile3, inactiveTime);
				mb2 = new TeleportMoveBehavior(projectile3, this.subject);
				AccelerateMoveBehavior mb4 = new AccelerateMoveBehavior(projectile3, generalVelocity, Vector.zero());
				queue3.pushBack(mb1);
				queue3.pushBack(mb2);
				queue3.pushBack(mb4);
				projectile3.setMoveBehavior(queue3);
				bullets[i * spacing + 2] = projectile3;
			}
		}
		
		return bullets;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setInterval(double interval) {
		this.interval = interval;
		this.fireRate = (this.interval + 0.25) * this.numBursts;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setPeriod(double period) {
		this.period = period;
	}
	
	public void setNumBursts(int numBursts) {
		this.numBursts = numBursts;
		this.fireRate = (this.interval + 0.25) * this.numBursts;
	}
	
	public void setWithStraight(boolean withStraight) {
		this.withStraight = withStraight;
	}
}
