import java.awt.*;

/**
* This class represents the Game Over screen of the game
*
* @author	Jino Basilio
* @author	Kryzl Pascual
*/
public class GameOverState extends State {
        PlayerSettings ps = PlayerSettings.getInstance();
        int currentScore, highScore;
        int ctr = 0;
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
		
		System.out.println (curr.getScore());
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
				curr.setHearts (3);
				curr.setScore (0);
				popSelf (1, null);
			} else if (btnMain.isCollidingWith (input.getMousePosition()))
				popSelf (2, null);
                        else
                                ctr = currentScore;
		}
               if(input.isKeyTyped()) ctr = currentScore;
	}

	@Override
	public void update(double delta) {
                currentScore = ps.getScore();
                highScore = ps.getHighScore();
                
                if(ctr < currentScore) ctr++;
                score.setText(Integer.toString(ctr));
                    
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
		
		//if (curr.getScore() > curr.getHighScore())
		if(ctr == currentScore && ctr >= highScore) rw.draw (lblHigh);
	}
}