import java.util.Scanner;
public class HumanPlayer extends Player {
    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    @Override
    public void makeMove(Board board) {
        System.out.println(name + ", please input your move:");
        int column = scanner.nextInt()-1; // Num of column is input - 1.
        while (!board.isValid(column)){
            System.out.println("Sorry, the column you choose is full. Please choose another column: ");
            column = scanner.nextInt()-1;
        }
        board.move(column, symbol);
    }
}
