
public abstract class BossEntity extends Entity {

	public BossEntity(Vector position, Vector dimension, BulletStage stage) {
		super(position, dimension, stage);

		this.type = Entity.ENEMY_TYPE;
		
		this.damage = 1;
	}

	public BossEntity(Vector position, Vector dimension, BulletStage stage, double expireTime) {
		super(position, dimension, stage, expireTime);

		this.type = Entity.ENEMY_TYPE;
		
		this.damage = 1;
	}
	
	@Override
	public void deathAction() {
		Entity drop = null;
		int dropID = (int) (Math.random() * 10) + 1;
		switch(dropID) {
			case PlayerSettings.PIERCE_RUNE:
				drop = new PierceRune(this.position, this.stage);
				break;
			case PlayerSettings.HOMING_RUNE:
				drop = new HomingRune(this.position, this.stage);
				break;
			case PlayerSettings.SPREAD_RUNE:
				drop = new SpreadRune(this.position, this.stage);
				break;
			case PlayerSettings.EXPLOSION_RUNE:
				drop = new ExplosionRune(this.position, this.stage);
				break;
			case PlayerSettings.SNIPE_RUNE:
				drop = new SnipeRune(this.position, this.stage);
				break;
			case PlayerSettings.SPLIT_RUNE:
				drop = new SplitRune(this.position, this.stage);
				break;
			case PlayerSettings.BURST_RUNE:
				drop = new BurstRune(this.position, this.stage);
				break;
			case PlayerSettings.SENTINEL_RUNE:
				drop = new SentinelRune(this.position, this.stage);
				break;
			case PlayerSettings.ANTI_RUNE:
				drop = new AntiRune(this.position, this.stage);
				break;
			case PlayerSettings.SUMMON_RUNE:
				drop = new SummonRune(this.position, this.stage);
				break;
		}
		AccelerateMoveBehavior mb = new AccelerateMoveBehavior(drop, Vector.zero(), new Vector(0, 100), 10);
		drop.setMoveBehavior(mb);
		this.spawnEntity(drop);
	}
}
