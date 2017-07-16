package battleship;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ahmed Al-Hulaibi on 7/12/2017.
 */
public class Board {
    private int size;
    private int boardArray[][];

    private ArrayList<Ship> ships = new ArrayList<>();

    public Board(int size)
    {
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

        int shipSizeMax = (size - 1);
        int shipLengthLeft = shipSizeMax * shipSizeMax;
        while(shipLengthLeft > 1)
        {
            int newShipLength = Utility.randomInRange(2, Math.min(shipSizeMax,shipLengthLeft));
            ships.add(new Ship(newShipLength));
            shipLengthLeft -= newShipLength;
        }
    }

    public boolean placeShip(char y, int x, char orientation, Ship aShip)
    {
        int yInt = Utility.alphabet.indexOf(y);
        return placeShip(Utility.clamp(x - 1,0,this.size - 1), yInt, orientation, aShip);
    }

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
                case 'h':
                    if(x + (aShip.getLength() - 1) < this.size)
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
                case 'v':
                    if(y + (aShip.getLength() - 1) < this.size)
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
        if (setShipOnBoard)
        {
            aShip.setOrientation(orientation);
        }
        return setShipOnBoard;
    }

    public boolean hitShip(char y, int x)
    {
        int yPos = Utility.alphabet.indexOf(y);
        int xPos = Utility.clamp(x - 1,0,this.size - 1);
        switch(boardArray[yPos][xPos])
        {
            case 0:
                System.out.println("Miss!");
                break;
            case 1:
                boardArray[yPos][xPos] = 2;
                System.out.println("Direct hit!");
                return true;
            case 2:
                System.out.println("Already hit.");
                break;
        }
        return false;

    }

    //if all ships on board have been hit
    public boolean isBoardClear()
    {
        if(Arrays.asList(boardArray).contains(1))
        {
            return false;
        }
        return true;
    }

    public ArrayList<Ship> getShips()
    {
        return ships;
    }

    public void setBoardArrayValue(int y, int x, int val)
    {
        this.boardArray[y][x] = val;
    }

    public int getSize()
    {
        return size;
    }

    public String toString()
    {
        String output = "  ";

        //build header for board
        for(int i = 1; i <= this.size; i++)
        {
            output = output + Integer.toString(i) + " ";
        }

        for(int i = 0; i < this.size; i++)
        {
            output = output + "\n" + Utility.alphabet.charAt(i) + " ";
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
                }
                output = output + Character.toString(boardRepresentation) + " ";
            }
        }
        output = output + "\n";

        return output;
    }
}
