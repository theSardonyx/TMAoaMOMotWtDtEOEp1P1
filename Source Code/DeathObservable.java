public interface DeathObservable {
	public void addObserver (DeathObserver o);
	public void removeObserver (DeathObserver o);
	public void notifyObservers();
	public void die();
}