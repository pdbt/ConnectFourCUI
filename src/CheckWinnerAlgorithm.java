/**
 * The CheckWinnerAlgorithm class contains a field for the winning token, determined by 
 * an algorithm that checks for a winner based on the current game board.
 * 
 * @author Patricia Danielle Tan
 */
public class CheckWinnerAlgorithm {

	private char winnerToken;

	/**
	 * Constructs a new CheckWinnerAlgorithm instance.
	 */
	public CheckWinnerAlgorithm() {
		this.winnerToken = ' ';
	}
	
	/**
	 * Accesses the winning token.
	 * @return a char representing the winning token
	 */
	public char getWinnerToken() {
		return this.winnerToken;
	}
	
	/**
	 * Resets the winning token field to blank.
	 */
	public void resetWinnerToken() {
		this.winnerToken = ' ';
	}
	
	/**
	 * Checks if there are four identical tokens in a vertical, horizontal or diagonal row
	 * on the current game board, returning the winning token or blank if no winner.
	 * @param currentBoard A 2D char array representing the slots in the current 6x7 game board
	 * @return a char representing the winning token
	 */
	private char winner(char[][] currentBoard) {
		// array of directions to check (down, back diagonal, forward diagonal, across)
	    int[][] directions = {{1,0}, {1,-1}, {1,1}, {0,1}};
	    
	    // for each direction... 
	    for (int[] d : directions) {
	        int dx = d[0];
	        int dy = d[1];
	        
	        // for each coordinate...
	        for (int x=0; x<6; x++) {
	            for (int y=0; y<7; y++) {
	            	
	            	// check if this token and the next 3 tokens in this direction are non-empty and identical
	                int lastX = x + 3*dx;
	                int lastY = y + 3*dy;
	                if (0<=lastX && lastX<6 && 0<=lastY && lastY<7) {
	                    char w = currentBoard[x][y];
	                    if (w != ' ' && w == currentBoard[x+dx][y+dy] 
	                                 && w == currentBoard[x+2*dx][y+2*dy] 
	                                 && w == currentBoard[lastX][lastY]) {
	                        return w; // return winning token
	                    }
	                }
	            }
	        }
	    }
	    return ' '; // return blank if no winner
	}
	
	/**
	 * Checks whether or not the game board is currently full.
	 * @param currentBoard A 2D char array representing the slots in the current 6x7 game board
	 * @return a boolean, true if the board is full and false otherwise.
	 */
	private boolean isFull(char[][] currentBoard) {
		for (int col=0; col<7; col++) {
			if (currentBoard[0][col]==' ') {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns the winning token based on the current game board, blank if no winner or 'T' if
	 * game is a tie.
	 * @param board A Board object representing the current game board
	 * @return a char representing the winning token
	 */
	public char findWinner(Board board) {
		char[][] currentBoard = board.getBoard();
		
		if (winner(currentBoard)!=' ') {
			this.winnerToken = winner(currentBoard);
			return this.winnerToken;
		}
		if (isFull(currentBoard)) {
			this.winnerToken = 'T';
			return this.winnerToken;
		}
		return this.winnerToken;
	}

}
