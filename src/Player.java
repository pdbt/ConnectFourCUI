/**
 * The Player class contains accessor and modifier methods for attributes
 * specific to a game player: name, token and win count.
 * 
 * @author Patricia Danielle Tan
 */
public class Player {
	
	private String name;
	private char token;
	private int winCount;
	
	public Player(char token) {
		this.name = "";
		this.token = token;
		this.winCount = 0;
	}
	
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
