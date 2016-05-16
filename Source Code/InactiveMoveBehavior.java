
public class InactiveMoveBehavior extends MoveBehavior {
	
	public InactiveMoveBehavior(Entity subject, double expireTime) {
		super(subject, expireTime);
	}

	@Override
	protected void moveHook(double delta) {
		subject.setPosition(null);
	}
}
