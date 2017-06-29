package CardsAssignmentThree;
/**
 * Created by Ahmed Al-Hulaibi on 5/22/2017.
 * This program checks if the result of a War.SingleDraw was a tie, and proceeds with a special War round
 * @author Ahmed Al-Hulaibi
 * @version 1.0
 * @since 2017-05-21
 */
public class War2 {
     public void WarCall(War war, FullDeck deck)
     {
         while(war.Tie && deck.getDeckLength() > 2)
         {
             System.out.println("WAR is CALLED!");
             //leave two cards to decide the result of a WAR round
             for(int i = 0; i < 10 && deck.getDeckLength() > 2; i++)
             {
                 //call single round draw function @param war set to true to set result to discard
                war.SingleDraw(true, deck);
             }
             //call single round draw with @param war set to false to set result normally
             war.SingleDraw(false, deck);
         }
     }
}
