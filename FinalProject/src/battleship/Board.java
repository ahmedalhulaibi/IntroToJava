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

    private String boardName = "";
    public Board(int size, String boardName)
    {
        this.boardName = boardName;
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

        int shipSizeMax = (size - 2);
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
                    //check if pos is used
                    if(x + (aShip.getLength() - 1) < this.size)
                    {
                        for(int xCopy = x; xCopy < this.size && xCopy <= x + (aShip.getLength() - 1); xCopy++)
                        {
                            if(this.boardArray[y][xCopy] != 0)
                            {
                                setShipOnBoard = false;
                                break;
                            }
                        }
                    }
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
                case 'v':
                    //check if pos is used
                    if(y + (aShip.getLength() - 1) < this.size)
                    {
                        for(int yCopy = y; yCopy < this.size && yCopy <= y + (aShip.getLength() - 1); yCopy++)
                        {
                            if(this.boardArray[yCopy][x] != 0)
                            {
                                setShipOnBoard = false;
                                break;
                            }
                        }
                    }
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
        if (setShipOnBoard)
        {
            aShip.setOrientation(orientation);
        }
        return setShipOnBoard;
    }

    public int hitShip(char y, int x)
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
                break;
            case 2:
                System.out.println("Already hit.");
                break;
        }
        return boardArray[yPos][xPos];

    }

    //if all ships on board have been hit
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


    public int[][] getBoardArray()
    {
        return boardArray;
    }

    public String outputBoardOnlyHits()
    {
        String output = this.toString();

        //output = output.replace('#','-');
        return output.replace('#','-');
    }

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
