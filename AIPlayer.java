import java.util.Random;

public class AIPlayer extends Player {
    private char oppoSymbol;

    public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    private void setOppoSymbol(){
        oppoSymbol = ' ';
        for (int i = 0; i < 6; i++){
			for (int j = 0; j < 7; j++){
				if (board.getSymbol(i, j) != ' ' && board.getSymbol(i, j) != '_' && board.getSymbol(i, j) != symbol){
                    oppoSymbol = board.getSymbol(i, j);
                    return;
                }
			}
		}
    }

    @Override
    public void makeMove(Board board) {

        setOppoSymbol();
        for (int i = 0; i < 7; i++){
            if (willWin(i, symbol)) {
                board.move(i, symbol);
                return;
            }
        }
        for (int i = 0; i < 7; i++){
            if (willWin(i, oppoSymbol)) {
                board.move(i, symbol);
                return;
            }
        }
        Random ran = new Random();
        int column = ran.nextInt(7);
        while (!board.isValid(column)){
            column = ran.nextInt(7);
        }
        board.move(column, symbol);
    }

    private int getRow(int column){
        int row = 0;
        while  (row < 6){
            if (board.getSymbol(row, column) != ' ' && board.getSymbol(row, column) != '_') break;
            row++;
        }
        return row-1;
    }

    private char getHypoSymbol(int row, int column, int hypoRow, int hypoColumn, char hypoSymbol){
        if (row == hypoRow && column == hypoColumn) return hypoSymbol;
        return board.getSymbol(row, column);
    }

    private boolean willWin(int column, char aSymbol){
        if (!board.isValid(column)) return false; // If given column is not valid move.
        char n = aSymbol;
        int row = getRow(column);
        
        for (int i = 0; i < 3; i++){
			for (int j = 0; j < 7; j++){
                if (getHypoSymbol(i, j, row, column, n) == n && getHypoSymbol(i+1, j, row, column, n) == n && getHypoSymbol(i+2, j, row, column, n) == n && getHypoSymbol(i+3, j, row, column, n) == n) return true;
			}
		}
        for (int i = 0; i < 6; i++){
			for (int j = 0; j < 4; j++){
		        if (getHypoSymbol(i, j, row, column, n) == n && getHypoSymbol(i, j+1, row, column, n) == n && getHypoSymbol(i, j+2, row, column, n) == n && getHypoSymbol(i, j+3, row, column, n) == n) return true;
			}
		}
        for (int i = 0; i < 3; i++){
			for (int j = 0; j < 4; j++){
		        if (getHypoSymbol(i, j, row, column, n) == n && getHypoSymbol(i+1, j+1, row, column, n) == n && getHypoSymbol(i+2, j+2, row, column, n) == n && getHypoSymbol(i+3, j+3, row, column, n) == n) return true;
			}
		}
		return false;
    }
}
