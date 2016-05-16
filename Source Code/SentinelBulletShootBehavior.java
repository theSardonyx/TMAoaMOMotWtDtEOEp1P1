
public class SentinelBulletShootBehavior extends UpgradableShootBehavior {

	private Entity[] sentinels;
	private double height, width, period;
	private int numBullets;
	
	public SentinelBulletShootBehavior(Entity subject, BulletStage stage) {
		super(subject, stage);

		this.fireRate = 1;
	}
	
	public SentinelBulletShootBehavior(Entity subject, BulletStage stage, double expireTime) {
		super(subject, stage, expireTime);

		this.fireRate = 0;
	}
	
	@Override
	public void shoot(double delta) {
		super.shoot(delta);
		if(this.expireTime <= 0) {
			for(Entity sentinel: sentinels) {
				sentinel.despawn();
				sentinel = null;
			}
		}
	}

	@Override
	public void update() {
		this.numBullets = (this.getLevel() / 3) + 1;
		if(this.sentinels == null) {
			this.sentinels = new Entity[0];
		}
		
		if(this.sentinels.length != this.numBullets) {
			this.sentinels = new Entity[numBullets];
		}
		
		this.height = this.width = 100;
		this.period = 2 * Math.PI;
	}

	@Override
	public Entity[] getBullets() {
		Entity[] bullets = new Entity[numBullets];
		double rotation = (2 * Math.PI) / numBullets;
		for(int i = 0; this.numBullets > i; i++) {
			if(this.sentinels[i] == null) {
				double rPart1 = Math.pow(this.height, 2) * Math.pow(Math.cos(rotation * i), 2);
				double rPart2 = Math.pow(this.width,  2) * Math.pow(Math.sin(rotation * i), 2);
				double r = (this.width * this.height) / (2 * Math.sqrt(rPart1 + rPart2));
				Vector newPosition = new Vector(r * Math.cos(rotation * i), r * Math.sin(rotation * i));
	
				SentinelBullet projectile = new SentinelBullet(this.subject.position.add(newPosition),  this.stage);
				EllipseMoveBehavior mb = new EllipseMoveBehavior(projectile, this.subject.position, this.height, this.width, this.period);
				mb.setRotation(rotation * i);
				mb.setAnchor(this.subject);
				projectile.setMoveBehavior(mb);
				
				bullets[i] = projectile;
				this.sentinels[i] = projectile;
			}
		}
		return bullets;
	}
}