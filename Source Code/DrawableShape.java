import java.awt.Color;
import java.awt.Graphics2D;

public abstract class DrawableShape extends Drawable {

	protected Color color;
	protected boolean filled;
	
	public DrawableShape(Vector position, Vector dimension) {
		super(position, dimension);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public final void draw(Graphics2D g) {
		g.setColor(color);
		if(filled)
			drawFilled(g);
		else
			drawOutline(g);
	}
	
	protected abstract void drawFilled(Graphics2D g);
	protected abstract void drawOutline(Graphics2D g);
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}
}
