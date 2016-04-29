/**
* This class represents a State of the game
* 
* @see	State
*
* @author	Aemielvin Loremia
*/
public class GameState extends State {
	/**
	* A saved instance of the BulletStage class
	*
	* @see BulletStage
	*/
	BulletStage bulletStage;
	SideScreen sideScreen;
	/**
	* Instantiates a GameState with a new BulletStage object
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
	}

	/**
	* Updates the GameState based on changed values after the
	* specified time has passed
	* 
	* @param	delta	The time passed in seconds
	*
	* @see	BulletStage#update
	*/
	@Override
	public void update(double delta) {
		bulletStage.update(delta);
		sideScreen.update(delta);
	}

	/**
	* Renders graphics in the specified RenderWindow
	*
	* @param	rw	RenderWindow where the graphics will be drawn
	*
	* @see	RenderWindow
	* @see	BulletStage#render
	*/
	@Override
	public void render(RenderWindow rw) {
		bulletStage.render(rw);
        sideScreen.render(rw);
	}
}
