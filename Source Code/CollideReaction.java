
public abstract class CollideReaction {
	
	private CollideReaction next;
	private Entity entity;

	public CollideReaction(Entity entity) {
		this.entity = entity;
	}
	
	public final void collide(Entity other) {
		react(other);
		
		if(next!=null)
			next.collide(other);
	}
	
	public void addNext(CollideReaction next) {
		if(this.next==null)
			this.next = next;
		else
			this.next.addNext(next);
	}
	
	protected abstract void react(Entity other);
}
