/**
* This class is used to handle different State objects
* 
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
import java.util.*;

public class StateManager {
	private static StateManager instance;
	
	private ArrayList<State> state_list;
	private Stack<State> state_stack;
	private int current_state_id;
	
	/*
	Constructor for a StateManager object 
	Creates an arraylist for the states used
	And a stack for the State handling
	*/
	private StateManager() {
		state_list = new ArrayList<State>();
		state_stack = new Stack<State>();
	}
	/*
	Allows a State in the top of the State stack to executes its handeInput function
	@param ic: the InputCollector object to check for mouse and keyboard movement
	*/
	public void handleInput(InputCollector ic) {
		state_stack.peek().handleInput(ic);
	}
	/*
	Allows a State in the top of the State stack to executes its update function
	@param delta: used to update objects based from time passed
	*/
	public void update(double delta) {
		state_stack.peek().update(delta);
	}
	/*
	Allows a State in the top of the State stack to executes its render function
	@param rw: the RenderWindow object where objects will be rendered/drawn
	*/
	public void render(RenderWindow rw) {
		state_stack.peek().render(rw);
	}
	/*
	Pushes a State to the top of the stack
	Usually used to enter a screen
	Activates the State after it is pushed
	@param id: ID of a State
	@param s: name of the State
	*/
	public void push (int id, String s) {
		if(!state_stack.isEmpty())
			state_stack.peek().onDeactivate();
		state_stack.push( state_list.get( id ) );
		state_stack.peek().onActivate(s);
		state_stack.peek().init();
	}
	/*
	Pops a State from the top of the stack and removes it
	Usually used to exit a screen
	@param id: ID of a State
	@param s: name of the State
	*/
	public void pop (int level, String s) {
		for(int i=0; i<level && !state_stack.isEmpty(); i++) {
			State temp = state_stack.pop();
			temp.onDeactivate();
			state_stack.peek().onActivate(s);
		}
		
		if(!state_stack.isEmpty())
			current_state_id = state_stack.peek().getID();
	}
	/*
	Adds a State to the arraylist of State objects
	@param state: The State to be added on the list
	*/
	public int addState (State state) {
		if(state == null) return -1;
		
		int stateID = state_list.size();
		state_list.add(state);
		state.setStateManager(this);
		state.setID(stateID);
		return stateID;
	}
	/*
	Get the ID of the current active state
	*/
	public int getCurrentState() {
		return current_state_id;
	}
	/*
	Returns a single instance of a StateManager
	Makes sure that only one instance of a StateManager is used on the game
	*/
	public static StateManager getInstance() {
		if(instance == null)
			instance = new StateManager();
		return instance;
	}
}