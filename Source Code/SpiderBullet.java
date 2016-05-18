import java.awt.Color;

public class SpiderBullet extends Entity {

	public SpiderBullet(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(24, 24), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/enemyBulletSheet.png", 32, 32);
		this.visual = new DrawableImage(position, dimension, Sprite.integrateSprites(ss.get(0, 3), ss.get(1, 3), color));
		
		//TODO damage
		this.damage = 1;
		
		this.type = Entity.ENEMY_BULLET_TYPE;
		
		this.collideShape = new CollideShape(this.position, this.dimension)
				.setCollideRectangle(true);
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}
}
