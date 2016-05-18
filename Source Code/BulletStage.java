
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
	private ArrayList<Entity> dropEntity;
	
	private Queue<Request> requestQueue;
	
	private Player player;
	
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
		dropEntity = new ArrayList<Entity>();
		
		requestQueue = new ArrayDeque<Request>();

		player = new Player(new Vector(200, 200), new Vector(64, 64), this);
		player.setMoveBehavior(new PlayerMoveBehavior(player, 300));
		
		WitchEnemy eye = new WitchEnemy(new Vector(300, 300), new Vector(64, 64), this);
		//eye.setMoveBehavior(new EllipseMoveBehavior(eye, new Vector(300, 300), 250, 250, 5));
		eye.setShootBehavior(new WitchShootBehaviorHard(eye, player, this, Color.RED));
		addEntity(eye);
		
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
		dropEntity.forEach( e -> e.handleInput(input) );
	}
	
	public void update(double delta) {
		ambientEntity.forEach( e -> e.update(delta) );
		allyBulletEntity.forEach( e -> e.update(delta) );
		allyEntity.forEach( e -> e.update(delta) );
		enemyEntity.forEach( e -> e.update(delta) );
		enemyBulletEntity.forEach( e -> e.update(delta) );
		dropEntity.forEach( e -> e.update(delta) );
		
		while(!requestQueue.isEmpty()) {
			Request curr = requestQueue.poll();
			curr.execute();
		}
		
		this.checkCollision(this.ambientEntity);
		this.checkCollision(this.allyEntity);
		this.checkCollision(this.allyBulletEntity);
		this.checkCollision(this.enemyEntity);
		this.checkCollision(this.enemyBulletEntity);
		this.checkCollision(this.dropEntity);
	}
	
	private void checkCollision(ArrayList<Entity> entities) {
		for(int i = 0; entities.size() > i; i++) {
			Entity curr = entities.get(i);
			if(curr.canCollideAmbient)
				this.ambientEntity.forEach(e -> curr.collide(e));
			if(curr.canCollideAlly)
				this.allyEntity.forEach(e -> curr.collide(e));
			if(curr.canCollideAllyBullet)
				this.allyBulletEntity.forEach(e -> curr.collide(e));
			if(curr.canCollideEnemy)
				this.enemyEntity.forEach(e -> curr.collide(e));
			if(curr.canCollideEnemyBullet)
				this.enemyBulletEntity.forEach(e -> curr.collide(e));
			if(curr.canCollideDrop)
				this.dropEntity.forEach(e -> curr.collide(e));
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
		dropEntity.forEach( e -> e.draw(graphics) );
		
		rw.draw( visual );
	}
	
	public void addRequest(Request r) {
		requestQueue.add(r);
	}
	
	public void addEntity(Entity entity) {
		switch(entity.getType())
		{
			case Entity.AMBIENT_TYPE:
				this.ambientEntity.add(entity);
				break;
			case Entity.ALLY_TYPE:
				this.allyEntity.add(entity);
				break;
			case Entity.ALLY_BULLET_TYPE:
				this.allyBulletEntity.add(entity);
				break;
			case Entity.ENEMY_TYPE:
				this.enemyEntity.add(entity);
				break;
			case Entity.ENEMY_BULLET_TYPE:
				this.enemyBulletEntity.add(entity);
				break;
			case Entity.DROP_TYPE:
				this.dropEntity.add(entity);
				break;
		}
	}
	
	public void removeEntity(Entity entity) {
		switch(entity.getType())
		{
			case Entity.AMBIENT_TYPE:
				this.ambientEntity.remove(entity);
				break;
			case Entity.ALLY_TYPE:
				this.allyEntity.remove(entity);
				break;
			case Entity.ALLY_BULLET_TYPE:
				this.allyBulletEntity.remove(entity);
				break;
			case Entity.ENEMY_TYPE:
				this.enemyEntity.remove(entity);
				break;
			case Entity.ENEMY_BULLET_TYPE:
				this.enemyBulletEntity.remove(entity);
				break;
			case Entity.DROP_TYPE:
				this.dropEntity.remove(entity);
				break;
		}
	}
}
