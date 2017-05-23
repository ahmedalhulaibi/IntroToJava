package CardsAssignmentTwo;

/**
 * Created by Ahmed Al-Hulaibi on 5/22/2017.
 * This program checks if the result of a War.SingleDraw was a tie, and proceeds with a special War round
 * @author Ahmed Al-Hulaibi
 * @version 1.0
 * @since 2017-05-21
 */
public class War2 {
     public static void WarCall()
     {
         if(War.Tie)
         {
             System.out.println("WAR is CALLED!");
             for(int i = 0; i < 10; i++)
             {
                 //call single round draw function @param war set to true to set result to discard
                War.SingleDraw(true);
             }
             //call single round draw with @param war set to false to set result normally
             War.SingleDraw(false);
         }
     }
}
