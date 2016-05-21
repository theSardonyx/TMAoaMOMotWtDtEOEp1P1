import java.awt.Color;

public class Wave0Factory extends MobWaveFactory {
	
	public Wave0Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] combo = new MobWave[7];
		
		int[] spawnX = {280, 185, 90, 440, 535, 630};
		int[] targetY = {70, 175, 280, 70, 175, 280};
		int[] speedX = {-200, -150, -100, 200, 150, 100};
		Color batColor = getRandomColor(EnemyCreationResource.BAT_ENEMY, difficulty);
		
		for(int i=0; i<6; i++)
		{
			BatEnemy bat = new BatEnemy(new Vector(spawnX[i], -100), this.stage, batColor);
			bat.setMoveBehavior(new QueueMoveBehavior(bat, new MoveBehavior[] {
					new TimedGlideMoveBehavior(bat, new Vector(spawnX[i], targetY[i]), 1, 1),
					new AccelerateMoveBehavior(bat, Vector.zero(), Vector.zero(), 4),
					new AccelerateMoveBehavior(bat, new Vector(speedX[i], 0), Vector.zero(), 1.5)
			}));
			ShootBehavior sb = getShootBehavior(bat, batColor, EnemyCreationResource.BAT_ENEMY, difficulty);
			sb.setFireRate(3.5);
			bat.setShootBehavior( sb );
			combo[i] = new SingleSpawnMobWave(this.stage, 0, bat);
		}
		combo[6] = new VacantMobWave(this.stage, 6.5);
		
		return new CombinationMobWave(this.stage, 0, combo);
	}

}
