import java.util.*;

public class QueueMobWave extends MobWave {
	
	private Queue<MobWave> subwaves;
	
	public QueueMobWave(BulletStage stage, double delay, MobWave[] subwaves) {
		super(stage, delay, 0);
		this.subwaves = new ArrayDeque<MobWave>();
		
		if(subwaves == null)
			return;
		
		for(MobWave sw: subwaves)
		{
			this.subwaves.offer( sw );
			this.expireTime += sw.getExpireTime();
		}
	}

	@Override
	protected void spawn(double delta)
	{
		double timeLeft = delta;
		
		while(!this.subwaves.isEmpty() && timeLeft > 0)
		{
			MobWave curr = this.subwaves.peek();
			double timeSpent = Math.min(timeLeft, curr.getExpireTime());
			
			curr.update(timeSpent);
			timeLeft -= timeSpent;
			
			if(curr.getDelayLeft() <= 0 && curr.getExpireTime() <= 0)
				this.subwaves.poll();
		}
	}

}
