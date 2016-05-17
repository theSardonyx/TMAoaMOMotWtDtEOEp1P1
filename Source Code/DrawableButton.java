import java.awt.*;
import java.awt.geom.*;

/**
* This class represents a clickable Drawable
*
* @see Drawable
*
* @author	Ivan Martinez
* @author	Kryzl Pascual
*/
public class DrawableButton extends Drawable {
	/**
	* The DrawableRectangle that serves as the button background
	*
	* @see	DrawableRectangle
	*/
	private DrawableRectangle dr;
	
	/**
	* The DrawableStringg that serves as the button text
	*
	* @see	DrawableString
	*/
	private DrawableString ds;
	
	/**
	* The distance between the bounds of the button text and the
	* bounds of the button background
	*/
	private Vector padding;
	
	/**
	* The colors of the button background when it's in a passive
	* state (#rectUnhovered) and when it's in a hovered state
	* (#rectHovered)
	*/
	private Color rectUnhovered, rectHovered;
	
	/**
	* The colors of the button text when it's in a passive state
	* (#fontUnhovered) and when it's in a hovered state
	* (#fontHovered)
	*/
	private Color fontUnhovered, fontHovered;
	
	/**
	* The CollideRectangle object that checks if the button is
	* colliding with the mouse pointer or not
	*
	* @see	CollideRectangle
	*/
	private CollideShape cr, pointer;
	
	/**
	* The font this button uses to display text
	*
	* @see	Font
	*/
	private Font font;
	
	/**
	* Creates a new DrawableButton instance with the specified
	* position, text, font, padding, and color
	*
	* @param	position	Coordinates of the position of the
	*						DrawableButton
	* @param	text	The displayed button text
	* @param	font	The Font the button text will be displayed in
	* @param	padding		The distance between the bounds of the
	*						button text and the bounds of the button
	*						background
	* @param	color	The color of the button background when it's
	*					in a passive state and of the button text
	*					when it's in a hovered state
	*
	* @see	CollideRectangle
	* @see	DrawableRectangle
	* @see	DrawableString
	* @see	FontLoader
	*/
	public DrawableButton(Vector position, String text, Font font, Vector padding, Color color){
		super(position, new Vector(FontLoader.getInstance().getWidth(font, text) + (padding.x * 2), FontLoader.getInstance().getHeight(font, text) + (padding.y * 2)));
		
		this.font = font;
		
		this.padding = padding;
		
		this.rectUnhovered = new Color(0, 0, 0, 0);
		this.rectHovered = color;
		this.fontUnhovered = color;
		this.fontHovered = new Color(0, 0, 0);
		
		this.cr = new CollideShape(this.position, this.dimension).setCollideRectangle(true);
		this.pointer = new CollideShape(this.position, new Vector(1, 1)).setCollideEllipse(true);
		this.dr = new DrawableRectangle(position, dimension, rectUnhovered);
		this.ds = new DrawableString(position, text, font, fontUnhovered);
		this.dr.setFilled(false);
	}

	/**
	* Draws the DrawableButton
	*
	* @param	g	The graphics to be drawn
	*
	* @see	DrawableRectangle#draw
	* @see	DrawableString#draw
	*/
	@Override
	public void draw(Graphics2D g) {
		this.dr.draw(g);
		this.ds.draw(g);
	}

	/**
	* Checks if the button is colliding with the mouse pointer
	*
	* @param	p	Coordinates of the mouse pointer
	* @return	true	If the mouse pointer is hovering on the
	*					button, or
	*			false	If it's not
	*
	* @see	CollideRectangle#isCollidingWith
	* @see	CollideCircle
	*/
	public boolean isCollidingWith(Vector p) {
		return cr.isCollidingWith(this.pointer.updatePosition(p));
	}
	
	/**
	* Sets the button state to passive
	*
	* @see	DrawableRectangle#setFilled
	* @see	DrawableRectangle#setColor
	* @see	DrawableString#setColor
	*/
	public void setUnhovered() {
		this.dr.setFilled(false);
		this.dr.setColor(rectUnhovered);
		this.ds.setColor(fontUnhovered);
	}
	
