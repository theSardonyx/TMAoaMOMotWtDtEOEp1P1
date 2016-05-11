import java.awt.Color;
import java.awt.image.BufferedImage;

public class PixieEnemy extends Entity {

	public PixieEnemy(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 2), ss.get(2, 2)}, 
												new BufferedImage[] {
													ss.get(1, 2), ss.get(3, 2)}, Color.GREEN);
		((Sprite) this.visual).setStateRate(1);
		
		//TODO damage and health pls
		this.health = 1;
		this.damage = 1;
		
		this.type = Entity.ENEMY_BULLET_TYPE;
		this.canCollideAlly = true;
	}

	@Override
	public void updateHook(double delta) {
		((Sprite) this.visual).update(delta);
	}

	@Override
	public void collideAlly(Entity e) {
		this.despawn();
	}
}
