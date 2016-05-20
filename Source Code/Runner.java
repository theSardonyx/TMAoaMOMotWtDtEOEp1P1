
public class Runner {
	public final static int RES_WIDTH = 960;
	public final static int RES_HEIGHT = 720;
	public final static String TITLE = "Magical Old Man";
	
	public static int SPLASH_STATE;
	public static int MENU_STATE;
	public static int GAME_STATE;
	public static int OPTION_STATE;
	public static int PAUSE_STATE;
        public static int GAME_OVER_STATE;
	
	/*
	Function for the main game loop
	Essentially sets up the objects needed for the game
	Within the game loop are three major processes
	Handle Input --> gets all the inputs
	Update --> Calculate physics, from position to collission, and adjust accordingly
	Render --> Draw objects according to calculations
	*/
	public static void main(String[] args) {
		RenderWindow w = new RenderWindow(TITLE, RES_WIDTH, RES_HEIGHT);
		InputCollector ic = new InputCollector(w);
		StateManager sm = StateManager.getInstance();
		
		GameState game = new GameState();
		
		SPLASH_STATE = sm.addState(new SplashState());
		MENU_STATE = sm.addState(new MenuState());
		GAME_STATE = sm.addState(game);
		OPTION_STATE = sm.addState(new OptionState());
		PAUSE_STATE = sm.addState(new PauseState(game));
                GAME_OVER_STATE = sm.addState(new GameOverState());
                
		sm.push(SPLASH_STATE, null);
                //sm.push(GAME_OVER_STATE, null);
		
		double lag = 0;
		double past = toSecond(System.nanoTime());
		double benchmark;
		while(true){
			
			// HANDLE INPUT
			ic.preProcess();
			sm.handleInput(ic);
			ic.postProcess();
			
			// UPDATE
			double now = toSecond(System.nanoTime());
			double dt = now - past;
			sm.update(dt);
			past = now;
			
			// RENDER
			w.preDraw();
			sm.render(w);
			w.finishDraw();
			
			benchmark = toSecond(System.nanoTime()) - past + lag;
			lag = 0;
			
			double spf = Config.getInstance().getSPF();
			if(benchmark>=spf) {
				lag = benchmark-spf;
			} else {
				long milli = toMilliSecond(spf-benchmark);
				try {
					Thread.sleep(milli);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	Method used to convert nanoseconds to seconds
	Used for timestep
	*/
	public static double toSecond(long nanoSecond) {
		return  (nanoSecond/1e9);
	}
	/*
	Method used to convert seconds to milliseconds
	Used for timestep
	*/
	public static long toMilliSecond(double second) {
		return (long) ((1e3)*second); 
	}
}
