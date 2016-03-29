public abstract class Entity {
	StateManager sm;
	MoveBehavior move_behave;
	ShootBehavior shoot_behave;
	private Vector position;
	private Vector showSize;
	private Vector hitSize;
	
	// abstract methods
	public abstract void handleInput (InputHandler  input);
	public abstract void render (Graphics g);
	public abstract void update (float dt);
	
	// final methods
	public final void move (float dt);
	public final void shoot();
	
	// setters
	public void setShowSize (Vector v);
	public void setHitSize (Vector v);
	public void setPosition (Vector v);
	
	// getters
	public Vector getShowSize();
	public Vector getHitSize();
	public Vector getPosition();
	public getPointClosestTo (Vector target);
}