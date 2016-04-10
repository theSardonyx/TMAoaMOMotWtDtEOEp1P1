import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class SplashState extends State {
	
	private boolean fadeIn, stay, fadeOut;
	private double fadeRate, alpha, counter, duration;
	private DrawableRectangle shade;
	private DrawableImage logo;
	
	public SplashState() {
		fadeRate = 150;
		duration = 3;
		
		Vector position = new Vector(Runner.RES_WIDTH/2.0, Runner.RES_HEIGHT/2.0);
		BufferedImage image = ImageLoader.getInstance().getFile("res/img/logo.png");
		Vector dimension = new Vector(image.getWidth(), image.getHeight());
		
		logo = new DrawableImage(position, dimension, image);
		shade = new DrawableRectangle(position, dimension, Color.BLACK);
	}
	
	@Override
	public void handleInput(InputCollector input) {
		if(input.isKeyPressed() || 
		   input.getMouseClicked(InputCollector.MOUSE_BUTTON1) ||
		   input.getMouseClicked(InputCollector.MOUSE_BUTTON2) ||
		   input.getMouseClicked(InputCollector.MOUSE_BUTTON3)) {
			sm.push(Runner.MENU_STATE, null);
		}
	}

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

	@Override
	public void render(RenderWindow rw) {
		rw.draw(logo);
		rw.draw(shade);
	}
	
	@Override
	public void onActivate(String s) {
		super.onActivate(s);
		reset();
	}
	
	private void reset() {
		fadeIn = true;
		stay = false;
		fadeOut = false;
		
		alpha = 255;
		counter = 0;
	}

}
