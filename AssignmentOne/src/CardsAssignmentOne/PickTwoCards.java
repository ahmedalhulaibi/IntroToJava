package CardsAssignmentOne;
/**
 * Created by Ahmed Al-Hulaibi on 2017-05-09.
 * This program creates two card objects assigning them a suit and a random integer value
 * @author Ahmed Al-Hulaibi
 * @version 1.0
 * @since 2017-05-21
 */
public class PickTwoCards {

    public static void main(String[] args)
    {
        Card card1 = new Card("Diamonds",Utility.randomInRange(1,13));
        Card card2 = new Card("Hearts", Utility.randomInRange(1,13));
        System.out.println(card1);
        System.out.println(card2);

        System.exit(0);
    }


}
