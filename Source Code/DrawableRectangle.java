import java.awt.*;

public class DrawableRectangle extends DrawableShape {

	public DrawableRectangle(Vector position, Vector dimension, Color color) {
		super(position, dimension, color);
	}

	@Override
	protected void drawFilled(Graphics2D g) {
		g.fillRect(topLeftPosition.getX(), topLeftPosition.getY(), dimension.getX(), dimension.getY());
	}

	@Override
	protected void drawOutline(Graphics2D g) {
		g.drawRect(topLeftPosition.getX(), topLeftPosition.getY(), dimension.getX(), dimension.getY());
	}
}
