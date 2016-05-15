
public class InactiveMoveBehavior extends MoveBehavior {
	
	private Vector hidingSpot;
	
	public InactiveMoveBehavior(Entity subject, double expireTime) {
		super(subject, expireTime);
		this.hidingSpot = new Vector(1000000, 1000000);
	}

	@Override
	protected void moveHook(double delta) {
		subject.setPosition(hidingSpot);
	}
}
