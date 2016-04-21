
public abstract class MoveBehavior {
	protected Entity subject;
	protected Vector velocity;
	protected Vector acceleration;
	
	public MoveBehavior(Entity subject) {
		this.subject = subject;
	}
	
	public abstract void move(double delta);
}
