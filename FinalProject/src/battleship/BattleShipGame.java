package battleship;

public class BattleShipGame {
    public static void main(String[] args) {
	// write your code here
        Player HumanPlayer = new Player();
        Player ComputerPlayer = new Player();

        Board HumanBoard = new Board(5);
        Board ComputerBoard = new Board(5);

        HumanBoard.placeShip(0,0,'h',HumanBoard.getShips().get(0));

    }
}
