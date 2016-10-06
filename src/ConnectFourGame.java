/**
 * The ConnectFourGame class contains all essential game components: the board, players, 
 * turn counter and an algorithm to check for the winner, and specifies game behaviours 
 * around these components.
 * 
 * @author Patricia Danielle Tan
 */
public class ConnectFourGame {
	
	private Board board;
	private Player playerX;
	private Player playerO;
	private int turnCount;
	private CheckWinnerAlgorithm checkWinner;
	
	/**
	 * Constructs a new ConnectFourGame instance.
	 */
	public ConnectFourGame() {
		this.board = new Board();
		this.playerX = new Player('X');
		this.playerO = new Player('O');
		this.turnCount = 1;
		this.checkWinner = new CheckWinnerAlgorithm();
	}
	
	/**
	 * Accesses the current game board.
	 * @return a Board object representing the current game board
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 * Accesses player X and their attributes. 
	 * @return a PlayerX object
	 */
	public Player getPlayerX() {
		return this.playerX;
	}

	/**
	 * Accesses player O and their attributes.
	 * @return a PlayerO object
	 */
	public Player getPlayerO() {
		return this.playerO;
	}

	/**
	 * Returns the winner of the game.
	 * @return a CheckWinnerAlgorithm object containing the winner token
	 */
	public CheckWinnerAlgorithm getWinner() {
		return this.checkWinner;
	}

	/**
	 * Determines whose turn it is based on the game's turn counter.
	 * @return the Player whose turn it currently is
	 */
	public Player determineTurn() {
		if (this.turnCount % 2 == 0) {
			return this.playerO;
		} 
		else {
			return this.playerX;
		}
	}

	/**
	 * Assesses whether a player move can be made, placing a token if valid.
	 * @param colNum An int specifying the column chosen by the player
	 * @param playerToken A char representing the player's token
	 * @return a boolean, true if a valid move has been made and false otherwise
	 */
	public boolean moveIsMade(int colNum, char playerToken) {
		// move cannot be made if selected column is full
		if (board.getBoard()[0][colNum-1] != ' ') {
			System.out.println("The selected column is full.");
			return false;
		}
		// if column is not full, place token at bottom-most available spot
		for (int row=0; row<6; row++) {
			if (board.getBoard()[row][colNum-1] != ' ') {
				board.getBoard()[row-1][colNum-1] = playerToken;
				this.turnCount++;
				return true;
			}
		}
		// place token at bottom of empty column
		board.getBoard()[5][colNum-1] = playerToken;
		this.turnCount++;
		return true;
	}
	
	/**
	 * Specifies whether the game is over.
	 * @return a boolean, true if the game is over and false otherwise
	 */
	public boolean isOver() {
		if (this.checkWinner.findWinner(this.board)!=' ') {
			this.resetGame();
			return true;
		}
		return false;
	}

	/**
	 * Resets the game to a beginning state.
	 */
	public void resetGame() {
		this.board.clearBoard();
		this.checkWinner.resetWinnerToken();
		this.turnCount = 1;
	}
	
}
