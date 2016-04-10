import java.awt.Graphics2D;

public abstract class Entity extends Drawable {
	
	protected MoveBehavior move;
	protected ShootBehavior shoot;
	protected CollideReaction collideReaction;
	
	protected Drawable visual;
	protected CollideShape hitbox;
	protected int health;
	protected BulletStage stage;

	public Entity(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension);
		this.stage = stage;
	}
	
	public final void update( double delta ) {
		move.move(delta);
		if(shoot != null)
			shoot.shoot(delta);
		
		updateHook(delta);
	}
	
	public abstract void updateHook(double delta);
	
	@Override
	public final void draw(Graphics2D g) {
		visual.setPosition( position );
		visual.setDimension( dimension );
		visual.draw(g);
	}
	
	public boolean isCollidingWith( Entity e ) {
		return hitbox.isCollidingWith( e.getHitbox() );
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public Vector getDimension() {
		return dimension;
	}

	public void setVelocity(Vector velocity) {
		move.setVelocity(velocity);
	}
	
	public void setAcceleration(Vector acceleration) {
		move.setAcceleration(acceleration);
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
	
	public void addCollideReaction(CollideReaction next) {
		if(this.collideReaction == null)
			this.collideReaction = next;
		else
			this.collideReaction.addNext(next);
	}
	
	public void collide(Entity other) {
		if(collideReaction!=null)
			collideReaction.collide(other);
	}
	
	public void deathAction() {
		// EMPTY
	}
}
