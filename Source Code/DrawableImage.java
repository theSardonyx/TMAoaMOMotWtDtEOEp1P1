import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class DrawableImage extends Drawable {
	
	public BufferedImage image;
	
	public DrawableImage(Vector position, Vector dimension, BufferedImage image) {
		super(position, dimension);
		this.image = image;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, topLeftPosition.getX(), topLeftPosition.getY(), dimension.getX(), dimension.getY(), null);
	}

}
