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
	
	public InputCollector(Component component) {
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
			}
			else if(event.getID()== InputCollector.MOUSE_PRESSED) {
				MouseEvent me = (MouseEvent) event;
				mousePressed[ me.getButton() ] = true;
			}
			else if(event.getID()== InputCollector.MOUSE_RELEASED) {
				MouseEvent me = (MouseEvent) event;
				mouseReleased[ me.getButton() ] = true;
			}
			else if(event.getID()== InputCollector.MOUSE_MOVED)
				mouseMoved = true;
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
	
	public boolean getMouseClicked(int button) {
		return mouseClicked[button];
	}

	public boolean getMouseDragged(int button) {
		return mouseDragged[button];
	}

	public boolean getMouseReleased(int button) {
		return mouseReleased[button];
	}

	public boolean getMousePressed(int button) {
		return mousePressed[button];
	}

	public boolean isMouseMoved() {
		return mouseMoved;
	}

	public boolean isMouseEntered() {
		return mouseEntered;
	}

	public boolean isMouseExited() {
		return mouseExited;
	}

	public boolean isKeyTyped(char key) {
		return keyTyped.contains(key);
	}

	public boolean isKeyPressed(int keycode) {
		return keyPressed.contains(keycode);
	}

	public boolean isKeyReleased(int keycode) {
		return keyReleased.contains(keycode);
	}

	public boolean hasTyped() {
		return hasTyped;
	}
	
	public int getBackspaces() {
		System.out.println(backspaced);
		return backspaced;
	}

	public String getTypedContent() {
		return typedContent;
	}
	
	public void clearTypedContent() {
		typedContent = "";
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		offer(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		offer(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		offer(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		offer(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		offer(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		offer(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		offer(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		offer(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		offer(e);	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		offer(e);
	}
	
	private void offer(InputEvent e) {
		if(processing)
			altEventPoll.add(e);
		else
			eventPoll.add(e);
	}
}
