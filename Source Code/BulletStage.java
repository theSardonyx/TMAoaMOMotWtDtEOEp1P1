
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class BulletStage {
	
	private Vector position;
	private Vector dimension;
	
	private DrawableImage visual;
	private Graphics2D graphics;
	
	private ArrayList<Entity> ambientEntity;
	private ArrayList<Entity> allyEntity;
	private ArrayList<Entity> allyBulletEntity;
	private ArrayList<Entity> enemyEntity;
	private ArrayList<Entity> enemyBulletEntity;
	
	private Queue<Request> requestQueue;
	
	private Player player;
	private BatEnemy bat;
	
	public BulletStage() {
		position = new Vector( 0, 0 );
		dimension = new Vector( Runner.RES_HEIGHT, Runner.RES_HEIGHT );
		BufferedImage image = new BufferedImage(dimension.getX(), dimension.getY(), BufferedImage.TYPE_4BYTE_ABGR);
		visual = new DrawableImage(position.add( dimension.scalarMult( 0.5 ) ), dimension, image);
		graphics = (Graphics2D) image.getGraphics();
		
		ambientEntity = new ArrayList<Entity>();
		allyEntity = new ArrayList<Entity>();
		allyBulletEntity = new ArrayList<Entity>();
		enemyEntity = new ArrayList<Entity>();
		enemyBulletEntity = new ArrayList<Entity>();
		
		requestQueue = new ArrayDeque<Request>();

		player = new Player(new Vector(200, 200), new Vector(64, 64), this);
		player.setMoveBehavior(new PlayerMoveBehavior(player, 300));
		
		bat = new BatEnemy(new Vector(800, 800), new Vector(64, 64), this);
		bat.setMoveBehavior(new HomingMoveBehavior(bat, player, new Vector(300, 0), Math.PI, 20));
		bat.setShootBehavior(new BatBulletShootBehaviorEasy(bat, this, Color.RED));
		addEntity(bat);
		
		BasicBulletShootBehavior sb = new BasicBulletShootBehavior(player, this);
		sb.setLevel(10);
		
		player.setShootBehavior(sb);
		addEntity(player);
	}
	
	public void handleInput(InputCollector input) {
		ambientEntity.forEach( e -> e.handleInput(input) );
		allyEntity.forEach( e -> e.handleInput(input) );
		allyBulletEntity.forEach( e -> e.handleInput(input) );
		enemyEntity.forEach( e -> e.handleInput(input) );
		enemyBulletEntity.forEach( e -> e.handleInput(input) );
	}
	
	public void update(double delta) {
		ambientEntity.forEach( e -> e.update(delta) );
		allyBulletEntity.forEach( e -> e.update(delta) );
		allyEntity.forEach( e -> e.update(delta) );
		enemyEntity.forEach( e -> e.update(delta) );
		enemyBulletEntity.forEach( e -> e.update(delta) );
		
		while(!requestQueue.isEmpty()) {
			Request curr = requestQueue.poll();
			curr.execute();
		}
	}
	
	public void render(RenderWindow rw) {
		graphics.setColor(new Color(0x101010));
		graphics.fillRect( 0, 0, dimension.getX(), dimension.getY());
		
		ambientEntity.forEach( e -> e.draw(graphics) );
		allyBulletEntity.forEach( e -> e.draw(graphics) );
		allyEntity.forEach( e -> e.draw(graphics) );
		enemyEntity.forEach( e -> e.draw(graphics) );
		enemyBulletEntity.forEach( e -> e.draw(graphics) );
		
		rw.draw( visual );
	}
	
	public void addRequest(Request r) {
		requestQueue.add(r);
	}
	
	public void addEntity(Entity entity) {
		switch(entity.getType())
		{
			case Entity.AMBIENT_TYPE:
				ambientEntity.add(entity);
				break;
			case Entity.ALLY_TYPE:
				allyEntity.add(entity);
				break;
			case Entity.ALLY_BULLET_TYPE:
				allyBulletEntity.add(entity);
				break;
			case Entity.ENEMY_TYPE:
				enemyEntity.add(entity);
				break;
			case Entity.ENEMY_BULLET_TYPE:
				enemyBulletEntity.add(entity);
				break;
		}
	}
	
	public void removeEntity(Entity entity) {
		switch(entity.getType())
		{
			case Entity.AMBIENT_TYPE:
				ambientEntity.remove(entity);
				break;
			case Entity.ALLY_TYPE:
				allyEntity.remove(entity);
				break;
			case Entity.ALLY_BULLET_TYPE:
				allyBulletEntity.remove(entity);
				break;
			case Entity.ENEMY_TYPE:
				enemyEntity.remove(entity);
				break;
			case Entity.ENEMY_BULLET_TYPE:
				enemyBulletEntity.remove(entity);
				break;
		}
	}
}
