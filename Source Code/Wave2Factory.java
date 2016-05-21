import java.awt.Color;


public class Wave2Factory extends MobWaveFactory {

	public Wave2Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] combo = new MobWave[5];
		Color eyeColor = getRandomColor(EnemyCreationResource.EYE_ENEMY, difficulty);
		int[] spawnX = {-100, -100, 820, 820};
		int[] spawnY = {100, 200, 100, 200};
		int[] targetX = {200, 100, 520, 620};
		
		for(int i=0; i<4; i++)
		{
			EyeEnemy eye = new EyeEnemy(new Vector(spawnX[i], spawnY[i]), this.stage, eyeColor);
			eye.setMoveBehavior(new QueueMoveBehavior(eye, new MoveBehavior[] {
					new TimedGlideMoveBehavior(eye, new Vector(targetX[i], spawnY[i]), 1, 1),
					new AccelerateMoveBehavior(eye, Vector.zero(), Vector.zero(), 2),
					new TimedGlideMoveBehavior(eye, new Vector(targetX[i], -100), 2, 2)
			}));
			ShootBehavior sb = getShootBehavior(eye, eyeColor, EnemyCreationResource.EYE_ENEMY, difficulty);
			sb.setFireRate( 2.8 );
			eye.setShootBehavior(sb);
			combo[i] = new SingleSpawnMobWave(this.stage, 0, eye);
		}
		combo[4] = new VacantMobWave(this.stage, 5);
		
		return new CombinationMobWave(this.stage, 0, combo);
	}

}
