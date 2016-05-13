
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class BulletStage {
	
	private Vector position;
	private Vector dimension;
	
	private DrawableImage visual;
	private Graphics2D graphics;
	
	private ArrayList<Entity> entities;
	private Queue<Request> requestQueue;
	
	private Player player;
	
	public BulletStage() {
		position = new Vector( 0, 0 );
		dimension = new Vector( Runner.RES_HEIGHT, Runner.RES_HEIGHT );
		BufferedImage image = new BufferedImage(dimension.getX(), dimension.getY(), BufferedImage.TYPE_4BYTE_ABGR);
		visual = new DrawableImage(position.add( dimension.scalarMult( 0.5 ) ), dimension, image);
		graphics = (Graphics2D) image.getGraphics();
		
		entities = new ArrayList<Entity>();
		requestQueue = new ArrayDeque<Request>();

		player = new Player(new Vector(0, 200), new Vector(64, 64), this);
		player.setMoveBehavior(new QueueMoveBehavior(player, new MoveBehavior[] {
				new AccelerateMoveBehavior(player, new Vector(200, 0), new Vector(0, 0), 1),
		}));
		addEntity(player);
	}
	
	public void handleInput(InputCollector input) {
		player.handleInput(input);
	}
	
	public void update(double delta) {
		for(Entity e: entities) {
			e.update(delta);
		}
		
		while(!requestQueue.isEmpty()) {
			Request curr = requestQueue.poll();
			curr.execute();
		}
	}
	
	public void render(RenderWindow rw) {
		graphics.setColor(new Color(0x101010));
		graphics.fillRect( 0, 0, dimension.getX(), dimension.getY());
		
		for(Entity e: entities ) {
			e.draw(graphics);
		}
		
		rw.draw( visual );
	}
	
	public void addRequest(Request r) {
		requestQueue.add(r);
	}
	
	public void addEntity(Entity entity) {
		entities.add( entity );
	}
	
	public void removeEntity(Entity entity) {
		entities.remove( entity );
	}
}
