import java.awt.Color;
import java.awt.image.BufferedImage;

public class BatBoss extends BossEntity {

	public BatBoss(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(64, 64), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 1), ss.get(2, 1)}, 
												new BufferedImage[] {
													ss.get(1, 1), ss.get(3, 1)}, color);
		((Sprite) visual).setStateRate(1);
		
		//TODO health & damage pls
		this.health = 1;
		
		this.canCollideAllyBullet = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension.mult(1, 0.25))
				.setCollideRectangle(true);
	}
	
	public BatBoss(Vector position, BulletStage stage, Color color, double expireTime) {
		super(position, new Vector(64, 64), stage, expireTime);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 1), ss.get(2, 1)}, 
												new BufferedImage[] {
													ss.get(1, 1), ss.get(3, 1)}, color);
		((Sprite) visual).setStateRate(1);
		
		//TODO health & damage pls
		this.health = 1;
		
		this.canCollideAllyBullet = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension.mult(1, 0.25))
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
