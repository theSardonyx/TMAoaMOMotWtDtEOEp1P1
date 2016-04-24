/**
* This class represents a rectangular CollideShape object
*
* @see	CollideShape
*
* @author Aemielvin Loremia
*/
public class CollideRectangle extends CollideShape {
	/**
	* Creates a new rectangular CollideShape with the specified
	* position and dimensions
	* 
	* @param	position	The position of this instance of
	*						CollideShape
	* @param	dimension	The dimensions of this CollideRectangle
	*/
	public CollideRectangle(Vector position, Vector dimension) {
		super(position, dimension);
	}

	/**
	* Calculates the shortest distance between this CollideRectangle
	* and the specified Vector
	*
	* @param	external	The Vector from which the distance is
	*						to be measured
	* @param	double	The shortest distance between this instance
	*					of CollideRectangle and the specified Vector
	*/
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
