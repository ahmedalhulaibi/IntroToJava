package CardsAssignmentThree;


/**
 * Created by squig on 2017-06-29.
 */
public class FullDeck {
    private Card deck[] = new Card[52];
    private int deckLength = 52;
    public FullDeck()
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 13; j++)
            {
                int deckPos = i + j + (i * 12);
                deck[deckPos] = new Card(i,j+1);
                //System.out.println("Card num " + deckPos + " " + deck[deckPos]);
            }
        }
    }

    public Card drawCard()
    {
        if(deckLength > 0) {
            int randomCardPos = Utility.randomInRange(0, deckLength - 1);
            Card randomCard = new Card(deck[randomCardPos]);
            removeCard(randomCardPos);
            //System.out.println("Cards left: " + deckLength);
            return randomCard;
        }
        return null;
    }

    private void removeCard(int index)
    {
        for (int i = index; i < deck.length - 1; i++)
        {
            deck[i] = deck[i + 1];
        }
        deck[deck.length - 1] = null;
        deckLength--;
    }

    public int getDeckLength()
    {
        return deckLength;
    }
}
