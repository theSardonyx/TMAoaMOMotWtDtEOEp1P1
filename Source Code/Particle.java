/**
* This Entity represent the special effects/other particles or ambience for the game
* 
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
import java.awt.*;

public class Particle extends Entity {

	private double duration;
	private double timeLeft;
	private Vector growRate;
	private int alpha;
	private Color color;
	
	/*
	Contructor for a Particle object
	Creates a particle based from position, diameter and color, and is added to the BulletStage object
	@param position: starting position of object
	@param diameter: diameter of a particle
	@param color: color of the particle
	@oaram stage: stage where the particle will be rendered and appear
	*/
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
	
	/*
	Method used to increase/decrease the lifetime of a particle
	@param newDuration: the new duration of the particle
	*/
	public void adjustDuration(double newDuration) {
		double changeRate = newDuration/duration;
		timeLeft *= changeRate;
		duration = newDuration;
	}
	/*
	Method used to increase/decrease the grow rate of a particle
	@param growRate: the new grow rate of a particle
	*/
	public void setGrowRate(Vector growRate) {
		this.growRate = growRate;
	}
	
	public void setFilled(boolean filled) {
		((DrawableShape) visual).setFilled(filled);
	}
	/*
	Method used to detect collision
	@param e: Entity object used for collision checking
	*/
	@Override
	public boolean isCollidingWith(Entity e) {
		return false;
	}
	
	/*
	Method to set a new velocity
	@param velocity: Vector object for setting the velocity
	*/
	public void setVelocity(Vector velocity) {
		((AccelerateMoveBehavior)move.peek()).setVelocity(velocity);
	}
	/*
	Method to set a new acceleration
	@param velocity: Vector object for setting the acceleration
	*/
	public void setAcceleration(Vector acceleration) {
		((AccelerateMoveBehavior)move.peek()).setAcceleration(acceleration);
	}

	/*
	Hook method for updating the particle based on time elapsed and its lifetime
	@param delta: used to update objects based from time passed
	*/
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
