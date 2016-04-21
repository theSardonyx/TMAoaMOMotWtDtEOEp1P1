import java.awt.*;

public class DrawableString extends Drawable {
	private String text;
	private Font font;
	private Color color;

	public DrawableString(Vector position, String text, Font font, Color color) {
		super(position, new Vector(FontLoader.getInstance().getWidth(font, text), FontLoader.getInstance().getHeight(font,  text)));
		this.text = text;
		this.font = font;
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, topLeftPosition.getX(), topLeftPosition.getY() + dimension.getY());
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String s) {
		this.text = s;
	}
	
	public Font getFont() {
		return this.font;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
