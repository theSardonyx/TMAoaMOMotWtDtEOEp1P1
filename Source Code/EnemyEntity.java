
public abstract class EnemyEntity extends Entity {
	
	protected double heartChance;

	public EnemyEntity(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);
		
		this.type = Entity.ENEMY_TYPE;
		
		this.damage = 1;
		
		this.heartChance = 10;
	}
	
	public EnemyEntity(Vector position, Vector dimension, BulletStage stage, double expireTime) {
		super(position, dimension, stage, expireTime);
		
		this.type = Entity.ENEMY_TYPE;
		
		this.damage = 1;
		
		this.heartChance = 10;
	}
	
	@Override
	public void getDamaged(int damage) {
		super.getDamaged(damage);
		if(this.health <= 0 && Math.random() * 100 <= heartChance) {
			Heart heart = new Heart(this.position, this.stage);
			AccelerateMoveBehavior mb = new AccelerateMoveBehavior(heart, Vector.zero(), new Vector(0, 100), 10);
			heart.setMoveBehavior(mb);
			this.spawnEntity(heart);
		}
	}
}
