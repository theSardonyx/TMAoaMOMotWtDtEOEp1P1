/**
* This abstract class represents the shooting behavior or pattern for a certain Entity
* 
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
public abstract class ShootBehavior {
	protected Entity subject;
	protected BulletStage bulletStage;
	protected double fireRate;
	
	/*
	Constructor for a ShootBehavior object
	Assigns an instance of ShootBehavior to a subject, alongside a set fire rate
	@param subject: Entity in which the instance of this class is given
	@param fireRate: value to set the fire rate for a given ShootBehavior
	*/
	public ShootBehavior(Entity subject, double fireRate, BulletStage bulletStage) {
		this.subject = subject;
		this.bulletStage = bulletStage;
		this.fireRate = fireRate;
	}
	/*
	Abstract method to execute the ShootBehavior of a given Entity
	*/
	public abstract void shoot(double delta);
}
