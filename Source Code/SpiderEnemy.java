import java.awt.Color;
import java.awt.image.BufferedImage;

public class SpiderEnemy extends Entity {

	public SpiderEnemy(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 4), ss.get(2, 4)}, 
												new BufferedImage[] {
													ss.get(1, 4), ss.get(3, 4)}, Color.GREEN);
		((Sprite) this.visual).setStateRate(1);
		
		//TODO damage & health pls
		this.health = 1;
		this.damage = 1;
		
		this.type = Entity.ENEMY_TYPE;
		this.canCollideAllyBullet = true;
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
