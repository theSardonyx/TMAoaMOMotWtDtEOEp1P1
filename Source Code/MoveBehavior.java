
public abstract class MoveBehavior {
	private Entity subject;
	
	public MoveBehavior(Entity subject) {
		this.subject = subject;
	}
	
	public abstract void move(double delta);
}
