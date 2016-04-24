/**
* This class represents the configuration settings of the game.
*
* @author	Kryzl Pascual
*/
public class Config {
	/**
	* Holds how many times per second the frame updates
	*/
	int frameRate;

	/**
	* Creates a new Config object to set this game's default settings
	*/
	public Config() {
		frameRate = 30;
	}
	
	/**
	* Gets the value of frameRate
	*
	* @return	#frameRate
	*/
	publid int getFrameRate() {
		return frameRate;
	}
	
	/**
	* Sets the value of frameRate
	*
	* @param	frameRate	The number of times per second the frame
	* 						updates
	*/
	public void setFrameRate (int frameRate) {
		this.frameRate = frameRate;
	}
}