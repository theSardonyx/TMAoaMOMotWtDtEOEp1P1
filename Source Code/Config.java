public class Config {
	int frameRate;

	public Config() {
		// contains default values
		frameRate = 30;
	}
	
	publid int getFrameRate() {
		return frameRate;
	}
	
	public void setFrameRate (int frameRate) {
		this.frameRate = frameRate;
	}
}