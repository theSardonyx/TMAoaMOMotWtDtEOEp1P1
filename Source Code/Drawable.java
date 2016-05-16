import java.awt.*;

/**
* This class is a template for all Drawable objects
*
* @see	AnimatedDrawable
* @see	DrawableButton
* @see	DrawableImage
* @see	DrawableShape
* @see	DrawableString
*
* @author	Aemielvin Loremia
*/
public abstract class Drawable {
	/**
	* The position of this instance of Drawable
	*/
	protected Vector position;
	
	/**
	* The dimensions of this instance of Drawable
	*/
	protected Vector dimension;
	
	/**
	* The coordinates of the top-left corner of this Drawable
	*/
	protected Vector topLeftPosition;
	
	/**
	* The coordinates of the center of this Drawable
	*/
	protected Vector origin;
	
	/**
	* Creates a new Drawable with the specified positions and
	* dimensions
	*
	* @param	position	Coordinates of the new instance
	* @param	dimension	The dimensions of the new instance
	*
	* @see #setPosition
	*/
	public Drawable(Vector position, Vector dimension) {
		this.dimension = dimension;
		this.origin = dimension.scalarMult(0.5);
		setPosition( position );
	}
	
	/**
	* An abstract method for drawing this instance of Drawable
	*
	* @param	g	The graphics to be drawn
	*/
	public abstract void draw(Graphics2D g);

	/**
	* Gets the position of this instance of Drawable
	*
	* @return	#position
	*/
	public Vector getPosition() {
		return position;
	}
	
	/**
	* Sets the position of this instance of Drawable
	*
	* @param	position	Coordinates of the new position
	*/
	public void setPosition(Vector position) {
		this.position = position;
		if(position == null)
			return;
		this.topLeftPosition = position.subtract( origin );
	}

	/**
	* Gets the dimensions of this instance of Drawable
	*
	* @return	#dimension
	*/
	public Vector getDimension() {
		return dimension;
	}
	
	/**
	* Sets the dimensions of this instance of Drawable
	*
	* @param	dimension	New dimensions of this Drawable
	*/
	public void setDimension(Vector dimension) {
		this.dimension = dimension;
		this.origin = dimension.scalarMult(0.5);
		setPosition( this.position );
	}
}
