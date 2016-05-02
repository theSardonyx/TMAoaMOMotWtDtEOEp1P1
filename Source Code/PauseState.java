import java.awt.Color;
import java.awt.Font;

/**
* This class represents the pause screen of the game
* 
* @see	State
* 
* @author	Kryzl Pascual
*/
public class PauseState extends State {
	/**
	 * The DrawableString that shows the title of the screen
	 * 
	 * @see	DrawableString
	 */
	DrawableString label;
	
	/**
	 * The button that will exit the Pause screen
	 * 
	 * @see	DrawableButton
	 */
	DrawableButton btnResume, btnExit;
	
	/**
	 * Creates a Pause Screen containing the player's saved Runes in an arranged order
	 */
	public PauseState() {
		Font font = FontLoader.getInstance().getFont ("Press Start 2P", Font.PLAIN, 20);
		Vector padding = new Vector (10, 10);
		
		btnResume = new DrawableButton (new Vector (Runner.RES_WIDTH / 2, 600), "Resume", font, padding, Color.WHITE);
		btnExit = new DrawableButton (new Vector (Runner.RES_WIDTH / 2, 650), "Back to Main Menu", font, padding, Color.WHITE);
		
		font = font.deriveFont (Font.PLAIN, 40);
		label = new DrawableString (new Vector (Runner.RES_WIDTH / 2, 100), "Pause", font, Color.WHITE);
	}

	/**
	* Renders all compatible objects into this window
	* 
	* @param	rw	The RenderWindow object where objects will be rendered/drawn
	*/
	@Override
	public void render(RenderWindow rw) {
		rw.draw (label);
		
		rw.draw (btnResume);
		rw.draw (btnExit);
	}
	
	/**
	* Handles all inputs from an InputCollector object, responds with the appropriate method
	* 
	* @param	input	The InputCollector object to check for mouse and keyboard movement
	*/
	@Override
	public void handleInput(InputCollector input) {
		btnResume.update (input.getMousePosition());
		btnExit.update (input.getMousePosition());
		
		if (input.getMouseReleased (InputCollector.MOUSE_BUTTON1)) {
			if (btnResume.isCollidingWith (input.getMousePosition()))
				popSelf (1, null);
			else if (btnExit.isCollidingWith (input.getMousePosition()))
				popSelf (2, null);
		}
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
}