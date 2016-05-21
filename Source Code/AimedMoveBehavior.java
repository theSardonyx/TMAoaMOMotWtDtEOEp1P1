
public class AimedMoveBehavior extends MoveBehavior {
	
	private Entity target;
	private double velocityMagnitude, accelerationMagnitude;
	private Vector velocity, acceleration;

	public AimedMoveBehavior(Entity subject, Entity target, double velocityMagnitude, double accelerationMagnitude) {
		super(subject);
		
		this.target = target;
		this.velocityMagnitude = velocityMagnitude;
		this.accelerationMagnitude = accelerationMagnitude;
		
		Vector diff= new Vector(0, -1);
		if(this.target != null)
			diff = this.target.position.subtract(this.subject.position).normalize();
		this.velocity = diff.scalarMult(velocityMagnitude);
		this.acceleration = diff.scalarMult(accelerationMagnitude);
	}
	
	public AimedMoveBehavior(Entity subject, Entity target, double velocityMagnitude, double accelerationMagnitude, double expireTime) {
		super(subject, expireTime);
		
		this.target = target;
		this.velocityMagnitude = velocityMagnitude;
		this.accelerationMagnitude = accelerationMagnitude;
		
		Vector diff = this.target.position.subtract(this.subject.position).normalize();
		this.velocity = diff.scalarMult(velocityMagnitude);
		this.acceleration = diff.scalarMult(accelerationMagnitude);
	}

	@Override
	protected void moveHook(double delta) {
		this.subject.setPosition(this.subject.position.add(this.velocity.scalarMult(delta)));
		this.velocity = this.velocity.add(this.acceleration.scalarMult(delta));
		((DrawableImage) this.subject.visual).setRotation((Math.PI / 2) + this.velocity.getAngle());
	}
	
	@Override
	public void update() {
		Vector diff = this.target.position.subtract(this.subject.position).normalize();
		this.velocity = diff.scalarMult(this.velocityMagnitude);
		this.acceleration = diff.scalarMult(this.accelerationMagnitude);
	}
	
	public Vector getVelocity() {
		return this.velocity;
	}
}
