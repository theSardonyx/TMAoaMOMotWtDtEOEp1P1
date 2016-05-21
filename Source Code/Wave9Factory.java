import java.awt.Color;


public class Wave9Factory extends MobWaveFactory {

	public Wave9Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] queue = new MobWave[7];
		Color eyeColor = getRandomColor(EnemyCreationResource.EYE_ENEMY, difficulty);
		
		int[] spawnX = {-100, 820, -100, 820, -100, 820};
		int spawnY = 50;
		int[] centerX = {-100, 820, -100, 820, -100, 820};
		int centerY = 360;
		
		for(int i=0; i<6; i++)
		{
			EyeEnemy eye = new EyeEnemy(new Vector(spawnX[i], spawnY), this.stage, eyeColor);
			EllipseMoveBehavior mb = new EllipseMoveBehavior(eye, new Vector(centerX[i], centerY), 620, 1500, 15, 7.5);
			mb.update();
			if( i%2!=0 )
				mb.reverse();
			eye.setMoveBehavior(mb);
			ShootBehavior sb = getShootBehavior(eye, eyeColor, EnemyCreationResource.EYE_ENEMY, difficulty);
			if(difficulty == 1) {
				((EyeShootBehaviorMedium) sb).setHeight(20);
				((EyeShootBehaviorMedium) sb).setWithStraight(true);
			}
			sb.setFireRate(2.6);
			eye.setShootBehavior(sb);
			queue[i] = new SingleSpawnMobWave(this.stage, 2, eye);
		}
		queue[6] = new VacantMobWave(this.stage, 7.5);
		
		return new QueueMobWave(this.stage, 0, queue);
	}

}
