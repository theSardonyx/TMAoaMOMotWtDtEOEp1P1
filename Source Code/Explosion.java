import java.awt.Color;

public class Explosion extends Entity {
	
	private Color color;

	public Explosion(Vector position, Vector dimension, BulletStage stage, Color color) {
		super(position, dimension, stage);
		this.color = color;
		
		this.visual = new DrawableOval(this.position, this.dimension, this.color);
		
		this.damage = 1;
		
		this.type = Entity.ALLY_BULLET_TYPE;
		
		this.collideShape = new CollideShape(this.position, this.dimension)
				.setCollideRectangle(true);
		
		this.canCollideEnemy = true;
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collideEnemy(Entity e) {
		this.despawn();
		Particle p = new Particle(this.position, new Vector(1, 1), this.color, this.stage);
		p.setFilled(false);
		p.setGrowRate(this.dimension.scalarMult(3));
		p.adjustDuration(1/3.0);
		this.spawnEntity(p);
	}
}
