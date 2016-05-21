/**
* This Entity represents the avatar of the player
* 
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Player extends Entity {
	private PlayerSettings playerSettings;
	private EntityHeart heart;
	private boolean withHeart;

	/*
	Contructor for a Player object
	@param position: Vector object that will determine the player's starting position
	@param dimension: Vector object that determines the size of the player
	@param stage: BulletStage object where the player will spawn
	*/
	public Player(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(48, 48), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 0), ss.get(2, 0)
													}, new BufferedImage[] {
													ss.get(1, 0), ss.get(3, 0)
													}, color);
		this.playerSettings = PlayerSettings.getInstance();
		//TODO damage & health pls
		this.health = playerSettings.getHearts();
		this.damage = 1;
		
		this.type = Entity.ALLY_TYPE;
		this.canCollideEnemyBullet = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension)
				.setCollideRectangle(true);
		
		this.heart = new EntityHeart(this, this.stage);
		this.spawnEntity(heart);
		this.withHeart = false;
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
			
			if(pmb.getFocus() && !this.withHeart) {
				this.heart.appear();
				this.withHeart = true;
			}
			else if(!pmb.getFocus() && this.withHeart) {
				this.heart.dissapear();
				this.withHeart = false;
			}
		}
	}
	/*
	Hook method for updating the corresponding player sprite
	@param delta: time elapsed
	*/
	@Override
	public void updateHook(double delta) {
		((Sprite) visual).update(delta);
		this.heart.setPosition(this.position);
	}
	
	@Override
	public void collideEnemyBullet(Entity e) {
		playerSettings.graze();
	}
	
	@Override
	public void setHealth(int health) {
		playerSettings.setHearts(health);
		this.health = health;
	}
	
	@Override
	public int getHealth() {
		return playerSettings.getHearts();
	}
	
	@Override
	public void getDamaged(int damage) {
		playerSettings.heartDecrement(damage);
		super.getDamaged(damage);
		if(this.health > 0)
			this.stage.removeType(Entity.ENEMY_BULLET_TYPE);
	}
}
