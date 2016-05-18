import java.util.Arrays;

/**
 * The representation of the list of Runes the Player is currently using
 * 
 * @author Kryzl Pascual
 */
public class PlayerSettings {
	public static final int BASIC_RUNE = 0;
	public static final int PIERCE_RUNE = 1;
	public static final int HOMING_RUNE = 2;
	public static final int SPREAD_RUNE = 3;
	public static final int EXPLOSION_RUNE = 4;
	public static final int SNIPE_RUNE = 5;
	public static final int SPLIT_RUNE = 6;
	public static final int BURST_RUNE = 7;
	public static final int SENTINEL_RUNE = 8;
	public static final int ANTI_RUNE = 9;
	public static final int SUMMON_RUNE = 10;
	/**
	 * The actual list of runes
	 */
	private int[] runes;
	
	private int hearts, score, kills, graze;
	
	private boolean changedRunes;
	
	/**
	 * A saved instance of a RuneList object
	 */
	private static PlayerSettings instance;
	
	/**
	 * Creates a new RuneList that contains nothing
	 */
	private PlayerSettings() {
		this.runes = new int[11];
		this.reset();
		this.changedRunes = false;
	}
	
	/**
	 * Gets a new or existing instance of the RuneList and makes sure only one instance
	 * is used throughout the entire game session
	 * 
	 * @return	#instance
	 */
	public static PlayerSettings getInstance() {
		if (instance == null)
			instance = new PlayerSettings();
		return instance;
	}
	
	public void reset() {
		Arrays.fill(this.runes, -1);
		this.hearts = 3;
		this.score = 0;
		this.kills = 0;
		this.graze = 0;
	}
	
	/**
	 * Gets the Rune located at the specified index
	 * 
	 * @param index		The index you want to get the Rune from
	 * @return	String	The name of the Rune
	 */
	public int getRune (int index) {
		return runes[index];
	}
	
	/**
	 * Sets the specified room into the specified index
	 * 
	 * @param rune		The Rune you want to place
	 * @param index		The index where you want to put the specified Rune
	 */
	public void setRune (int rune, int index) {
		runes[index] = rune;
		this.changedRunes = true;
	}
	
	public void addRune(int rune) {
		this.changedRunes = true;
		for(int i = 0; runes.length - 1 > i; i++) {
			if(runes[i] == -1) {
				runes[i] = rune;
				return;
			}
		}
		runes[runes.length - 1] = rune;
	}
	
	public boolean changedRunes() {
		return this.changedRunes;
	}
	
	public void setChangedRunes(boolean changedRunes) {
		this.changedRunes = changedRunes;
	}
	
	/**
	 * Gets the maximum number of runes that can be put in the RuneList
	 * 
	 * @return	int		The length of the String[] containing all runes
	 */
	public int getRuneNum() {
		return runes.length;
	}
	
	public void scoreIncrement(int score) {
		this.score += score;
	}
	
	public void scoreDecrement(int score) {
		this.score -= score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void graze() {
		this.graze++;
	}
	
	public void setGraze(int graze) {
		this.graze = graze;
	}
	
	public int getGraze() {
		return this.graze;
	}
	
	public void kill() {
		this.kills++;
	}
	
	public void setKills(int kills) {
		this.kills = kills;
	}
	
	public int getKills() {
		return this.kills;
	}
	
	public void heartIncrement(int hearts) {
		this.hearts += hearts;
	}
	
	public void heartIncrement() {
		this.hearts++;
	}
	
	public void heartDecrement(int hearts) {
		this.hearts -= hearts;
	}
	
	public void heartDecrement() {
		this.hearts--;
	}
	
	public void setHearts(int hearts) {
		this.hearts = hearts;
	}
	
	public int getHearts() {
		return this.hearts;
	}
}
