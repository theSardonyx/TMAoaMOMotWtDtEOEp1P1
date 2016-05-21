import java.awt.Color;


public class Wave1Factory extends MobWaveFactory {

	public Wave1Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] queue = new MobWave[7];
		Color spiderColor = getRandomColor(EnemyCreationResource.SPIDER_ENEMY, difficulty);
		
		int[] spawnX = {620, 100, 620, 100, 620, 100};
		int targetY = 140;
		int[] genVelocityX = {-260, 260, -260, 260, -260, 260};
		boolean[] startAtRight = {true, false, true, false, true, false};
		for(int i=0; i<6; i++)
		{
			SpiderEnemy spider = new SpiderEnemy(new Vector(spawnX[i], -100), this.stage, spiderColor);
			spider.setMoveBehavior(new QueueMoveBehavior(spider, new MoveBehavior[] {
					new TimedGlideMoveBehavior(spider, new Vector(spawnX[i], targetY), 1, 1),
					new WaveMoveBehavior(spider, new Vector(genVelocityX[i], 0), 40, 1, startAtRight[i], 2),
					new AccelerateMoveBehavior(spider, new Vector(0, 200), Vector.zero(), 3)
			}));;
			ShootBehavior sb = getShootBehavior(spider, spiderColor, EnemyCreationResource.SPIDER_ENEMY, difficulty);
			if(sb!=null)
				sb.setFireRate(1 + (Math.random()*0.5));
			spider.setShootBehavior(sb);
			queue[i] = new SingleSpawnMobWave(this.stage, 0.5, spider);
		}
		queue[6] = new VacantMobWave(this.stage, 6);
		
		return new QueueMobWave(this.stage, 0, queue);
	}

}
