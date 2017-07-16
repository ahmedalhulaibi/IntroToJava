package battleship;

import java.util.Arrays;

/**
 * Created by Ahmed Al-Hulaibi on 2017-07-15.
 * Battleship AI module
 * This module keeps track of the
 */
public class BSAI {
    Board ComputerBoard;
    Board OpponentBoardPerceived;
    String rankPos[];
    int maxNumberOfShipSpots;

    public BSAI(Board computerBoard)
    {
        this.ComputerBoard = computerBoard;
        this.OpponentBoardPerceived = new Board(this.ComputerBoard.getSize(),
                                     "NPC Perception of Player Board");
        int boardArea = this.ComputerBoard.getSize() * this.ComputerBoard.getSize();
        this.maxNumberOfShipSpots = this.ComputerBoard.getSize() - 1;



        for(int i = 0; i < this.OpponentBoardPerceived.getSize(); i++)
        {
            for(int j = 0; j < this.OpponentBoardPerceived.getSize(); j++)
            {
                this.OpponentBoardPerceived.setBoardArrayValue(i,j,1);
            }
        }
    }

    public String selectNextTarget()
    {
        int n[] = {0,0};
        int size = OpponentBoardPerceived.getSize();
        int randRow = Utility.randomInRange(0,size-1);
        int randCol = Utility.randomInRange(1,size-1);
        String pos;
        while(this.OpponentBoardPerceived.getBoardArray()[randRow][randCol-1] == 0 ||
              this.OpponentBoardPerceived.getBoardArray()[randRow][randCol-1] == 2)
        {
            randRow = Utility.randomInRange(0,size-1);
            randCol = Utility.randomInRange(1,size-1);
        }
        boolean posFound = false;
        for(int i = 0; i < size && !posFound; i++)
        {
            for(int j = 0; j < size && !posFound; j++)
            {
                if(this.OpponentBoardPerceived.getBoardArray()[i][j] == 2)
                {
                    if(i + 1 < size && !posFound)
                    {
                        if(this.OpponentBoardPerceived.getBoardArray()[i + 1][j] == 1)
                        {
                            randRow = i + 1;
                            randCol = j;
                            randCol++;
                            posFound=true;
                        }
                    }

                    if(i - 1 > -1 && !posFound)
                    {
                        if(this.OpponentBoardPerceived.getBoardArray()[i - 1][j] == 1)
                        {
                            randRow = i - 1;
                            randCol = j;
                            randCol++;
                            posFound=true;
                        }
                    }

                    if(j + 1 < size && !posFound)
                    {
                        if(this.OpponentBoardPerceived.getBoardArray()[i][j + 1] == 1)
                        {
                            randRow = i;
                            randCol = j + 1;
                            randCol++;
                            posFound=true;
                        }
                    }

                    if(j - 1 > -1 && !posFound)
                    {
                        if(this.OpponentBoardPerceived.getBoardArray()[i][j - 1] == 1)
                        {
                            randRow = i;
                            randCol = j - 1;
                            randCol++;
                            posFound=true;
                        }
                    }
                }
            }
        }
        pos = Utility.alphabet.toCharArray()[randRow] + Integer.toString(randCol);
        return pos;
    }

    public void hitResult(int pos[], int result)
    {
        this.OpponentBoardPerceived.setBoardArrayValue(pos[0],pos[1],result);

    }

}
