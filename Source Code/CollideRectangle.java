
public class CollideRectangle extends CollideShape {

	public CollideRectangle(Vector position, Vector dimension) {
		super(position, dimension);
	}

	@Override
	public double getDistanceFromPointNearestTo(Vector external) {
		double minX = position.x - (dimension.x/2.0);
		double maxX = position.x + (dimension.x/2.0);
		double clampedX = Math.max(minX, Math.min(external.x, maxX));
		
		double minY = position.y - (dimension.y/2.0);
		double maxY = position.y + (dimension.y/2.0);
		double clampedY = Math.max(minY, Math.min(external.y, maxY));
		
		Vector pointNearestToExternal = new Vector(clampedX, clampedY);
		Vector displacement = pointNearestToExternal.subtract(position);
		return displacement.magnitude();
	}

}
