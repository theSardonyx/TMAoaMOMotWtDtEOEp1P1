
public class WaveMoveBehavior extends MoveBehavior {
	
	private AccelerateMoveBehavior accelerate;
	private Vector origin;
	private Vector direction;
	
	private Vector accToLeft;
	private Vector accToRight;
	
	private Vector velToLeft;
	private Vector velToRight;
	
	public WaveMoveBehavior(Entity subject, Vector genVelocity, double height, double period) {
		super(subject);
		Vector vX = genVelocity;
		Vector vY = genVelocity.rotate( Math.PI/2 ).normalize();
		vY = vY.scalarMult( 4.0 * height / period );
		Vector finalInitialVelocity = vX.add(vY);
		velToLeft = finalInitialVelocity;
		velToRight = vX.add(vY.scalarMult(-1));
		
		Vector acceleration = genVelocity.rotate( -Math.PI/2 ).normalize();
		acceleration = acceleration.scalarMult( 8 * height / ( period * period ) );
		
		accToRight = acceleration;
		accToLeft = accToRight.scalarMult(-1);
		accelerate = new AccelerateMoveBehavior(subject, finalInitialVelocity, acceleration);
		
		origin = subject.getPosition();
		direction = genVelocity;
	}

	@Override
	public void move(double delta) {
		accelerate.move(delta);
		
		Vector currentPosition = subject.getPosition();
		Vector inRelationToOrigin = currentPosition.subtract(origin);
		
		double cross = direction.cross( inRelationToOrigin );
		if(cross > 0 && accelerate.getAcceleration()!=accToRight) {
			accelerate.setAcceleration( accToRight );
			accelerate.setVelocity(velToLeft);
		}
		else if(cross < 0 && accelerate.getAcceleration()!=accToLeft) {
			accelerate.setAcceleration( accToLeft );
			accelerate.setVelocity(velToRight);
		}
	}

}