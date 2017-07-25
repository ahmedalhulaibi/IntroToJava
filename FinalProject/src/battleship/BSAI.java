package battleship;


/**
 * Created by Ahmed Al-Hulaibi on 2017-07-15.
 * Battleship AI module
 * This module keeps track of the hits/misses of the player
 */
public class BSAI {
    Board ComputerBoard;
    Board OpponentBoardPerceived;
    int maxNumberOfShipSpots;

    /***
     * BSAI constructor
     * @param computerBoard
     */
    public BSAI(Board computerBoard)
    {
        this.ComputerBoard = computerBoard;
        this.OpponentBoardPerceived = new Board(this.ComputerBoard.getSize(),
                                     "NPC Perception of Player Board");
        this.maxNumberOfShipSpots = this.ComputerBoard.getSize() - 1;


        //load the opponents board with all '1's, this assumes all positions on the board are valid targets
        for(int i = 0; i < this.OpponentBoardPerceived.getSize(); i++)
        {
            for(int j = 0; j < this.OpponentBoardPerceived.getSize(); j++)
            {
                this.OpponentBoardPerceived.setBoardArrayValue(i,j,1);
            }
        }
    }

    /***
     * Selects the next target to hit based on previous success/failure
     * It looks around the board position of successful hits
     * If no hits have been made, it will make a random decision
     * @return String in format of battleship pos A1
     */
    public String selectNextTarget()
    {
        int size = OpponentBoardPerceived.getSize();
        int randRow = Utility.randomInRange(0,size-1);
        int randCol = Utility.randomInRange(1,size-1);
        String pos;
        while(this.OpponentBoardPerceived.getBoardArray()[randRow][randCol-1] == 0 ||
                this.OpponentBoardPerceived.getBoardArray()[randRow][randCol-1] == 2||
                this.OpponentBoardPerceived.getBoardArray()[randRow][randCol-1] == 3)
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

    /***
     * Store hitResult
     * @param pos
     * @param result
     */
    public void hitResult(int pos[], int result)
    {
        this.OpponentBoardPerceived.setBoardArrayValue(pos[0],pos[1],result);

    }

    /***
     * Checks if the player has already attempted to hit this position (successful or miss)
     * @param pos
     * @return
     */
    public boolean hasPositionBeenAttempted(int pos[])
    {
        return this.OpponentBoardPerceived.getBoardArray()[pos[0]][pos[1]] == 2 ||
                this.OpponentBoardPerceived.getBoardArray()[pos[0]][pos[1]] == 3 ;
    }

}
