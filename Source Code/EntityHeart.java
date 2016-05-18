import java.awt.Color;

public class EntityHeart extends Entity {
	private Entity host;
	private DrawableOval heart;

	public EntityHeart(Entity host, Vector position, BulletStage stage) {
		super(position, new Vector(15, 15), stage);
		
		this.host = host;
		
		this.heart = new DrawableOval(this.position, this.dimension, Color.WHITE);
		this.heart.setFilled(true);
		this.visual =  null;
		
		this.damage = 0;
		
		this.type = Entity.DROP_TYPE;
		this.canCollideEnemy = true;
		this.canCollideEnemyBullet = true;
		
		this.collideShape = new CollideShape(this.position, this.dimension)
				.setCollideEllipse(true);
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void collideEnemy(Entity e) {
		host.getDamaged(e.damage);
	}
	
	@Override
	public void collideEnemyBullet(Entity e) {
		host.getDamaged(e.damage);
	}
	
	public void appear() {
		this.visual = this.heart;
	}
	
	public void dissapear() {
		this.visual = null;
	}
}
