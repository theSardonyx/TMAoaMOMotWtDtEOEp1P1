import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class DrawableArc extends DrawableShape {
	
	double startAngle, arcAngle;

	public DrawableArc(Vector position, Vector dimension, Color color) {
		super(position, dimension, color);
		this.startAngle = 0;
		this.arcAngle = Math.PI * 2;
	}

	@Override
	protected void drawFilled(Graphics2D g) {
		g.setStroke(new BasicStroke(15));
		g.fillArc(position.getX() - (dimension.getX() / 2), position.getY() - (dimension.getY() / 2), dimension.getX(), dimension.getY(), (int) this.startAngle, (int) this.arcAngle);
	}

	@Override
	protected void drawOutline(Graphics2D g) {
		g.setStroke(new BasicStroke(15));
		g.drawArc(position.getX() - (dimension.getX() / 2), position.getY() - (dimension.getY() / 2), dimension.getX(), dimension.getY(), (int) this.startAngle, (int) this.arcAngle);
	}
	
	public void setStartAngle(double radians) {
		this.startAngle = Math.toDegrees(radians);
	}
	
	public void setArcAngle(double radians) {
		this.arcAngle = Math.toDegrees(radians);
	}

}
