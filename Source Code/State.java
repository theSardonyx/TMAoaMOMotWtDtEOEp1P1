/**
* The abstract class used for the different states of the game
* 
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
public abstract class State {
	protected StateManager sm;
	protected boolean isActive;
	protected int id;
	
	/*
	Handles all inputs from an InputCollector object, responds with the appropiate method
	@param input: the InputCollector object to check for mouse and keyboard movement
	*/
	public abstract void handleInput (InputCollector input);
	/*
	Updates the current objects given a value, and adjusts positions/states accordingly
	@param delta: used to update objects based from time passed
	*/
	public abstract void update (double delta);
	/*
	Renders all compatible objects into this window
	@param rw: the RenderWindow object where objects will be rendered/drawn
	*/
	public abstract void render (RenderWindow rw);
	
	/*
	Method for popping a State from the state stack
	@param level: Detects the level of this certain state from the state stack
	@param string: Name of the state to be popped
	*/
	public void popSelf (int level, String s) {
		sm.pop(level, s);
	}
	
	/*
	Method for putting a State to active state
	@param string: Name of the state to be popped
	*/
	public void onActivate (String s) {
		if(s != null && s.equals("reset"))
			this.init();
		isActive = true;
	}
	/*
	Method for putting a State to inactive state
	*/
	public void onDeactivate () {
		isActive = false;
	}
	
	/*
	Method for determing the StateManager object
	@param sm: The StateManager object to bes used with the state stack
	*/
	public void setStateManager(StateManager sm) {
		this.sm = sm;
	}
	/*
	Method to get the ID for a certain State object
	*/
	public int getID() {
		return id;
	}
	/*
	Method to set the ID for a certain State object
	@param id: value for the ID for a State object
	*/
	public void setID(int id) {
		this.id = id;
	}
	/*
	Method to determine whether a State is in active state or not
	*/
	public boolean isActive() {
		return isActive;
	}
	
	public abstract void init();
}