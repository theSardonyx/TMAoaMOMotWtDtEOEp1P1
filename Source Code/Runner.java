
public class Runner {
	public final static float FPS = 30.0f;
	public final static float SPF = FPS/2.0f;
	public final static int RES_WIDTH = 800;
	public final static int RES_HEIGHT = 600;
	public final static String TITLE = "Magical Old Man";
	
	public static void main(String[] args) {
		RenderWindow w = new RenderWindow(TITLE, RES_WIDTH, RES_HEIGHT);
		while(w.isActive()){
			w.preDraw();
			w.draw();
			w.finishDraw();
			try{
				Thread.sleep(33);
			} catch (Exception e) {
				
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
