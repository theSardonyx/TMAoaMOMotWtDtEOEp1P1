import java.awt.*;

/*
* This class represents a String Drawable
* 
* @see Drawable
*
* @author	Ivan Martinez
*/
public class DrawableString extends Drawable {
	/*
	* The String to be drawn as a Drawable
	*/
	private String text;
	
	/*
	* The Font #text is to be drawn in
	*/
	private Font font;
	
	/*
	* The Color #text is to be drawn in
	*/
	private Color color;

	/*
	* Creates a new DrawableString from a String drawn with the
	* specified Font with the specified position and color
	* 
	* @param	position	Coordinates of the position of this
	*						Drawable
	* @param	text	The String to be drawn as a Drawable
	* @param	font	The Font to be used when drawing this
	*					DrawableString
	* @param	color	The color the String will be drawn in
	*
	* @see	FontLoader#getInstance
	* @see	FontLoader#getWidth
	* @see	FontLoader#getHeight
	*/
	public DrawableString(Vector position, String text, Font font, Color color) {
		super(position, new Vector(FontLoader.getInstance().getWidth(font, text), FontLoader.getInstance().getHeight(font,  text)));
		this.text = text;
		this.font = font;
		this.color = color;
	}

	/*
	* Draws this instance of DrawableString
	*
	* @param	g	The graphics to be drawn
	*
	* @see	Drawable#topLeftPosition
	* @see	Drawable#dimension
	*/
	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, topLeftPosition.getX(), topLeftPosition.getY() + dimension.getY());
	}
	
	/*
	* Gets the String to be drawn
	*
	* @return	#text
	*/
	public String getText() {
		return this.text;
	}
	
	/*
	* Sets the String to be drawn
	*
	* @param	s	The new String to be drawn
	*/
	public void setText(String s) {
		this.text = s;
	}
	
	/*
	* Gets the Font used to draw this DrawableString
	*
	* @return #font
	*/
	public Font getFont() {
		return this.font;
	}
	
	/*
	* Sets the Font to be used to draw this DrawableString
	*
	* @param	font	The new Font to be used
	*/
	public void setFont(Font font) {
		this.font = font;
	}
	
	/*
	* Gets the color of this instance of DrawableString
	*
	* @return	#color
	*/
	public Color getColor() {
		return this.color;
	}
	
	/*
	* Sets the color of this instance of DrawableString to the
	* specified color
	*
	* @param	color	The new color of this DrawableShape
	*/
	public void setColor(Color color) {
		this.color = color;
	}
}
