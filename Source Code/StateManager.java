import java.util.ArrayList;
import java.util.Stack;

public class StateManager {
	private ArrayList<State> state_list;
	private Stack<State> state_stack;
	private int current_state_id;
	private StateManager instance;
	
	private StateManager() {
		// default constructor
	}
	
	public void final work (InputHandler input, Graphics g, float delta) {
		// stuff
	}
	
	public void push (int id, String s) {
		// stuff
	}
	
	public void pop (int level, String s) {
		// stuff
	}
	
	public int addState (int level, String s) {
		// stuff
	}
	
	public int getCurrentState() {
		return current_state_id;
	}
	
	public static StateManager getInstance() {
		return instance;
	}
}