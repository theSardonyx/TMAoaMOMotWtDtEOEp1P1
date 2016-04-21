
public class PlayerMoveBehavior extends MoveBehavior {
	private boolean up, down, left, right, focus;
	private double speed;

	public PlayerMoveBehavior(Entity subject, double speed) {
		super(subject);
		this.speed = speed;
	}

	@Override
	public void move(double delta) {
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
	
	public void setUp(boolean b) {
		this.up = b;
	}
	
	public void setDown(boolean b) {
		this.down = b;
	}
	
	public void setLeft(boolean b) {
		this.left = b;
	}
	
	public void setRight(boolean b) {
		this.right = b;
	}
	
	public boolean getUp() {
		return up;
	}
	
	public boolean getDown() {
		return down;
	}
	
	public boolean getLeft() {
		return left;
	}
	
	public boolean getRight() {
		return right;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setFocus(boolean b) {
		focus = b;
	}
	
	public boolean getFocus() {
		return focus;
	}
	
}
