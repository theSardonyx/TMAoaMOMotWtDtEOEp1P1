import java.awt.Color;


public class StageDirector implements DeathObserver {
	private BulletStage stage;
	private LevelProducer waveGen;
	private MobWave current;
	private Entity[] bosses;
	private boolean fightingBoss;
	private int waveCount;
	private int maxWaves;
	
	public StageDirector(BulletStage stage)
	{
		this.stage = stage;
		this.waveGen = new LevelProducer(this.stage);
		
		reset();
	}
	
	public void reset() 
	{
		this.bosses = new Entity[10];
		this.instantiateBosses();
		
		this.fightingBoss = false;
		this.waveCount = 0;
		this.maxWaves = 9;
		this.current = waveGen.generateLevel(waveCount);
	}
	
	public void instantiateBosses() 
	{
		Vector initPos = new Vector(320, 160);
		
		bosses[0] = new PixieBoss(initPos, this.stage, Color.LIGHT_GRAY);
		bosses[1] = new SpiderBoss(initPos, this.stage, Color.LIGHT_GRAY);
		bosses[2] = new BatBoss(initPos, this.stage, Color.LIGHT_GRAY);
		bosses[3] = new LadyBoss(initPos, this.stage, Color.LIGHT_GRAY);
		bosses[4] = new WitchBoss(initPos, this.stage, Color.LIGHT_GRAY);
		bosses[5] = new GhostBoss(initPos, this.stage, Color.LIGHT_GRAY);
		bosses[6] = new EyeBoss(initPos, this.stage, Color.LIGHT_GRAY);
		bosses[7] = new LadyBoss(initPos, this.stage, Color.LIGHT_GRAY);
		bosses[8] = new GhostBoss(initPos, this.stage, Color.LIGHT_GRAY);
		bosses[9] = new OverlordBoss(initPos, this.stage, Color.LIGHT_GRAY);
		
		for(int i=0; i<10; i++){
			bosses[i].setMoveBehavior(new TimedGlideMoveBehavior(bosses[i], new Vector(320, 160), 1, Double.POSITIVE_INFINITY));
			bosses[i].registerDeathObserver(this);
		}
	}
	
	public void update(double delta)
	{
		if(!fightingBoss)
		{
			double timeRemaining = delta;
			double timeSpent = Math.min(this.current.getExpireTime(), timeRemaining);
			this.current.update(timeSpent);
			if(current.getExpireTime() <= 0)
			{
				System.out.println("BOss be bossy!!!");
				fightingBoss = true;
				SpawnRequest bossSpawn = new SpawnRequest(bosses[waveCount++], this.stage);
				this.stage.addRequest( bossSpawn );
			}
		}
	}

	@Override
	public void getDeathNotif(DeathObservable subject) {
		if(waveCount > maxWaves)
		{
			// DO STUFF
		}
		else
		{		
			this.fightingBoss = false;
			this.current = waveGen.generateLevel(waveCount);
		}
	}
	
	
}
