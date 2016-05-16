import java.util.ArrayDeque;

public class QueueShootBehavior extends ShootBehavior {

	private ArrayDeque<ShootBehavior> shootQueue;
	
	public QueueShootBehavior(Entity subject, BulletStage stage, ShootBehavior[] shootBehaviors) {
		super(subject, stage, 0);
		this.shootQueue = new ArrayDeque<ShootBehavior>();
		
		if(shootBehaviors == null) {
			return;
		}
		
		for(ShootBehavior sb: shootBehaviors) {
			this.expireTime += sb.getExpireTime();
			this.shootQueue.offer(sb);
		}
	}
	
	@Override
	public void shoot(double delta) {
		if(this.shootQueue.isEmpty())
			this.expireTime = 0;
		else {
			double timeRemaining = delta;
			while(!this.shootQueue.isEmpty() && timeRemaining > 0) {
				double timeMove = Math.min(timeRemaining, this.shootQueue.peek().getExpireTime());
				super.shoot(timeMove);
				if(this.shootQueue.peek().getExpireTime() <= 0)
					this.expire();
				timeRemaining -= timeMove;
			}
			this.expireTime -= delta;
		}
	}

	@Override
	public Entity[] getBullets() {
		return this.shootQueue.peek().getBullets();
	}

	@Override
	public ShootBehavior getShootBehavior() {
		if(this.shootQueue.isEmpty())
			return null;
		
		return this.shootQueue.peek();
	}
	
	private void expire() {
		this.shootQueue.pop();
	}
	
	public void pushBack(ShootBehavior sb) {
		this.expireTime += sb.getExpireTime();
		this.shootQueue.offer(sb);
	}
	
	public void pushFront(ShootBehavior sb) {
		this.expireTime += sb.getExpireTime();
		this.shootQueue.push(sb);
	}
}
