import java.awt.Color;


public class Wave15Factory extends MobWaveFactory {

	public Wave15Factory(BulletStage stage) {
		super(stage);
	}

	@Override
	public MobWave getMobWave(int difficulty) {
		MobWave[] queue = new MobWave[4];
		
		Color pixieColor = getRandomColor(EnemyCreationResource.PIXIE_ENEMY, difficulty);
		Vector center = new Vector(360, 0);
		int[] height = {200, 400, 600};
		int width = 920;
		
		for(int i=0; i<3; i++)
		{
			PixieEnemy pixie = new PixieEnemy( new Vector(-820, 0), this.stage, pixieColor);
			pixie.setMoveBehavior( new EllipseMoveBehavior(pixie, center, height[i], width, 10, 5));
			pixie.setShootBehavior( getShootBehavior(pixie, pixieColor, EnemyCreationResource.PIXIE_ENEMY, difficulty) );
			pixie.getShootBehavior().setFireRate(0.075);
			
			double bulletSpeed = 150;
			switch(difficulty)
			{
			case 0:
				((PixieShootBehaviorEasy) pixie.getShootBehavior()).setSpeed(bulletSpeed);
				break;
			case 1:
				((PixieShootBehaviorMedium) pixie.getShootBehavior()).setSpeed(bulletSpeed);
				break;
			case 2:
				((PixieShootBehaviorHard) pixie.getShootBehavior()).getTop().setRotationDelta(Math.PI/8);
				((PixieShootBehaviorHard) pixie.getShootBehavior()).getBottom().setRotationDelta(Math.PI/8);
				((PixieShootBehaviorHard) pixie.getShootBehavior()).getTop().setSpeed(bulletSpeed);
				((PixieShootBehaviorHard) pixie.getShootBehavior()).getBottom().setSpeed(bulletSpeed);
				break;
			}
			
			queue[i] = new SingleSpawnMobWave(this.stage, 0.5, pixie);
		}
		queue[3] = new VacantMobWave(this.stage, 7);
		
		return new QueueMobWave(this.stage, 0, queue);
	}

}
