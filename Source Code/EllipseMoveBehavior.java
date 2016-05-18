
public class EllipseMoveBehavior extends MoveBehavior {
	
	private Entity anchor;
	Vector center;
	double height, width, rotationDelta, rotation;

	public EllipseMoveBehavior(Entity subject, Vector center, double height, double width, double period) {
		super(subject);
		this.center = center;
		this.height = height;
		this.width = width;
		this.rotation = 0;
		this.rotationDelta = (2 * Math.PI) / period;
		this.anchor = null;
	}

	public EllipseMoveBehavior(Entity subject, Vector center, double height, double width, double period, double expireTime) {
		super(subject, expireTime);
		this.center = center;
		this.height = height;
		this.width = width;
		this.rotation = 0;
		this.rotationDelta = (2 * Math.PI) / period;
		this.anchor = null;
	}
	
	@Override
	protected void moveHook(double delta) {
		if(anchor != null)
			this.center = anchor.position;
		rotation += (rotationDelta * delta);
		rotation %= (Math.PI * 2);
		double rPart1 = Math.pow(height, 2) * Math.pow(Math.cos(rotation), 2);
		double rPart2 = Math.pow(width,  2) * Math.pow(Math.sin(rotation), 2);
		double r = (width * height) / (2 * Math.sqrt(rPart1 + rPart2));
		Vector newPosition = new Vector(r * Math.cos(rotation), r * Math.sin(rotation));
		subject.setPosition(newPosition.add(center));
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public void setCenter(Vector center) {
		this.center = center;
	}
	
	public Vector getCenter() {
		return this.center;
	}
	
	public void setRotation(double radians) {
		this.rotation = radians;
	}
	
	public double getRotation() {
		return this.rotation;
	}
	
	public void setPeriod(double period) {
		this.rotationDelta = (2 * Math.PI) / period;
	}
	
	public double getPeriod() {
		return this.rotationDelta * (2 * Math.PI);
	}
	
	public void setAnchor(Entity anchor) {
		this.anchor = anchor;
	}

	public Entity getAnchor() {
		return this.anchor;
	}
	
	@Override
	public void update() {
		if(this.anchor != null)
			this.center = this.anchor.getPosition();
		this.rotation = subject.getPosition().subtract( this.center ).getAngle();
	}
	
	public void reverse() {
		this.rotationDelta = -this.rotationDelta;
	}
}
