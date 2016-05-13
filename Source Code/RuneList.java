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
	
	public static RuneList getInstance() {
		if (instance == null)
			instance = new RuneList();
		return instance;
	}
	
	public String getRune (int index) {
		return runes[index];
	}
	
	public void setRune (String rune, int index) {
		runes[index] = rune;
	}
	
	public int getRuneNum() {
		return runes.length;
	}
}
