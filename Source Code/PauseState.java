import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;

/**
* This class represents the pause screen of the game
* 
* @see	State
* 
* @author	Kryzl Pascual
*/
public class PauseState extends State {
	/**
	 * The DrawableRectangle that serves as the overlay
	 * 
	 * @see	DrawableRectangle
	 */
	DrawableRectangle overlay;
	
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
	 */
	String[] runes;
	
	/**
	 * The new rune gotten by the player
	 */
	String latest;
	
	/**
	 * CollideRectangles that represent a "compartment" or places where runes appear
	 * 
	 * @see	CollideRectangle
	 */
	CollideRectangle rune0, rune1, rune2, rune3, rune4, rune5, rune6, rune7, rune8, rune9, newRune;
	
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
	public PauseState() {
		runes = new String[10];
		Arrays.fill (runes, "");
		//test instances
		runes[4] = "homing";
		runes[5] = "spread";
		latest = "antibullet";
		
		overlay = new DrawableRectangle (new Vector (Runner.RES_WIDTH / 2, Runner.RES_HEIGHT / 2), new Vector (500, 400), Color.BLUE);
		
		Font font = FontLoader.getInstance().getFont ("Press Start 2P", Font.PLAIN, 20);
		Vector padding = new Vector (10, 10);
		
		lblNewRune = new DrawableString (new Vector ((Runner.RES_WIDTH / 2) + 192, (Runner.RES_HEIGHT / 2) - 35), "New", font, Color.WHITE);
		
		btnResume = new DrawableButton (new Vector (Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) + 120), "Resume", font, padding, Color.WHITE);
		btnExit = new DrawableButton (new Vector (Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) + 170), "Back to Main Menu", font, padding, Color.WHITE);
		
		font = font.deriveFont (Font.PLAIN, 40);
		label = new DrawableString (new Vector (Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) - 160), "Pause", font, Color.WHITE);
		
		font = font.deriveFont (Font.PLAIN, 15);
		instructions = new DrawableString (new Vector (Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) - 90), "Click any two runes to swap them", font, Color.WHITE);
		
		for(int i = 0; i < 10; i++)
		{
			int adjust = 0;
			Vector pos;
			if(i < 5)
			{
				adjust = 64 * i;
				pos = new Vector((Runner.RES_WIDTH / 2) + (adjust - 192), (Runner.RES_HEIGHT / 2) - 35);
			}					
			else 
			{
				adjust = 64 * (i - 5);
				pos = new Vector((Runner.RES_WIDTH / 2) + (adjust - 192), (Runner.RES_HEIGHT / 2) + 35);
			}
			Vector dim = new Vector(64, 64);
			
			switch (i) {
				case 0 : rune0 = new CollideRectangle (pos, dim);
				break;
				case 1 : rune1 = new CollideRectangle (pos, dim);
				break;
				case 2 : rune2 = new CollideRectangle (pos, dim);
				break;
				case 3 : rune3 = new CollideRectangle (pos, dim);
				break;
				case 4 : rune4 = new CollideRectangle (pos, dim);
				break;
				case 5 : rune5 = new CollideRectangle (pos, dim);
				break;
				case 6 : rune6 = new CollideRectangle (pos, dim);
				break;
				case 7 : rune7 = new CollideRectangle (pos, dim);
				break;
				case 8 : rune8 = new CollideRectangle (pos, dim);
				break;
				case 9 : rune9 = new CollideRectangle (pos, dim);
				break;
			}
		}
		
		newRune = new CollideRectangle (new Vector ((Runner.RES_WIDTH / 2) + 192, (Runner.RES_HEIGHT / 2) + 35), new Vector (64, 64));
	}

	/**
	* Renders all compatible objects into this window
	* 
	* @param	rw	The RenderWindow object where objects will be rendered/drawn
	*/
	@Override
	public void render(RenderWindow rw) {
		rw.draw (overlay);
		
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
			CollideCircle mouse = new CollideCircle (input.getMousePosition(), 0);
			if (btnResume.isCollidingWith (input.getMousePosition()))
				popSelf (1, null);
			else if (btnExit.isCollidingWith (input.getMousePosition()))
				popSelf (2, null);
			else if (rune0.isCollidingWith (mouse)) {
				if (!pressed) {
					pressed = true;
					index = 0;
				} else {
					swap (0, index);
					pressed = false;
					index = 12;
				}
			} else if (rune1.isCollidingWith (mouse)) {
				if (!pressed) {
					pressed = true;
					index = 1;
				} else {
					swap (1, index);
					pressed = false;
					index = 12;
				}
			} else if (rune2.isCollidingWith (mouse)) {
				if (!pressed) {
					pressed = true;
					index = 2;
				} else {
					swap (2, index);
					pressed = false;
					index = 12;
				}
			} else if (rune3.isCollidingWith (mouse)) {
				if (!pressed) {
					pressed = true;
					index = 3;
				} else {
					swap (3, index);
					pressed = false;
					index = 12;
				}
			} else if (rune4.isCollidingWith (mouse)) {
				if (!pressed) {
					pressed = true;
					index = 4;
				} else {
					swap (4, index);
					pressed = false;
					index = 12;
				}
			} else if (rune5.isCollidingWith (mouse)) {
				if (!pressed) {
					pressed = true;
					index = 5;
				} else {
					swap (5, index);
					pressed = false;
					index = 12;
				}
			} else if (rune6.isCollidingWith (mouse)) {
				if (!pressed) {
					pressed = true;
					index = 6;
				} else {
					swap (6, index);
					pressed = false;
					index = 12;
				}
			} else if (rune7.isCollidingWith (mouse)) {
				if (!pressed) {
					pressed = true;
					index = 7;
				} else {
					swap (7, index);
					pressed = false;
					index = 12;
				}
			} else if (rune8.isCollidingWith (mouse)) {
				if (!pressed) {
					pressed = true;
					index = 8;
				} else {
					swap (8, index);
					pressed = false;
					index = 12;
				}
			} else if (rune9.isCollidingWith (mouse)) {
				if (!pressed) {
					pressed = true;
					index = 9;
				} else {
					swap (9, index);
					pressed = false;
					index = 12;
				}
			} else if (newRune.isCollidingWith (mouse)) {
				if (!pressed) {
					pressed = true;
					index = 10;
				} else {
					swap (10, index);
					pressed = false;
					index = 12;
				}
			}
		}
	}
	
	/**
	 * Swaps the positions of the two specified runes
	 */
	public void swap (int x, int y) {
		if (x == runes.length || y == runes.length) {
			if (x == runes.length) {
				String temp = latest;
				latest = runes[y];
				runes[y] = temp;
			} else {
				String temp = latest;
				latest = runes[x];
				runes[x] = temp;
			}
		} else {
			String temp = runes[x];
			runes[x] = runes[y];
			runes[y] = temp;
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
	
	/**
	 * Draws runes is their respective "compartments"
	 * 
	 * @see	#runes
	 * @see RenderWindow
	 */
	public void runeRender(RenderWindow rw) {
			//draw the runes based on the list
			for(int i = 0; i < runes.length + 1; i++)
			{
				String curr;
				Vector pos;
				if (i < runes.length) {
					int adjust = 0;
					if(i < 5)
					{
						adjust = 64 * i;
						pos = new Vector((Runner.RES_WIDTH / 2) + (adjust - 192), (Runner.RES_HEIGHT / 2) - 35);
					}					
					else 
					{
						adjust = 64 * (i - 5);
						pos = new Vector((Runner.RES_WIDTH / 2) + (adjust - 192), (Runner.RES_HEIGHT / 2) + 35);
					}
					curr = runes[i];
				} else {
					pos = new Vector ((Runner.RES_WIDTH / 2) + 192, (Runner.RES_HEIGHT / 2) + 35);
					curr = latest;
				}
				Vector dim = new Vector(64, 64);
				
				switch (curr) {
					case "pierce" : rw.draw (new DrawableImage (pos, dim, ss.get (0, 2)));
					break;
					case "homing" : rw.draw (new DrawableImage (pos, dim, ss.get (0, 3)));
					break;
					case "explosion" : rw.draw (new DrawableImage (pos, dim, ss.get (0, 4)));
					break;
					case "burst" : rw.draw (new DrawableImage (pos, dim, ss.get (0, 5)));
					break;
					case "snipe" : rw.draw (new DrawableImage (pos, dim, ss.get (0, 6)));
					break;
					case "split" : rw.draw (new DrawableImage (pos, dim, ss.get (0, 7)));
					break;
					case "spread" : rw.draw (new DrawableImage (pos, dim, ss.get (0, 8)));
					break;
					case "sentinel" : rw.draw (new DrawableImage (pos, dim, ss.get (0, 9)));
					break;
					case "antibullet" : rw.draw (new DrawableImage (pos, dim, ss.get (0, 10)));
					break;
					case "summon" : rw.draw (new DrawableImage (pos, dim, ss.get (0, 11)));
					break;
					default : rw.draw(new DrawableImage(pos, dim, ss.get(1, 0)));
				}
			}
	}
}