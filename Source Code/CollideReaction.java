/**
* This class is a template for the actions done when a collision
* between multiple Entity objects are detected
*
* @author	Aemielvin Loremia
*/
public abstract class CollideReaction {
	/**
	* The CollideReaction that this instance of CollideReaction
	* triggers
	*/
	private CollideReaction next;
	
	/**
	* The Entity that "owns" this CollideReaction
	*
	* @see	Entity
	*/
	private Entity entity;

	/**
	* Creates a CollideReaction object for the specified Entity
	*
	* @param	entity	The Entity for which the new CollideReaction
	*					is for
	*
	* @see	Entity
	*/
	public CollideReaction(Entity entity) {
		this.entity = entity;
	}
	
	/**
	* Makes #entity react on collision with the specified Entity * * and if #next is not null, starts a chain reaction
	*
	* @param	other	The Entity to check for a collision with
	* 
	* @see	#react
	* @see	#next
	*/
	public final void collide(Entity other) {
		react(other);
		
		if(next!=null)
			next.collide(other);
	}
	
	/**
	* Adds a CollideReaction to the chain
	*
	* @param	next	The CollideReaction to be added to the end
	* 					of the chain
	* 
	* @see	#next
	*/
	public void addNext(CollideReaction next) {
		if(this.next==null)
			this.next = next;
		else
			this.next.addNext(next);
	}
	
	/**
	* The template for the actions performed when #entity collides * with the specified Entity
	*
	* @param	other	The Entity to check for a collision with
	*
	* @see	Entity
	*/
	protected abstract void react(Entity other);
}
