import java.awt.Color;


public class Wave6Factory extends MobWaveFactory {

	public Wave6Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] combo = new MobWave[7];
		Color batColor = getRandomColor(EnemyCreationResource.BAT_ENEMY, difficulty);
		
		int[] spawnX = {-100, 820, -100, 820, -100, 820};
		int[] spawnY = {-100, 820, 100, 100, 300, 300};
		int[] stayX = {100, 620, 300, 420, 100, 620};
		int[] stayY = {100, 100, 200, 200, 300, 300};
		int[] exitX = {300, 420, 500, 220, 820, -100};
		int[] exitY = {-100, -100, -100, -100, 400, 400};
		
		for(int i=0; i<6; i++)
		{
			BatEnemy bat = new BatEnemy(new Vector(spawnX[i], spawnY[i]), this.stage, batColor);
			bat.setMoveBehavior(new QueueMoveBehavior(bat, new MoveBehavior[] {
				new TimedGlideMoveBehavior(bat, new Vector(stayX[i], stayY[i]), 1, 6),
				new TimedGlideMoveBehavior(bat, new Vector(exitX[i], exitY[i]), 3, 3)
			}));
			ShootBehavior sb = getShootBehavior(bat, batColor, EnemyCreationResource.BAT_ENEMY, difficulty);
			if(difficulty==0) 
			{
				((BatShootBehaviorEasy) sb).setOrientationToRight(i%2==0);
				((BatShootBehaviorEasy) sb).setHeight(35);
				sb.setFireRate( 0.75 );
			}
			else if(difficulty == 1) {
				((BatShootBehaviorMedium) sb).setNumBullets(3);
				sb.setFireRate( 2 );
			}
			else if(difficulty==2)
			{
				((BatShootBehaviorHard) sb).setHeight(25);
				sb.setFireRate( 1.5 );
			}
			bat.setShootBehavior( sb );
			combo[i] = new SingleSpawnMobWave(this.stage, 0, bat);
		}
		combo[6] = new VacantMobWave(this.stage, 13);
		
		return new CombinationMobWave(this.stage, 0, combo);
	}

}
