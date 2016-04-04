public class Vector {
	public double x;
	public double y;
	
	public Vector (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector add (Vector that) {
		return new Vector(this.x + that.x, this.y + that.y);
	}
	
	public Vector subtract (Vector that) {
		return new Vector(this.x - that.x, this.y - that.y);
	}
	
	public Vector scalarMult (double factor) {
		return new Vector(this.x * factor, this.y * factor);
	}
	
	public Vector rotate (double degree) {
		double radians =  Math.toRadians(degree);
		return new Vector(	(Math.cos(radians)*x - Math.sin(radians)*y),
							(Math.sin(radians)*x + Math.cos(radians)*y));
	}
	
	public double magnitude() {
		return  Math.sqrt(x*x + y*y);
	}
	
	public Vector normalize() {
		return this.scalarMult( 1 / this.magnitude() );
	}
	
	public Vector negate() {
		return this.scalarMult(-1);
	}
	
	public double getAngle() {
		double radians = Math.atan2(y, x);
		return Math.toDegrees(radians);
	}
	
	public void setAngle(double degree) {
		double targetRadians = Math.toRadians(degree);
		double currentRadians = Math.atan2(y, x);
		double deltaRadians = targetRadians-currentRadians;
				
		this.rotate(Math.toDegrees(deltaRadians));
	}
	
	//getters
	public int getX() {
		return (int) (x + 0.5);
	}
	
	public int getY() {
		return (int) (y + 0.5);
	}
	
}