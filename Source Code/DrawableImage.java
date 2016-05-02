import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
* This class represents an image Drawable
*
* @see	Drawable
*
* @author	Aemielvin Loremia
*/
public class DrawableImage extends Drawable {
	/**
	* The BufferedImage to be "converted" to Drawable
	*/
	public BufferedImage image;
	
	/**
	* Converts the specified image into a Drawable with the specified
	* position and dimensions
	*
	* @param	position	Coordinates of the position of this
	* 						Drawable
	* @param	dimension	The dimensions of this Drawable
	* @param	image	The BufferedImage to be "converted" to
	*					Drawable
	*/
	public DrawableImage(Vector position, Vector dimension, BufferedImage image) {
		super(position, dimension);
		this.image = image;
	}

	/**
	* Draws this instance of DrawableImage
	*
	* @param	g	The graphics to be drawn
	*
	* @see	Drawable#topLeftPosition
	*/
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, topLeftPosition.getX(), topLeftPosition.getY(), dimension.getX(), dimension.getY(), null);
	}
}
