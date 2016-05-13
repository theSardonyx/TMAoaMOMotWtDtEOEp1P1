/**
* This abstract class represents the shooting behavior or pattern for a certain Entity
* 
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
public abstract class ShootBehavior {
	protected Entity subject;
	protected BulletStage stage;
	protected double fireRate, timer;
	
	/*
	Constructor for a ShootBehavior object
	Assigns an instance of ShootBehavior to a subject, alongside a set fire rate
	@param subject: Entity in which the instance of this class is given
	@param fireRate: value to set the fire rate for a given ShootBehavior
	*/
	public ShootBehavior(Entity subject, BulletStage stage) {
		this.subject = subject;
		this.stage = stage;
		this.fireRate = 1;
		this.timer = 0;
	}
	/*
	Abstract method to execute the ShootBehavior of a given Entity
	*/
	public void shoot(double delta) {
		this.timer += delta;
		if(this.timer >= this.fireRate) {
			this.timer = 0;
			Entity[] bullets = this.getBullets();
			if(bullets == null)
				return;
			for(Entity bullet : bullets) {
				if(bullet != null)
					subject.spawnEntity(bullet);
			}
		}
	}
	
	public abstract Entity[] getBullets();
	
	public double getFireRate() {
		return this.fireRate;
	}
	
	public void setFireRate(double fireRate) {
		this.fireRate = fireRate;
	}
}
