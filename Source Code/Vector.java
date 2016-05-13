/**
* This class is used mainly for the physics and computations of the different positions and dimensions of the objects
* Assume operations are done by right hand side
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
public class Vector {
	public double x;
	public double y;
	
	/*
	Constructor of a Vector object
	A Vector object is composed of only two points, essentially for 2D computations
	double data type is used for consistency and precision
	@param x: x coordinate for the Vector
	@param y: y coordinate for the Vector
	*/
	public Vector (double x, double y) {
		this.x = x;
		this.y = y;
	}
	/*
	Method used to add this Vector from another given Vector and put the results in this Vector
	@param that: Vector object to be added on this Vector
	*/
	public Vector add (Vector that) {
		return new Vector(this.x + that.x, this.y + that.y);
	}
	
	public Vector add (double x, double y) {
		return new Vector(this.x + x, this.y + y);
	}
	/*
	Method used to subtract this Vector from another given Vector and put the results in this Vector
	@param that: Vector object to be subtracted on this Vector
	*/
	public Vector subtract (Vector that) {
		return new Vector(this.x - that.x, this.y - that.y);
	}
	/*
	Multiplication method for calculating an equation involving a scalar and a vector value
	And stores the results on this Vector
	@param factor: the scalar value to be multiplied on this Vector
	*/
	public Vector scalarMult (double factor) {
		return new Vector(this.x * factor, this.y * factor);
	}
	/*
	Method used for getting the dot product between two vectors.
	@param that: dot product is derived in relation to this other vector  
	*/
	public double dot(Vector that) {
		return (this.x*that.x) + (this.y*that.y);
	}
	/*
	Method used for getting the cross product between two vectors.
	@param that: cross product is derived in relation to this other vector  
	*/
	public double cross(Vector that) {
		return (this.x*that.y) - (this.y*that.x);
	}
	/*
	Method used for vector rotation
	vector rotation = rotating the vector in such a way it corresponds to a certain angle
	Used to change an object's position/angle in a circular motion
	@param radians: the angle(in radians) to rotate the vector from
	*/
	public Vector rotate (double radians) {
		return new Vector(	(Math.cos(radians)*x - Math.sin(radians)*y),
							(Math.sin(radians)*x + Math.cos(radians)*y));
	}
	/*
	Returns the magnitude of a Vector object
	*/
	public double magnitude() {
		return  Math.sqrt(x*x + y*y);
	}
	/*
	Normalizes a Vector object by dividing the coordinates by its magnitude
	Returns a unit vector: Vector in the same direction but with a length of 1
	*/
	public Vector normalize() {
		double magnitude = this.magnitude();
		if(magnitude!=0)
			return this.scalarMult( 1 / this.magnitude() );
		else
			return new Vector(0, 0);
	}
	/*
	Reverses this Vector by negating it's coordinates
	*/
	public Vector negate() {
		return this.scalarMult(-1);
	}
	/*
	Method for getting the current angle of a Vector
	*/
	public double getAngle() {
		return Math.atan2(y, x);
	}
	/*
	Method for setting the current angle of a Vector
	@param targetRadians: target angle(in radians) for a Vector
	*/
	public void setAngle(double targetRadians) {
		double currentRadians = Math.atan2(y, x);
		double deltaRadians = targetRadians-currentRadians;
				
		this.rotate(deltaRadians);
	}
	
	/*
	Return the x coordinate
	*/
	public int getX() {
		return (int) Math.round(x);
	}
	/*
	Return the y coordinate
	*/
	public int getY() {
		return (int) Math.round(y);
	}
	
	public Vector clone() {
		return new Vector(this.x, this.y);
	}
	
	public static Vector zero() {
		return new Vector(0, 0);
	}
	
	public String toString() {
		return this.x + " " + this.y;
	}
}