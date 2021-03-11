import java.util.Arrays;
import java.util.Scanner;

class TicTacToeGame {
    private Scanner Input = new Scanner(System.in);
    private char[] board = new char[9];
    private char[] emptyBoard = new char[9];

    private void clearBoard() {
        Arrays.fill(board, ' ');
    }

    private void printBoard() {
       if(Arrays.equals(board, emptyBoard)) System.out.println("board is currently empty");
       else {
           for (int counter = 1; counter < board.length + 1; ++counter) {
               System.out.print(board[counter - 1] + " ");
               if (counter % 3 == 0) System.out.print("\n");
           }
       }
    }

    private boolean checkVictory(char playerChance) {
        return (board[0] == playerChance && board[1] == playerChance && board[2] == playerChance)
                || (board[3] == playerChance && board[4] == playerChance && board[5] == playerChance)
                || (board[6] == playerChance && board[7] == playerChance && board[8] == playerChance)
                || (board[0] == playerChance && board[4] == playerChance && board[8] == playerChance)
                || (board[2] == playerChance && board[4] == playerChance && board[6] == playerChance)
                || (board[0] == playerChance && board[3] == playerChance && board[6] == playerChance)
                || (board[1] == playerChance && board[4] == playerChance && board[7] == playerChance)
                || (board[2] == playerChance && board[5] == playerChance && board[8] == playerChance);
    }

    public void run() {
        int playerChance = 1;
        while (true) {
            printBoard();
            try {
                changeBoard(playerChance, checkVictory(((playerChance == 1) ? 'X' : 'O')));
            } catch (IllegalArgumentException e) {
                System.out.println(e.toString());
                continue;
            }
            if (checkVictory(((playerChance == 1) ? 'X' : 'O'))) {
                System.out.print("player " + ((playerChance == 1) ? 'X' : 'O') + " has won.\ndo you want to play again ? ('y' or 'n') : ");
                char command  = Character.toLowerCase(Input.next().charAt(0));
                if (command == 'y'){
                    playerChance = 1;
                    clearBoard();
                    continue;
                }
                else return;
            }
            playerChance *= -1;
        }
    }

    private void changeBoard(int playerChance, boolean victory) {
        System.out.print("player " + ((playerChance == 1) ? 'X' : 'O') + " please input your command(1 to 9) : ");
        int index = Input.nextInt();
        if (victory) return;
        if (index < 1 || index > board.length || board[index - 1] != ' ')
            throw new IllegalArgumentException("Illegal index");
        else board[index - 1] = ((playerChance == 1) ? 'X' : 'O');
    }

    public TicTacToeGame() {
        this.clearBoard();
        this.emptyBoard = board.clone();
    }
}

public class Game {
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.run();
    }
}
