
public class SplitBullet extends Entity {
	
	private int numFragments;

	public SplitBullet(Vector position, BulletStage stage) {
		super(position, new Vector(32, 32), stage);
		SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeBulletMSheet.png", 32, 32);
		this.visual = new DrawableImage(position, dimension, ss.get(0, 5));
		
		//TODO damage pls
		this.damage = 1;
		
		this.type = Entity.ALLY_BULLET_TYPE;
		this.canCollideEnemy = true;
		
		this.numFragments = 2;
		
		this.collideShape = new CollideShape(this.position, this.dimension.scalarMult(0.75))
				.setCollideRectangle(true);
	}

	@Override
	public void updateHook(double delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collideEnemy(Entity e) {
		this.despawn();
		for(int i = 0; this.numFragments > i; i++) {
			FragmentBullet projectile = new FragmentBullet(this.position, this.stage);
			AccelerateMoveBehavior mb = new AccelerateMoveBehavior(projectile, new Vector(0, 300).rotate(Math.random() * 2 * Math.PI), Vector.zero());
			projectile.setMoveBehavior(mb);
			
			this.spawnEntity(projectile);
		}
	}
	
	public void setNumFragments(int numFragments) {
		this.numFragments = numFragments;
	}
	
	public int getNumFragments() {
		return this.numFragments;
	}
}
