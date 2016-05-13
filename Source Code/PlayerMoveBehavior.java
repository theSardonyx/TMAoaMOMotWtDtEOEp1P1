
public class PlayerMoveBehavior extends MoveBehavior {
	private boolean up, down, left, right, focus;
	private double speed;

	/*
	Constructor for PlayerMoveBehavior
	@param subject: Entity object where a PlayerMoveBehavior will be set on
	@param speed: rate of movement
	*/
	public PlayerMoveBehavior(Entity subject, double speed) {
		super(subject);
		this.speed = speed;
	}
	/*
	Method used to change the position of the given Entity object
	using simple vector calculations
	@param delta: value used in coordination with the calculations
	*/
	@Override
	protected void moveHook(double delta) {
		Vector base = new Vector(0, 0);
		double distance = speed*delta;
		if(up) {
			base = base.add(new Vector(0, -1));
		}
		if(down) {
			base = base.add(new Vector(0, 1));
		}
		if(left) {
			base = base.add(new Vector(-1, 0));
		}
		if(right) {
			base = base.add(new Vector(1, 0));
		}
		
		base = base.normalize();
		if(focus)
			base = base.scalarMult(distance/2);
		else
			base = base.scalarMult(distance);

		Vector newPosition = subject.getPosition().add(base);
		subject.setPosition(newPosition);
	}
	
	/*
	Method used to set the "face" of the Entity subject to up
	@param b: boolean value that will determine whether to face this direction
	*/
	public void setUp(boolean b) {
		this.up = b;
	}
	/*
	Method used to set the "face" of the Entity subject to down
	@param b: boolean value that will determine whether to face this direction
	*/
	public void setDown(boolean b) {
		this.down = b;
	}
	/*
	Method used to set the "face" of the Entity subject to left
	@param b: boolean value that will determine whether to face this direction
	*/
	public void setLeft(boolean b) {
		this.left = b;
	}
	/*
	Method used to set the "face" of the Entity subject to right
	@param b: boolean value that will determine whether to face this direction
	*/
	public void setRight(boolean b) {
		this.right = b;
	}
	/*
	Method used to determine whether a subject is facing up
	*/
	public boolean getUp() {
		return up;
	}
	/*
	Method used to determine whether a subject is facing down
	*/
	public boolean getDown() {
		return down;
	}
	/*
	Method used to determine whether a subject is facing left
	*/
	public boolean getLeft() {
		return left;
	}
	/*
	Method used to determine whether a subject is facing right
	*/
	public boolean getRight() {
		return right;
	}
	/*
	Method used to set a new speed for a PlayerMoveBehavior object
	@param speed: value used for the new speed
	*/
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	/*
	Method to get the speed of the subject
	*/
	public double getSpeed() {
		return speed;
	}
	/*
	Method used to set the subject to be focused
	@param b: boolean value whether to set the subject focused or not
	*/
	public void setFocus(boolean b) {
		focus = b;
	}
	/*
	Method to determine whether the subject is in focus
	*/
	public boolean getFocus() {
		return focus;
	}
	
}
