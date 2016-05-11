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
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 0), ss.get(2, 0)
													}, new BufferedImage[] {
													ss.get(1, 0), ss.get(3, 0)
													}, Color.GREEN);
		
		//TODO damage & health pls
		this.health = 1;
		this.damage = 1;
		
		this.type = Entity.ALLY_TYPE;
		this.canCollideEnemy = true;
		this.canCollideEnemyBullet = true;
	}
	
	/*
	Used to handle different input events and respond accordingly, based from an InputCollector object
	@param input: InputCollector object where the different inputs will be gotten from
	*/
	public void handleInput(InputCollector input) {
		if(move.getMoveBehavior() instanceof PlayerMoveBehavior) {
			PlayerMoveBehavior pmb = (PlayerMoveBehavior) move.getMoveBehavior();
			Config curr = Config.getInstance();
			pmb.setUp(input.isKeyPressed(curr.getUpKey()));
			pmb.setDown(input.isKeyPressed(curr.getDownKey()));
			pmb.setLeft(input.isKeyPressed(curr.getLeftKey()));
			pmb.setRight(input.isKeyPressed(curr.getRightKey()));
			pmb.setFocus(input.isKeyPressed(curr.getFocusKey()));
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
	
	@Override
	public void collideEnemy(Entity e) {
		this.getDamaged(e.damage);
	}
	
	@Override
	public void collideEnemyBullet(Entity e) {
		this.getDamaged(e.damage);
	}
}
