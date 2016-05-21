import java.awt.event.*;

/**
* This class represents a State of the game
* 
* @see	State
*
* @author	Aemielvin Loremia
* @author	Jino Basilio
* @author	Kryzl Pascual
*/
public class GameState extends State {
	/**
	* A saved instance of the BulletStage class
	*
	* @see BulletStage
	*/
	BulletStage bulletStage;
	
	/**
	 * The SideScreen object being used
	 * 
	 * @see	SideScreen
	 */
	SideScreen sideScreen;
	
	/**
	* Instantiates a GameState with a new BulletStage object and SideScreen object
	* 
	* @see	BulletStage
	* @see	SideScreen
	*/
	public GameState() {
		bulletStage = new BulletStage();
                sideScreen = new SideScreen();
	}
	
	/**
	* Handles input from specified InputCollector
	* 
	* @param	input	The InputCollector where this GameState
	*					gets input from
	*
	* @see	InputCollector
	* @see	BulletStage#handleInput
	*/
	@Override
	public void handleInput(InputCollector input) {
		bulletStage.handleInput(input);
		
		//Tentative
		if (input.isKeyPressed (Config.getInstance().getPauseKey())) {
			sm.push (Runner.PAUSE_STATE, null);
		}
	}

	/**
	* Updates the GameState based on changed values after the
	* specified time has passed
	* 
	* @param	delta	The time passed in seconds
	*
	* @see	BulletStage#update
	* @see	SideScreen#update
	*/
	@Override
	public void update(double delta) {
		bulletStage.update(delta);
		sideScreen.update(delta);
        if (PlayerSettings.getInstance().getHearts() == 0)
        	sm.push(Runner.GAME_OVER_STATE, null);
	}

	/**
	* Renders graphics in the specified RenderWindow
	*
	* @param	rw	RenderWindow where the graphics will be drawn
	*
	* @see	RenderWindow
	* @see	BulletStage#render
	* @see	SideScreen#render
	*/
	@Override
	public void render(RenderWindow rw) {
		bulletStage.render(rw);
        sideScreen.render(rw);
	}

	@Override
	public void init() {
		PlayerSettings.getInstance().reset();
		bulletStage = new BulletStage();
	}
}
