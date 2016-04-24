import java.awt.*;

/*
* This class represents an oval Drawable
*
* @see	DrawableShape
*
* @author	Aemielvin Loremia
*/
public class DrawableOval extends DrawableShape {
	/*
	* Creates a new oval Drawable with the specified position,
	* dimensions, and color
	*
	* @param	position	Coordinates of the position of this
	*						Drawable
	* @param	dimension	The dimensions of this Drawable
	* @param	color	The color this oval will be drawn in
	*/
	public DrawableOval(Vector position, Vector dimension, Color color) {
		super(position, dimension, color);
	}

	/*
	* Draws this DrawableOval as a filled oval
	*
	* @param	g	The graphics to be drawn
	*
	* @see	Drawable#topLeftPosition
	* @see	Drawable#dimension
	*/
	@Override
	protected void drawFilled(Graphics2D g) {
		g.fillOval(topLeftPosition.getX(), topLeftPosition.getY(), dimension.getX(), dimension.getY());
	}

	/*
	* Draws this DrawableOval as an outline
	*
	* @param	g	The graphics to be drawn
	*
	* @see	Drawable#topLeftPosition
	* @see	Drawable#dimension
	*/
	@Override
	protected void drawOutline(Graphics2D g) {
		g.drawOval(topLeftPosition.getX(), topLeftPosition.getY(), dimension.getX(), dimension.getY());
	}
}
