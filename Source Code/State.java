public abstract class State {
	protected StateManager sm;
	protected boolean isActive;
	protected int id;
	
	// abstract methods
	public abstract void handleInput (InputCollector input);
	public abstract void update (double delta);
	public abstract void render (RenderWindow rw);
	
	// others
	public void popSelf (int level, String s) {
		sm.pop(level, s);
	}
	
	public void onActivate (String s) {
		isActive = true;
	}
	
	public void onDeactivate () {
		isActive = false;
	}
	
	public void setStateManager(StateManager sm) {
		this.sm = sm;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public boolean isActive() {
		return isActive;
	}
}