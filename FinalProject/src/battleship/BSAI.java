package battleship;

import java.util.Arrays;

/**
 * Created by squig on 2017-07-15.
 */
public class BSAI {
    Board ComputerBoard;
    Board OpponentProbablityBoard;
    Board OpponentBoardPerceived;

    public BSAI(Board computerBoard)
    {
        this.ComputerBoard = computerBoard;
        this.OpponentProbablityBoard = new Board(this.ComputerBoard.getSize());
        this.OpponentBoardPerceived = new Board(this.ComputerBoard.getSize());
    }

    public void Update()
    {

    }

    public void initProbabilities()
    {
        int size = this.OpponentProbablityBoard.getSize();

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                this.OpponentProbablityBoard.setBoardArrayValue(i,j,4);
            }
        }
    }

    public void recalculateProbabilities()
    {
        int size = this.OpponentProbablityBoard.getSize();

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {

            }
        }
    }

    public int[] selectNextTarget()
    {
        int n[] = {0,0};
        return n;
    }

    public void hitSuccessful(int pos[])
    {
        this.OpponentBoardPerceived.setBoardArrayValue(pos[0],pos[1],2);
    }
}
