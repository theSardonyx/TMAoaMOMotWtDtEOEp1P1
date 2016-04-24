import java.awt.Component;
import java.awt.event.*;
import java.util.*;

import javax.swing.event.MouseInputListener;

public class InputCollector implements MouseInputListener, KeyListener{
	
	private Queue<InputEvent> eventPoll, altEventPoll;
	private boolean processing;
	private boolean[] mouseClicked, mouseDragged, mouseReleased, mousePressed;
	private boolean mouseMoved, mouseEntered, mouseExited;
	private HashSet<Integer> keyPressed, keyReleased;
	private HashSet<Character> keyTyped;
	private boolean hasTyped;
	private int backspaced;
	private String typedContent;
	private Vector mousePosition;
	
	public static final int MOUSE_BUTTON1 = MouseEvent.BUTTON1;
	public static final int MOUSE_BUTTON2 = MouseEvent.BUTTON2;
	public static final int MOUSE_BUTTON3 = MouseEvent.BUTTON3;
	
	public static final int MOUSE_CLICKED = MouseEvent.MOUSE_CLICKED;
	public static final int MOUSE_PRESSED = MouseEvent.MOUSE_PRESSED;
	public static final int MOUSE_RELEASED = MouseEvent.MOUSE_RELEASED;
	public static final int MOUSE_ENTERED = MouseEvent.MOUSE_ENTERED;
	public static final int MOUSE_EXITED = MouseEvent.MOUSE_EXITED;
	public static final int MOUSE_DRAGGED = MouseEvent.MOUSE_DRAGGED;
	public static final int MOUSE_MOVED = MouseEvent.MOUSE_MOVED;
	
	public static final int KEY_TYPED = KeyEvent.KEY_TYPED;
	public static final int KEY_PRESSED = KeyEvent.KEY_PRESSED;
	public static final int KEY_RELEASED = KeyEvent.KEY_RELEASED;
	
	/*
	Contructor for an InputCollector object, given a Component instance
	Initilization of all instantiated arrays, hashsets and dequeues
	@param component: Component object used to gather the input types 
	*/
	
	public InputCollector(Component component) {
		mousePosition = new Vector(0, 0);
		
		mouseClicked = new boolean[4];
		mouseDragged = new boolean[4];
		mouseReleased = new boolean[4];
		mousePressed = new boolean[4];
		mouseMoved = mouseEntered = mouseExited = false;
		typedContent = "";
		
		keyTyped = new HashSet<Character>();
		keyPressed = new HashSet<Integer>();
		keyReleased = new HashSet<Integer>();
		
		eventPoll = new ArrayDeque<InputEvent>();
		altEventPoll = new ArrayDeque<InputEvent>();
		
		processing = false;
		
		component.addMouseListener(this);
		component.addMouseMotionListener(this);
		component.addKeyListener(this);
	}
	
