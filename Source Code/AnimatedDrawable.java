/**
* This abstract class serves as a template for all images that are
* to be animated
*
* @see	Drawable
* 
* @author	Aemielvin Loremia
*/
public abstract class AnimatedDrawable extends Drawable {
	/**
	* Creates a new Drawable with the specified position and
	* dimensions
	*
	* @param	position	Coordinates where the Drawable will be
	*						drawn
	* @param	dimension	The dimensions of the Drawable to be drawn
	*/
	public AnimatedDrawable(Vector position, Vector dimension) {
		super(position, dimension);
	}
	
	/**
	* An abstract method for updating the AnimatedDrawable
	*
	* @param	delta	Time passed in seconds
	*/
	public abstract void update(double delta);
}
