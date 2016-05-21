import java.awt.Color;


public class Wave3Factory extends MobWaveFactory {

	public Wave3Factory(BulletStage stage) {
		super(stage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] queue = new MobWave[7];
		Color spiderColor = getRandomColor(EnemyCreationResource.SPIDER_ENEMY, difficulty);
		
		int[] spawnX = {500, 820, 500, 820, 500, 820};
		int[] centerX = {-100, 820, -100, 820, -100, 820};
		int[] spawnY = {-100, 300, -100, 300, -100, 300};
		int centerY = -100;
		
		for(int i=0; i<6; i++)
		{
			SpiderEnemy spider = new SpiderEnemy(new Vector(spawnX[i], spawnY[i]), this.stage, spiderColor);
			MoveBehavior mb = new EllipseMoveBehavior(spider, new Vector(centerX[i], centerY), 800, 1200, 12, 2.8);
			mb.update();
			spider.setMoveBehavior(mb);
			ShootBehavior sb = getShootBehavior(spider, spiderColor, EnemyCreationResource.SPIDER_ENEMY, difficulty);
			if(sb!=null)
				sb.setFireRate(0.7 + Math.random()*0.8);
			spider.setShootBehavior(sb);
			queue[i] = new SingleSpawnMobWave(this.stage, 0.25, spider);
		}
		queue[6] = new VacantMobWave(this.stage, 3);
		
		return new QueueMobWave(this.stage, 0, queue);
	}

}
