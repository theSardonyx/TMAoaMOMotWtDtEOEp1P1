
public class GameState extends State {

	BulletStage bulletStage;
	
	public GameState() {
		bulletStage = new BulletStage();
	}
	
	@Override
	public void handleInput(InputCollector input) {
		bulletStage.handleInput(input);
	}

	@Override
	public void update(double delta) {
		bulletStage.update(delta);
	}

	@Override
	public void render(RenderWindow rw) {
		bulletStage.render(rw);
	}
}
