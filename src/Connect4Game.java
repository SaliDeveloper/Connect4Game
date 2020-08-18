import java.util.ArrayList;
import java.util.List;

/**
 * Developed by Mohammad Mahdi Salmani
 */

public class Connect4Game {
    public static final int HEIGHT = 6;
    public static final int WIDTH = 7;
    private int lastMove = -1;
    private ArrayList<Character>[] board = new ArrayList[WIDTH];
    Direction.Sides winDir;

    public Connect4Game() {
        for (int i = 0; i < WIDTH; i++) {
            board[i] = new ArrayList<>();
        }
    }

    public Connect4Game(ArrayList<Character>[] board) {
//        this.lastMove = lastMove;
        this.board = board;
    }

    public ArrayList<Character>[] copyBoard() {
        ArrayList<Character>[] newBoard = new ArrayList[WIDTH];
        for (int i = 0; i < WIDTH; i++) {
            newBoard[i] = new ArrayList<>();
            newBoard[i].addAll(board[i]);
        }
        return newBoard;
    }

    public void move(int column, char turn) {
        lastMove = column;
        board[column].add(turn);
    }

    public List<Integer> getMoves() {
        List<Integer> moves = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            if (board[i].size() < HEIGHT)
                moves.add(i);
        }
        return moves;
    }

    //Returns:
//-1 if game is not over
//0 if the game is a draw
//1 if player X wins
//2 if player O wins
    public int isTerminal() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < board[i].size(); j++) {
                if (scorePoint('X', i, j, Direction.Sides.RIGHT) > 1000) return 1;
                if (scorePoint('O', i, j, Direction.Sides.RIGHT) > 1000) return 2;

                if (scorePoint('X', i, j, Direction.Sides.UP_RIGHT) > 1000) return 1;
                if (scorePoint('O', i, j, Direction.Sides.UP_RIGHT) > 1000) return 2;

                if (scorePoint('X', i, j, Direction.Sides.UP) > 1000) return 1;
                if (scorePoint('O', i, j, Direction.Sides.UP) > 1000) return 2;

                if (scorePoint('X', i, j, Direction.Sides.UP_LEFT) > 1000) return 1;
                if (scorePoint('O', i, j, Direction.Sides.UP_LEFT) > 1000) return 2;
            }
        }
        if (getMoves().size() == 0) return 0;
        else return -1;
    }

    public int scoreGame() {
        int score=0;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < board[i].size(); j++) {
                score+=scorePoint('X', i, j, Direction.Sides.RIGHT);
                score-=scorePoint('O', i, j, Direction.Sides.RIGHT);
                score+=scorePoint('X', i, j, Direction.Sides.UP_RIGHT);
                score-=scorePoint('O', i, j, Direction.Sides.UP_RIGHT);
                score+=scorePoint('X', i, j, Direction.Sides.UP);
                score-=scorePoint('O', i, j, Direction.Sides.UP);
                score+=scorePoint('X', i, j, Direction.Sides.UP_LEFT);
                score-=scorePoint('O', i, j, Direction.Sides.UP_LEFT);
            }
        }
        return score;
    }

    public int scorePoint(char turn, int x, int y, Direction.Sides d) {
        int score;
        try {
            if (board[x].get(y) != turn) {
                score = 0;
            } else {
                score = 10 * scorePoint(turn, x + Direction.X(d), y + Direction.Y(d), d) + 1;
            }
            if(score>1000){
                winDir=d;

            }
            return score;
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    public void print() {
        System.out.println("+---+---+---+---+---+---+---+");
        for (int j = HEIGHT - 1; j >= 0; j--) {
            for (int i = 0; i < WIDTH; i++) {
                if (board[i].size() > j) {
                    System.out.print("| " + board[i].get(j) + " ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
            System.out.println("+---+---+---+---+---+---+---+");
        }
    }
}
