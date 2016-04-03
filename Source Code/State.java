public abstract class State {
	StateManager sm;
	
	// abstract methods
	protected abstract void handleInput (InputHandler input);
	protected abstract void update (float delta);
	protected abstract void render (Graphics g);
	
	// final method
	public final void work (InputHandler input, Graphics g, float delta) {
		handleInput(input);
		update(delta);
		render(g);
	}
	
	// others
	public void popSelf (inr level, String s);
	public void onActivate (String s);
	public void onDeactivate ();
}