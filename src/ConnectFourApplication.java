import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Connect Four (CUI)
 * <p>
 * This program provides interaction with a console-based version of the 2-player board game Connect Four.
 * The program implements a player database .txt file that stores players' names and lifetime win counts, 
 * and includes all appropriate error handling.
 * 
 * @author Patricia Danielle Tan
 * @version 1.0 - 22.08.2016: Created
 */
public class ConnectFourApplication {
	
	/**
	 * Implements ConnectFourGame functionality and takes user keyboard input via console.
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Welcome to Connect Four!");
		System.out.println("------------------------");
		
		ConnectFourGame game = new ConnectFourGame();
		PlayerDatabase pd = new PlayerDatabase();
		Scanner keyboard = new Scanner(System.in);

		// READ PLAYER DATABASE FROM FILE
		try {
			File file = new File("player-database.txt");
			// if player database file does not exist, create it and write a blank database
			if(!file.exists()) {
				file.createNewFile();
				writeDatabaseFile(pd); 
			}
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("player-database.txt"));
			pd = (PlayerDatabase) in.readObject();
			in.close();
		} catch (FileNotFoundException e1) {
			System.out.println("Error: Player database not found. Program will now terminate.");
			System.exit(0);
		} catch (ClassNotFoundException e1) {
			System.out.println("Error: Player database not found. Program will now terminate.");
			System.exit(0);
		} catch (IOException e1) {
			System.out.println("Error: Updating player database failed. Program will now terminate.");
			System.exit(0);
		}
		
		// ENTER PLAYER ONE'S INFO
		System.out.println("\nPlayer X, please enter your name (case sensitive):");
		game.getPlayerX().setName(keyboard.nextLine());
		// check if this player name exists in database, and create new entry if absent
		if (pd.getPlayers().containsKey(game.getPlayerX().getName())) {
			game.getPlayerX().setWinCount(pd.getPlayers().get(game.getPlayerX().getName()));
			System.out.println("Welcome back, "+game.getPlayerX().getName());
		} else {
			pd.addPlayer(game.getPlayerX());
			System.out.println("Created new player record for "+game.getPlayerX().getName());
		}

		// ENTER PLAYER TWO'S INFO
		System.out.println("\nPlayer O, please enter your name (case sensitive):");
		game.getPlayerO().setName(keyboard.nextLine());
		// check if this player name exists in database, and create new entry if absent
		if (pd.getPlayers().containsKey(game.getPlayerO().getName())) {
			game.getPlayerO().setWinCount(pd.getPlayers().get(game.getPlayerO().getName()));
			System.out.println("Welcome back, "+game.getPlayerO().getName());
		} else {
			pd.addPlayer(game.getPlayerO());
			System.out.println("Created new player record for "+game.getPlayerO().getName());
		}
		
		// DISPLAY BOTH PLAYERS' LIFETIME WINS
		System.out.println("\n"+game.getPlayerX().getName()+"'s lifetime wins: "+pd.getPlayers().get(game.getPlayerX().getName()));
		System.out.println(game.getPlayerO().getName()+"'s lifetime wins: "+pd.getPlayers().get(game.getPlayerO().getName()));
		
		char playAgain = ' ';
		
		// START NEW GAME ROUND
		do {
			System.out.println("\nStarting new game...\n");

			do {
				game.getBoard().display();
				System.out.println("\nIt's "+game.determineTurn().getName()+"'s turn");
				int colChoice=0;
				
				// prompt player to make a move
				do {
					try {
						System.out.println("Select a column number to drop a token (1-7):");
						colChoice = keyboard.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Error: only integers accepted");
					}
					keyboard.nextLine();
				} while (colChoice<1 || colChoice>7);

				// if a valid move has been made, check for a winner
				if (game.moveIsMade(colChoice, game.determineTurn().getToken())) { 
					
					if (game.getWinner().findWinner(game.getBoard())!=' ') {
						if (game.getWinner().getWinnerToken()=='X') {
							game.getBoard().display();
							game.getPlayerX().incrementWinCount();
							pd.addPlayer(game.getPlayerX()); // overwrite player's database entry with new score
							System.out.println("\nGame over: The winner is "+game.getPlayerX().getName()+"!");
						}
						if (game.getWinner().getWinnerToken()=='O') {
							game.getBoard().display();
							game.getPlayerO().incrementWinCount();
							pd.addPlayer(game.getPlayerO()); // overwrite player's database entry with new score
							System.out.println("\nGame over: The winner is "+game.getPlayerO().getName()+"!");
						}
						if (game.getWinner().getWinnerToken()=='T') {
							game.getBoard().display();
							System.out.println("\nGame over: It's a tie!");
						}
						// write player database to file
						try {
							writeDatabaseFile(pd); 
						} catch (FileNotFoundException e2) {
							System.out.println("Error: Player database not found");
						} catch (IOException e2) {
							System.out.println("Error: Updating player database failed");
						}
					}
				}
			} while (!game.isOver());
			
			// GAME OVER: print lifetime wins & ask to either play another round or quit
			System.out.println("\n"+game.getPlayerX().getName()+"'s lifetime wins: "+pd.getPlayers().get(game.getPlayerX().getName()));
			System.out.println(game.getPlayerO().getName()+"'s lifetime wins: "+pd.getPlayers().get(game.getPlayerO().getName()));
			
			do {
				System.out.println("\nPlay another round? (Y/N)");
				playAgain = Character.toUpperCase(keyboard.next().charAt(0));
			} while (playAgain!='Y' && playAgain!='N');
			
			if (playAgain=='N') { // quit
				System.out.println("\nQuitting game. Goodbye!");
				keyboard.close();
				System.exit(0);
			}
			
		} while (playAgain=='Y'); // play a new round	
	}
	
	/**
	 * Writes a PlayerDatabase object to a .txt file.
	 * @param pd a PlayerDatabase object containing player names and lifetime wins
	 * @throws FileNotFoundException if the player-database.txt file cannot be found
	 * @throws IOException if the writing operation failed
	 */
	public static void writeDatabaseFile(PlayerDatabase pd) 
			throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("player-database.txt"));
		out.writeObject(pd);
		out.close();
	}

}
