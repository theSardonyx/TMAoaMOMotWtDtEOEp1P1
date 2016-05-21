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
	 * The GameState that will be shown behind the overlay
	 * 
	 * @see	GameState
	 */
	GameState game;
	
	/**
	 * The DrawableRectangles that serve as the overlay
	 * 
	 * @see	DrawableRectangle
	 */
	DrawableRectangle overlay, outline;
	
	/**
	 * The DrawableString that shows the title of the screen
	 * 
	 * @see	DrawableString
	 */
	DrawableString label, instructions, lblNewRune;
	
	/**
	 * The button that will exit the Pause screen
	 * 
	 * @see	DrawableButton
	 */
	DrawableButton btnResume, btnExit;
	
	/**
	 * The list of runes currently being used by the Player
	 * 
	 * @see	RuneList
	 */
	PlayerSettings playerSettings;
	
	/**
	 * CollideRectangles that represent a "compartment" or places where runes appear
	 * 
	 * @see	CollideRectangle
	 */
	CollideShape rune0, rune1, rune2, rune3, rune4, rune5, rune6, rune7, rune8, rune9, newRune;
	CollideShape[] collideShapeRunes;
	int[] runes;
	
	/**
	 * The instance of the SpriteSheet where the rune sprites will be loaded from
	 * 
	 * @see	SpriteSheet
	 * @see	SpriteSheetLoader#getInstance
	 * @see	SpriteSheetLoader#getSpriteSheet
	 */
	private SpriteSheet ss = SpriteSheetLoader.getInstance().getSpriteSheet("res/img/runeSheet.png", 64, 64);
	
	/**
	 * Check is a rune has been selected
	 */
	boolean pressed;
	
	/**
	 * The index of the currently selected rune
	 */
	int index = 12;
	
	/**
	 * Creates a Pause Screen containing the player's saved Runes in an arranged order
	 */
	public PauseState (GameState game) {
		this.game = game;
		playerSettings = PlayerSettings.getInstance();
		
		overlay = new DrawableRectangle (new Vector (Runner.RES_WIDTH / 2, Runner.RES_HEIGHT / 2), new Vector (500, 400), Color.BLACK);
		overlay.setFilled (true);
		outline = new DrawableRectangle (new Vector (Runner.RES_WIDTH / 2, Runner.RES_HEIGHT / 2), new Vector (500, 400), Color.BLUE);
		
		Font font = FontLoader.getInstance().getFont ("Press Start 2P", Font.PLAIN, 20);
		Vector padding = new Vector (10, 10);
		
		lblNewRune = new DrawableString (new Vector ((Runner.RES_WIDTH / 2) + 192, (Runner.RES_HEIGHT / 2) - 35), "New", font, Color.WHITE);
		
		btnResume = new DrawableButton (new Vector (Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) + 120), "Resume", font, padding, Color.WHITE);
		btnExit = new DrawableButton (new Vector (Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) + 170), "Back to Main Menu", font, padding, Color.WHITE);
		
		font = font.deriveFont (Font.PLAIN, 40);
		label = new DrawableString (new Vector (Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) - 160), "Pause", font, Color.WHITE);
		
		font = font.deriveFont (Font.PLAIN, 15);
		instructions = new DrawableString (new Vector (Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) - 90), "Click any two runes to swap them", font, Color.WHITE);
		
		collideShapeRunes = new CollideShape[] {rune0, rune1, rune2, rune3, rune4, rune5, rune6, rune7, rune8, rune9, newRune};
		for(int i = 0; i < 10; i++)
		{
			Vector pos;
			if(i < 5)
				pos = new Vector((Runner.RES_WIDTH / 2) + (64 * i - 192), (Runner.RES_HEIGHT / 2) - 35);		
			else 
				pos = new Vector((Runner.RES_WIDTH / 2) + (64 * (i - 5) - 192), (Runner.RES_HEIGHT / 2) + 35);
			Vector dim = new Vector(64, 64);
			
			collideShapeRunes[i] = new CollideShape (pos, dim).setCollideRectangle(true);
		}
		collideShapeRunes[10] = new CollideShape (new Vector ((Runner.RES_WIDTH / 2) + 192, (Runner.RES_HEIGHT / 2) + 35), new Vector (64, 64)).setCollideRectangle(true);
	
		runes = new int[] {PlayerSettings.PIERCE_RUNE, PlayerSettings.HOMING_RUNE, 
				PlayerSettings.SPREAD_RUNE, PlayerSettings.EXPLOSION_RUNE, 
				PlayerSettings.SNIPE_RUNE, PlayerSettings.SPLIT_RUNE, 
				PlayerSettings.BURST_RUNE, PlayerSettings.SENTINEL_RUNE, 
				PlayerSettings.ANTI_RUNE, PlayerSettings.SUMMON_RUNE};
	}

	/**
	* Renders all compatible objects into this window
	* 
	* @param	rw	The RenderWindow object where objects will be rendered/drawn
	*/
	@Override
	public void render(RenderWindow rw) {
		game.render(rw);
		rw.draw (overlay);
		rw.draw (outline);
		
		rw.draw (label);
		rw.draw (instructions);
		rw.draw (lblNewRune);
		
		rw.draw (btnResume);
		rw.draw (btnExit);
		
		runeRender (rw);
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
			CollideShape mouse = new CollideShape (input.getMousePosition(), new Vector(1, 1)).setCollideEllipse(true);
			if (btnResume.isCollidingWith (input.getMousePosition()))
				popSelf (1, null);
			else if (btnExit.isCollidingWith (input.getMousePosition()))
				popSelf (2, null);
			else {
				for(int i = 0; 11 > i; i++) {
					if(collideShapeRunes[i].isCollidingWith(mouse)) {
						if(!pressed) {
							if(this.playerSettings.getRune(i) != -1) {
								pressed = true;
								index = i;
							}
						} else {
							this.swap(i, index);
							pressed = false;
							index = 12;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Swaps the positions of the two specified runes
	 */
	public void swap (int x, int y) {
		int temp = playerSettings.getRune(x);
		playerSettings.setRune(playerSettings.getRune(y), x);
		playerSettings.setRune (temp, y);
	}
	
	/**
	* Updates the current objects given a value, and adjusts positions/states accordingly
	* 
	* @param	delta	The time passed
	*/
	@Override
	public void update(double delta) {}
	
	/**
	 * Draws runes is their respective "compartments"
	 * 
	 * @see	#runes
	 * @see RenderWindow
	 */
	public void runeRender(RenderWindow rw) {
		for(int i = 0; i < 11; i++) {
			Vector pos;
			if (i < 10) {
				if(i < 5)
					pos = new Vector((Runner.RES_WIDTH / 2) + (64 * i - 192), (Runner.RES_HEIGHT / 2) - 35);			
				else 
					pos = new Vector((Runner.RES_WIDTH / 2) + (64 * (i - 5) - 192), (Runner.RES_HEIGHT / 2) + 35);
			} else {
				pos = new Vector ((Runner.RES_WIDTH / 2) + 192, (Runner.RES_HEIGHT / 2) + 35);
			}
			Vector dim = new Vector(64, 64);
			int curr = playerSettings.getRune(i);
			
			for(int j = 0; 10 > j; j++) {
				if(curr == runes[j]) {
					if(i == index && playerSettings.getRune(i) == curr)
						rw.draw (new DrawableImage (pos, dim, ss.get (1, j + 2)));
					else
						rw.draw (new DrawableImage (pos, dim, ss.get (0, j + 2)));
					break;
				} else 
					rw.draw(new DrawableImage(pos, dim, ss.get(1, 0)));
			}
		}
	}
	
	@Override
	public void init() {}
}