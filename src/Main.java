import java.util.Scanner;

/**
 * Developed by Mohammad Mahdi Salmani
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connect4Game game = new Connect4Game();
        int winner;
        System.out.println("********** Connect4 Game **********");
        System.out.print("Enter a number in range 0 to 6: ");
        for (int i = 1; (winner = game.isTerminal()) == -1; i++) {
            game.move(sc.nextInt(), 'X');
            if ((winner = game.isTerminal()) != -1) break;
            game.move(MiniMax.miniMax(game, 'O', 5, Integer.MIN_VALUE, Integer.MAX_VALUE).get(MiniMax.MOVE), 'O');
            game.print();
            System.out.println("********** turn " + i + " **********");
        }
        System.out.println("GAME OVER");
        System.out.print("The winner is player " + winner);
    }
}
