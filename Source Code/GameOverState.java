import java.awt.*;
public class GameOverState extends State {
        PlayerSettings ps = PlayerSettings.getInstance();
        int currentScore = 0;
        int highScore = ps.getHighScore();
        int ctr = 0;
        DrawableString decoration1, decoration2, totalLabel, total, newhighnotif;
        DrawableButton mainMenu, retry;
        private Vector center = new Vector((Runner.RES_WIDTH / 2), (Runner.RES_HEIGHT / 2));
        private String deco = "---------------------------------------------------------------------------------------";
        public GameOverState()
        {
            Font font = FontLoader.getInstance().getFont("Press Start 2P", Font.PLAIN, 20);
            Vector padding = new Vector(10, 10);
            
            decoration1 = new DrawableString(new Vector(center.x, center.y -200), deco, font, Color.WHITE);
            decoration2 = new DrawableString(new Vector(center.x, center.y + 100), deco, font, Color.WHITE);
            totalLabel = new DrawableString(new Vector(center.x - 150, center.y - 100), "Current Score", font, Color.WHITE);
            total = new DrawableString(new Vector(center.x + 100, center.y - 100), "lll", font, Color.WHITE);
            newhighnotif = new DrawableString(new Vector(center.x + 320, center.y - 100), "NEW HIGH SCORE", font, Color.YELLOW);
            
            mainMenu = new DrawableButton(new Vector(center.x, center.y + 200),"Main Menu", font, padding, Color.WHITE);
            retry = new DrawableButton(new Vector(center.x , center.y + 150), "Retry", font, padding, Color.WHITE);
        }
	@Override
	public void handleInput(InputCollector input) {
            mainMenu.update(input.getMousePosition());
            retry.update(input.getMousePosition());
            
            if(input.getMouseReleased(InputCollector.MOUSE_BUTTON1))
            {
                if(mainMenu.isCollidingWith(input.getMousePosition()))
                {
                    sm.push(Runner.MENU_STATE, null);
                }
                else if(retry.isCollidingWith(input.getMousePosition()))
                {
                    sm.push(Runner.GAME_STATE, null);
                }
                else
                {
                    //if the screen is clicked, immediately finishes counting
                    ctr = currentScore;
                }
            }
	}

	@Override
	public void update(double delta) {
            total.setText(Integer.toString(ctr));
            currentScore = ps.getScore();
            if(ctr < currentScore) ctr++;
            if(ctr >= highScore) ps.setHighScore(ctr);
	}

	@Override
	public void render(RenderWindow rw) {
            rw.draw(decoration1);
            rw.draw(decoration2);
            rw.draw(totalLabel);
            rw.draw(total);
            if(ctr >= highScore) rw.draw(newhighnotif);
            rw.draw(mainMenu);
            rw.draw(retry);
	}
}