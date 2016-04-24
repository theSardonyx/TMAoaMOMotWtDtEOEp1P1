/**
* This request class allows an Entity object to be added on the BulletStage
* 
* 
* @see Request
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
public class SpawnRequest implements Request {
	
	private BulletStage stage;
	private Entity entity;
	
	/*
	Constructor for a SpawnRequest object
	Requests for a new Entity to be added on the BulletStage
	@param entity: Entity object to be put on the BulletStage
	@param stage: BulletStage object where Entity objects are to be added
	*/
	public SpawnRequest( Entity entity, BulletStage stage ) {
		this.entity = entity;
		this.stage = stage;
	}
	/*
	Function used to execute a Request instance
	Add the given Entity to a BulletStage
	*/
	@Override
	public void execute() {
		stage.addEntity( entity );
	}

}
