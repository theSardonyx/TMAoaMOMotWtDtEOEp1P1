import java.awt.Color;


public class Wave10Factory extends MobWaveFactory {

	public Wave10Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] combo = new MobWave[6];
		
		Color witchColor = getRandomColor(EnemyCreationResource.WITCH_ENEMY, difficulty);
		WitchEnemy witch = new WitchEnemy(new Vector(360, -100), this.stage, witchColor);
		witch.setMoveBehavior(new QueueMoveBehavior(witch, new MoveBehavior[] {
				new TimedGlideMoveBehavior(witch, new Vector(360, 100), 1, 3),
				new AccelerateMoveBehavior(witch, new Vector(0, -150), Vector.zero(), 1)
		}));
		ShootBehavior sb = getShootBehavior(witch, witchColor, EnemyCreationResource.WITCH_ENEMY, difficulty);
		witch.setShootBehavior(sb);
		combo[0] = new SingleSpawnMobWave(this.stage, 0, witch);
		
		int[] spawnX = {260, 460};
		int spawnY = -100;
		int targetY = 150;
		int[] exitX = {-100, 820};
		Color batColor = getRandomColor(EnemyCreationResource.BAT_ENEMY, difficulty);
		for(int i=0; i<2; i++)
		{
			BatEnemy bat = new BatEnemy(new Vector(spawnX[i], spawnY), this.stage, batColor);
			bat.setMoveBehavior(new QueueMoveBehavior(bat, new MoveBehavior[]{
					new TimedGlideMoveBehavior(bat, new Vector(spawnX[i], targetY), 1, 3),
					new TimedGlideMoveBehavior(bat, new Vector(exitX[i], targetY), 1, 1)
			}));

			ShootBehavior batShoot = getShootBehavior(bat, batColor, EnemyCreationResource.BAT_ENEMY, difficulty);
			batShoot.setFireRate( 0.75 );
			if(difficulty==0)
			{
				((BatShootBehaviorEasy) batShoot).setOrientationToRight( i % 2 == 0 );
			}		
			
			bat.setShootBehavior(batShoot);
			
			combo[i+1] = new SingleSpawnMobWave(this.stage, 0, bat);
		}
		
		spawnX = new int[] {100, 620};
		targetY = 50;
		Color eyeColor = getRandomColor(EnemyCreationResource.EYE_ENEMY, difficulty);
		for(int i=0; i<2; i++)
		{
			EyeEnemy eye = new EyeEnemy(new Vector(spawnX[i], spawnY), this.stage, eyeColor);
			eye.setMoveBehavior(new QueueMoveBehavior(eye, new MoveBehavior[]{
					new TimedGlideMoveBehavior(eye, new Vector(spawnX[i], targetY), 1, 3),
					new TimedGlideMoveBehavior(eye, new Vector(exitX[i], targetY), 1, 1)
			}));
			ShootBehavior eyeShoot = getShootBehavior(eye, eyeColor, EnemyCreationResource.EYE_ENEMY, difficulty);
			eyeShoot.setFireRate(1);
			eye.setShootBehavior(eyeShoot);
			
			combo[i+3] = new SingleSpawnMobWave(this.stage, 0, eye);
		}
		combo[5] = new VacantMobWave(this.stage, 7.2);
		
		return new CombinationMobWave(this.stage, 0, combo);
	}

}
