import java.awt.Graphics2D;

public class DrawableRectangle extends DrawableShape {

	public DrawableRectangle(Vector position, Vector dimension) {
		super(position, dimension);
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