	/**
	* Sets the button state to hovered
	*
	* @see	DrawableRectangle#setFilled
	* @see	DrawableRectangle#setColor
	* @see	DrawableString#setColor
	*/
	public void setHovered() {
		this.dr.setFilled(true);
		this.dr.setColor(rectHovered);
		this.ds.setColor(fontHovered);
	}
	
	/**
	* Updates the button interface depending on the button state
	*
	* @param	input	Coordinates of the mouse pointer
	* 
	* @see	#isCollidingWith
	* @see	#setHovered
	* @see	#setUnhovered
	*/
	public void update(Vector input) {
		if(isCollidingWith(input)) {
			setHovered();
		} else {
			setUnhovered();
		}
	}
	
	/**
	* Gets the color of the button background when it's in a
	* passive state
	*
	* @return	#rectUnhovered
	*/
	public Color getUnhoveredRectColor() {
		return this.rectUnhovered;
	}
	
	/**
	* Sets the color of the button background when it's in a
	* passive state
	*
	* @param	color	The new color of the button background
	*					in the passive state
	*/
	public void setUnhoveredRectColor(Color color) {
		this.rectUnhovered = color;
	}
	
	/**
	* Gets the color of the button background when it's in a
	* hovered state
	*
	* @return	#rectHovered
	*/
	public Color getHoveredRectColor() {
		return this.rectHovered;
	}
	
	/**
	* Sets the color of the button background when it's in a
	* hovered state
	*
	* @param	color	The new color of the button background
	*					in the hovered state
	*/
	public void setHoveredRectColor(Color color) {
		this.rectHovered = color;
	}
	
	/**
	* Gets the color of the button text when it's in a passive
	* state
	*
	* @return	#fontUnhovered
	*/
	public Color getUnhoveredFontColor() {
		return this.fontUnhovered;
	}
	
	/**
	* Sets the color of the button text when it's in a passive
	* state
	*
	* @param	color	The new color of the button text in the
	*					passive state
	*/
	public void setUnhoveredFontColor(Color color) {
		this.fontUnhovered = color;
	}
	
	/**
	* Gets the color of the button text when it's in a hovered
	* state
	*
	* @return	#fontHovered
	*/
	public Color getHoveredFontColor() {
		return this.fontHovered;
	}
	
	/**
	* Sets the color of the button text when it's in a hovered
	* state
	*
	* @param	color	The new color of the button text in the
	*					hovered state
	*/
	public void setHoveredFontColor(Color color) {
		this.fontHovered = color;
	}

	/**
	* Gets the font used to draw the DrawableString
	*
	* @return	Font	The Font used by DrawableString
	*
	* @see	DrawableString#getFont
	*/
	public Font getFont() {
		return this.ds.getFont();
	}

	/**
	* Sets the font to be used to draw the DrawableString
	*
	* @param	font	The new font to be used by DrawableString
	*
	* @see	DrawableString#setFont
	*/
	public void setFont(Font font) {
		this.ds.setFont(font);
	}
	
	/**
	* Gets the button text
	*
	* @return	String	The text to be displayed on the button
	*
	* @see	DrawableString#getText
	*/
	public String getText() {
		return this.ds.getText();
	}
	
	/**
	* Sets the button text
	*
	* @param	s	The new text to be displayed on the button
	*
	* @see	DrawableString#setText
	*/
	public void setText(String s) {
		this.setDimension (new Vector (FontLoader.getInstance().getWidth (font, s) + (padding.x * 2), FontLoader.getInstance().getHeight (font, s) + (padding.y * 2)));
		this.cr = new CollideShape(this.position, dimension)
				.setCollideRectangle(true);
		this.dr = new DrawableRectangle(position, dimension, rectUnhovered);
		this.ds = new DrawableString(position, s, font, fontUnhovered);
	}
}