	/*
	Basically, this function takes in all of the inputs of both mouse and keyboard. 
	All of them are put into an event queue, and the queue is polled, 
	and the corresponding input is used to determine the aciton taken
	*/
	public void preProcess() {
		processing = true;
		
		while(!eventPoll.isEmpty()) {
			InputEvent event = eventPoll.poll();
			if(event.getID()== InputCollector.MOUSE_CLICKED) {
				MouseEvent me = (MouseEvent) event;
				mouseClicked[ me.getButton() ] = true;
			}
			else if(event.getID()== InputCollector.MOUSE_DRAGGED) {
				MouseEvent me = (MouseEvent) event;
				mouseDragged[ me.getButton() ] = true;
				mousePosition = new Vector( me.getX(), me.getY() );
			}
			else if(event.getID()== InputCollector.MOUSE_PRESSED) {
				MouseEvent me = (MouseEvent) event;
				mousePressed[ me.getButton() ] = true;
			}
			else if(event.getID()== InputCollector.MOUSE_RELEASED) {
				MouseEvent me = (MouseEvent) event;
				mouseReleased[ me.getButton() ] = true;
			}
			else if(event.getID()== InputCollector.MOUSE_MOVED) {
				mouseMoved = true;
				
				MouseEvent me = (MouseEvent) event;
				mousePosition = new Vector( me.getX(), me.getY() );
			}
			else if(event.getID()== InputCollector.MOUSE_ENTERED)
				mouseEntered = true;
			else if(event.getID()== InputCollector.MOUSE_EXITED)
				mouseExited = true;
			else if(event.getID()== InputCollector.KEY_TYPED) {
				
				KeyEvent ke = (KeyEvent) event;
				char typed = ke.getKeyChar();
				keyTyped.add( typed );
				hasTyped = true;
				
				if(typed=='\b') 
				{
					if(typedContent.length() > 0)
						typedContent = typedContent.substring(0, typedContent.length()-1);
					else
						backspaced++;
				}
				else
					typedContent += ke.getKeyChar();
			}
			else if(event.getID()== InputCollector.KEY_PRESSED) {
				
				KeyEvent ke = (KeyEvent) event;
				if(!isKeyPressed( ke.getKeyCode() ))
					keyPressed.add( ke.getKeyCode() );
			}
			else if(event.getID()== InputCollector.KEY_RELEASED) {
				
				KeyEvent ke = (KeyEvent) event;
				
				keyReleased.add( ke.getKeyCode() );
				if(isKeyPressed( ke.getKeyCode() ))
					keyPressed.remove( ke.getKeyCode() );
			}
		}
	}
	/*
	Reverts everything back to initialization
	*/
	public void postProcess() {
		eventPoll.clear();
		keyReleased.clear();
		keyTyped.clear();
		hasTyped = false;
		backspaced = 0;
		
		mouseClicked[1] = mouseClicked[2] = mouseClicked[3] = false;
		mouseDragged[1] = mouseDragged[2] = mouseDragged[3] = false;
		mouseReleased[1] = mouseReleased[2] = mouseReleased[3] = false;
		mousePressed[1] = mousePressed[2] = mousePressed[3] = false;
		mouseMoved = mouseEntered = mouseExited = false;
		
		Queue<InputEvent> temp = eventPoll;
		eventPoll = altEventPoll;
		altEventPoll = temp;
		processing = false;
	}
	/*
	Method for determining if mouse was clicked, given which button
	@param button the id of the button that was clicked
	*/
	public boolean getMouseClicked(int button) {
		return mouseClicked[button];
	}
	/*
	Method for determining if mouse was dragged, given which button
	@param button the id of the button that was dragged
	*/
	public boolean getMouseDragged(int button) {
		return mouseDragged[button];
	}
	/*
	Method for determining if mouse was released, given which button
	@param button the id of the button that was released
	*/
	public boolean getMouseReleased(int button) {
		return mouseReleased[button];
	}
	/*
	Method for determining if mouse was pressed, given which button
	@param button the id of the button that was pressed
	*/
	public boolean getMousePressed(int button) {
		return mousePressed[button];
	}
	/*
	Method used for returning a boolean value when the mouse is moved
	*/
	public boolean isMouseMoved() {
		return mouseMoved;
	}
	/*
	Method used for returning a boolean value when the mouse is entered
	*/
	public boolean isMouseEntered() {
		return mouseEntered;
	}
	/*
	Method used for returning a boolean value when the mouse is exited
	*/
	public boolean isMouseExited() {
		return mouseExited;
	}
	/*
	Method for determining the mouse position
	*/
	public Vector getMousePosition() {
		return mousePosition;
	}
	/*
	Method for determining which is key was typed
	Limited to only characters
	*/
	public boolean isKeyTyped(char key) {
		return keyTyped.contains(key);
	}
	/*
	Method for determining which key was pressed
	All keys in the keyboard included
	*/
	public boolean isKeyPressed(int keycode) {
		return keyPressed.contains(keycode);
	}
	/*
	Method for determining which key was released
	All keys in the keyboard included
	*/
	public boolean isKeyReleased(int keycode) {
		return keyReleased.contains(keycode);
	}
	/*
	Method used to determine whether the player has typed
	*/
	public boolean hasTyped() {
		return hasTyped;
	}
	/*
	Method for determining if the uses uses Backspace
	*/
	public int getBackspaces() {
		System.out.println(backspaced);
		return backspaced;
	}
	/*
	Method for getting the typed text
	*/
	public String getTypedContent() {
		return typedContent;
	}
	/*
	Method for clearing out the typed content
	*/
	public void clearTypedContent() {
		typedContent = "";
	}
	/*
	Overrided method for the default mouseClicked
	@param e: MouseEvent object to be put on queue
	*/
	@Override
	public void mouseClicked(MouseEvent e) {
		offer(e);
	}
	/*
	Overrided method for the default mousePressed
	@param e: MouseEvent object to be put on queue
	*/
	@Override
	public void mousePressed(MouseEvent e) {
		offer(e);
	}
	/*
	Overrided method for the default mouseReleased
	@param e: MouseEvent object to be put on queue
	*/
	@Override
	public void mouseReleased(MouseEvent e) {
		offer(e);
	}
	/*
	Overrided method for the default mouseEntered
	@param e: MouseEvent object to be put on queue
	*/
	@Override
	public void mouseEntered(MouseEvent e) {
		offer(e);
	}
	/*
	Overrided method for the default mouseExited
	@param e: MouseEvent object to be put on queue
	*/
	@Override
	public void mouseExited(MouseEvent e) {
		offer(e);
	}
	/*
	Overrided method for the default mouseDragged
	@param e: MouseEvent object to be put on queue
	*/
	@Override
	public void mouseDragged(MouseEvent e) {
		offer(e);
	}
	/*
	Overrided method for the default mouseMoved
	@param e: MouseEvent object to be put on queue
	*/
	@Override
	public void mouseMoved(MouseEvent e) {
		offer(e);
	}
	/*
	Overrided method for the default keyTyped
	@param e: KeyEvent object to be put on queue
	*/
	@Override
	public void keyTyped(KeyEvent e) {
		offer(e);
	}
	/*
	Overrided method for the default keyPressed
	@param e: KeyEvent object to be put on queue
	*/
	@Override
	public void keyPressed(KeyEvent e) {
		offer(e);	
	}
	/*
	Overrided method for the default keyReleased
	@param e: KeyEvent object to be put on queue
	*/
	@Override
	public void keyReleased(KeyEvent e) {
		offer(e);
	}
	/*
	Method used to add an event in the event queue
	@param e: InputEvent object to be put on queue
	*/
	private void offer(InputEvent e) {
		if(processing)
			altEventPoll.add(e);
		else
			eventPoll.add(e);
	}
	/*
	Method for returning a true value if a key was pressed
	*/
	public boolean isKeyPressed() {
		return !keyPressed.isEmpty();
	}
	/*
	Method for returning a true value if a key was typed or pressed once
	*/
	public boolean isKeyTyped() {
		return !keyTyped.isEmpty();
	}
	/*
	Method for returning a true value if a key was released
	*/
	public boolean isKeyReleased() {
		return !keyReleased.isEmpty();
	}
	
}
