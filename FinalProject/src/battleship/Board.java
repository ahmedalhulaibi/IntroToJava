package battleship;

import java.util.ArrayList;

/**
 * Created by Ahmed Al-Hulaibi on 7/12/2017.
 */
public class Board {
    private int size;
    private int boardArray[];


    private ArrayList<Ship> ships = new ArrayList<>();

    public Board(int size)
    {
        if(size < 5)
        {
            size = 5;
        }
        this.size = size;
        this.boardArray = new int[this.size * this.size];
        for(int i = 0; i < this.boardArray.length; i++)
        {
            this.boardArray[i] = 0;
        }

        int shipSizeMax = (size - 1);
        int shipLengthLeft = shipSizeMax * shipSizeMax;
        while(shipLengthLeft > 0)
        {
            int newShipLength = Utility.randomInRange(2, Math.min(shipSizeMax,shipLengthLeft));
            ships.add(new Ship(newShipLength));
            shipLengthLeft -= newShipLength;
        }
    }

    public boolean placeShip(int x, int y, char orientation, Ship aShip)
    {
        //if not yet used
        if(aShip.getOrientation() == 'N')
        {
            int shipHeadPos = x * this.size + y;
            int shipTailPos;
            boolean setShipOnBoard;
            switch(orientation)
            {
                case 'h':
                    shipTailPos = (x + (aShip.getLength() - 1)) * this.size + y;
                    int xCopy;
                    setShipOnBoard = true;
                    for(xCopy = x; (xCopy * this.size + y) <= shipTailPos; xCopy++)
                    {
                        if(boardArray[xCopy * this.size + y] != 0)
                        {
                             setShipOnBoard = false;
                             break;
                        }
                    }
                    if(setShipOnBoard)
                    {
                        for(xCopy = x; (xCopy * this.size + y) <= shipTailPos; xCopy++)
                        {
                            boardArray[xCopy * this.size + y] = 1;
                        }
                    }
                    aShip.setOrientation(orientation);
                    break;
                case 'v':
                    shipTailPos = x * this.size + (y + aShip.getLength() - 1);
                    int yCopy;
                    setShipOnBoard = true;
                    for(yCopy = y; (x * this.size + (y + yCopy)) <= shipTailPos; yCopy++)
                    {
                        if(boardArray[x * this.size + (y + yCopy)] != 0)
                        {
                            setShipOnBoard = false;
                            break;
                        }
                    }
                    if(setShipOnBoard)
                    {
                        for(yCopy = y; (x * this.size + (y + yCopy)) <= shipTailPos; yCopy++)
                        {
                            boardArray[x * this.size + (y + yCopy)] = 1;
                        }
                    }
                    aShip.setOrientation(orientation);
                    break;
                default:
                    return false;
            }
        }else
        {
            return false;
        }
        return true;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public String toString()
    {

    }
}
