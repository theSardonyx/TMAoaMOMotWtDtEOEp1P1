
public abstract class MoveBehavior {
	protected Entity subject;
	protected Vector velocity;
	protected Vector acceleration;
	
	public MoveBehavior(Entity subject) {
		this.subject = subject;
	}
	
	public abstract void move(double delta);

	public Vector getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	public Vector getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}
}
