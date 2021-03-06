import java.awt.image.BufferedImage;

public class SummonRune extends Entity {

	public SummonRune(Vector position, BulletStage stage) {
		super(position, new Vector(64, 64), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeSheet.png", 64, 64);
		this.visual = new Sprite(this.position, this.dimension, new BufferedImage[] {
																ss.get(0, 11), ss.get(1, 11)}, 
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
		PlayerSettings.getInstance().addRune(PlayerSettings.SUMMON_RUNE);
		this.despawn();
	}
}
