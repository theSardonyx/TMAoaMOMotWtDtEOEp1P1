import java.awt.image.BufferedImage;

public class SnipeRune extends Entity {

	public SnipeRune(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeSheet.png", 64, 64);
		this.visual = new Sprite(this.position, this.dimension, new BufferedImage[] {
																ss.get(0, 6), ss.get(1, 6)}, 
																null, null);
		((Sprite) this.visual).setStateRate(1);
		
		this.type = Entity.DROP_TYPE;
		this.canCollideAlly = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension)
				.setCollideRectangle(true);
	}

	@Override
	public void updateHook(double delta) {
		((Sprite) this.visual).update(delta);
	}
	
	@Override
	public void collideAlly(Entity e) {
		PlayerSettings.getInstance().addRune(PlayerSettings.SNIPE_RUNE);
		this.despawn();
	}
}
