
public class Runner {
	public final static float FPS = 30.0f;
	public final static float SPF = 1.0f/FPS;
	public final static int RES_WIDTH = 800;
	public final static int RES_HEIGHT = 600;
	public final static String TITLE = "Magical Old Man";
	
	public static void main(String[] args) {
		RenderWindow w = new RenderWindow(TITLE, RES_WIDTH, RES_HEIGHT);
		float lag = 0;
		float past;
		float benchmark;
		while(true){
			past = toSecond(System.nanoTime());
			
			// HandleInput
			// Update
			
			w.preDraw();
			// Render
			w.finishDraw();
			
			benchmark = toSecond(System.nanoTime()) - past + lag;
			lag = 0;
			
			if(benchmark>=SPF) {
				lag = benchmark-SPF;
			} else {
				long milli = toMilliSecond(SPF-benchmark);
				try {
					Thread.sleep(milli);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static float toSecond(long nanoSecond) {
		return (float) (nanoSecond/1e9);
	}

	public static long toMilliSecond(float second) {
		return (long) ((1e3)*second); 
	}
}
