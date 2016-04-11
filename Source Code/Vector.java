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
	
	public Vector rotate (double radians) {
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
		return Math.atan2(y, x);
	}
	
	public void setAngle(double targetRadians) {
		double currentRadians = Math.atan2(y, x);
		double deltaRadians = targetRadians-currentRadians;
				
		this.rotate(deltaRadians);
	}
	
	//getters
	public int getX() {
		return (int) (x + 0.5);
	}
	
	public int getY() {
		return (int) (y + 0.5);
	}
	
}