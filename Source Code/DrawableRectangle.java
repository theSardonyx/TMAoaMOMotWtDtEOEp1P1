import java.awt.*;

/*
* This class represents a rectangular Drawable
* 
* @see	DrawableShape
*
* @author	Aemielvin Loremia
*/
public class DrawableRectangle extends DrawableShape {
	/*
	* Creates a new rectangular Drawable with the specified position,
	* dimensions, and color
	*
	* @param	position	Coordinates of the position of this
	* 						Drawable
	* @param	dimension	The dimensions of this Drawable
	* @param	color	The color this rectangle will be drawn in
	*/
	public DrawableRectangle(Vector position, Vector dimension, Color color) {
		super(position, dimension, color);
	}

	/*
	* Draws this DrawableRectangle as a filled rectangle
	* 
	* @param	g	The graphics to be drawn
	* 
	* @see	Drawable#topLeftPosition
	* @see	Drawable#dimension
	*/
	@Override
	protected void drawFilled(Graphics2D g) {
		g.fillRect(topLeftPosition.getX(), topLeftPosition.getY(), dimension.getX(), dimension.getY());
	}

	/*
	* Draws this DrawableRectangle as an outline
	*
	* @param	g	The graphics to be drawn
	*
	* @see	Drawable#topLeftPosition
	* @see	Drawable#dimension
	*/
	@Override
	protected void drawOutline(Graphics2D g) {
		g.drawRect(topLeftPosition.getX(), topLeftPosition.getY(), dimension.getX(), dimension.getY());
	}
}
