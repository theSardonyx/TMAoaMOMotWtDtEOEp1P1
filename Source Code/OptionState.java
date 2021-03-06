import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
* This class represents the Option screen of the game
* 
* @see	State
*
* @author	Kryzl Pascual
*/
public class OptionState extends State {
	/**
	* Buttons that will toggle settings
	*
	* @see	DrawableButton
	*/
	DrawableButton btnUp, btnDown, btnLeft, btnRight, btnFocus, btnShoot, btnPause, btnFrameRate, btnParticles;
	
	/**
	 * Button that will revert settings to default
	 * 
	 * @see	DrawableButton
	 */
	DrawableButton btnRestore;
	
	/**
	* The button that will exit the Options screen
	*
	* @see	DrawableButton
	*/
	DrawableButton btnBack;
	
	/**
	* The Screen title and labels for the buttons on the screen
	*
	* @see	DrawableString
	*/
	DrawableString label, lblKeys, lblMisc, lblUp, lblDown, lblLeft, lblRight, lblFocus, lblShoot, lblPause, lblFrameRate, lblParticles;
	
	/**
	* The Config object that contains all settings
	*
	* @see	Config
	*/
	Config curr;
	
	/**
	* Check if a button was pressed and is waiting for a key event
	*/
	boolean pressed = false;
	
	/**
	* The String representation of the button waiting for a key event
	*/
	String text = "";
	
	/**
	* Creates a new Config class to save settings in or reads from
	* an existing config file and defines the displayed values for
	* each setting
	*
	* @see	Config
	* @see	FontLoader
	* @see	DrawableString
	* @see	DrawableButton
	*/
	public OptionState() {
		curr = Config.getInstance();
		
		Font font = FontLoader.getInstance().getFont ("Press Start 2P", Font.PLAIN, 20);
		Vector padding = new Vector (10, 10);
		
		lblUp = new DrawableString (new Vector(150, 250), "Up   ", font, Color.WHITE);
		lblDown = new DrawableString (new Vector(150, 300), "Down ", font, Color.WHITE);
		lblLeft = new DrawableString (new Vector(150, 350), "Left ", font, Color.WHITE);
		lblRight = new DrawableString (new Vector(150, 400), "Right", font, Color.WHITE);
		lblFocus = new DrawableString (new Vector(150, 450), "Focus", font, Color.WHITE);
		lblShoot = new DrawableString (new Vector(150, 500), "Shoot", font, Color.WHITE);
		lblPause = new DrawableString (new Vector(150, 550), "Pause", font, Color.WHITE);
		
		btnUp = new DrawableButton (new Vector (350, 250), KeyEvent.getKeyText (curr.getUpKey()).toUpperCase(), font, padding, Color.WHITE);
		btnDown = new DrawableButton (new Vector (350, 300), KeyEvent.getKeyText (curr.getDownKey()).toUpperCase(), font, padding, Color.WHITE);
		btnLeft = new DrawableButton (new Vector (350, 350), KeyEvent.getKeyText (curr.getLeftKey()).toUpperCase(), font, padding, Color.WHITE);
		btnRight = new DrawableButton (new Vector (350, 400), KeyEvent.getKeyText (curr.getRightKey()).toUpperCase(), font, padding, Color.WHITE);
		btnFocus = new DrawableButton (new Vector (350, 450), KeyEvent.getKeyText (curr.getFocusKey()).toUpperCase(), font, padding, Color.WHITE);
		btnShoot = new DrawableButton (new Vector (350, 500), KeyEvent.getKeyText (curr.getShootKey()).toUpperCase(), font, padding, Color.WHITE);
		btnPause = new DrawableButton (new Vector (350, 550), KeyEvent.getKeyText (curr.getPauseKey()).toUpperCase(), font, padding, Color.WHITE);
		
		lblKeys = new DrawableString (new Vector (Runner.RES_WIDTH / 4, 200), "Controls", font, Color.WHITE);
		lblMisc = new DrawableString (new Vector (3 * (Runner.RES_WIDTH / 4), 200), "Misc Settings", font, Color.WHITE);
		
		lblFrameRate = new DrawableString (new Vector ((Runner.RES_WIDTH /2) + 150, 250), "Frame Rate", font, Color.WHITE);
		lblParticles = new DrawableString (new Vector ((Runner.RES_WIDTH /2) + 150, 300), "Particles ", font, Color.WHITE);
		
		btnFrameRate = new DrawableButton (new Vector (Runner.RES_WIDTH - 100, 250), "" + curr.getFrameRate(), font, padding, Color.WHITE);
		btnParticles = new DrawableButton (new Vector (Runner.RES_WIDTH - 100, 300), "", font, padding, Color.WHITE);
		if (curr.getParticles())
			btnParticles.setText ("ON");
		else btnParticles.setText ("OFF");
		
		btnRestore = new DrawableButton (new Vector (3 * (Runner.RES_WIDTH / 4), 550), "Restore Defaults", font, padding, Color.WHITE);
		
		btnBack = new DrawableButton (new Vector (Runner.RES_WIDTH / 2, 650), "Back", font, padding, Color.WHITE);
		
		font = font.deriveFont (Font.PLAIN, 40);
		label = new DrawableString (new Vector (Runner.RES_WIDTH / 2, 100), "Options", font, Color.WHITE);
	}

