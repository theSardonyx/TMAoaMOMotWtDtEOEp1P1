
public class DespawnRequest implements Request {

	private BulletStage stage;
	private Entity entity;
	
	public DespawnRequest( Entity entity, BulletStage stage ) {
		this.entity = entity;
		this.stage = stage;
	}
	
	@Override
	public void execute() {
		stage.removeEntity( entity );
	}

}
