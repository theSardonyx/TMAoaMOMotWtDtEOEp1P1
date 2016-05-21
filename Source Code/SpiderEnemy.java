import java.awt.Color;
import java.awt.image.BufferedImage;

public class SpiderEnemy extends EnemyEntity {

	public SpiderEnemy(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(48, 48), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 4), ss.get(2, 4)}, 
												new BufferedImage[] {
													ss.get(1, 4), ss.get(3, 4)}, color);
		((Sprite) this.visual).setStateRate(1);
		
		//TODO damage & health pls
		this.health = 1;

		this.canCollideAllyBullet = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension.scalarMult(0.75))
				.setCollideEllipse(true);
	}
	
	public SpiderEnemy(Vector position, BulletStage stage, Color color, double expireTime) {
		super(position, new Vector(48, 48), stage, expireTime);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 4), ss.get(2, 4)}, 
												new BufferedImage[] {
													ss.get(1, 4), ss.get(3, 4)}, color);
		((Sprite) this.visual).setStateRate(1);
		
		//TODO damage & health pls
		this.health = 1;

		this.canCollideAllyBullet = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension.scalarMult(0.75))
				.setCollideEllipse(true);
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
