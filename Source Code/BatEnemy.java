import java.awt.Color;
import java.awt.image.BufferedImage;

public class BatEnemy extends Entity {

	public BatEnemy(Vector position, BulletStage stage, Color color) {
		super(position, new Vector(48, 48), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/64x64-sheet.png", 64, 64);
		this.visual = new Sprite(position, dimension, new BufferedImage[] {
													ss.get(0, 1), ss.get(2, 1)}, 
												new BufferedImage[] {
													ss.get(1, 1), ss.get(3, 1)}, color);
		((Sprite) visual).setStateRate(1);
		
		//TODO health & damage pls
		this.health = 1;
		this.damage = 1;
		
		this.type = Entity.ENEMY_TYPE;
		this.canCollideAllyBullet = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension.mult(1, 0.25))
				.setCollideRectangle(true);
	}

	@Override
	public void updateHook(double delta) {
		((Sprite) visual).update(delta);
	}
	
	public void collideAllyBullet(Entity e) {
		this.getDamaged(e.damage);
	}
}
