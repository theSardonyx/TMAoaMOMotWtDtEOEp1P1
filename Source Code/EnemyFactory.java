public interface EnemyFactory {
	protected void randomize();
	public Enemy createEnemy (Enemy e);
}