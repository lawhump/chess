package src.com.lhumphr2.chess.model;

/**
 * Created by lawrencehumphrey on 9/6/15.
 * Attributes of a player playing a game, man.
 */
public class Player {
    // Name of this player
    private String name = "Sam";
    // Unique identifier for this player. Similar to P1, P2, etc.
    private final int ID;
    // Score of this player this game.
    private int score = 0;
    // Used to assign unique IDs
    private static int numPlayers = 1;
    // Exclusive to chess but w/e
    private static boolean inCheck = false;

    public Player() {
        this.ID = numPlayers;
        numPlayers++;
    }

    public Player(String name) {
        this.name = name;
        ID = numPlayers;
        numPlayers++;
    }

    // Getters and setters. Nothing special here.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static boolean isInCheck() {
        return inCheck;
    }

    public static boolean isNotInCheck() { return !inCheck; }

    public static void setInCheck(boolean inCheck) {
        Player.inCheck = inCheck;
    }

    // Convenience methods to modify score of this player
    public void incrementScore(int val) {
        score += val;
    }

    public void decrementScore(int val) {
        score -= val;
    }

    void reset() {
        score = 0;
        inCheck = false;
    }
}
