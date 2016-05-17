import java.awt.Color;
import java.awt.image.BufferedImage;

public class EyeEnemy extends Entity {

	public EyeEnemy(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 3), ss.get(2, 3)}, 
												new BufferedImage[] {
													ss.get(1, 3), ss.get(3, 3)}, Color.GREEN);
		((Sprite) visual).setStateRate(1);
		
		//TODO health & damage
		this.health = 1;
		this.damage = 1;
		
		this.type = Entity.ENEMY_TYPE;
		this.canCollideAllyBullet = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension)
				.setCollideEllipse(true);
	}

	@Override
	public void updateHook(double delta) {
		((Sprite) visual).update(delta);
	}

	@Override
	public void collideAllyBullet(Entity e) {
		this.getDamaged(e.damage);
	}
}
