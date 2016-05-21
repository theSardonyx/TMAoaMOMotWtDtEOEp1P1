import java.awt.Color;


public class Wave16Factory extends MobWaveFactory {

	public Wave16Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] combo = new MobWave[5];
		Color witchColor = getRandomColor(EnemyCreationResource.WITCH_ENEMY, difficulty);
		int[] spawnX = {-100, -100, 820, 820};
		int[] spawnY = {100, 200, 100, 200};
		int[] targetX = {200, 100, 520, 620};
		
		for(int i=0; i<4; i++)
		{
			WitchEnemy witch = new WitchEnemy(new Vector(spawnX[i], spawnY[i]), this.stage, witchColor);
			witch.setMoveBehavior(new QueueMoveBehavior(witch, new MoveBehavior[] {
					new TimedGlideMoveBehavior(witch, new Vector(targetX[i], spawnY[i]), 1, 1),
					new AccelerateMoveBehavior(witch, Vector.zero(), Vector.zero(), 8),
					new TimedGlideMoveBehavior(witch, new Vector(targetX[i], -100), 2, 2)
			}));
			ShootBehavior sb = getShootBehavior(witch, witchColor, EnemyCreationResource.WITCH_ENEMY, difficulty);
			witch.setShootBehavior(sb);
			combo[i] = new SingleSpawnMobWave(this.stage, 0, witch);
		}
		combo[4] = new VacantMobWave(this.stage, 13);
		
		return new CombinationMobWave(this.stage, 0, combo);
	}

}
