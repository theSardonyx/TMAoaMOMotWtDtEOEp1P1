/**
* This class represents a circular CollideShape object
*
* @see	CollideShape
*
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
public class CollideCircle extends CollideShape {
	/**
	* Creates a new circular CollideShape with the specified
	* position and diameter
	*
	* @param	position	The position of this instance of
	*						CollideShape
	* @param	diameter	The diameter of this CollideCircle
	*/
	public CollideCircle(Vector position, double diameter) {
		super(position, new Vector(diameter, diameter));
	}
	
	/**
	* Calculates the shortest distance between this CollideCircle
	* and the specified Vector
	*
	* @param	external	The Vector from which the distance is
	*						to be measured
	* @return	double	The shortest distance between this instance
	*					of CollideCircle and the specified Vector
	*/
	@Override
	public double getDistanceFromPointNearestTo(Vector external) {
		return dimension.x/2.0;
	}

}
