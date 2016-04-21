import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Player extends Entity {
	HashMap<Integer, Vector> moveVectors;

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
	
	public void handleInput(InputCollector input) {
		PlayerMoveBehavior pmb = (PlayerMoveBehavior) move.peek();
		pmb.setUp(input.isKeyPressed(KeyEvent.VK_UP));
		pmb.setDown(input.isKeyPressed(KeyEvent.VK_DOWN));
		pmb.setLeft(input.isKeyPressed(KeyEvent.VK_LEFT));
		pmb.setRight(input.isKeyPressed(KeyEvent.VK_RIGHT));
		pmb.setFocus(input.isKeyPressed(KeyEvent.VK_SHIFT));
		
		if(input.isKeyPressed(KeyEvent.VK_Z)) {
			Particle p = new Particle(position, 20, Color.YELLOW, stage);
			p.setAcceleration(new Vector(0, 20));
			p.setVelocity(new Vector(0, -200));
			p.setGrowRate(new Vector(0, 0));
			p.adjustDuration(2);
			this.spawnEntity(p);
		}
	}

	@Override
	public void updateHook(double delta) {
		((Sprite) visual).update(delta);
	}
}
