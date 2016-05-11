import java.awt.Color;

public class SpiderBullet extends Entity {

	public SpiderBullet(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/enemyBulletSheet.png", 32, 32);
		this.visual = new DrawableImage(position, dimension, Sprite.integrateSprites(ss.get(0, 3), ss.get(1, 3), Color.GREEN));
		
		//TODO damage
		this.damage = 1;
		
		this.type = Entity.ENEMY_BULLET_TYPE;
		this.canCollideEnemy = true;
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collideEnemy(Entity e) {
		this.despawn();
	}
}
