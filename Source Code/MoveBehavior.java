/**
* This abstract class represents the mocing behavior or pattern for a certain Entity
* 
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
public abstract class MoveBehavior {
	protected Entity subject;
	protected Vector velocity;
	protected Vector acceleration;
	
	/*
	Constructor for MoveBehavior
	@oaram subject: Entity object to get and determine the MoveBehavior
	*/
	public MoveBehavior(Entity subject) {
		this.subject = subject;
	}
	/*
	Abstract method for a specific MoveBehavior
	@param delta: used to update objects based from time passed
	*/
	public abstract void move(double delta);
}
