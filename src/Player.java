/**
 * Abstract class specifying common attributes and behaviours of Player objects.
 * 
 * @author Patricia Danielle Tan
 */
public abstract class Player {
	
	protected String name;
	protected char token;
	protected int winCount;
	
	/**
	 * Modifies the player's name.
	 * @param name A String representing the player's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Accesses the player's name.
	 * @return a String representing the player's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Accesses the player's token.
	 * @return a char representing the player's token
	 */
	public char getToken() {
		return this.token;
	}
	
	/**
	 * Accesses the player's win count.
	 * @return an int representing the player's win count
	 */
	public int getWinCount() {
		return this.winCount;
	}
	
	/**
	 * Sets the player's win count to a given value.
	 * @param winCount An int specifying the player's win count
	 */
	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}
	
	/**
	 * Increments the player's win count by 1.
	 */
	public void incrementWinCount() {
		this.winCount++;
	}

}
