/**
* This abstract class represents the mocing behavior or pattern for a certain Entity
* 
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
public abstract class MoveBehavior {
	protected Entity subject;
	protected double expireTime;
	
	/*
	Constructor for MoveBehavior
	@oaram subject: Entity object to get and determine the MoveBehavior
	*/
	public MoveBehavior(Entity subject) {
		this.subject = subject;
		this.expireTime = Double.POSITIVE_INFINITY;
	}
	
	public MoveBehavior(Entity subject, double expireTime)
	{
		this.subject = subject;
		this.expireTime = expireTime;
	}
	
	/*
	Abstract method for a specific MoveBehavior
	@param delta: used to update objects based from time passed
	*/
	public final void move(double delta)
	{
		if(expireTime > 0){
			double moveTime = Math.min(expireTime, delta);
			moveHook(moveTime);
			this.expireTime -= moveTime;
		}
	}
	
	protected abstract void moveHook(double delta);
	
	
	public MoveBehavior getMoveBehavior() {
		return this;
	}
	
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public double getExpireTime() {
		return expireTime;
	}
	
	public void setExpireTime(double expireTime) {
		this.expireTime = expireTime;
	}
}
