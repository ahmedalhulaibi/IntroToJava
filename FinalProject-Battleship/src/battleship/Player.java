package battleship;

/**
 * Created by Ahmed Al-Hulaibi on 7/12/2017.
 * This class describes a Battleship player (human and computer)
 */
public class Player {
    //Battleship AI module
    public BSAI aiModule;
    //the player's board
    private Board board;
    //player's name
    private String playerName;

    /***
     * Player constructor, takes Board object as input and a String name
     * @param board
     * @param playerName
     */
    public Player(Board board, String playerName)
    {
        this.board = board;
        this.aiModule = new BSAI(this.board);
        this.playerName = playerName;
    }

    /***
     * Returns the palyer's name
     * @return string playerName
     */
    public String getPlayerName() {
        return playerName;
    }
}
