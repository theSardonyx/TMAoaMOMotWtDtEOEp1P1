
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
	
	public BulletStage() {
		position = new Vector( 0, 0 );
		dimension = new Vector( 550, 600 );
		BufferedImage image = new BufferedImage(dimension.getX(), dimension.getY(), BufferedImage.TYPE_4BYTE_ABGR);
		visual = new DrawableImage(position.add( dimension.scalarMult( 0.5 ) ), dimension, image);
		graphics = (Graphics2D) image.getGraphics();
		
		entities = new ArrayList<Entity>();
		requestQueue = new ArrayDeque<Request>();
	}
	
	public void handleInput(InputCollector input) {
		// TODO
	}
	
	public void update(double delta) {
		for(Entity e: entities) {
			e.update(delta);
		}
	}
	
	public void render(RenderWindow rw) {
		graphics.clearRect( 0, 0, dimension.getX(), dimension.getY());
		
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
