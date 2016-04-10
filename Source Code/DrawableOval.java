import java.awt.*;

public class DrawableOval extends DrawableShape {

	public DrawableOval(Vector position, Vector dimension, Color color) {
		super(position, dimension, color);
	}

	@Override
	protected void drawFilled(Graphics2D g) {
		g.fillOval(topLeftPosition.getX(), topLeftPosition.getY(), dimension.getX(), dimension.getY());
	}

	@Override
	protected void drawOutline(Graphics2D g) {
		g.drawOval(topLeftPosition.getX(), topLeftPosition.getY(), dimension.getX(), dimension.getY());
	}

}
