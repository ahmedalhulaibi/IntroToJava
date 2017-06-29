package CardsAssignmentThree;

/**
 * Created by Ahmed Al-Hulaibi on 5/22/2017.
 *
 * @author Ahmed Al-Hulaibi
 * @version 1.0
 * @since 2017-05-21
 */
public class GameLoop {
    public static void main(String[] args)
    {
        FullDeck deck = new FullDeck();
        War war = new War();
        War2 war2 = new War2();
        //loop game while there are at least 2 or more cards in play
        while (deck.getDeckLength() >= 2)
        {
            war.SingleDraw(false,deck);
            war2.WarCall(war,deck);
        }
        String format = "%-25s%-25s%-25s\n";
        System.out.printf(format, "Computer Wins", "Human Player Wins", "Ties Count");
        System.out.printf(format, war.ComputerScore, war.PlayerScore, war.Ties);

        System.exit(0);
    }
}
