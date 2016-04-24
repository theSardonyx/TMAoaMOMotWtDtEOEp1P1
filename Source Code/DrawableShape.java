import java.awt.Color;
import java.awt.Graphics2D;

/*
* This class is a template for Drawable objects that draw shapes
*
* @see	Drawable
* @see	DrawableCircle
* @see	DrawableRectangle
*
* @author	Aemielvin Loremia
*/
public abstract class DrawableShape extends Drawable {
	/*
	* The color of this DrawableShape
	*/
	protected Color color;
	
	/*
	* Whether the shape drawn will be filled or not
	*/
	protected boolean filled;
	
	/*
	* Creates a new DrawableShape with the specified position,
	* dimensions, and color
	*
	* @param	position	Coordinates of the position of this
	*						DrawableShape
	* @param	dimension	The dimensions of this Drawable
	* @param	color	The color this shape will be drawn in
	*/
	public DrawableShape(Vector position, Vector dimension, Color color) {
		super(position, dimension);
		this.color = color;
	}
	
	/*
	* Draws this instance of DrawableShape
	*
	* @param	g	The graphics to be drawn
	*
	* @see	#filled
	* @see	#drawFilled
	* @see	#drawOutline
	*/
	@Override
	public final void draw(Graphics2D g) {
		g.setColor(color);
		if(filled)
			drawFilled(g);
		else
			drawOutline(g);
	}
	
	/*
	* An abstract method that draws this DrawableShape as a filled
	* shape
	*
	* @param	g	The graphics to be drawn
	*/
	protected abstract void drawFilled(Graphics2D g);
	
	/*
	* An abstract method that draws this DrawableShape as an outline
	*
	* @param	g	The graphics to be drawn
	*/
	protected abstract void drawOutline(Graphics2D g);
	
	/*
	* Gets the color of this instance of DrawableShape
	*
	* @return	#color
	*/
	public Color getColor() {
		return color;
	}
	
	/*
	* Sets the color of this instance of DrawableShape to the
	* specified color
	* 
	* @param	color	The new color of this DrawableShape
	*/
	public void setColor(Color color) {
		this.color = color;
	}

	/*
	* Checks whether this shape is to be drawn filled or not
	*
	* @return	#filled
	*/
	public boolean isFilled() {
		return filled;
	}

	/*
	* Sets whether this shape is to be drawn filled or not
	*
	* @param	filled	The value is true if this shape is to be
	*					drawn filled, and false if it isn't
	*/
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
}
