import java.util.Arrays;

/**
 * The representation of the list of Runes the Player is currently using
 * 
 * @author Kryzl Pascual
 */
public class RuneList {
	/**
	 * The actual list of runes
	 */
	String[] runes;
	
	/**
	 * A saved instance of a RuneList object
	 */
	private static RuneList instance;
	
	/**
	 * Creates a new RuneList that contains nothing
	 */
	private RuneList() {
		runes = new String[10];
		Arrays.fill(runes, "");
	}
	
	/**
	 * Gets a new or existing instance of the RuneList and makes sure only one instance
	 * is used throughout the entire game session
	 * 
	 * @return	#instance
	 */
	public static RuneList getInstance() {
		if (instance == null)
			instance = new RuneList();
		return instance;
	}
	
	/**
	 * Gets the Rune located at the specified index
	 * 
	 * @param index		The index you want to get the Rune from
	 * @return	String	The name of the Rune
	 */
	public String getRune (int index) {
		return runes[index];
	}
	
	/**
	 * Sets the specified room into the specified index
	 * 
	 * @param rune		The Rune you want to place
	 * @param index		The index where you want to put the specified Rune
	 */
	public void setRune (String rune, int index) {
		runes[index] = rune;
	}
	
	/**
	 * Gets the maximum number of runes that can be put in the RuneList
	 * 
	 * @return	int		The length of the String[] containing all runes
	 */
	public int getRuneNum() {
		return runes.length;
	}
}
