import java.awt.*;

/**
* This class represents the Game Over screen of the game
*
* @author	Jino Basilio
* @author	Kryzl Pascual
* @author	Ivan Martinez
*/
public class GameOverState extends State {
    int currentScore, highScore;
    int scorectr = 0;
    int adjuster = 10;
	DrawableButton btnRetry, btnMain;
	DrawableString label, lblHigh, score;
	int blink = 0;
	PlayerSettings curr = PlayerSettings.getInstance();
	
	public GameOverState() {
		Font font = FontLoader.getInstance().getFont ("Press Start 2P", Font.PLAIN, 20);
		Vector padding = new Vector (10, 10);
		
		btnRetry = new DrawableButton (new Vector (Runner.RES_WIDTH / 2, 600), "Retry", font, padding, Color.WHITE);
		btnMain = new DrawableButton (new Vector (Runner.RES_WIDTH / 2, 650), "Back to Main Menu", font, padding, Color.WHITE);
		
		lblHigh = new DrawableString (new Vector (Runner.RES_WIDTH / 2, (Runner.RES_HEIGHT / 2) - 150), "New High Score", font, Color.WHITE);
		
		font = font.deriveFont (Font.PLAIN, 40);
		label = new DrawableString (new Vector (Runner.RES_WIDTH / 2, 100), "Game Over", font, Color.WHITE);
		
		font = font.deriveFont (Font.PLAIN, 120);
		score = new DrawableString (new Vector (Runner.RES_WIDTH / 2, Runner.RES_HEIGHT / 2), curr.getScore() + "", font, Color.WHITE);
	}

	@Override
	public void handleInput(InputCollector input) {
		btnRetry.update (input.getMousePosition());
		btnMain.update (input.getMousePosition());
		
		if (input.getMouseReleased (InputCollector.MOUSE_BUTTON1)) {
			if (btnRetry.isCollidingWith (input.getMousePosition())) {
				popSelf (1, "reset");
			} else if (btnMain.isCollidingWith (input.getMousePosition())) {
				if (currentScore > highScore)
					curr.setHighScore(currentScore);
				popSelf (2, null);
			} else scorectr = currentScore;
		}
        
		if(input.isKeyTyped())
			scorectr = currentScore;
	}

	@Override
	public void update(double delta) {
                currentScore = curr.getScore();
                highScore = curr.getHighScore();
                
                if(scorectr < currentScore) scorectr++;
                
                //this algorithm adjusts the score text position depending on the number of digits
                if(scorectr % adjuster == 0)
                {
                    score.setPosition(new Vector(score.getPosition().x -= (int)score.getDimension().x / 2, score.getPosition().y));
                    adjuster *= 10;
                }
                score.setText(Integer.toString(scorectr));
                    
		int speed = Config.getInstance().getFrameRate();
		if (blink % speed < (speed / 2) - 1)
			lblHigh.setColor (Color.BLACK);
		else lblHigh.setColor (Color.WHITE);
		blink++;
	}

	@Override
	public void render(RenderWindow rw) {
		rw.draw (btnRetry);
		rw.draw (btnMain);
		
		rw.draw (label);
		rw.draw (score);
		
		if(scorectr == currentScore && scorectr >= highScore)
			rw.draw (lblHigh);
	}

	@Override
	public void init() {
		scorectr = 0;
	}
}