	/**
	* Handles all inputs from an InputCollector object, responds
	* with the appropriate method
	*
	* @param	input	The InputCollector object to check for mouse
	* 					and keyboard movement
	* 
	* @see	InputCollector
	* @see	Config
	* @see	DrawableButton#isCollidingWith
	* @see	State#popSelf
	* @see	#pressed
	* @see	#text
	*/
	@Override
	public void handleInput(InputCollector input) {
		btnUp.update (input.getMousePosition());
		btnDown.update (input.getMousePosition());
		btnLeft.update (input.getMousePosition());
		btnRight.update (input.getMousePosition());
		btnFocus.update (input.getMousePosition());
		btnShoot.update (input.getMousePosition());
		btnPause.update (input.getMousePosition());
		
		btnFrameRate.update (input.getMousePosition());
		btnParticles.update (input.getMousePosition());
		
		btnRestore.update (input.getMousePosition());
		
		btnBack.update (input.getMousePosition());
		
		if (pressed && input.isKeyPressed()) {
			int code = input.getKeyCodesPressed()[0];
			switch (text) {
				case "Up" :
					curr.setUpKey (code);
					btnUp.setText (KeyEvent.getKeyText (code).toUpperCase());
					pressed = false;
					text = "";
					break;
				case "Down" :
					curr.setDownKey (code);
					btnDown.setText (KeyEvent.getKeyText (code).toUpperCase());
					pressed = false;
					text = "";
					break;
				case "Left" :
					curr.setLeftKey (code);
					btnLeft.setText (KeyEvent.getKeyText (code).toUpperCase());
					pressed = false;
					text = "";
					break;
				case "Right" :
					curr.setRightKey (code);
					btnRight.setText (KeyEvent.getKeyText (code).toUpperCase());
					pressed = false;
					text = "";
					break;
				case "Focus" :
					curr.setFocusKey (code);
					btnFocus.setText (KeyEvent.getKeyText (code).toUpperCase());
					pressed = false;
					text = "";
					break;
				case "Shoot" :
					curr.setShootKey (code);
					btnShoot.setText (KeyEvent.getKeyText (code).toUpperCase());
					pressed = false;
					text = "";
					break;
				case "Pause" :
					curr.setPauseKey (code);
					btnPause.setText (KeyEvent.getKeyText (code).toUpperCase());
					pressed = false;
					text = "";
					break;
			}
		}
		
		if (input.getMouseReleased (InputCollector.MOUSE_BUTTON1)) {
			pressed = !pressed;
			if (!pressed)
				updateText();
				
			if (btnUp.isCollidingWith (input.getMousePosition())) {
				if (pressed) {
					text = "Up";
					btnUp.setText ("Press Any Key");
				} else text = "";
			} else if (btnDown.isCollidingWith (input.getMousePosition())) {
				if (pressed) {
					text = "Down";
					btnDown.setText ("Press Any Key");
				} else text = "";
			} else if (btnLeft.isCollidingWith (input.getMousePosition())) {
				if (pressed) {
					text = "Left";
					btnLeft.setText ("Press Any Key");
				} else text = "";
			} else if (btnRight.isCollidingWith (input.getMousePosition())) {
				if (pressed) {
					text = "Right";
					btnRight.setText ("Press Any Key");
				} else text = "";
			} else if (btnFocus.isCollidingWith (input.getMousePosition())) {
				if (pressed) {
					text = "Focus";
					btnFocus.setText ("Press Any Key");
				} else text = "";
			} else if (btnShoot.isCollidingWith (input.getMousePosition())) {
				if (pressed) {
					text = "Shoot";
					btnShoot.setText ("Press Any Key");
				} else text = "";
			} else if (btnPause.isCollidingWith (input.getMousePosition())) {
				if (pressed) {
					text = "Pause";
					btnPause.setText ("Press Any Key");
				} else text = "";
			} else if (btnFrameRate.isCollidingWith (input.getMousePosition())) {
				curr.toggleFrameRate();
				btnFrameRate.setText ("" + curr.getFrameRate());
			} else if (btnParticles.isCollidingWith (input.getMousePosition())) {
				curr.toggleParticles();
				if (curr.getParticles())
					btnParticles.setText ("ON");
				else btnParticles.setText ("OFF");
			} else if (btnRestore.isCollidingWith (input.getMousePosition())) {
				if (curr.getFrameRate() != 60) {
					if (curr.getFrameRate() == 120)
						curr.toggleFrameRate();
					curr.toggleFrameRate();
				}
				btnFrameRate.setText ("" + curr.getFrameRate());
				
				if (!curr.getParticles())
					curr.toggleParticles();
				btnParticles.setText ("ON");
				
				curr.setUpKey (KeyEvent.VK_UP);
				curr.setDownKey (KeyEvent.VK_DOWN);
				curr.setLeftKey (KeyEvent.VK_LEFT);
				curr.setRightKey (KeyEvent.VK_RIGHT);
				curr.setFocusKey (KeyEvent.VK_SHIFT);
				curr.setShootKey (KeyEvent.VK_Z);
				curr.setPauseKey (KeyEvent.VK_P);
				
				updateText();
			} else if (btnBack.isCollidingWith (input.getMousePosition())) {
				ObjectMapper mapper = new ObjectMapper();
				
				try {
					mapper.writeValue (new File ("config.json"), curr);
				} catch (JsonGenerationException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				popSelf(1, null);
			}
		}
	}
	
	/**
	 * Updates the text on relevant buttons to mirror any changes made to the Config class
	 */
	public void updateText() {
		btnUp.setText (KeyEvent.getKeyText (curr.getUpKey()).toUpperCase());
		btnDown.setText (KeyEvent.getKeyText (curr.getDownKey()).toUpperCase());
		btnLeft.setText (KeyEvent.getKeyText (curr.getLeftKey()).toUpperCase());
		btnRight.setText (KeyEvent.getKeyText (curr.getRightKey()).toUpperCase());
		btnFocus.setText (KeyEvent.getKeyText (curr.getFocusKey()).toUpperCase());
		btnShoot.setText (KeyEvent.getKeyText (curr.getShootKey()).toUpperCase());
		btnPause.setText (KeyEvent.getKeyText (curr.getPauseKey()).toUpperCase());
	}
	
	/**
	* Updates the current objects given a value, and adjusts positions/states accordingly
	* 
	* @param	delta	The time passed
	*/
	@Override
	public void update(double delta) {
		// TODO Auto-generated method stub

	}
	
	/**
	* Renders all compatible objects into this window
	* 
	* @param	rw	The RenderWindow object where objects will be rendered/drawn
	* 
	* @see	RenderWindow
	*/
	@Override
	public void render(RenderWindow rw) {
		rw.draw (label);
		rw.draw (lblKeys);
		rw.draw (lblMisc);
		rw.draw (btnBack);
		
		rw.draw (lblUp);
		rw.draw (lblDown);
		rw.draw (lblLeft);
		rw.draw (lblRight);
		rw.draw (lblFocus);
		rw.draw (lblShoot);
		rw.draw (lblPause);
		
		rw.draw (btnUp);
		rw.draw (btnDown);
		rw.draw (btnLeft);
		rw.draw (btnRight);
		rw.draw (btnFocus);
		rw.draw (btnShoot);
		rw.draw (btnPause);
		
		rw.draw (lblFrameRate);
		rw.draw (lblParticles);
		
		rw.draw (btnFrameRate);
		rw.draw (btnParticles);
		
		rw.draw (btnRestore);
	}

	@Override
	public void init() {}
}
