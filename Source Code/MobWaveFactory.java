import java.awt.Color;


public abstract class MobWaveFactory {
	protected EnemyCreationResource resource;
	protected BulletStage stage;
	
	public MobWaveFactory(BulletStage stage)
	{
		this.stage = stage;
		this.resource = new EnemyCreationResource(stage);
	}
	
	public abstract MobWave getMobWave(int difficulty);
	
	public Color getRandomColor(int enemyType, int difficulty)
	{
		return resource.getRandomColor(enemyType, difficulty);
	}
	
	public ShootBehavior getShootBehavior(Entity subject, Color color, int enemyType, int difficulty)
	{
		return resource.getShootBehavior(subject, color, enemyType, difficulty);
	}
	
}
