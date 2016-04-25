/**
* This class represents the movement of entities when they are
* accelerating
* 
* @see	MoveBehavior
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
public class AccelerateMoveBehavior extends MoveBehavior {
	private Vector velocity;
	private Vector acceleration;
	
	/**
	* Creates an AccelerateMoveBehavior object for the specified
	* Entity with the specified initial velocity and acceleration
	* 
	* @param	subject		The Entity for which the new
	* 						MoveBehavior is for
	* @param	initVelocity	The initial velocity of the
	*							specified Entity
	* @param	acceleration	The acceleration of the specified
	*							Entity
	*
	* @see	MoveBehavior#subject
	* @see	MoveBehavior#velocity
	* @see	MoveBehavior#acceleration
	*/
	public AccelerateMoveBehavior(Entity subject, Vector initVelocity, Vector acceleration) {
		super(subject);
		this.velocity = initVelocity;
		this.acceleration = acceleration;
	}

	/**
	* Sets the position of #subject based on changed values after
	* the specified time has passed
	* 
	* @param	delta	The time passed in seconds
	* 
	* @see	Entity#getPosition
	* @see	Entity#setPosition
	*/
	@Override
	public void move(double delta) {
		Vector currentPos = subject.getPosition();
		Vector newPosition = currentPos.add( velocity.scalarMult( delta ) );
		velocity = velocity.add( acceleration.scalarMult( delta ) );
		subject.setPosition(newPosition);
	}

	/**
	* Gets the velocity of #subject
	* 
	* @return	#velocity
	*/
	public Vector getVelocity() {
		return velocity;
	}

	/**
	* Sets the velocity of #subject
	*
	* @param	velocity	The new value of #velocity
	*/
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	/**
	* Gets the acceleration of #subject
	*
	* @return	#acceleration
	*/
	public Vector getAcceleration() {
		return acceleration;
	}

	/**
	* Sets the acceleration of #subject
	*
	* @param	acceleration	The new value of #acceleration
	*/
	public void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}
}
