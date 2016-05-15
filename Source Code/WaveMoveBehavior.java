
public class WaveMoveBehavior extends MoveBehavior {
	
	private AccelerateMoveBehavior accelerate;
	
	private double height;
	private double period;
	private boolean startOnRight;
	private Vector genVelocity;
	
	private Vector origin;
	private Vector direction;
	
	private Vector accToLeft;
	private Vector accToRight;
	
	private Vector velToLeft;
	private Vector velToRight;
	
	public WaveMoveBehavior(Entity subject, Vector genVelocity, double height, double period, boolean startOnRight) {
		super(subject);
		this.height = height;
		this.period = period;
		this.genVelocity = genVelocity;
		this.startOnRight = startOnRight;
		
		update();
	}

	public WaveMoveBehavior(Entity subject, Vector genVelocity, double height, double period, boolean startOnRight, double expireTime) {
		super(subject, expireTime);
		this.height = height;
		this.period = period;
		this.genVelocity = genVelocity;
		this.startOnRight = startOnRight;
		
		update();
	}

	@Override
	protected void moveHook(double delta) {
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

	@Override
	public void update() {
		Vector vX = genVelocity;
		Vector vY = genVelocity.rotate( Math.PI/2 ).normalize();
		vY = vY.scalarMult( 4.0 * height / period );
		
		velToLeft = vX.add(vY);
		velToRight = vX.add(vY.scalarMult(-1));
		accToRight = genVelocity.rotate( -Math.PI/2 ).normalize().scalarMult( 8 * height / ( period * period ) );
		accToLeft = accToRight.scalarMult(-1);
		
		if(this.startOnRight)
			accelerate = new AccelerateMoveBehavior(subject, velToRight, accToLeft);
		else
			accelerate = new AccelerateMoveBehavior(subject, velToLeft, accToRight);
		
		origin = subject.getPosition();
		direction = genVelocity;
	}
}
