
public abstract class ShootBehavior {
	protected Entity subject;
	protected double fireRate;
	
	public ShootBehavior(Entity subject, double fireRate) {
		this.subject = subject;
	}
	
	public abstract void shoot(double delta);
}
