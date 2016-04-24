
public abstract class MoveBehavior {
	protected Entity subject;
	protected Vector velocity;
	protected Vector acceleration;
	
	/*
	Constructor for MoveBehavior
	@oaram subject: Entity object to get and determine the MoveBehavior
	*/
	public MoveBehavior(Entity subject) {
		this.subject = subject;
	}
	/*
	Abstract method for a specific MoveBehavior
	@param delta: value used for an objects MoveBehavior
	*/
	public abstract void move(double delta);
}
