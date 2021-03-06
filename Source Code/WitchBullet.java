import java.awt.Color;

public class WitchBullet extends Entity {

	public WitchBullet(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(24 ,24), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/enemyBulletSheet.png", 32, 32);
		this.visual = new DrawableImage(position, dimension, Sprite.integrateSprites(ss.get(0, 4), ss.get(1, 4), color));
		
		//TODO damge pls
		this.damage = 1;
		
		this.type = Entity.ENEMY_BULLET_TYPE;
		
		this.collideShape = new CollideShape(this.position, this.dimension.scalarMult(0.5))
				.setCollideEllipse(true);
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}
}
