
public class TimedGlideMoveBehavior extends MoveBehavior{
	
	private double timeLeft;
	private Vector velocity;
	
	public TimedGlideMoveBehavior(Entity subject, Vector targetPos, double timeCap) {
		super(subject);
		this.timeLeft = timeCap;
		System.out.println(timeLeft);
		
		Vector init = subject.getPosition();
		Vector fin = targetPos;
		Vector totalDisp = fin.subtract(init);
		
		double distance = totalDisp.magnitude();
		
		if(distance<=0 || timeLeft<=0)
			velocity = new Vector(0, 0);
		else
			velocity = totalDisp.scalarMult( 1.0/timeLeft );
	}

	@Override
	public void move(double delta) {
		if(timeLeft>0) {
			double timeMove = Math.min(delta, timeLeft);
			
			Vector currentPos = subject.getPosition();
			Vector newPosition = currentPos.add( velocity.scalarMult( timeMove ) );
			subject.setPosition(newPosition);
			
			timeLeft -= delta;
		}
	}

}
