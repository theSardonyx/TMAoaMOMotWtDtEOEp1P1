import java.awt.*;
import java.awt.geom.*;
import java.io.*;

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
	DrawableButton btnUp, btnDown, btnLeft, btnRight, btnFocus, btnShoot, btnFrameRate, btnParticles;
	
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
	DrawableString label, lblKeys, lblMisc, lblUp, lblDown, lblLeft, lblRight, lblFocus, lblShoot, lblFrameRate, lblParticles;
	
	/**
	* The Config object that contains all settings
	*
	* @see	Config
	*/
	Config curr;
	
	/**
	* Creates a new Config class to save settings in or reads from
	* an existing config file and defines the displayed values for
	* each setting
	*
	* @see	Config
	* @see	FontLoader
	*/
	public OptionState() {
		curr = new Config();
		//TODO Read from existing settings file
		
		Font font = FontLoader.getInstance().getFont ("Press Start 2P", Font.PLAIN, 20);
		Vector padding = new Vector (10, 10);
		
		lblUp = new DrawableString (new Vector(150, 250), "Up   ", font, Color.WHITE);
		lblDown = new DrawableString (new Vector(150, 300), "Down ", font, Color.WHITE);
		lblLeft = new DrawableString (new Vector(150, 350), "Left ", font, Color.WHITE);
		lblRight = new DrawableString (new Vector(150, 400), "Right", font, Color.WHITE);
		lblFocus = new DrawableString (new Vector(150, 450), "Focus", font, Color.WHITE);
		lblShoot = new DrawableString (new Vector(150, 500), "Shoot", font, Color.WHITE);
		
		btnUp = new DrawableButton (new Vector (350, 250), curr.getUpKey(), font, padding, Color.WHITE);
		btnDown = new DrawableButton (new Vector (350, 300), curr.getDownKey(), font, padding, Color.WHITE);
		btnLeft = new DrawableButton (new Vector (350, 350), curr.getLeftKey(), font, padding, Color.WHITE);
		btnRight = new DrawableButton (new Vector (350, 400), curr.getRightKey(), font, padding, Color.WHITE);
		btnFocus = new DrawableButton (new Vector (350, 450), curr.getFocusKey(), font, padding, Color.WHITE);
		btnShoot = new DrawableButton (new Vector (350, 500), curr.getShootKey(), font, padding, Color.WHITE);
		
		lblKeys = new DrawableString (new Vector (Runner.RES_WIDTH / 4, 200), "Controls", font, Color.WHITE);
		lblMisc = new DrawableString (new Vector (3 * (Runner.RES_WIDTH / 4), 200), "Misc Settings", font, Color.WHITE);
		
		lblFrameRate = new DrawableString (new Vector ((Runner.RES_WIDTH /2) + 150, 250), "Frame Rate", font, Color.WHITE);
		lblParticles = new DrawableString (new Vector ((Runner.RES_WIDTH /2) + 150, 300), "Particles ", font, Color.WHITE);
		
		btnFrameRate = new DrawableButton (new Vector (Runner.RES_WIDTH - 100, 250), "" + curr.getFrameRate(), font, padding, Color.WHITE);
		btnParticles = new DrawableButton (new Vector (Runner.RES_WIDTH - 100, 300), "", font, padding, Color.WHITE);
		if (curr.getParticles())
			btnParticles.setText ("On");
		else btnParticles.setText ("Off");
		
		btnBack = new DrawableButton (new Vector (Runner.RES_WIDTH / 2, 600), "Back", font, padding, Color.WHITE);
		
		font = font.deriveFont (Font.PLAIN, 40);
		label = new DrawableString (new Vector (Runner.RES_WIDTH / 2, 100), "Options", font, Color.WHITE);
	}

	/**
	* Handles all inputs from an InputCollector object, responds
	* with the appropiate method
	*
	* @param	input	The InputCollector object to check for mouse
	* 					and keyboard movement
	*/
	@Override
	public void handleInput(InputCollector input) {
		btnUp.update (input.getMousePosition());
		btnDown.update (input.getMousePosition());
		btnLeft.update (input.getMousePosition());
		btnRight.update (input.getMousePosition());
		btnFocus.update (input.getMousePosition());
		btnShoot.update (input.getMousePosition());
		
		btnFrameRate.update (input.getMousePosition());
		btnParticles.update (input.getMousePosition());
		
		btnBack.update (input.getMousePosition());
		
		if (input.getMouseReleased (InputCollector.MOUSE_BUTTON1)) {
			if (btnUp.isCollidingWith (input.getMousePosition()))
				btnUp.setText ("Pressed");
			else if (btnDown.isCollidingWith (input.getMousePosition()))
				btnDown.setText ("Pressed");
			else if (btnLeft.isCollidingWith (input.getMousePosition()))
				btnLeft.setText ("Pressed");
			else if (btnRight.isCollidingWith (input.getMousePosition()))
				btnRight.setText ("Pressed");
			else if (btnFocus.isCollidingWith (input.getMousePosition()))
				btnFocus.setText ("Pressed");
			else if (btnShoot.isCollidingWith (input.getMousePosition()))
				btnShoot.setText ("Pressed");
			else if (btnFrameRate.isCollidingWith (input.getMousePosition())) {
				curr.toggleFrameRate();
				btnFrameRate.setText ("" + curr.getFrameRate());
			} else if (btnParticles.isCollidingWith (input.getMousePosition())) {
				curr.toggleParticles();
				if (curr.getParticles())
					btnParticles.setText ("On");
				else btnParticles.setText ("Off");
			} else if (btnBack.isCollidingWith (input.getMousePosition())) {
				//TODO Save Config object into external file
				popSelf(1, curr.getSettings());
			}
		}
	}
	/*
	Overriden method from the State update
	Updates the current objects given a value, and adjusts positions/states accordingly
	@param delta: used to update objects based from time passed
	*/
	@Override
	public void update(double delta) {
		// TODO Auto-generated method stub

	}
	/*
	Overriden method from the State render
	Renders all compatible objects into this window
	@param rw: the RenderWindow object where objects will be rendered/drawn
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
		
		rw.draw (btnUp);
		rw.draw (btnDown);
		rw.draw (btnLeft);
		rw.draw (btnRight);
		rw.draw (btnFocus);
		rw.draw (btnShoot);
		
		rw.draw (lblFrameRate);
		rw.draw (lblParticles);
		
		rw.draw (btnFrameRate);
		rw.draw (btnParticles);
	}

}
