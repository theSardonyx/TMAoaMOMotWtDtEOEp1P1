
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
	SpriteSheet ss;
	Sprite test;
	
	public BulletStage() {
		position = new Vector( 0, 0 );
		dimension = new Vector( Runner.RES_HEIGHT, Runner.RES_HEIGHT );
		BufferedImage image = new BufferedImage(dimension.getX(), dimension.getY(), BufferedImage.TYPE_4BYTE_ABGR);
		visual = new DrawableImage(position.add( dimension.scalarMult( 0.5 ) ), dimension, image);
		graphics = (Graphics2D) image.getGraphics();
		
		entities = new ArrayList<Entity>();
		requestQueue = new ArrayDeque<Request>();
		ss = new SpriteSheet(ImageLoader.getInstance().getFile("res/img/64x64-sheet.png"), 64, 64);
		test = new Sprite(dimension.scalarMult( 0.5 ), new Vector(64, 64), new BufferedImage[] { 
				ss.get(0, 0),
				ss.get(2, 0)
		}, new BufferedImage[] {
				ss.get(1, 0),
				ss.get(3, 0)
		}, Color.BLUE);
	}
	
	public void handleInput(InputCollector input) {
		if(input.isKeyPressed()) {
			
			int particleCount = 1 + (int) (Math.random()*4); 
			for(int i=0; i<particleCount; i++) {
				
				double diameter = 5 + Math.random()*5;
				double speed = 50 + Math.random()*50;
				double direction = Math.random() * 2 * Math.PI;
				Vector velocity = new Vector(speed, 0).rotate(direction);
				Vector acceleration = new Vector(00, 00);
				Vector growRate = new Vector(10, 10);
				double duration = 1;
				
				Particle p = new Particle(input.getMousePosition(), diameter, Color.YELLOW, this);
				p.setVelocity(velocity);
				p.setAcceleration(acceleration);
				p.setGrowRate(growRate);
				p.adjustDuration(duration);
				addEntity(p);
			}			
		}
	}
	
	public void update(double delta) {
		for(Entity e: entities) {
			e.update(delta);
		}
		
		while(!requestQueue.isEmpty()) {
			Request curr = requestQueue.poll();
			curr.execute();
		}
		
		test.update(delta);
	}
	
	public void render(RenderWindow rw) {
		graphics.clearRect( 0, 0, dimension.getX(), dimension.getY());
		
		for(Entity e: entities ) {
			e.draw(graphics);
		}
		
		test.draw(graphics);
		
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
