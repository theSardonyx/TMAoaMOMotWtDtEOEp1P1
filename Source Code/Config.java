/**
* This class represents the configuration settings of the game.
*
* @author	Kryzl Pascual
*/
public class Config {
	/**
	* Holds the String representation of keys for player control
	*/
	String[] keys;
	
	/**
	* Holds how many times per second the frame updates
	*/
	int frameRate;
	
	/**
	* Whether particle effects will be displayed or not
	*/
	boolean particles;

	/**
	* Creates a new Config object to set this game's default settings
	*/
	public Config() {
		frameRate = 60;
		particles = true;
		keys = new String[] {"UP", "DOWN", "LEFT", "RIGHT", "SHIFT", "Z"};
	}
	
	/**
	* Gets the value of frameRate
	*
	* @return	#frameRate
	*/
	public int getFrameRate() {
		return frameRate;
	}
	
	/**
	* Toggles the value of frameRate depending on the previous
	* setting
	*/
	public void toggleFrameRate () {
		switch (frameRate) {
			case 30 : frameRate = 60;
			break;
			case 60 : frameRate = 120;
			break;
			case 120 : frameRate = 30;
			break;
		}
	}
	
	/**
	* Checks whether particle effects will be displayed or not
	*
	* @return	#particles
	*/
	public boolean getParticles() {
		return particles;
	}
	
	/**
	* Toggles the particle effects on or off depending on the
	* previous settings
	*/
	public void toggleParticles() {
		this.particles = !particles;
	}

	/**
	* Gets the key used to move the Player up
	*
	* @return	String	The String representation of the key
	*/
	public String getUpKey() {
		return keys[0];
	}
	
	/**
	* Gets the key used to move the Player down
	*
	* @return	String	The String representation of the key
	*/
	public String getDownKey() {
		return keys[1];
	}
	
	/**
	* Gets the key used to move the Player left
	*
	* @return	String	The String representation of the key
	*/
	public String getLeftKey() {
		return keys[2];
	}
	
	/**
	* Gets the key used to move the Player right
	*
	* @return	String	The String representation of the key
	*/
	public String getRightKey() {
		return keys[3];
	}
	
	/**
	* Gets the key used to move the Player slower
	*
	* @return	String	The String representation of the key
	*/
	public String getFocusKey() {
		return keys[4];
	}
	
	/**
	* Gets the key used to make the Player shoot bullets
	*
	* @return	String	The String representation of the key
	*/
	public String getShootKey() {
		return keys[5];
	}
	
	/**
	* Sets the key used to move the Player up
	*
	* @param	up	The String representation of the new key
	*/
	public void setUpKey (String up) {
		keys[0] = up;
	}
	
	/**
	* Sets the key used to move the Player down
	*
	* @param	down	The String representation of the new key
	*/
	public void setDownKey (String down) {
		keys[1] = down;
	}
	
	/**
	* Sets the key used to move the Player left
	*
	* @param	left	The String representation of the new key
	*/
	public void setLeftKey (String left) {
		keys[2] = left;
	}
	
	/**
	* Sets the key used to move the Player right
	*
	* @param	right	The String representation of the new key
	*/
	public void setRightKey (String right) {
		keys[3] = right;
	}
	
	/**
	* Sets the key used to move the Player slower
	*
	* @param	focus	The String representation of the new key
	*/
	public void setFocusKey (String focus) {
		keys[4] = focus;
	}
	
	/**
	* Sets the key used to make the Player shoot bullets
	*
	* @param	shoot	The String representation of the new key
	*/
	public void setShootKey (String shoot) {
		keys[5] = shoot;
	}
	
	/**
	* Gets all the settings contained within this Config class
	*
	* @return	String	All settings in this Config class in
	* 					order - #frameRate, #particles, #keys -
	*					and separated with |
	*/
	public String getSettings() {
		String ret = "" + frameRate + "|" + particles + "|";
		for (int i = 0; i < 6; i++) {
			ret += keys[i] + "|";
		}
		return ret;
	}
}