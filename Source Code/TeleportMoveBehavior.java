
public class TeleportMoveBehavior extends MoveBehavior {

	private Vector targetPosition;
	private Entity anchor;
	
	public TeleportMoveBehavior(Entity subject, Vector targetPosition) {
		super(subject, 0.01);
		this.targetPosition = targetPosition;
		this.anchor = null;
	}
	
	public TeleportMoveBehavior(Entity subject, Entity anchor)
	{
		super(subject, 0.01);
		this.anchor = anchor;
		this.targetPosition = this.anchor.getPosition();
	}

	@Override
	protected void moveHook(double delta) {
		if(this.anchor != null)
			targetPosition = this.anchor.getPosition();
		
		subject.setPosition(targetPosition);
	}

}
