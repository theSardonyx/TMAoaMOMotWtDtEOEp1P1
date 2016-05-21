import java.awt.Color;


public class Wave7Factory extends MobWaveFactory {

	public Wave7Factory(BulletStage stage) {
		super(stage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] queue = new MobWave[7];
		Color spiderColor = getRandomColor(EnemyCreationResource.SPIDER_ENEMY, difficulty);
		
		int[] spawnX = {-100, 820, -100, 820, -100, 820};
		int spawnY = 50;
		int[] centerX = {-100, 820, -100, 820, -100, 820};
		int centerY = 820;
		
		for(int i=0; i<6; i++)
		{
			SpiderEnemy spider = new SpiderEnemy(new Vector(spawnX[i], spawnY), this.stage, spiderColor);
			EllipseMoveBehavior mb = new EllipseMoveBehavior(spider, new Vector(centerX[i], centerY), 1500, 1500, 12, 2.8);
			mb.update();
			if( i%2!=0 )
				mb.reverse();
			spider.setMoveBehavior(mb);
			ShootBehavior sb = getShootBehavior(spider, spiderColor, EnemyCreationResource.SPIDER_ENEMY, difficulty);
			if(sb!=null)
				sb.setFireRate(0.2*difficulty + Math.random()*0.5*difficulty);
			spider.setShootBehavior(sb);
			queue[i] = new SingleSpawnMobWave(this.stage, 0.25, spider);
		}
		queue[6] = new VacantMobWave(this.stage, 3);
		
		return new QueueMobWave(this.stage, 0, queue);
	}

}
