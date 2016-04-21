
public class AccelerateMoveBehavior extends MoveBehavior {
	public AccelerateMoveBehavior(Entity subject, Vector initVelocity, Vector acceleration) {
		super(subject);
		this.velocity = initVelocity;
		this.acceleration = acceleration;
	}

	@Override
	public void move(double delta) {
		Vector currentPos = subject.getPosition();
		Vector newPosition = currentPos.add( velocity.scalarMult( delta ) );
		velocity = velocity.add( acceleration.scalarMult( delta ) );
		subject.setPosition(newPosition);
	}

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
