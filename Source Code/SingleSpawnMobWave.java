
public class SingleSpawnMobWave extends MobWave {

	private SpawnRequest request;
	
	public SingleSpawnMobWave(BulletStage stage, double delay, Entity entity) {
		super(stage, delay, 0.0000000001);
		this.request = new SpawnRequest(entity, stage);
	}

	@Override
	protected void spawn(double delta) {
		stage.addRequest(request);
	}
}
