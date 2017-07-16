package battleship;

/**
 * Created by Ahmed Al-Hulaibi on 7/12/2017.
 */
public class Player {
    public BSAI aiModule;
    private Board board;
    public Player(Board board)
    {
        this.board = board;
        this.aiModule = new BSAI(this.board);
    }
}
