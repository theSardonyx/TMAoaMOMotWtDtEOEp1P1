import java.awt.Color;

public class PixieBullet extends Entity {

	public PixieBullet(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(24, 24), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/enemyBulletSheet.PNG", 32, 32);
		this.visual = new DrawableImage(position, dimension, Sprite.integrateSprites(ss.get(0, 2), ss.get(1, 2), color));
		
		//TODO damage pls
		this.damage = 1; 
		
		this.type = Entity.ENEMY_BULLET_TYPE;
		
		this.collideShape = new CollideShape(this.position, this.dimension.scalarMult(.5))
				.setCollideEllipse(true);
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}
}
