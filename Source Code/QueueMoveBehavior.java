
import java.util.*;

public class QueueMoveBehavior extends MoveBehavior {

	private ArrayDeque<MoveBehavior> moveQueue;
	
	public QueueMoveBehavior(Entity subject, MoveBehavior[] movebehavior) {
		super(subject, 0);
		this.moveQueue = new ArrayDeque<MoveBehavior>();
		
		if(movebehavior==null)
			return;
		
		for(MoveBehavior mb: movebehavior)
		{
			this.expireTime += mb.getExpireTime();
			this.moveQueue.offer( mb );
		}
		
		this.moveQueue.peek().update();
	}

	@Override
	protected void moveHook(double delta) {
		if(this.moveQueue.isEmpty())
		{
			this.expireTime = 0;
		}
		else
		{
			double timeRemaining = delta;
			while(!this.moveQueue.isEmpty() && timeRemaining > 0)
			{
				double timeMove = Math.min(delta, this.moveQueue.peek().getExpireTime());
				this.moveQueue.peek().move(timeMove);
				if(this.moveQueue.peek().getExpireTime()<=0)
					expire();
				timeRemaining -= timeMove;
			}
		}
	}
	
	@Override
	public MoveBehavior getMoveBehavior() {
		if(this.moveQueue.isEmpty())
			return null;
		
		return this.moveQueue.peek();
	}
	
	private void expire()
	{
		this.moveQueue.pop();
		if(!this.moveQueue.isEmpty())
			this.moveQueue.peek().update();
	}
	
	public void pushBack(MoveBehavior mb)
	{
		this.expireTime += mb.getExpireTime();
		this.moveQueue.offer(mb);
		this.moveQueue.peek().update();
	}
	
	public void pushFront(MoveBehavior mb)
	{
		this.expireTime += mb.getExpireTime();
		this.moveQueue.push(mb);
		this.moveQueue.peek().update();
	}
}
