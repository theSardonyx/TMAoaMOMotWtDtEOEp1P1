import java.awt.Color;


public class Wave12Factory extends MobWaveFactory {

	public Wave12Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] combo = new MobWave[4];
		
		Color pixieColor = getRandomColor(EnemyCreationResource.PIXIE_ENEMY, difficulty);
		int[] spawnX = {360, -100, 820};
		int[] spawnY = {-100, 300, 300};
		int[] targetX = {360, 200, 520};
		int[] targetY = {250, 300, 300};
		
		for(int i=0; i<3; i++)
		{
			PixieEnemy pixie = new PixieEnemy(new Vector(spawnX[i], spawnY[i]), this.stage, pixieColor);
			pixie.setMoveBehavior(new QueueMoveBehavior(pixie, new MoveBehavior[] {
					new TimedGlideMoveBehavior(pixie, new Vector(targetX[i], targetY[i]), 3, 7),
					new TimedGlideMoveBehavior(pixie, pixie.getPosition(), 1, 1)
			}));
			pixie.setShootBehavior( getShootBehavior(pixie, pixieColor, EnemyCreationResource.PIXIE_ENEMY, difficulty) );
			combo[i] = new SingleSpawnMobWave(this.stage, 0, pixie);
		}
		combo[3] = new VacantMobWave(this.stage, 12);
		
		return new CombinationMobWave(this.stage, 0, combo);
	}

}
