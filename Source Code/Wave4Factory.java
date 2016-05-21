import java.awt.Color;


public class Wave4Factory extends MobWaveFactory {

	public Wave4Factory(BulletStage stage) {
		super(stage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MobWave getMobWave(int difficulty) {

		MobWave[] queue = new MobWave[9];
		Color eyeColor = getRandomColor(EnemyCreationResource.EYE_ENEMY, difficulty);
		Color batColor = getRandomColor(EnemyCreationResource.BAT_ENEMY, difficulty);
		
		int[] spawnX = {-100, -100, -100, -100, 820, 820, 820, 820};
		int[] targetX = {820, 820, 820, 820, -100, -100, -100, -100};
		int[] posY = {240, 240, 240, 240, 160, 160, 160, 160};
		
		for(int i=0; i<8; i++)
		{
			Entity p;
			Vector init = new Vector(spawnX[i], posY[i]);
			Vector target = new Vector(targetX[i], posY[i]);
			if(i % 2 == 0)
			{
				p = new BatEnemy(init, this.stage, batColor);
				ShootBehavior sb = getShootBehavior(p, batColor, EnemyCreationResource.BAT_ENEMY, difficulty);
				sb.setFireRate(2.5);
				p.setShootBehavior(sb);
			}
			else
			{
				p = new EyeEnemy(init, this.stage, eyeColor);
				ShootBehavior sb = getShootBehavior(p, eyeColor, EnemyCreationResource.EYE_ENEMY, difficulty);
				sb.setFireRate(2.5);
				p.setShootBehavior(sb);
			}
			p.setMoveBehavior(new TimedGlideMoveBehavior(p, target, 4, 4));
			queue[i] = new SingleSpawnMobWave(this.stage, 0.5, p);
		}
		queue[8] = new VacantMobWave(this.stage, 4);
		
		return new QueueMobWave(this.stage, 0, queue);
	}

}
