
public interface DeathObservable {
	public void registerDeathObserver(DeathObserver observer);
	public void removeDeathObserver(DeathObserver observer);
	public void notifyObservers();
}
