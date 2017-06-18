package CardsAssignmentThree;

/**
 * Created by Ahmed Al-Hulaibi on 5/22/2017.
 * This program will draw two cards for a player and computer and determine the result
 * @author Ahmed Al-Hulaibi
 * @version 1.0
 * @since 2017-05-21
 */
public class War {
    public boolean PlayerWin = false;
    public boolean ComputerWin = false;
    public boolean Tie = false;
    public Card playerCard;
    public Card computerCard;
    /***
     * This program will draw two cards for a player and computer and determine the result
     * @param war Set to true if calling for a special war else set to false
     */
    public void SingleDraw(boolean war)
    {
        //default constructor randomly assigns suit and cardValue
        playerCard = new Card();
        computerCard = new Card();

        //while the suit and card value are the same
        //change suit of playerCard
        while (playerCard.isCardEqualTo(computerCard))
        {
            playerCard.setSuit(Card.suits[Utility.randomInRange(0,3)]);
        }

        //set boolean results each call
        PlayerWin = computerCard.isCardValueLessThan(playerCard);
        ComputerWin = computerCard.isCardValueGreaterThan(playerCard);
        Tie = computerCard.isCardValueEqualTo(playerCard);

        //output format for aligned columns
        String format = "%-25s%-25s%-25s\n";

        //print result header
        if(!war) {
            System.out.printf(format, "Computer Player", "Human Player", "Result");
        }
        String result = "";

        //set result string
        if(war)
        {
            result = "Discard";
        }else if(Tie)
        {
            result = "Tie";
        }else if(PlayerWin)
        {
            result = "Player wins";
        }else if(ComputerWin)
        {
            result = "Computer wins";
        }


        System.out.printf(format,computerCard,playerCard,result);
    }


}
