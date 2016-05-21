import java.awt.Color;
import java.awt.image.BufferedImage;

public class OverlordBoss extends BossEntity {

	public OverlordBoss(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(64, 64), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
				ss.get(0, 0), ss.get(2, 0)}, 
			new BufferedImage[] {
				ss.get(1, 0), ss.get(3, 0)}, color);
		((Sprite) this.visual).setStateRate(1);
		
		//TODO damage & health pls
		this.health = 1;

		this.canCollideAllyBullet = true;
	}
	
	public OverlordBoss(Vector position, BulletStage stage, Color color, double expireTime) {
		super(position, new Vector(64, 64), stage, expireTime);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
				ss.get(0, 0), ss.get(2, 0)}, 
			new BufferedImage[] {
				ss.get(1, 0), ss.get(3, 0)}, color);
		((Sprite) this.visual).setStateRate(1);
		
		//TODO damage & health pls
		this.health = 1;

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
