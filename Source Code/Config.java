import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
* This class represents the configuration settings of the game.
*
* @author	Kryzl Pascual
*/
public class Config {
	/**
	 * A saved instance of a Config object
	 */
	private static Config instance;
	
	/**
	* Holds the key codes of the keys for player control
	*/
	int[] keys;
	
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
	private Config() {
		frameRate = 60;
		particles = true;
		keys = new int[] {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SHIFT, KeyEvent.VK_Z, KeyEvent.VK_P};
	}
	
	/**
	 * Gets a new or existing instance of the Config class and makes sure only one instance
	 * is used throughout the entire game session
	 * 
	 * @return	#instance
	 */
	public static Config getInstance() {
		if (instance == null) {
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				instance = mapper.readValue (new File ("config.json"), Config.class);
			} catch (JsonGenerationException e) {
				
			} catch (JsonMappingException e) {
				
			} catch (IOException e) {
				
			}
			
			if (instance == null)
				instance = new Config();
		}
		return instance;
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
	* @return	int		The key code of the key
	*/
	public int getUpKey() {
		return keys[0];
	}
	
	/**
	* Gets the key used to move the Player down
	*
	* @return	int		The key code of the key
	*/
	public int getDownKey() {
		return keys[1];
	}
	
	/**
	* Gets the key used to move the Player left
	*
	* @return	int		The key code of the key
	*/
	public int getLeftKey() {
		return keys[2];
	}
	
	/**
	* Gets the key used to move the Player right
	*
	* @return	int		The key code of the key
	*/
	public int getRightKey() {
		return keys[3];
	}
	
	/**
	* Gets the key used to move the Player slower
	*
	* @return	int		The key code of the key
	*/
	public int getFocusKey() {
		return keys[4];
	}
	
	/**
	* Gets the key used to make the Player shoot bullets
	*
	* @return	int		The key code of the key
	*/
	public int getShootKey() {
		return keys[5];
	}
	
	/**
	* Gets the key used to make the game pause
	*
	* @return	int		The key code of the key
	*/
	public int getPauseKey() {
		return keys[6];
	}
	
	/**
	* Sets the key used to move the Player up
	*
	* @param	up	The key code of the new key
	*/
	public void setUpKey (int up) {
		keys[0] = up;
	}
	
	/**
	* Sets the key used to move the Player down
	*
	* @param	down	The key code of the new key
	*/
	public void setDownKey (int down) {
		keys[1] = down;
	}
	
	/**
	* Sets the key used to move the Player left
	*
	* @param	left	The key code of the new key
	*/
	public void setLeftKey (int left) {
		keys[2] = left;
	}
	
	/**
	* Sets the key used to move the Player right
	*
	* @param	right	The key code of the new key
	*/
	public void setRightKey (int right) {
		keys[3] = right;
	}
	
	/**
	* Sets the key used to move the Player slower
	*
	* @param	focus	The key code of the new key
	*/
	public void setFocusKey (int focus) {
		keys[4] = focus;
	}
	
	/**
	* Sets the key used to make the Player shoot bullets
	*
	* @param	shoot	The key code of the new key
	*/
	public void setShootKey (int shoot) {
		keys[5] = shoot;
	}
	
	/**
	* Sets the key used to make the Player shoot bullets
	*
	* @param	pause	The key code of the new key
	*/
	public void setPauseKey (int pause) {
		keys[6] = pause;
	}
}