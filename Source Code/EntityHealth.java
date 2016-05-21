import java.awt.Color;

public class EntityHealth extends Entity {
	
	private Entity host;
	private int healthCap;

	public EntityHealth(Entity host, Vector dimension, BulletStage stage) {
		super(host.position, dimension, stage);

		this.host = host;
		this.healthCap = host.getHealth();
		this.update(0);
		
		this.type = Entity.AMBIENT_TYPE;
	}

	@Override
	public void updateHook(double delta) {
		DrawableArc arc = new DrawableArc(host.position, new Vector(200, 200), Color.RED);
		arc.setStartAngle(Math.PI / 2);
		arc.setArcAngle((2 * Math.PI) * (this.host.getHealth() / (this.healthCap + 0.0)));
		this.visual = arc;
	}
}
