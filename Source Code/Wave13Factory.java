import java.awt.Color;


public class Wave13Factory extends MobWaveFactory {

	public Wave13Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] combo = new MobWave[4];
		
		Color pixieColor = getRandomColor(EnemyCreationResource.PIXIE_ENEMY, difficulty);
		PixieEnemy pixie = new PixieEnemy(new Vector(360, -100), this.stage, pixieColor);
		pixie.setMoveBehavior(new TimedGlideMoveBehavior(pixie, new Vector(360, 820), 10, 10));
		pixie.setShootBehavior( getShootBehavior(pixie, pixieColor, EnemyCreationResource.PIXIE_ENEMY, difficulty) );
		combo[0] = new SingleSpawnMobWave(this.stage, 0, pixie);
		
		Color witchColor = getRandomColor(EnemyCreationResource.WITCH_ENEMY, difficulty);
		int[] spawnX = {200, 520};
		for(int i=0; i<2; i++)
		{
			WitchEnemy witch = new WitchEnemy(new Vector(spawnX[i], -100), this.stage, witchColor);
			witch.setMoveBehavior(new TimedGlideMoveBehavior(witch, new Vector(spawnX[i], 820), 10, 10));
			witch.setShootBehavior( getShootBehavior(witch, witchColor, EnemyCreationResource.WITCH_ENEMY, difficulty) );
			combo[i+1] = new SingleSpawnMobWave(this.stage, 0, witch);
		}
		combo[3] = new VacantMobWave(this.stage, 12);
		
		return new CombinationMobWave(this.stage, 0, combo);
	}

}
