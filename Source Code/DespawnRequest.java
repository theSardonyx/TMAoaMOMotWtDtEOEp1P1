/*
* This class represents a Request sent to a BulletStage to despawn
* an Entity
*
* @see	Request
*
* @author	Aemielvin Loremia
*/
public class DespawnRequest implements Request {
	/*
	* The BulletStage to send the Request to
	*
	* @see BulletStage
	*/
	private BulletStage stage;
	
	/*
	* The Entity that is "sending" the Request
	*
	* @see Entity
	*/
	private Entity entity;
	
	/*
	* Creates a new DespawnRequest "sent" by the specified Entity
	* to the specified BulletStage
	*
	* @param	entity	The Entity that is "sending" the Request
	* @param	stage	The BulletStage to send the Request to
	*
	* @see	Entity
	* @see	BulletStage
	*/
	public DespawnRequest( Entity entity, BulletStage stage ) {
		this.entity = entity;
		this.stage = stage;
	}
	
	/*
	* Removes #entity from the list of Entities in #stage
	*
	* @see	BulletStage#removeEntity
	*/
	@Override
	public void execute() {
		stage.removeEntity( entity );
	}

}
