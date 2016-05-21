import java.awt.Color;


public class Wave5Factory extends MobWaveFactory {

	public Wave5Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {

		MobWave[] combo = new MobWave[4];
		Color batColor = getRandomColor(EnemyCreationResource.BAT_ENEMY, difficulty);
		Color witchColor = getRandomColor(EnemyCreationResource.WITCH_ENEMY, difficulty);
		
		WitchEnemy witch = new WitchEnemy(new Vector(360, -100), this.stage, witchColor);
		witch.setShootBehavior(getShootBehavior(witch, witchColor, EnemyCreationResource.WITCH_ENEMY, difficulty) );
		witch.setMoveBehavior(new AccelerateMoveBehavior(witch, new Vector(0, 100), Vector.zero(), 9));
		combo[0] = new SingleSpawnMobWave(this.stage, 0, witch);
		
		int[] spawnX = {-100, 820};
		int spawnY = 100;
		int[] targetX = {260, 460};
		int targetY = 820;
		for(int i=0; i<2; i++)
		{
			BatEnemy bat = new BatEnemy(new Vector(spawnX[i], spawnY), this.stage, batColor);
			EllipseMoveBehavior emb = new EllipseMoveBehavior(bat, Vector.zero(), 200, 200, 2, 4);
			emb.setAnchor( witch );
			bat.setMoveBehavior(new QueueMoveBehavior(bat, new MoveBehavior[] {
				new TimedGlideMoveBehavior(bat, new Vector(targetX[i], spawnY), 2, 2),
				emb,
				new TimedGlideMoveBehavior(bat, new Vector(targetX[i], targetY), 3, 3)
			}));
			ShootBehavior sb = getShootBehavior(bat, batColor, EnemyCreationResource.BAT_ENEMY, difficulty);
			if(difficulty==0)
				((BatShootBehaviorEasy) sb).setOrientationToRight(i%2==0);
			bat.setShootBehavior( sb );
			combo[i+1] = new SingleSpawnMobWave(this.stage, 0, bat);
		}
		combo[3] = new VacantMobWave(stage, 9);
		
		return new CombinationMobWave(this.stage, 0, combo);
	}

}
