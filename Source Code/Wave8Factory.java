import java.awt.Color;


public class Wave8Factory extends MobWaveFactory {

	public Wave8Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] combo = new MobWave[5];
		Color batColor = getRandomColor(EnemyCreationResource.BAT_ENEMY, difficulty);
		int[] spawnX = {-100, -100, 820, 820};
		int[] spawnY = {100, 200, 100, 200};
		int[] targetX = {300, 100, 420, 620};
		
		for(int i=0; i<4; i++)
		{
			BatEnemy bat = new BatEnemy(new Vector(spawnX[i], spawnY[i]), this.stage, batColor);
			bat.setMoveBehavior(new QueueMoveBehavior(bat, new MoveBehavior[] {
					new TimedGlideMoveBehavior(bat, new Vector(targetX[i], spawnY[i]), 1, 3),
					new AccelerateMoveBehavior(bat, new Vector(0, 200), Vector.zero(), 4.5)
			}));
			
			ShootBehavior sb = getShootBehavior(bat, batColor, EnemyCreationResource.BAT_ENEMY, difficulty);
			sb.setFireRate( 1 );
			if(difficulty == 0){
				((BatShootBehaviorEasy) sb).setOrientationToRight(Math.random()<0.5);
				((BatShootBehaviorEasy) sb).setHeight(35);
			}
			else if(difficulty == 1)
			{
				((BatShootBehaviorMedium) sb).setNumBullets(3);
				sb.setFireRate(2);
			}
			else if(difficulty == 2)
			{
				((BatShootBehaviorHard) sb).setHeight(35);
				((BatShootBehaviorHard) sb).setFireRate(2.5);
			}
			bat.setShootBehavior(sb);
			
			combo[i] = new SingleSpawnMobWave(this.stage, 0, bat);
		}
		combo[4] = new VacantMobWave(this.stage, 5);
		
		return new CombinationMobWave(this.stage, 0, combo);
	}

}
