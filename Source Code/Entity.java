import java.awt.Graphics2D;

public abstract class Entity extends Drawable {
	
	protected static final int AMBIENT_TYPE = 0;
	protected static final int ALLY_TYPE = 1;
	protected static final int ALLY_BULLET_TYPE = 2;
	protected static final int ENEMY_TYPE = 3;
	protected static final int ENEMY_BULLET_TYPE = 4;
	protected static final int DROP_TYPE = 5;
	
	private static int ID = 0;
	
	protected int id;
	protected MoveBehavior move;
	protected ShootBehavior shoot;
	
	protected Drawable visual;
	protected CollideShape collideShape;
	protected int health, damage;
	protected BulletStage stage;
	protected double expireTime;
	
	protected int type;
	protected boolean canCollideAmbient, canCollideAlly, canCollideAllyBullet, canCollideEnemy, canCollideEnemyBullet, canCollideDrop;

	public Entity(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension);
		
		this.id = ID++;
		this.move = null;
		this.shoot = null;
		
		this.visual = null;
		this.collideShape = null;
		
		this.health = 1;
		this.damage = 0;
		this.stage = stage;
		
		this.type = Entity.AMBIENT_TYPE;
		this.canCollideAmbient = false;
		this.canCollideAlly = false;
		this.canCollideAllyBullet = false;
		this.canCollideEnemy = false;
		this.canCollideEnemyBullet = false;
		this.canCollideDrop = false;
		
		this.expireTime = Double.POSITIVE_INFINITY;
	}
	
	public Entity(Vector position, Vector dimension, BulletStage stage, double expireTime) {
		super(position, dimension);
		
		this.id = ID++;
		this.move = null;
		this.shoot = null;
		
		this.visual = null;
		this.collideShape = null;
		
		this.health = 1;
		this.damage = 0;
		this.stage = stage;
		
		this.type = Entity.AMBIENT_TYPE;
		this.canCollideAmbient = false;
		this.canCollideAlly = false;
		this.canCollideAllyBullet = false;
		this.canCollideEnemy = false;
		this.canCollideEnemyBullet = false;
		this.canCollideDrop = false;
		
		this.expireTime = expireTime;
	}
	
	public final void update( double delta ) {
		if(this.move != null) {
			if(this.move.getExpireTime() <= 0) {
				this.despawn();
				return;
			}
			this.move.move(delta);
		}
		if(this.shoot != null && this.position != null)
			this.shoot.shoot(delta);
		this.updateHook(delta);
		if(this.getCollideShape() != null)
			this.getCollideShape().updatePosition(this.position);
	}
	
	public abstract void updateHook(double delta);
	
	@Override
	public void draw(Graphics2D g) {
		if(this.position == null)
			return;
		if(this.visual == null)
			return;
		this.visual.setPosition( this.position );
		this.visual.setDimension( this.dimension );
		this.visual.draw(g);
	}
	
	public Vector getPosition() {
		return this.position;
	}
	
	public Vector getDimension() {
		return this.dimension;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void getDamaged( int damage ) {
		this.health -= damage;
		if(this.health <= 0)
			this.despawn();
	}
	
	public CollideShape getCollideShape() {
		return this.collideShape;
	}
	
	public void setCollideShape(CollideShape cs) {
		this.collideShape = cs;
	}
	
	public void spawnEntity( Entity e ) {
		this.stage.addRequest( new SpawnRequest( e , stage ) );
	}
	
	public void despawn() {
		this.deathAction();
		this.stage.addRequest( new DespawnRequest( this , stage ) );
	}
	
	public void collide(Entity other) {
		if(this.getCollideShape() == null || other.getCollideShape() == null)
			return;
		if(this.getCollideShape().isCollidingWith(other.getCollideShape())) {
			if(this.canCollideAmbient && other.getType() == Entity.AMBIENT_TYPE)
				this.collideAmbient(other);
			if(this.canCollideAlly && other.getType() == Entity.ALLY_TYPE)
				this.collideAlly(other);
			if(this.canCollideAllyBullet && other.getType() == Entity.ALLY_BULLET_TYPE)
				this.collideAllyBullet(other);
			if(this.canCollideEnemy && other.getType() == Entity.ENEMY_TYPE)
				this.collideEnemy(other);
			if(this.canCollideEnemyBullet && other.getType() == Entity.ENEMY_BULLET_TYPE)
				this.collideEnemyBullet(other);
			if(this.canCollideDrop && other.getType() == Entity.DROP_TYPE)
				this.collideDrop(other);
		}
	}
	
	public void deathAction() {}
	
	public void setMoveBehavior(MoveBehavior mb) {
		this.move = mb;
	}
	
	public MoveBehavior getMoveBehavior() {
		return this.move;
	}
	
	public void setShootBehavior(ShootBehavior sb) {
		this.shoot = sb;
	}
	
	public ShootBehavior getShootBehavior() {
		return this.shoot;
	}
	
	public void setDrawable(Drawable d) {
		this.visual = d;
	}
	
	public Drawable getDrawable() {
		return this.visual;
	}
	
	public void handleInput(InputCollector ic) {}
	
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
	public void collideDrop(Entity e) {}
}
