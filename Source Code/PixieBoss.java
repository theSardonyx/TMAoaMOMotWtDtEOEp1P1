import java.awt.Color;
import java.awt.image.BufferedImage;

public class PixieBoss extends BossEntity {

	public PixieBoss(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(64, 64), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 2), ss.get(2, 2)}, 
												new BufferedImage[] {
													ss.get(1, 2), ss.get(3, 2)}, color);
		((Sprite) this.visual).setStateRate(1);
		
		//TODO damage and health pls
		this.health = 1;

		this.canCollideAllyBullet = true;
		
		this.collideShape = new CollideShape(this.position, new Vector(0, 3), this.dimension.scalarMult(0.5))
				.setCollideRectangle(true);
	}
	
	public PixieBoss(Vector position, BulletStage stage, Color color, double expireTime) {
		super(position, new Vector(64, 64), stage, expireTime);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 2), ss.get(2, 2)}, 
												new BufferedImage[] {
													ss.get(1, 2), ss.get(3, 2)}, color);
		((Sprite) this.visual).setStateRate(1);
		
		//TODO damage and health pls
		this.health = 1;

		this.canCollideAllyBullet = true;
		
		this.collideShape = new CollideShape(this.position, new Vector(0, 3), this.dimension.scalarMult(0.5))
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
