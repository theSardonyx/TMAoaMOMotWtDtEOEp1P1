import java.awt.*;

public class Particle extends Entity {

	private double duration;
	private double timeLeft;
	private Vector growRate;
	private int alpha;
	private Color color;
	

	public Particle(Vector position, double diameter, Color color, BulletStage stage) {
		super(position, new Vector(diameter, diameter), stage);
		visual = new DrawableOval(position, this.dimension, color);
		((DrawableShape) visual).setFilled(true);
		
		hitbox = new CollideCircle(position, diameter);
		move.offer(new AccelerateMoveBehavior(this, new Vector(100, 0), new Vector(0, 0)));
		
		this.color = color;
		duration = timeLeft = 1;
		growRate = new Vector(0, 0);
	}
	

	public void adjustDuration(double newDuration) {
		double changeRate = newDuration/duration;
		timeLeft *= changeRate;
		duration = newDuration;
	}
	
	public void setGrowRate(Vector growRate) {
		this.growRate = growRate;
	}
	
	public void setFilled(boolean filled) {
		((DrawableShape) visual).setFilled(filled);
	}
	
	@Override
	public boolean isCollidingWith(Entity e) {
		return false;
	}
	
	
	public void setVelocity(Vector velocity) {
		((AccelerateMoveBehavior)move.peek()).setVelocity(velocity);
	}
	
	public void setAcceleration(Vector acceleration) {
		((AccelerateMoveBehavior)move.peek()).setAcceleration(acceleration);
	}

	@Override
	public void updateHook(double delta) {
		timeLeft -= delta;
		if(timeLeft<=0) {
			despawn();
			return;
		}
		
		// SET ALPHA
		alpha = (int) ( 255 * timeLeft / duration );
		color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
		((DrawableShape) visual).setColor(color);
		
		// SET GROWTH
		dimension = dimension.add( growRate.scalarMult( delta ) );
		
		if(alpha <= 0 || dimension.getX()<=0 || dimension.getY()<=0)
			despawn();
	}

}
