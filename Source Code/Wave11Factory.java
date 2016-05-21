import java.awt.Color;


public class Wave11Factory extends MobWaveFactory {

	public Wave11Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] queue = new MobWave[3];
		MobWave[] combo = new MobWave[4];
		
		Color pixieColor = getRandomColor(EnemyCreationResource.PIXIE_ENEMY, difficulty);
		PixieEnemy pixie = new PixieEnemy(new Vector(360, -100), this.stage, pixieColor);
		pixie.setMoveBehavior(new QueueMoveBehavior(pixie, new MoveBehavior[] {
				new TimedGlideMoveBehavior(pixie, new Vector(360, 100), 1, 7),
				new AccelerateMoveBehavior(pixie, new Vector(0, -150), Vector.zero(), 1)
		}));
		ShootBehavior sb = getShootBehavior(pixie, pixieColor, EnemyCreationResource.PIXIE_ENEMY, difficulty);
		
		pixie.setShootBehavior(sb);
		combo[0] = new SingleSpawnMobWave(this.stage, 0, pixie);
		
		Color spiderColor = getRandomColor(EnemyCreationResource.SPIDER_ENEMY, difficulty);
		SpiderEnemy spider = new SpiderEnemy(new Vector(360, -100), this.stage, spiderColor);
		spider.setMoveBehavior(new TimedGlideMoveBehavior(spider, new Vector(360, 820), 5, 5));
		sb = getShootBehavior(spider, spiderColor, EnemyCreationResource.SPIDER_ENEMY, difficulty);
		spider.setShootBehavior(sb);
		queue[1] = new SingleSpawnMobWave(this.stage, 0, spider);
		
		int[] spawnX = {260, 460};
		int spawnY = -100;
		int targetY = 150;
		int[] exitX = {-100, 820};
		Color witchColor = getRandomColor(EnemyCreationResource.WITCH_ENEMY, difficulty);
		for(int i=0; i<2; i++)
		{
			WitchEnemy witch = new WitchEnemy(new Vector(spawnX[i], spawnY), this.stage, witchColor);
			witch.setMoveBehavior(new QueueMoveBehavior(witch, new MoveBehavior[]{
					new TimedGlideMoveBehavior(witch, new Vector(spawnX[i], targetY), 1, 7),
					new TimedGlideMoveBehavior(witch, new Vector(exitX[i], targetY), 1, 1)
			}));

			ShootBehavior witchShoot = getShootBehavior(witch, witchColor, EnemyCreationResource.WITCH_ENEMY, difficulty);
			witch.setShootBehavior(witchShoot);
			
			combo[i+1] = new SingleSpawnMobWave(this.stage, 0, witch);
		}
		combo[3] = new VacantMobWave(this.stage, 2);
		queue[0] = new CombinationMobWave(this.stage, 0.9, combo);
		queue[2] = new VacantMobWave(this.stage, 7);
		
		return new QueueMobWave(this.stage, 0, queue);
	}

}
