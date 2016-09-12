import java.io.Serializable;
import java.util.HashMap;

/**
 * The PlayerDatabase class contains a HashMap of unique player names associated with their lifetime win counts,
 * and functionality for accessing and adding players to it. 
 * 
 * @author Patricia Danielle Tan
 */
public class PlayerDatabase implements Serializable {

	private HashMap<String, Integer> players;

	/**
	 * Constructs a new PlayerDatabase instance.
	 * */
	public PlayerDatabase() {
		players = new HashMap<>();
	}

	/**
	 * Accesses player names and their scores.
	 * @return a HashMap of Strings to Integers
	 * */
	public HashMap<String, Integer> getPlayers() {
		return this.players;
	}

	/**
	 * Adds a player to the database.
	 * @param p A Player object
	 * */
	public void addPlayer(Player p) {
		players.put(p.getName(), p.getWinCount());
	}
	
}
