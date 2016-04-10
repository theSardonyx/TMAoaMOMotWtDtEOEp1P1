import java.awt.Graphics2D;

public abstract class Entity extends Drawable {
	
	private MoveBehavior move;
	private ShootBehavior shoot;
	private AnimatedDrawable visual;
	private CollideShape hitbox;
	private int health;
	private BulletStage stage;

	public Entity(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension);
		this.stage = stage;
		health = 0;
	}
	
	public final void update( double delta ) {
		move.move(delta);
		shoot.shoot(delta);
		visual.update(delta);
	}
	
	public abstract void updateHook();
	
	@Override
	public final void draw(Graphics2D g) {
		visual.setPosition( position );
		visual.setDimension( dimension );
		visual.draw(g);
	}
	
	public boolean isCollidingWith( Entity e ) {
		return hitbox.isCollidingWith( e.getHitbox() );
	}
	
	public int getHealth() {
		return health;
	}
	
	public void getDamaged( int damage ) {
		health -= damage;
	}
	
	public CollideShape getHitbox() {
		return hitbox;
	}
	
	public void spawnEntity( Entity e ) {
		stage.addRequest( new SpawnRequest( e , stage ) );
	}
	
	public void despawn() {
		deathAction();
		stage.addRequest( new DespawnRequest( this , stage ) );
	}
	
	public void deathAction() {
		// EMPTY
	}
}
