/**
 * The Configurations class represents a game configuration for a board game.
 * It includes methods for managing the board, checking for win conditions, 
 * and interacting with a hash dictionary.
 */
public class Configurations {

	private char[][] board;
	private int lengthToWin;
	private int max_levels;
	private int boardSize;
	

	/**
    * Constructs a new Configurations object with the specified board size,
    * length to win, and maximum levels.
    * 
    * @param boardSize The size of the game board.
    * @param lengthToWin The number of symbols in a row needed to win.
    * @param max_levels The maximum levels in the game.
    */
	public Configurations(int boardSize, int lengthToWin, int max_levels) {
		this.boardSize = boardSize;
		this.lengthToWin = lengthToWin;
		this.max_levels = max_levels;
		this.board = new char[boardSize][boardSize];
		
		//create a board separated by spaces 
		for (int i=0; i<boardSize; i++) {
			for (int j=0; j<boardSize; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	/**
     * Creates and returns a new HashDictionary.
     * 
     * @return A new HashDictionary object.
     */
	public HashDictionary createDictionary() {
		int primeNumber = 9973; 
		return new HashDictionary(primeNumber);
	}
	
	/**
     * Checks if the current board configuration is already in the hash table.
     * 
     * @param hashtable The hash table to check.
     * @return The score associated with the configuration if found, 0 otherwise.
     */
	public int repeatedConfiguration(HashDictionary hashtable) {
		String boardStrings = boardToString();
		int result = 0;
		result = hashtable.get(boardStrings);
		return result;
	}
	
	/**
     * Adds the current board configuration to the hash table with the given score.
     * 
     * @param hashTable The hash table to add the configuration to.
     * @param score The score to associate with the configuration.
     */
	public void addConfiguration(HashDictionary hashTable, int score) {
		String boardStrings = boardToString();
		Data record = new Data(boardStrings,score);
		hashTable.put(record);
	}
	
	/**
     * Converts the current board configuration to a string representation.
     * 
     * @return The string representation of the board.
     */
	private String boardToString() {
		String boardString = "";
		for (int i=0; i<boardSize; i++) {
			for (int j=0; j<boardSize; j++) {
				boardString += board[i][j];
			}
		}
		return boardString;
	}
	
	 /**
     * Saves a play by placing the given symbol at the specified row and column.
     * 
     * @param row The row to place the symbol.
     * @param col The column to place the symbol.
     * @param symbol The symbol to place.
     */
	public void savePlay(int row, int col, char symbol) {
		board[row][col] = symbol;
	}
	
	/**
     * Checks if the specified square on the board is empty.
     * 
     * @param row The row of the square.
     * @param col The column of the square.
     * @return true if the square is empty, false otherwise.
     */
	public boolean squareIsEmpty(int row, int col) {
		if (board[row][col] == ' ') {
			return true;
		}
		return false;
	}
	
	/**
     * Checks if the specified symbol has won the game.
     * 
     * @param symbol The symbol to check for a win.
     * @return true if the symbol has won, false otherwise.
     */
	public boolean wins(char symbol) {
		// Check for horizontal first
		for (int i = 0; i < boardSize; i++) {
			int count = 0;
			for (int j = 0; j < boardSize; j++) {
				if (board[i][j] == symbol) {
					count++;
				}
				else {
					count = 0;
				}
				
				if (count >= this.lengthToWin) {
					return true;
				}
			}
		}
		
		// Check for vertical wins 
		for (int i = 0; i < boardSize; i++) {
			int count = 0;
			for (int j = 0; j < boardSize; j++) {
				if (board[j][i] == symbol) {
					count++;
				}
				else {
					count = 0;
				}
				
				if (count >= this.lengthToWin) {
					return true;
				}
			}	
		}
		
	 //Check for a diagonal win (bottom up)
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				int count = 0;
				for (int x = 0; x < this.lengthToWin; x++) {
					if (i-x < 0 || i-x >= boardSize || j+x < 0 || j+x >= boardSize) {
						continue;
					}
					if (board[i-x][j+x] == symbol) {
						count++;
					}
					else {
						count = 0;
					}
					if (count >= this.lengthToWin) {
						return true;
					}
				}
			}
		}
		
		//Check for diagonal wins (top down)
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
		
				int count = 0;
				for (int x = 0; x < this.lengthToWin; x++) {
					if (i+x < 0 || i+x >= boardSize || j+x < 0 || j+x >= boardSize) {
						continue;
					}
					if (board[i+x][j+x] == symbol) {
						count++;
					}
					else {
						count = 0;
					}
					if (count >= this.lengthToWin) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	 /**
     * Evaluates the current state of the board.
     * 
     * @return 2 if the game is a draw, 3 if 'O' wins, 0 if 'X' wins, 1 if the game is ongoing.
     */
	public int evalBoard() {
		if (isDraw() == true) {
			return 2;
		}
		else if (wins('O') == true) {
			return 3;
		}else if (wins('X')== true) {
			return 0;
		}else {
			return 1;
		}
	}
	
	  /**
     * Checks if the game is a draw.
     * 
     * @return true if the game is a draw, false otherwise.
     */
	public boolean isDraw() {
		boolean draw = true;
		for (int i = 0; i< boardSize; i++) {
			for (int j=0; j<boardSize; j++) {
				if (board[i][j] == ' ') {
					draw = false;
				}
			}
		}
		return draw;
	}

}