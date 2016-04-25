/**
* This Entity represents the avatar of the player
* 
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Player extends Entity {
	HashMap<Integer, Vector> moveVectors;

	/*
	Contructor for a Player object
	@param position: Vector object that will determine the player's starting position
	@param dimension: Vector object that determines the size of the player
	@param stage: BulletStage object where the player will spawn
	*/
	public Player(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 0), ss.get(2, 0)
													}, new BufferedImage[] {
													ss.get(1, 0), ss.get(3, 0)
													}, Color.GREEN);
	}
	
	/*
	Used to handle different input events and respond accordingly, based from an InputCollector object
	@param input: InputCollector object where the different inputs will be gotten from
	*/
	public void handleInput(InputCollector input) {
		if(move instanceof PlayerMoveBehavior) {
			PlayerMoveBehavior pmb = (PlayerMoveBehavior) move;
			pmb.setUp(input.isKeyPressed(KeyEvent.VK_UP));
			pmb.setDown(input.isKeyPressed(KeyEvent.VK_DOWN));
			pmb.setLeft(input.isKeyPressed(KeyEvent.VK_LEFT));
			pmb.setRight(input.isKeyPressed(KeyEvent.VK_RIGHT));
			pmb.setFocus(input.isKeyPressed(KeyEvent.VK_SHIFT));
		}
	}
	/*
	Hook method for updating the corresponding player sprite
	@param delta: time elapsed
	*/
	@Override
	public void updateHook(double delta) {
		((Sprite) visual).update(delta);
	}
}
