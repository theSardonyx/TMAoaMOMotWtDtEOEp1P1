import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
* This class is a template for CollideShape objects that checks
* whether they are overlapping / colliding with another
*
* @see	CollideCircle
* @see	CollideRectangle
* 
* @author	Aemielvin Loremia
*/
public class CollideShape {
	
	protected Rectangle2D.Double collideRectangle;
	protected Ellipse2D.Double collideEllipse;
	
	protected boolean canCollideRectangle, canCollideEllipse;
	
	protected Vector position, offset;
	
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
		this.position = position;
		this.dimension = dimension;
		this.offset = Vector.zero();
		this.collideRectangle = new Rectangle2D.Double(this.position.x - (this.dimension.x / 2), 
													this.position.y - (this.dimension.y / 2), 
													this.dimension.x, this.dimension.y);
		this.collideEllipse = new Ellipse2D.Double(this.position.x - (this.dimension.x / 2), 
												this.position.y - (this.dimension.y / 2), 
												this.dimension.x, this.dimension.y);
		this.canCollideEllipse = false;
		this.canCollideRectangle = false;
	}
	
	public CollideShape(Vector position, Vector offset, Vector dimension) {
		this.position = position;
		this.dimension = dimension;
		this.offset = offset;
		this.collideRectangle = new Rectangle2D.Double(this.position.x + this.offset.x - (this.dimension.x / 2), 
													this.position.y + this.offset.y - (this.dimension.y / 2), 
													this.dimension.x, this.dimension.y);
		this.collideEllipse = new Ellipse2D.Double(this.position.x + this.offset.x - (this.dimension.x / 2), 
												this.position.y + this.offset.y - (this.dimension.y / 2), 
												this.dimension.x, this.dimension.y);
		this.canCollideEllipse = false;
		this.canCollideRectangle = false;
	}
	
	public final boolean isCollidingWith(CollideShape other) {
		if(other == null)
			return false;
		if(canCollideRectangle) {
			if(this.getRectangle2D().getBounds2D().intersects(other.getRectangle2D().getBounds2D()))
				return true;
			if(this.getRectangle2D().getBounds2D().intersects(other.getEllipse2D().getBounds2D()))
				return true;
		}
		if(canCollideEllipse) {			
			if(this.getEllipse2D().getBounds2D().intersects(other.getRectangle2D().getBounds2D()))
				return true;
			if(this.getEllipse2D().getBounds2D().intersects(other.getEllipse2D().getBounds2D()))
				return true;
		}
		return false;
	}
	
	public Rectangle2D.Double getRectangle2D() {
		return this.collideRectangle;
	}
	
	public Ellipse2D.Double getEllipse2D() {
		return this.collideEllipse;
	}
	
	public CollideShape setCollideRectangle(boolean canCollideRectangle) {
		this.canCollideRectangle = canCollideRectangle;
		return this;
	}
	
	public CollideShape setCollideEllipse(boolean canCollideEllipse) {
		this.canCollideEllipse = canCollideEllipse;
		return this;
	}
	
	public CollideShape updatePosition(Vector v) {
		this.collideRectangle.x = v.x + this.offset.x - (this.dimension.x / 2);
		this.collideRectangle.y = v.y + this.offset.y - (this.dimension.y / 2);

		this.collideEllipse.x = v.x + this.offset.x - (this.dimension.x / 2);
		this.collideEllipse.y = v.y + this.offset.y - (this.dimension.y / 2);
		
		return this;
	}
}