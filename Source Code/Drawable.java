import java.awt.*;

public abstract class Drawable {
	protected Vector position;
	protected Vector dimension;
	protected Vector topLeftPosition;
	protected Vector origin;
	
	public Drawable(Vector position, Vector dimension) {
		this.dimension = dimension;
		this.origin = dimension.scalarMult(0.5);
		setPosition( position );
	}
	
	public abstract void draw(Graphics2D g);

	public void setPosition(Vector position) {
		this.position = position;
		this.topLeftPosition = position.subtract( origin );
	}
	
	public void setDimension(Vector dimension) {
		this.dimension = dimension;
		this.origin = dimension.scalarMult(0.5);
		setPosition( this.position );
	}
}
