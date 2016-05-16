import java.util.ArrayList;

public class CombinationShootBehavior extends ShootBehavior {
	
	private ArrayList<ShootBehavior> shoots;

	public CombinationShootBehavior(Entity subject, BulletStage stage, ShootBehavior[] shootBehaviors) {
		super(subject, stage, 0);
		shoots = new ArrayList<ShootBehavior>();
		
		if(shootBehaviors == null)
			return;
		
		for(ShootBehavior sb: shootBehaviors) {
			this.expireTime = Math.max(this.expireTime, sb.expireTime);
			this.shoots.add(sb);
		}
	}
	
	@Override
	public void shoot(double delta) {
		for(ShootBehavior sb: shoots) {
			sb.shoot(delta);
		}
	}

	@Override
	public Entity[] getBullets() { 
		return null;
	}
	
	public void add(ShootBehavior sb) {
		this.shoots.add(sb);
		this.expireTime = Math.max(this.expireTime, sb.expireTime);
	}
}
