import java.awt.Color;


public class Wave14Factory extends MobWaveFactory {

	public Wave14Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[][] queue = new MobWave[2][4];
		
		Color batColor = getRandomColor(EnemyCreationResource.BAT_ENEMY, difficulty);
		
		int[] spawnX = { 820, -100 };
		int spawnY = 410;
		Vector center = new Vector(360, 410);
		
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<2; j++)
			{
				
				BatEnemy bat = new BatEnemy(new Vector(spawnX[j], spawnY), this.stage, batColor);
				bat.setMoveBehavior(new EllipseMoveBehavior( bat, center, 600, 800, 10, 5));
				if(j == 0)
					((EllipseMoveBehavior) bat.getMoveBehavior()).reverse();
				else
					((EllipseMoveBehavior) bat.getMoveBehavior()).setRotation(Math.PI);
				
				bat.setShootBehavior( getShootBehavior(bat, batColor, EnemyCreationResource.BAT_ENEMY, difficulty) );
				bat.getShootBehavior().setFireRate(1.25 + Math.random()*0.75);
				if(difficulty == 0)
					((BatShootBehaviorEasy) bat.getShootBehavior()).setHeight(35);
				else if(difficulty == 2)
					((BatShootBehaviorHard) bat.getShootBehavior()).setHeight(35);
				
				queue[j][i] = new SingleSpawnMobWave(this.stage, 0.5, bat);
			}
		}
		
		MobWave[] combo = new MobWave[4];
		combo[0] = new QueueMobWave(this.stage, 0, queue[0]);
		combo[1] = new QueueMobWave(this.stage, 0, queue[1]);
		
		Color pixieColor = getRandomColor(EnemyCreationResource.PIXIE_ENEMY, difficulty);
		PixieEnemy pixie = new PixieEnemy(new Vector(360, -100), this.stage, pixieColor);
		pixie.setMoveBehavior(new QueueMoveBehavior(pixie, new MoveBehavior[] {
				new TimedGlideMoveBehavior(pixie, new Vector(360, 300), 3, 10),
				new TimedGlideMoveBehavior(pixie, pixie.getPosition(), 3, 3)
		}));
		pixie.setShootBehavior( getShootBehavior(pixie, pixieColor, EnemyCreationResource.PIXIE_ENEMY, difficulty) );
		combo[2] = new SingleSpawnMobWave(this.stage, 0, pixie);
		
		combo[3] = new VacantMobWave(this.stage, 15);
		
		return new CombinationMobWave(this.stage, 0, combo);
	}

}
