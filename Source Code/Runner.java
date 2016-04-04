
public class Runner {
	public final static double FPS = 30.0;
	public final static double SPF = 1.0/FPS;
	public final static int RES_WIDTH = 800;
	public final static int RES_HEIGHT = 600;
	public final static String TITLE = "Magical Old Man";
	
	public static void main(String[] args) {
		RenderWindow w = new RenderWindow(TITLE, RES_WIDTH, RES_HEIGHT);
		double lag = 0;
		double past;
		double benchmark;
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
	
	public static double toSecond(long nanoSecond) {
		return  (nanoSecond/1e9);
	}

	public static long toMilliSecond(double second) {
		return (long) ((1e3)*second); 
	}
}
