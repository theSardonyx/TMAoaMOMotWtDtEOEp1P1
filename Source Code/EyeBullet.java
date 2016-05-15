import java.awt.Color;

public class EyeBullet extends Entity {

	public EyeBullet(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(32, 32), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/enemyBulletSheet.png", 32, 32);
		this.visual = new DrawableImage(position, dimension, Sprite.integrateSprites(ss.get(0, 1), ss.get(1, 1), color));
		
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
