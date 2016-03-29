public class PeonFactory implements EnemyFactory {
	MoveBehavior[] movelist;
	ShootBehavior[] shootlist;
	
	protected void randomize();
	public Enemy createEnemy (Enemy e);
}