import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.Arrays;

public class MenuState extends State {
	DrawableButton playButton, optionsButton, exitButton;
	DrawableString firstLine, protag, lastLine, epPart;
	
	/*
	Constructor for a MenuState object
	*/
	public MenuState() {
		Font font = FontLoader.getInstance().getFont("Press Start 2P", Font.PLAIN, 20);
		Vector padding = new Vector(10, 10);
		playButton = new DrawableButton(new Vector(Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) + 100), "Play", font, padding, Color.WHITE);
		optionsButton = new DrawableButton(new Vector(Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) + 175), "Options", font, padding, Color.WHITE);
		exitButton = new DrawableButton(new Vector(Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) + 250), "Exit", font, padding, Color.WHITE);
		
		firstLine = new DrawableString(new Vector(Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) - 250), "The Melancholic Adventures of a", font, Color.WHITE);
		font = font.deriveFont(Font.PLAIN, 40);
		protag = new DrawableString(new Vector(Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) - 175), "Magical Old Man", font, Color.WHITE);
		font = font.deriveFont(Font.PLAIN, 20);
		lastLine = new DrawableString(new Vector(Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) - 100), "on the Way to Defeat the Evil Overlord", font, Color.WHITE);
		epPart = new DrawableString(new Vector(Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) - 50), "Episode 1 Part 1", font, Color.WHITE);
		
	}

	/*
	Override method for handleInput method
	This method is used to update the buttons depending on the position of the mouse and the keyboard button presses
	@param input: the InputCollector object used to update the buttons' state
	*/
	@Override
	public void handleInput(InputCollector input) {
		playButton.update(input.getMousePosition());
		optionsButton.update(input.getMousePosition());
		exitButton.update(input.getMousePosition());
		
		if(input.getMouseReleased(InputCollector.MOUSE_BUTTON1)) {
			if(playButton.isCollidingWith(input.getMousePosition()))
				sm.push(Runner.GAME_STATE, null);
			else if(optionsButton.isCollidingWith(input.getMousePosition()))
				sm.push(Runner.OPTION_STATE, null);
			else if(exitButton.isCollidingWith(input.getMousePosition()))
				System.exit(0);
		}
	}
	
	/*
	Overriden method for the State class' update
	@ param delta: the value used to update different objects in the game
	*/
	@Override
	public void update(double delta) {
		
	}
	/*
	Overriden method for the State class' render
	@param rw: RenderWindow object where objects will be drawn
	*/
	@Override
	public void render(RenderWindow rw) {
		rw.draw(playButton);
		rw.draw(optionsButton);
		rw.draw(exitButton);
		rw.draw(firstLine);
		rw.draw(protag);
		rw.draw(lastLine);
		rw.draw(epPart);
	}

}
