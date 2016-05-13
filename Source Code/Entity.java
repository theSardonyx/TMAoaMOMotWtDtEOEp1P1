import java.awt.Graphics2D;
import java.util.LinkedList;

public abstract class Entity extends Drawable {
	
	protected static final int AMBIENT_TYPE = 0;
	protected static final int ALLY_TYPE = 1;
	protected static final int ALLY_BULLET_TYPE = 2;
	protected static final int ENEMY_TYPE = 3;
	protected static final int ENEMY_BULLET_TYPE = 4;
	
	private static int ID = 0;
	
	protected int id;
	protected MoveBehavior move;
	protected ShootBehavior shoot;
	protected CollideReaction collideReaction;
	
	protected Drawable visual;
	protected CollideShape hitbox;
	protected int health, damage;
	protected BulletStage stage;
	
	protected int type;
	protected boolean canCollideAmbient, canCollideAlly, canCollideAllyBullet, canCollideEnemy, canCollideEnemyBullet;

	public Entity(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension);
		
		this.id = ID++;
		this.move = null;
		this.shoot = null;
		this.collideReaction = null;
		
		this.visual = null;
		this.hitbox = null;
		
		this.health = 1;
		this.damage = 0;
		this.stage = stage;
		
		this.type = Entity.AMBIENT_TYPE;
		this.canCollideAmbient = false;
		this.canCollideAlly = false;
		this.canCollideAllyBullet = false;
		this.canCollideEnemy = false;
		this.canCollideEnemyBullet = false;
	}
	
	public final void update( double delta ) {
		if(move != null)
			move.move(delta);
		if(shoot != null)
			shoot.shoot(delta);
		updateHook(delta);
	}
	
	public abstract void updateHook(double delta);
	
	@Override
	public void draw(Graphics2D g) {
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
	
	public int getHealth() {
		return health;
	}
	
	public void getDamaged( int damage ) {
		health -= damage;
		if(health <= 0)
			this.despawn();
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
	
	public void setMoveBehavior(MoveBehavior mb) {
		move = mb;
	}
	
	public MoveBehavior getMoveBehavior() {
		return this.move;
	}
	
	public void setShootBehavior(ShootBehavior sb) {
		shoot = sb;
	}
	
	public ShootBehavior getShootBehavior() {
		return this.shoot;
	}
	
	public void setDrawable(Drawable d) {
		visual = d;
	}
	
	public Drawable getDrawable() {
		return visual;
	}
	
	public void handleInput(InputCollector ic) {
		
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void collideAmbient(Entity e) {}
	public void collideAlly(Entity e) {}
	public void collideAllyBullet(Entity e) {}
	public void collideEnemy(Entity e) {}
	public void collideEnemyBullet(Entity e) {}
}
