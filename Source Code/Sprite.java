/**
* The class used for the sprites
* 
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
import java.awt.*;
import java.awt.image.*;

public class Sprite extends AnimatedDrawable {
	private int state, stateCount;
	private double stateRate, secPerState, ticCounter, ticCap;
	private BufferedImage[] animation;
	
	/*
	Constructor for a Sprite object
	Creates a new Sprite object, based from a spritesheet
	@param position: Base position of the sprite 
	@param dimension: Size of the sprite
	@param base: BufferedImage object for the body of the sprite
	@param part: BufferedImage object for the additional parts of the sprite
	@param c: Color of the sprite
	*/
	public Sprite(Vector position, Vector dimension, BufferedImage[] base, BufferedImage[] part, Color c) {
		super(position, dimension);
		this.stateCount = base.length;
		setStateRate(1);
		
		this.state = 0;
		this.animation = new BufferedImage[ stateCount ];
		for(int i = 0; i < stateCount; i++) {
 			this.animation[i] = Sprite.integrateSprites(base[i], part[i], c);
		}
	}
	/*
	Method for updating the sprite position/state
	@param delta: used to update objects based from time passed
	*/
	@Override
	public void update(double delta) {
		ticCounter += delta;
		if(ticCounter >= ticCap )
			ticCounter -= ticCap;
		state = (int) (ticCounter / secPerState);
	}
	
	/*
	Method for drawing the sprite
	@param g: Graphics2D object for the base of the sprite
	*/
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(animation[state], topLeftPosition.getX(), topLeftPosition.getY(), 
				dimension.getX(), dimension.getY(), null);
	}
	/*
	Integrates the two parts of the sprite: main and additional/animated
	@param base: BufferedImage object for the body of the sprite
	@param part: BufferedImage object for the additional parts of the sprite
	@param tint: color/tint of the sprite
	*/
	public static BufferedImage integrateSprites(BufferedImage base, BufferedImage part, Color tint) {
		if(	base == null ||
			base.getHeight() != part.getHeight() || 
			base.getWidth() != part.getWidth()) return null;
		if(part == null)
			return base;
		
		BufferedImage finished = new BufferedImage(base.getWidth(), base.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		
		for(int x = 0; x < base.getWidth(); x++) {
			for(int y = 0; y < base.getHeight(); y++) {
				
				int pixel = part.getRGB(x, y);
				if((pixel>>24 == 0))
				{
					finished.setRGB(x, y, base.getRGB(x, y));
					continue;
				}
				
				int value = ( pixel & 0xFF );
				int r = ((value + 1) * tint.getRed()) >> 8;
				int g = ((value + 1) * tint.getGreen()) >> 8;
				int b = ((value + 1) * tint.getBlue()) >> 8;
				int argb = (0xFF << 24) + (r << 16) + (g << 8) + (b << 0);
				
				finished.setRGB(x, y, argb);
				
			}
		}
		
		return finished;
	}
	
	/*
	Method for getting the rate of changing states
	*/
	public double getStateRate() {
		return stateRate;
	}
	/*
	Method for setting the rate of changing states
	@param stateRate: the new rate of state change
	*/
	public void setStateRate(double stateRate) {
		this.stateRate = stateRate;
		this.secPerState = 1.0/this.stateRate;
		this.ticCounter = 0;
		this.ticCap = stateCount * secPerState;
	}

}
