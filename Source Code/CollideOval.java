
public class CollideOval extends CollideShape {

	public CollideOval(Vector position, Vector dimension) {
		super(position, dimension);
	}

	@Override
	public double getDistanceFromPointNearestTo(Vector external) {
		Vector displacement = external.subtract( position );
		double angle = displacement.getAngle();
		double componentX = Math.cos(angle) * dimension.x;
		double componentY = Math.sin(angle) * dimension.y;
		return Math.sqrt( ( componentX * componentX ) + ( componentY * componentY ) )/2;
	}

}
