
public class TeleportMoveBehavior extends MoveBehavior {

	private Vector targetPosition;
	
	public TeleportMoveBehavior(Entity subject, Vector targetPosition) {
		super(subject, 0.01);
		this.targetPosition = targetPosition;
	}

	@Override
	protected void moveHook(double delta) {
		subject.setPosition(targetPosition);
	}

}
