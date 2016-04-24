/**
* This class represents the Splash(Logo) screen of the game
* 
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
import java.awt.*;
import java.awt.image.BufferedImage;

public class SplashState extends State {
	
	private boolean fadeIn, stay, fadeOut;
	private double fadeRate, alpha, counter, duration;
	private DrawableRectangle shade;
	private DrawableImage logo;
	
	/*
	Constructor for a SplashState object
	Initializes the positions of the ncessary text and images for the screen
	*/
	public SplashState() {
		fadeRate = 150;
		duration = 3;
		
		Vector position = new Vector(Runner.RES_WIDTH/2.0, Runner.RES_HEIGHT/2.0);
		BufferedImage image = ImageLoader.getInstance().getFile("res/img/logo.png");
		Vector dimension = new Vector(image.getWidth(), image.getHeight());
		
		logo = new DrawableImage(position, dimension, image);
		shade = new DrawableRectangle(position, dimension, Color.BLACK);
	}
	/*
	Overriden method from State class' handleInput
	Handles all inputs from an InputCollector object, responds with the appropiate method
	@param input: the InputCollector object to check for mouse and keyboard movement
	*/
	@Override
	public void handleInput(InputCollector input) {
		if(input.isKeyPressed() || 
		   input.getMouseClicked(InputCollector.MOUSE_BUTTON1) ||
		   input.getMouseClicked(InputCollector.MOUSE_BUTTON2) ||
		   input.getMouseClicked(InputCollector.MOUSE_BUTTON3)) {
			sm.push(Runner.MENU_STATE, null);
		}
	}
	/*
	Overriden method from State class' update
	Updates the current objects given a value, and adjusts positions/states accordingly
	@param delta: used to update objects based from time passed
	*/
	@Override
	public void update(double delta) {
		counter += delta;
		
		if(fadeIn) {
			alpha -= fadeRate*delta;
			if(alpha <= 0 ) {
				alpha = 0;
				counter -= (255/fadeRate);
				fadeIn = false;
				stay = true;
			}
		} else if(stay) {
			if(counter > duration) {
				counter -= duration;
				stay = false;
				fadeOut = true;
			}
		} else if(fadeOut) {
			alpha = counter*fadeRate;
			if(alpha >= 255) {
				alpha = 255;
				sm.push(Runner.MENU_STATE, null);
			}
		}
		
		Color a = new Color(0, 0, 0, (int) alpha);
		shade.setColor(a);
		shade.setFilled(true);
	}
	/*
	Overriden method from State class' render
	Renders all compatible objects into this window
	@param rw: the RenderWindow object where objects will be rendered/drawn
	*/
	@Override
	public void render(RenderWindow rw) {
		rw.draw(logo);
		rw.draw(shade);
	}
	
	/*
	Overriden method from State class' onActivate
	Automatically activates the next State based on the string
	*/
	@Override
	public void onActivate(String s) {
		super.onActivate(s);
		reset();
	}
	/*
	Reinitializes all variables
	*/
	private void reset() {
		fadeIn = true;
		stay = false;
		fadeOut = false;
		
		alpha = 255;
		counter = 0;
	}

}
