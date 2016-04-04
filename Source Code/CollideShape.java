
public abstract class CollideShape {
	protected Vector position;
	protected Vector dimension;
	
	public CollideShape(Vector position, Vector dimension) {
		this.setPosition(position);
		this.dimension = dimension;
	}
	
	public abstract double getDistanceFromPointNearestTo(Vector external);
	
	public final boolean isCollidingWith(CollideShape p) {
		double myDistance = getDistanceFromPointNearestTo(p.getPosition());
		double pDistance = p.getDistanceFromPointNearestTo(position);
		double actualDistance = position.subtract( p.getPosition() ).magnitude();
		
		return ( myDistance + pDistance > actualDistance );
	}

	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}
}