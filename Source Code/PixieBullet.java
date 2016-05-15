import java.awt.Color;

public class PixieBullet extends Entity {

	public PixieBullet(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(32, 32), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/enemyBulletSheet.PNG", 32, 32);
		this.visual = new DrawableImage(position, dimension, Sprite.integrateSprites(ss.get(0, 2), ss.get(1, 2), color));
		
		//TODO damage pls
		this.damage = 1; 
		
		this.type = Entity.ENEMY_BULLET_TYPE;
		this.canCollideAlly = true;
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collideAlly(Entity e) {
		this.despawn();
	}
}
