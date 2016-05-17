import java.awt.Color;
import java.awt.image.BufferedImage;

public class WitchEnemy extends Entity {

	public WitchEnemy(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 5), ss.get(2, 5)}, 
												new BufferedImage[] {
													ss.get(1, 5), ss.get(3, 5)}, Color.GREEN);
		((Sprite) this.visual).setStateRate(1);
		
		//TODO damage adn helath pls
		this.health = 1;
		this.damage = 1;
		
		this.type = Entity.ENEMY_TYPE;
		this.canCollideAllyBullet = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension.scalarMult(0.75))
				.setCollideRectangle(true);
	}

	@Override
	public void updateHook(double delta) {
		((Sprite) this.visual).update(delta);
	}

	@Override
	public void collideAllyBullet(Entity e) {
		this.getDamaged(e.damage);
	}
}
