import java.awt.*;
import java.awt.geom.*;

public class DrawableButton extends Drawable {
	private DrawableRectangle dr;
	private DrawableString ds;
	private Vector padding;
	private Color rectUnhovered, rectHovered, fontUnhovered, fontHovered;
	private CollideRectangle cr;
	
	public DrawableButton(Vector position, String text, Font font, Vector padding, Color color){
		super(position, new Vector(FontLoader.getInstance().getWidth(font, text) + (padding.x * 2), FontLoader.getInstance().getHeight(font, text) + (padding.y * 2)));
		
		this.padding = padding;
		
		this.rectUnhovered = new Color(0, 0, 0, 0);
		this.rectHovered = color;
		this.fontUnhovered = color;
		this.fontHovered = new Color(0, 0, 0);
		
		this.cr = new CollideRectangle(position, dimension);
		this.dr = new DrawableRectangle(position, dimension, rectUnhovered);
		this.ds = new DrawableString(position, text, font, fontUnhovered);
		this.dr.setFilled(false);
	}

	@Override
	public void draw(Graphics2D g) {
		this.dr.draw(g);
		this.ds.draw(g);
	}
	
	public boolean isCollidingWith(Vector p) {
		return cr.isCollidingWith(new CollideCircle(p, 0));
	}
	
	public void setUnhovered() {
		this.dr.setFilled(false);
		this.dr.setColor(rectUnhovered);
		this.ds.setColor(fontUnhovered);
	}
	
	public void setHovered() {
		this.dr.setFilled(true);
		this.dr.setColor(rectHovered);
		this.ds.setColor(fontHovered);
	}
	
	public void update(Vector input) {
		if(isCollidingWith(input)) {
			setHovered();
		} else {
			setUnhovered();
		}
	}
	
	public Color getUnhoveredRectColor() {
		return this.rectUnhovered;
	}
	
	public void setUnhoveredRectColor(Color color) {
		this.rectUnhovered = color;
	}
	
	public Color getHoveredRectColor() {
		return this.rectHovered;
	}
	
	public void setHoveredRectColor(Color color) {
		this.rectHovered = color;
	}
	
	public Color getUnhoveredFontColor() {
		return this.fontUnhovered;
	}
	
	public void setUnhoveredFontColor(Color color) {
		this.fontUnhovered = color;
	}
	
	public Color getHoveredFontColor() {
		return this.fontUnhovered;
	}
	
	public void setHoveredFontColor(Color color) {
		this.fontUnhovered = color;
	}
	
	public Font getFont() {
		return this.ds.getFont();
	}
	
	public void setFont(Font font) {
		this.ds.setFont(font);
	}
	
	public String getText() {
		return this.ds.getText();
	}
	
	public void setText(String s) {
		this.ds.setText(s);
	}
}
