/**
* This class is a template for CollideShape objects that checks
* whether they are overlapping / colliding with another
*
* @see	CollideCircle
* @see	CollideRectangle
* 
* @author	Aemielvin Loremia
*/
public abstract class CollideShape {
	/**
	* The position of this instance of CollideShape
	*/
	protected Vector position;
	
	/**
	* The dimensions of this instance of CollideShape
	*/
	protected Vector dimension;
	
	/**
	* Creates a new instance of CollideShape with the specified
	* position and dimensions
	*
	* @param	position	Coordinates of the new instance
	* @param	dimension	The dimensions of the new instance
	*
	* @see	#setPosition
	*/
	public CollideShape(Vector position, Vector dimension) {
		this.setPosition(position);
		this.dimension = dimension;
	}
	
	/**
	* An abstract method for getting the shortest distance between
	* the specified Vector and this instance of CollideShape
	*
	* @param	external	The Vector from which the distance
	*						is to be measured
	* @return	double	The shortest distance between this instance
	*					of CollideShape and the specified Vector
	*/
	public abstract double getDistanceFromPointNearestTo(Vector external);
	
	/**
	* Checks if this instance of CollideShape is overlapping / 
	* colliding with the specified CollideShape
	*
	* @param	p	The CollideShape with which to check if this
	*				instance is colliding
	* @return	true	If this instance is colliding with the
	*					specified CollideShape, or
	*			false	If they are not
	*
	* @see #getDistanceFromPointNearestTo
	*/
	public final boolean isCollidingWith(CollideShape p) {
		double myDistance = getDistanceFromPointNearestTo(p.getPosition());
		double pDistance = p.getDistanceFromPointNearestTo(position);
		double actualDistance = position.subtract( p.getPosition() ).magnitude();
		
		return ( myDistance + pDistance >= actualDistance );
	}

	/**
	* Gets the position of this CollideShape
	* 
	* @return	#position
	*/
	public Vector getPosition() {
		return position;
	}

	/**
	* Sets the position of this CollideShape
	*
	* @param	position	Coordinates of the new position
	*/
	public void setPosition(Vector position) {
		this.position = position;
	}
}