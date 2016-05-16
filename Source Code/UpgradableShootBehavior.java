
public abstract class UpgradableShootBehavior extends ShootBehavior {

	protected int level, levelMax, levelMin;
	
	public UpgradableShootBehavior(Entity subject, BulletStage bulletStage) {
		super(subject, bulletStage);

		this.setLevelMax(10);
		this.setLevelMin(0);
		this.setLevel(1);
	}
	
	public UpgradableShootBehavior(Entity subject, BulletStage bulletStage, double expireTime) {
		super(subject, bulletStage, expireTime);

		this.setLevelMax(10);
		this.setLevelMin(0);
		this.setLevel(1);
	}
	
	@Override
	public void shoot(double delta) {
		if(level > 0) {
			super.shoot(delta);
		} else {
			this.timer = 0;
		}
	}

	public int getLevel()  {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = Math.min(level, levelMax);
		this.level = Math.max(level, levelMin);
		this.update();
	}
	
	public void levelUp() {
		this.setLevel(this.level + 1);
	}
	
	public void levelDown() {
		this.setLevel(this.level - 1);
	}
	
	public int getLevelMax() {
		return this.levelMax;
	}
	
	public void setLevelMax(int levelMax) {
		this.levelMax = levelMax;
	}
	
	public int getLevelMin() {
		return this.levelMin;
	}
	
	public void setLevelMin(int levelMin) {
		this.levelMin = levelMin;
	}
	
	public abstract void update();
}
