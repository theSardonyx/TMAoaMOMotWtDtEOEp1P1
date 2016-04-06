import java.awt.Graphics2D;
import java.util.*;

public class StateManager {
	private static StateManager instance;
	
	private ArrayList<State> state_list;
	private Stack<State> state_stack;
	private int current_state_id;
	
	private StateManager() {
		state_list = new ArrayList<State>();
		state_stack = new Stack<State>();
	}
	
	public void handleInput(InputCollector ic) {
		state_stack.peek().handleInput(ic);
	}
	
	public void update(double delta) {
		state_stack.peek().update(delta);
	}
	
	public void render(RenderWindow rw) {
		state_stack.peek().render(rw);
	}
	
	public void push (int id, String s) {
		if(!state_stack.isEmpty())
			state_stack.peek().onDeactivate();
		state_stack.push( state_list.get( id ) );
		state_stack.peek().onActivate(s);
	}
	
	public void pop (int level, String s) {
		for(int i=0; i<level && !state_stack.isEmpty(); i++) {
			State temp = state_stack.pop();
			temp.onDeactivate();
			state_stack.peek().onActivate(s);
		}
		
		if(!state_stack.isEmpty())
			current_state_id = state_stack.peek().getID();
	}
	
	public int addState (State state) {
		if(state == null) return -1;
		
		int stateID = state_list.size();
		state_list.add(state);
		state.setStateManager(this);
		state.setID(stateID);
		return stateID;
	}
	
	public int getCurrentState() {
		return current_state_id;
	}
	
	public static StateManager getInstance() {
		if(instance == null)
			instance = new StateManager();
		return instance;
	}
}