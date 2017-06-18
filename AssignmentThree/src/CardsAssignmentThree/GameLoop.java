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
        War war = new War();
        war.SingleDraw(false);
        War2 war2 = new War2();
        war2.WarCall(war);

        System.exit(0);
    }
}
