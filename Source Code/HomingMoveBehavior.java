
public class HomingMoveBehavior extends MoveBehavior {

	private Entity target;
	private double turnRate;
	private Vector velocity;
	private double speed;
	private double tolerance;
	
	public HomingMoveBehavior(Entity subject, Entity target, Vector initialVelocity, double turnRate, double tolerance) {
		super(subject);
		this.target = target;
		this.turnRate = turnRate;
		this.velocity = initialVelocity;
		this.speed = initialVelocity.magnitude();
		this.tolerance = tolerance;
	}

	public HomingMoveBehavior(Entity subject, Entity target, Vector initialVelocity, double turnRate, double tolerance, double expireTime) {
		super(subject, expireTime);
		this.target = target;
		this.turnRate = turnRate;
		this.velocity = initialVelocity;
		this.speed = initialVelocity.magnitude();
		this.tolerance = tolerance;
	}
	
	@Override
	protected void moveHook(double delta) {
		Vector displacement = velocity.scalarMult( delta );
		if( this.target != null)
		{	
			Vector newDisplacement = displacement;
			Vector subjectToTarget = this.target.getPosition().subtract(  this.subject.getPosition() );
			
			if(subjectToTarget.magnitude() <= this.tolerance)
			{
				this.velocity = new Vector(0, 0);
				return;
			}
			if(subjectToTarget.magnitude()!=0 && this.velocity.magnitude()==0)
			{
				this.velocity = subjectToTarget.normalize().scalarMult( speed );
				newDisplacement = this.velocity.scalarMult( delta );
			}
			
			// ANGLE
			double currAngle = this.velocity.getAngle();
			double targetAngle = subjectToTarget.getAngle();
			
			double angleDifference = targetAngle - currAngle;
			while(angleDifference > Math.PI)
				angleDifference -= 2*Math.PI;
			while(angleDifference < -Math.PI)
				angleDifference += 2*Math.PI;
			
			double newAngle = angleDifference;
			if(Math.abs(newAngle) > Math.abs( this.turnRate*delta ))
			{
				if(newAngle <= 0)
					newAngle = -Math.abs(this.turnRate*delta);
				else 
					newAngle = Math.abs(this.turnRate*delta);
			}
			newDisplacement = newDisplacement.rotate( newAngle );
			
			// SPEED
			double distanceToTravel = this.speed*delta;
			double distanceToTarget = subjectToTarget.magnitude();
			if(distanceToTravel > distanceToTarget)
				distanceToTravel = distanceToTarget;
			newDisplacement = newDisplacement.normalize().scalarMult( distanceToTravel );
				
			this.velocity = newDisplacement.scalarMult( 1.0/delta );
			displacement = newDisplacement;
		}
		
		Vector newPosition = this.subject.getPosition().add( displacement );
		this.subject.setPosition( newPosition );
	}

}
