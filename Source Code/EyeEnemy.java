import java.awt.Color;
import java.awt.image.BufferedImage;

public class EyeEnemy extends EnemyEntity {

	public EyeEnemy(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(48, 48), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 3), ss.get(2, 3)}, 
												new BufferedImage[] {
													ss.get(1, 3), ss.get(3, 3)}, color);
		((Sprite) visual).setStateRate(1);
		
		//TODO health & damage
		this.health = 1;
		
		this.canCollideAllyBullet = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension)
				.setCollideEllipse(true);
	}
	
	public EyeEnemy(Vector position, BulletStage stage, Color color, double expireTime) {
		super(position, new Vector(48, 48), stage, expireTime);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 3), ss.get(2, 3)}, 
												new BufferedImage[] {
													ss.get(1, 3), ss.get(3, 3)}, color);
		((Sprite) visual).setStateRate(1);
		
		//TODO health & damage
		this.health = 1;
		
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
