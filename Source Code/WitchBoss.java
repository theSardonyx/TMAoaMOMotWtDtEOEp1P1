import java.awt.Color;
import java.awt.image.BufferedImage;

public class WitchBoss extends BossEntity {

	public WitchBoss(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(64, 64), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 5), ss.get(2, 5)}, 
												new BufferedImage[] {
													ss.get(1, 5), ss.get(3, 5)}, color);
		((Sprite) this.visual).setStateRate(1);
		
		//TODO damage adn helath pls
		this.health = 1;

		this.canCollideAllyBullet = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension.scalarMult(0.75))
				.setCollideRectangle(true);
	}
	
	public WitchBoss(Vector position, BulletStage stage, Color color, double expireTime) {
		super(position, new Vector(64, 64), stage, expireTime);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 5), ss.get(2, 5)}, 
												new BufferedImage[] {
													ss.get(1, 5), ss.get(3, 5)}, color);
		((Sprite) this.visual).setStateRate(1);
		
		//TODO damage adn helath pls
		this.health = 1;

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
