
public abstract class ShootBehavior {
	private Entity subject;
	private double fireRate;
	
	public ShootBehavior(Entity subject, double fireRate) {
		this.subject = subject;
	}
	
	public abstract void shoot(double delta);
}
