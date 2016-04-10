
public class SpawnRequest implements Request {
	
	private BulletStage stage;
	private Entity entity;
	
	public SpawnRequest( Entity entity, BulletStage stage ) {
		this.entity = entity;
		this.stage = stage;
	}
	
	@Override
	public void execute() {
		stage.addEntity( entity );
	}

}
