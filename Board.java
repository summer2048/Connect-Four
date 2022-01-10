public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	
	/* 
	 * The board object must contain the board state in some manner.
	 * You must decide how you will do this.
	 * 
	 * You may add addition private/public methods to this class is you wish.
	 * However, you should use best OO practices. That is, you should not expose
	 * how the board is being implemented to other classes. Specifically, the
	 * Player classes.
	 * 
	 */
	private char[][] board;
	private int leftPosition;
	
	
	public Board() {
		board = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
		reset();
	}
	
	public void printBoard() {
		for (int i = 0; i < NUM_OF_ROW; i++){
			System.out.print("|");
			for (int j = 0; j < NUM_OF_COLUMNS; j++){
				System.out.print(board[i][j] + "|");
			}
			System.out.println("");
		}
	}
	
	public boolean containsWin() {
		for (int i = 0; i < NUM_OF_ROW-3; i++){
			for (int j = 0; j < NUM_OF_COLUMNS; j++){
				char n = board[i][j];
				if (n == ' ' || n == '_') continue;
				if (board[i+1][j] == n && board[i+2][j] == n && board[i+3][j] == n) return true;
			}
		}
		for (int i = 0; i < NUM_OF_ROW; i++){
			for (int j = 0; j < NUM_OF_COLUMNS-3; j++){
				char n = board[i][j];
				if (n == ' ' || n == '_') continue;
				if (board[i][j+1] == n && board[i][j+2] == n && board[i][j+3] == n) return true;
			}
		}
		for (int i = 0; i < NUM_OF_ROW-3; i++){
			for (int j = 0; j < NUM_OF_COLUMNS-3; j++){
				char n = board[i][j];
				if (n == ' ' || n == '_') continue;
				if (board[i+1][j+1] == n && board[i+2][j+2] == n && board[i+3][j+3] == n) return true;
			}
		}
		return false;
	}
	
	public boolean isTie() {
		return leftPosition == 0;
	}
	
	public void reset() {
		leftPosition = NUM_OF_COLUMNS * NUM_OF_ROW;
		for (int i = 0; i < NUM_OF_ROW; i++){
			for (int j = 0; j < NUM_OF_COLUMNS; j++){
				// Last row of board is initialized with '_'.
				if (i == NUM_OF_ROW - 1) board[i][j] = '_';
				// Other rows are initialized with ' '.
				else board[i][j] = ' ';
			}
		}
	}

	public void move(int column, char symbol){
		for (int i = NUM_OF_ROW-1; i >= 0; i--){
			if (board[i][column] == ' ' || board[i][column] == '_') {
				board[i][column] = symbol;
				leftPosition--;
				break;
			}
		}
	}
	
	public boolean isValid(int column){
		// Valid if top of chosen column is empty.
		return board[0][column] == ' ';
	}

	public char getSymbol(int row, int column){
		return board[row][column];
	}
}
