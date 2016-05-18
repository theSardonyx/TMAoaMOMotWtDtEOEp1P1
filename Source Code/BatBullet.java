import java.awt.Color;

public class BatBullet extends Entity{

	public BatBullet(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(24, 24), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/enemyBulletSheet.PNG", 32, 32);
		this.visual = new DrawableImage(position, dimension, Sprite.integrateSprites(ss.get(0, 0), ss.get(1, 0), color));
		
		//TODO damage pls
		this.damage = 1; 
		
		this.type = Entity.ENEMY_BULLET_TYPE;
		
		this.collideShape = new CollideShape(this.position, this.dimension)
				.setCollideEllipse(true);
	}

	@Override
	public void updateHook(double delta) {
		
	}
}
