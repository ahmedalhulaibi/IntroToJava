package battleship;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ahmed Al-Hulaibi on 7/12/2017.
 * This class describes a board object
 */
public class Board {
    //describes size of the board as a single dimension e.g. size=5 then board is 5x5
    private int size;

    //two dimensional array of integers to track board positions
    private int boardArray[][];

    //a list a ships associated with the board
    private ArrayList<Ship> ships = new ArrayList<>();

    //board name
    private String boardName = "";

    /***
     * Board constructor takes size as input and a String for the name of the board
     * If size < 5 then it will default to 5
     * @param size
     * @param boardName
     */
    public Board(int size, String boardName)
    {
        this.boardName = boardName;
        Utility.clamp(size,5,26);
        if(size < 5)
        {
            size = 5;
        }
        this.size = size;
        this.boardArray = new int[this.size][this.size];
        for(int i = 0; i < this.size; i++)
        {
            for(int j = 0; j < this.size; j++)
            {
                this.boardArray[i][j] = 0;
            }
        }

        //generate ships programmatically based on size
        // if board is 5x5 then the sum of all ship lengths can be no more than 9
        int shipSizeMax = (size - 2);
        int shipLengthLeft = shipSizeMax * shipSizeMax;
        //ensure minimum ship length of 2
        while(shipLengthLeft > 1)
        {
            int newShipLength = Utility.randomInRange(2, Math.min(shipSizeMax,shipLengthLeft));
            ships.add(new Ship(newShipLength));
            shipLengthLeft -= newShipLength;
        }
    }

    /***
     * Place ship function places a ship on the board
     * @param y Represents the row in the table, sample input y = 'A'
     * @param x Represents the column in the table, sample input x = 1
     * @param orientation orientation = 'h' or 'v' for 'horizontal' or 'vertical'
     * @param aShip ship object to be placed on board
     * @return boolean if ship placed successfully
     */
    public boolean placeShip(char y, int x, char orientation, Ship aShip)
    {
        //Translate input to integer representations
        int yInt = Utility.clamp(Utility.alphabet.indexOf(y),0, this.size - 1);
        return placeShip(Utility.clamp(x - 1,0,this.size - 1), yInt, orientation, aShip);
    }

    /***
     * Place ship function places a ship on the board
     * @param x Represents the column in the table
     * @param y Represents the row in the table
     * @param orientation orientation = 'h' or 'v' for 'horizontal' or 'vertical'
     * @param aShip ship object to be placed on board
     * @return boolean if ship placed successfully
     */
    public boolean placeShip(int x, int y, char orientation, Ship aShip)
    {
        //if not yet used
        boolean setShipOnBoard = true;
        if(aShip.getOrientation() == 'N')
        {
            int shipHeadPos = y * this.size + x;
            int shipTailPos;
            switch(orientation)
            {
                //if trying to place ship horizontally
                case 'h':
                    //check if pos is used
                    if(x + (aShip.getLength() - 1) < this.size)
                    {
                        for(int xCopy = x; xCopy < this.size && xCopy <= x + (aShip.getLength() - 1); xCopy++)
                        {
                            //if this position is not free, then do not place ship on board
                            if(this.boardArray[y][xCopy] != 0)
                            {
                                setShipOnBoard = false;
                                break;
                            }
                        }
                    }
                    //if the ship length in the requested position does not exceed what's on the board then continue
                    if(x + (aShip.getLength() - 1) < this.size && setShipOnBoard)
                    {
                        for(int xCopy = x; xCopy < this.size && xCopy <= x + (aShip.getLength() - 1); xCopy++)
                        {
                            this.boardArray[y][xCopy] = 1;
                        }
                    }else
                    {
                        setShipOnBoard = false;
                    }
                    break;
                //if trying to place ship vertically
                case 'v':
                    //check if pos is used
                    if(y + (aShip.getLength() - 1) < this.size)
                    {
                        for(int yCopy = y; yCopy < this.size && yCopy <= y + (aShip.getLength() - 1); yCopy++)
                        {
                            //if this position is not free, then do not place ship on board
                            if(this.boardArray[yCopy][x] != 0)
                            {
                                setShipOnBoard = false;
                                break;
                            }
                        }
                    }
                    //if the ship length in the requested position does not exceed what's on the board then continue
                    if(y + (aShip.getLength() - 1) < this.size && setShipOnBoard)
                    {
                        for(int yCopy = y; yCopy < this.size && yCopy <= y + (aShip.getLength() - 1); yCopy++)
                        {
                            this.boardArray[yCopy][x] = 1;
                        }
                    }else
                    {
                        setShipOnBoard = false;
                    }
                    break;
                default:
                    setShipOnBoard = false;
            }
        }
        else
        {
            setShipOnBoard = false;
        }
        //if ship was successfully placed
        if (setShipOnBoard)
        {
            //set ship orientation value
            aShip.setOrientation(orientation);
        }
        return setShipOnBoard;
    }

