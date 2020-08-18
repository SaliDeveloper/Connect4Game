import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * Developed by Mohammad Mahdi Salmani
 */

public class MiniMax {
    public static final String BEST_SCORE = "best_score";
    public static final String MOVE = "move";

    public static Dictionary<String,Integer> miniMax(Connect4Game game, char player, int depth, int alpha, int beta) {
        int status = game.isTerminal();
        int bestMove = -1;
        int bestScore;
        switch (status) {
            case 0:
                bestScore = 0;
                break;
            case 1:
                bestScore = Integer.MAX_VALUE;
                break;
            case 2:
                bestScore = Integer.MIN_VALUE;
                break;
            default:
                if (depth > 0) {
                    List<Integer> moves = game.getMoves();
                    Connect4Game subGame;
                    if (player == 'X') {
                        bestScore = Integer.MIN_VALUE;
                        for (int move : moves) {
                            subGame = new Connect4Game(game.copyBoard());
                            subGame.move(move, player);
                            int score = miniMax(subGame, 'O', depth - 1, alpha, beta).get(BEST_SCORE);
                            if (score > bestScore) {
                                bestMove = move;
                                bestScore = score;
                            }
                            alpha = Math.max(alpha, bestMove);
                            if (bestScore == Integer.MAX_VALUE) break;
                            if (depth <= 2 && alpha >= beta)    break;
                        }
                    } else {
                        bestScore = Integer.MAX_VALUE;
                        for (int move : moves) {
                            subGame = new Connect4Game(game.copyBoard());
                            subGame.move(move, player);
                            int score = miniMax(subGame, 'X', depth - 1, alpha, beta).get(BEST_SCORE);
                            if (score <= bestScore) {
                                bestMove = move;
                                bestScore = score;
                            }
                            beta = Math.min(beta, bestScore);
                            if (bestScore == Integer.MIN_VALUE) break;
                            if (depth <= 2 && alpha >= beta)    break;
                        }
                    }
                } else {
                    bestScore = game.scoreGame();
                }
        }
        Dictionary<String,Integer> dictionary = new Hashtable<>();
        dictionary.put(BEST_SCORE, bestScore);
        dictionary.put(MOVE, bestMove);
        return dictionary;
    }
}
