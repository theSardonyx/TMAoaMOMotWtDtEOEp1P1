import java.util.ArrayList;

public class CombinationMobWave extends MobWave {

	private ArrayList<MobWave> subwaves;
	
	public CombinationMobWave(BulletStage stage, double delay, MobWave[] subwaves) {
		super(stage, delay, 0);
		this.subwaves = new ArrayList<MobWave>();
		
		if(subwaves == null)
			return;
		
		for(MobWave sw: subwaves)
		{
			this.subwaves.add( sw );
			this.expireTime = Math.max(this.expireTime, sw.getExpireTime());
		}
	}

	@Override
	protected void spawn(double delta) {
		subwaves.forEach( mb -> mb.update(delta) );
	}

}
