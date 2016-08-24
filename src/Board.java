/**
 * The Board class contains a representation of the slots in the 6x7 game board 
 * as a 2D char array, and behaviours for displaying and clearing the board.
 * 
 * @author Patricia Danielle Tan
 */
public class Board {

	private char[][] board;
	
	/**
	 * Constructs a new Board instance.
	 */
	public Board() {
		this.board = new char[6][7];
		for (int row=0; row<6; row++) {
			for (int col=0; col<7; col++) {
				board[row][col] = ' ';
			}
		}
	}
	
	/**
	 * Accesses the board's slots.
	 * @return a 2D char array representing the slots in the current 6x7 game board
	 */
	public char[][] getBoard() {
		return this.board;
	}
	
	/**
	 * Displays the board to the console.
	 */
	public void display() {
		for (int row=0; row<6; row++) {
			System.out.print("|");
			for (int col=0; col<7; col++) {
				System.out.print(board[row][col] + "|");
			}
			System.out.println();
		}
		// Column labels
		System.out.println("---------------");
		System.out.println("|1|2|3|4|5|6|7|");
	}
	
	/**
	 * Clears all the board's slots.
	 */
	public void clearBoard() {
		for (int row=0; row<6; row++) {
			for (int col=0; col<7; col++) {
				board[row][col] = ' ';
			}
		}
	}
	
}
