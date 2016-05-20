import java.awt.Graphics2D;

public class Background extends Drawable {
	DrawableImage[][] background;
	double speed;
	int y = -8;

	public Background (Vector position, Vector dimension) {
		super(position, dimension);
		speed = 8;
		background = new DrawableImage[24][23];
		update();
	}

	@Override
	public void draw (Graphics2D g) {
		update();
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 23; j++) {
				background[i][j].draw(g);
			}
		}
	}
	
	public void update() {
		this.y += 1;
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 23; j++) {
				background[i][j] = new DrawableImage (new Vector (j * 32, 732 - (y % 32) - (i * 32)), new Vector (32, 32), ImageLoader.getInstance().getFile("res/img/tile.png"));
			}
		}
	}
}
