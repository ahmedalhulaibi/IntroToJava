package battleship;

import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class BattleShipGame {
    public static void main(String[] args) {
	// write your code here

        Board HumanBoard = new Board(6, "Your Board: ");
        Board ComputerBoard = new Board(6, "Computer Board: ");
        Player HumanPlayer = new Player(HumanBoard, "Human Player");
        Player ComputerPlayer = new Player(ComputerBoard, "Computer Player");

        Scanner input = new Scanner(System.in);

        //playerShipPlacement(HumanBoard);
        computerShipPlacement(HumanBoard);

        computerShipPlacement(ComputerBoard);

        System.out.println("Let the games begin!");

        gameLoop(HumanPlayer,ComputerPlayer,HumanBoard,ComputerBoard);

        if(HumanBoard.isBoardClear())
        {
            System.out.println("Computer wins!");
        }

        if(ComputerBoard.isBoardClear())
        {
            System.out.println("Player wins!");
        }

        System.out.println(HumanBoard);
        System.out.println(ComputerBoard);
    }

    public static void playerShipPlacement(Board HumanBoard)
    {

        Scanner input = new Scanner(System.in);
        //inital player ship placement
        {
            System.out.println("Human you have the following ships: ");
            int n = 1;
            for (Ship ship: HumanBoard.getShips())
            {
                System.out.println("Ship #" + Integer.toString(n) + " Length: " + Integer.toString(ship.getLength()));
                n++;
            }

            System.out.print(HumanBoard);
            n = 1;


            for (Ship ship: HumanBoard.getShips())
            {
                boolean validInput = false;
                char row = ' ';
                int col = 1;
                char orientation = 'N';
                while(!validInput)
                {
                    System.out.println("Where would you like to place Ship #"
                            + Integer.toString(n)
                            + " Length: "
                            + Integer.toString(ship.getLength())
                            + " e.g. A2h");
                    String pos = input.nextLine();

                    validInput = Pattern.compile("[A-Z][1-9][hv]").matcher(pos).matches();
                    if(validInput)
                    {
                        row = pos.charAt(0);
                        col = Character.getNumericValue(pos.charAt(1));
                        orientation = pos.charAt(2);
                        //while input does not match [A-Z][1-9]
                        if(HumanBoard.placeShip(row,col,orientation,ship))
                        {
                            n++;
                        }
                        else
                        {
                            System.out.println("Ship can't be placed there.");
                            JOptionPane.showMessageDialog(null, "Ship can't be placed there.");
                            validInput = false;
                        }
                    }
                }
                System.out.print(HumanBoard);
            }
        }
    }

    public static void computerShipPlacement(Board ComputerBoard)
    {
        //initial computer ship placement
        {
            int n = 1;
            for (Ship ship: ComputerBoard.getShips())
            {
                //System.out.println("Ship #" + Integer.toString(n) + " Length: " + Integer.toString(ship.getLength()));
                n++;
            }

            System.out.print(ComputerBoard);
            n = 1;
            for (Ship ship: ComputerBoard.getShips())
            {
                boolean validInput = false;
                char row = ' ';
                int col = 1;
                char orientation = 'N';
                while(!validInput)
                {
                    //System.out.println("Where would you like to place Ship #" + Integer.toString(n) + " e.g. A2");
                    int randRow = Utility.randomInRange(0,ComputerBoard.getSize()-1);
                    int randCol = Utility.randomInRange(1,ComputerBoard.getSize()-1);
                    char randOrientation = 'N';
                    switch (Utility.randomInRange(0,1))
                    {
                        case 0:
                            randOrientation = 'v';
                            break;
                        case 1:
                            randOrientation = 'h';
                            break;
                    }
                    String pos = Utility.alphabet.toCharArray()[randRow] + Integer.toString(randCol) + randOrientation;
                    //System.out.println("Computer input: " + pos);
                    validInput = Pattern.compile("[A-Z][1-9][hv]").matcher(pos).matches();
                    if(validInput)
                    {
                        row = pos.charAt(0);
                        col = Character.getNumericValue(pos.charAt(1));
                        orientation = pos.charAt(2);

                        //while input does not match [A-Z][1-9]
                        if(ComputerBoard.placeShip(row,col,orientation,ship))
                        {
                            n++;
                        }
                        else
                        {

                            validInput = false;
                        }
                    }
                }
                //System.out.print(ComputerBoard);
            }
        }
    }

    public static void gameLoop(Player HumanPlayer, Player ComputerPlayer, Board HumanBoard, Board ComputerBoard)
    {
        Scanner input = new Scanner(System.in);
        while(!HumanBoard.isBoardClear() && !ComputerBoard.isBoardClear())
        {

            //playerTurn(HumanPlayer,ComputerBoard);
            computerTurn(HumanPlayer,ComputerBoard);

            if(!HumanBoard.isBoardClear() && !ComputerBoard.isBoardClear())
            {
                computerTurn(ComputerPlayer,HumanBoard);
            }

            System.out.println(HumanBoard);
            System.out.println(ComputerBoard.outputBoardOnlyHits());
        }
    }

    public static void playerTurn(Player HumanPlayer,Board EnemyBoard)
    {

        Scanner input = new Scanner(System.in);
        //take human input
        //attempt to hit computer board
        boolean successfulAttempt = false;
        while(!successfulAttempt)
        {
            System.out.println(HumanPlayer.getPlayerName() + " Turn. e.g. A2 or 'hint'");
            char row = ' ';
            int col = 1;
            String pos = input.nextLine();
                /*
                row = pos.charAt(0);
                col = Character.getNumericValue(pos.charAt(1));
                int posInt[] = {Utility.alphabet.indexOf(row),col-1};*/
            if(Pattern.compile("tip|help|hint").matcher(pos).matches())
            {
                System.out.println(HumanPlayer.aiModule.selectNextTarget());
                System.out.println("Human Turn. e.g. A2");
                pos = input.nextLine();
            }
            if(Pattern.compile("[A-Z][1-9]").matcher(pos).matches())
            {
                //pos = input.nextLine();
                row = pos.charAt(0);
                col = Character.getNumericValue(pos.charAt(1));
                int posInt[] = {Utility.alphabet.indexOf(row),col-1};
                if(!HumanPlayer.aiModule.hasPositionBeenAttempted(posInt))
                {
                    successfulAttempt = true;
                    HumanPlayer.aiModule.hitResult(posInt,EnemyBoard.hitShip(row, col));
                }else
                {
                    JOptionPane.showMessageDialog(null, "You have already tried to hit " + pos);
                    System.out.println("You have already tried to hit " + pos);
                }
            }
        }
    }

    public static  void computerTurn(Player ComputerPlayer, Board EnemyBoard)
    {
        char row = ' ';
        int col = 1;
        String pos = ComputerPlayer.aiModule.selectNextTarget();
        System.out.println(ComputerPlayer.getPlayerName() + " Turn " + pos);
        row = pos.charAt(0);
        col = Character.getNumericValue(pos.charAt(1));
        int posInt[] = {Utility.alphabet.indexOf(row),col-1};
        ComputerPlayer.aiModule.hitResult(posInt,EnemyBoard.hitShip(row,col));

    }
}
