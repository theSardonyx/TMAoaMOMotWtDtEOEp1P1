
public abstract class MobWave {
	protected double delayLeft;
	protected double expireTime;
	protected boolean ongoing;
	protected BulletStage stage;
	
	public MobWave(BulletStage stage, double delay, double runTime)
	{
		this.stage = stage;
		this.delayLeft = delay;
		this.expireTime = delay+runTime;
		this.ongoing = false;
	}
	
	public final void update(double delta)
	{
		if(this.expireTime <= 0)
			return;
					
		this.expireTime -= delta;
		
		if(!ongoing)
		{
			this.delayLeft -= delta;
			if( this.delayLeft<=0 )
			{
				ongoing = true;
				delta = -this.delayLeft;
			}
			else
			{
				return;
			}
		}
		
		spawn(delta);
	}
	
	protected abstract void spawn(double delta);
	
	public double getDelayLeft() {
		return delayLeft;
	}

	public double getExpireTime() {
		return expireTime;
	}

	public boolean isOngoing() {
		return ongoing;
	}
}