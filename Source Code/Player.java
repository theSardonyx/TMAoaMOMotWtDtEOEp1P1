import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Player extends Entity {
	HashMap<Integer, Vector> moveVectors;

	/*
	Contructor for a PLayer object
	@param position: Vector object that will determine the player's starting position
	@param dimension: Vector object that determines the size of the player
	@param stage: BulletStage object where the player will spawn
	*/
	public Player(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		SpriteSheet ss = new SpriteSheet(ImageLoader.getInstance().getFile("res/img/64x64-sheet.png"), 64, 64);
		visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 0), ss.get(2, 0)
													}, new BufferedImage[] {
													ss.get(1, 0), ss.get(3, 0)
													}, Color.GREEN);
		((Sprite) visual).setStateRate(5);
	}
	
	/*
	Used to handle different input events and respond accordingly, based from an InputCollector object
	@param input: InputCollector object where the different inputs will be gotten from
	*/
	public void handleInput(InputCollector input) {
		if(move.peek() instanceof PlayerMoveBehavior) {
			PlayerMoveBehavior pmb = (PlayerMoveBehavior) move.peek();
			pmb.setUp(input.isKeyPressed(KeyEvent.VK_UP));
			pmb.setDown(input.isKeyPressed(KeyEvent.VK_DOWN));
			pmb.setLeft(input.isKeyPressed(KeyEvent.VK_LEFT));
			pmb.setRight(input.isKeyPressed(KeyEvent.VK_RIGHT));
			pmb.setFocus(input.isKeyPressed(KeyEvent.VK_SHIFT));
			
			if(input.isKeyPressed(KeyEvent.VK_Z)) {
				Particle p = new Particle(position, 20, new Color((int) (Math.random() * (256 * 256 * 256))), stage);
				p.setAcceleration(new Vector(0, 20));
				p.setVelocity(new Vector(0, -200));
				p.setGrowRate(new Vector(0, 0));
				p.adjustDuration(2);
				this.spawnEntity(p);
			}
		}
	}
	/*
	Hook method for updating the corresponding player sprite
	@param delta: value used to update the sprite
	*/
	@Override
	public void updateHook(double delta) {
		((Sprite) visual).update(delta);
	}
}
