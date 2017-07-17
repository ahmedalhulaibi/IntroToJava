package battleship;

/**
 * Created by Ahmed Al-Hulaibi on 7/12/2017.
 */
public class Player {
    public BSAI aiModule;
    private Board board;
    private String playerName;

    public Player(Board board, String playerName)
    {
        this.board = board;
        this.aiModule = new BSAI(this.board);
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