    /***
     *
     * @param y sample input 'A'
     * @param x sample input 1
     * @return boardArray element
     */
    public int hitShip(char y, int x)
    {
        int yPos = Utility.clamp(Utility.alphabet.indexOf(y),0, this.size - 1);
        int xPos = Utility.clamp(x - 1,0,this.size - 1);
        switch(boardArray[yPos][xPos])
        {
            //if no ship is in this position, output miss
            case 0:
                System.out.println("Miss!");
                //set board position to 3 to represent a miss attempt
                boardArray[yPos][xPos] = 3;
                break;
            //if ship is in this position, output hit
            case 1:
                //set board position to 2 to represent a hit
                boardArray[yPos][xPos] = 2;
                System.out.println("Direct hit!");
                break;
            // if 2 or 3 this position has already been hit
            case 2:
            case 3:
                System.out.println("Already hit.");
                break;
        }
        return boardArray[yPos][xPos];

    }

    /***
     * @return boolean true if all ships on board have been hit
     */
    public boolean isBoardClear()
    {
        for(int i = 0; i < this.size; i++)
        {
            for(int j = 0; j < this.size; j++)
            {
                if(this.boardArray[i][j] == 1)
                {
                    return false;
                }
            }
        }
        return true;
    }

    /***
     * Get all ships attached tot his board
     * @return ArrayList of Ship objects
     */
    public ArrayList<Ship> getShips()
    {
        return ships;
    }

    /***
     * set BoardArray element
     * @param y
     * @param x
     * @param val
     */
    public void setBoardArrayValue(int y, int x, int val)
    {
        this.boardArray[y][x] = val;
    }

    /***
     * Get the board size as a single dimension e.g. board is 5x5 return val will be 5
     * @return int
     */
    public int getSize()
    {
        return size;
    }

    /***
     * Returns the boardArray
     * @return int[][]
     */
    public int[][] getBoardArray()
    {
        return boardArray;
    }

    /***
     * Outputs the board only showing the positions that have been hit
     * @return String
     */
    public String outputBoardOnlyHits()
    {
        String output = this.toString();

        //output = output.replace('#','-');
        //masks all ship positions that have not been hit as blanks
        return output.replace('#','-');
    }

    /***
     * Outputs the board as a string represetation
     * @return String
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(boardName);
        sb.append("\n  ");
        //String output = boardName;
        //output = output + "\n  ";

        //build header for board
        for(int i = 1; i <= this.size; i++)
        {
            sb.append(Integer.toString(i));
            sb.append(" ");
            //output = output + Integer.toString(i) + " ";
        }

        for(int i = 0; i < this.size; i++)
        {
            sb.append("\n");
            sb.append(Utility.alphabet.charAt(i));
            sb.append(" ");
            //output = output + "\n" + Utility.alphabet.charAt(i) + " ";
            for(int j = 0; j < this.size; j++)
            {
                char boardRepresentation = ' ';
                switch (this.boardArray[i][j])
                {
                    case 0:
                        //empty spot
                        boardRepresentation = '-';
                        break;
                    case 1:
                        //ship placed
                        boardRepresentation = '#';
                        break;
                    case 2:
                        //ship hit
                        boardRepresentation = 'X';
                        break;
                    case 3:
                        //ship miss
                        boardRepresentation = '+';
                        break;
                }
                sb.append(Character.toString(boardRepresentation));
                sb.append(" ");
                //output = output + Character.toString(boardRepresentation) + " ";
            }
        }
        sb.append("\n");
        //output = output + "\n";

        //return output;
        return sb.toString();
    }
}
