
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

		player = new Player(new Vector(200, 200), new Vector(64, 64), this);
		player.setMoveBehavior(new PlayerMoveBehavior(player, 300));
		player.setShootBehavior(new BatShootBehavior1(player, this, 1, Color.RED));
		addEntity(player);
	}
	
	public void handleInput(InputCollector input) {
		/*if(input.isKeyPressed()) {
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
		}*/
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
		graphics.setColor(new Color(0x777777));
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
