
public class TimedGlideMoveBehavior extends MoveBehavior{
	
	private double timeLeft;
	private Vector velocity, targetPos;
	
	public TimedGlideMoveBehavior(Entity subject, Vector targetPos, double timeCap) {
		super(subject);
		this.timeLeft = timeCap;
		this.targetPos = targetPos;
	}

	public TimedGlideMoveBehavior(Entity subject, Vector targetPos, double timeCap, double expireTime) {
		super(subject, expireTime);
		this.timeLeft = timeCap;
		this.targetPos = targetPos;
	}

	@Override
	protected void moveHook(double delta) {
		if(timeLeft > 0) {
			double timeMove = Math.min(delta, timeLeft);
			
			Vector totalDisp = this.targetPos.subtract(subject.getPosition());
			
			this.velocity = totalDisp.scalarMult(1.0 / this.timeLeft);
			
			Vector currentPos = subject.getPosition();
			Vector newPosition = currentPos.add( velocity.scalarMult( timeMove ) );
			subject.setPosition(newPosition);
			
			timeLeft -= delta;
		}
	}
}
