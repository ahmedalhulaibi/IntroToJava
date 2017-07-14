package battleship;

public class BattleShipGame {
    public static void main(String[] args) {
	// write your code here
        Player HumanPlayer = new Player();
        Player ComputerPlayer = new Player();

        Board HumanBoard = new Board(5);
        Board ComputerBoard = new Board(5);

        {
            System.out.println("Human you have the following ships: ");
            int n = 1;
            for (Ship ship: HumanBoard.getShips())
            {
                System.out.println("Ship " + Integer.toString(n) + " Length: " + Integer.toString(ship.getLength()));
                n++;
            }
        }

        System.out.print(HumanBoard);
        HumanBoard.placeShip('A',4,'h',HumanBoard.getShips().get(0));
        System.out.print(HumanBoard);
        HumanBoard.placeShip('B',1,'h',HumanBoard.getShips().get(1));
        System.out.print(HumanBoard);
        HumanBoard.placeShip('C',2,'v',HumanBoard.getShips().get(2));
        System.out.print(HumanBoard);

    }
}
