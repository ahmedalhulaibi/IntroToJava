package CardsAssignmentThree;

import java.util.Arrays;

/**
 * Created by Ahmed Al-Hulaibi on 2017-05-09.
 * This class contains the definition of a card object
 * @author Ahmed Al-Hulaibi
 * @version 1.0
 * @since 2017-05-29
 */
public class Card {

    /******************************************************************************
     * Card fields
     ******************************************************************************/

    //static String array of possible suits
    public static String suits[] = new String[]{"Spades","Hearts","Diamonds","Clubs"};

    //static String array of printable face value of card
    public static String cardValueNames[] = new String[]{"Ace",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "Jack",
            "Queen",
            "King"};
    //object instance suit
    private String suit;

    //object instance face value string name
    private String cardValueName;

    //object instance face value
    private int cardValue;

    /***
     * Default card constructor override
     * Randomly assigns suit
     * Randomly assigns cardValue
     * cardValueName is set based on cardValue minus 1 to account for zero-based index
     * */
    public Card() {
        suit = suits[Utility.randomInRange(0,suits.length - 1)];
        cardValue = Utility.randomInRange(13,1);
        cardValueName = cardValueNames[cardValue - 1];
    }

    /***
     * Constructor overload to set suit and face value (card cardValue)
     * @param suit default to "Spades" if not one of "Spades","Hearts","Diamonds","Clubs"
     * @param cardValue must be value from 1 to 13, else default to 1
     */
    public Card(String suit, int cardValue) {
        setSuit(suit);
        setCardValue(cardValue);
    }

    /***
     * Constructor overload to set suit and face value (card cardValue)
     * @param suit integer default to 0 if not between 0-3 inclusive 0,1,2,3 = "Spades","Hearts","Diamonds","Clubs"
     * @param cardValue must be value from 1 to 13, else default to 1
     */
    public Card(int suit, int cardValue) {
        setSuit(suit);
        setCardValue(cardValue);
    }

    /***
     * Copy constructor
     */
    public Card(Card other)
    {
        setSuit(other.getSuit());
        setCardValue(other.getCardValue());
    }
    /***
     * returns value of suit
     * @return String suit : "Spades","Hearts","Diamonds","Clubs"
     */
    public String getSuit() {
        return suit;
    }

    /***
     * sets value of suit
     * if @param suit not one of "Spades","Hearts","Diamonds","Clubs" default to "Spades"
     * @param suit : "Spades","Hearts","Diamonds","Clubs"
     */
    public void setSuit(String suit) {
        if(Arrays.asList(suits).contains(suit)) {
            this.suit = suit;
        }else
        {
            this.suit = "Spades";
        }
    }

    /***
     * Sets suit based on integer passed. Must be value 0-3 inclusive else defaults to 0.
     * 0 - Spades
     * 1 - Hearts
     * 2 - Diamonds
     * 3 - Clubs
     * @param suit
     */
    public void setSuit(int suit)
    {
        if(suit < 0 || suit > 3)
        {
            suit = 0;
        }
        this.suit = suits[suit];
    }

    /***
     * Get name of face value (card cardValue)
     * Output is a string value:
     * 1 - "Ace",
     * 2 - "2",
     * 3 - "3",
     * 4 - "4",
     * 5 - "5",
     * 6 - "6",
     * 7 - "7",
     * 8 - "8",
     * 9 - "9",
     * 10 - "10",
     * 11 - "Jack",
     * 12 - "Queen",
     * 13 - "King"
     * @return String cardValueName
     */
    public String getCardValueName() {
        return cardValueName;
    }

    /***
     * Get the integer value of the face value (card cardValue)
     * Output will be an integer value from 1 to 13
     * @return int cardValue
     */
    public int getCardValue() {
        return cardValue;
    }

    /***
     * Set the integer value of the face value (card cardValue)
     * Automatically assigns cardValueName based on cardValue
     * @param cardValue must be value from 1 to 13, else default to 1
     */
    public void setCardValue(int cardValue) {
        //if @param cardValue is less than 1 or greater than 13 set to 1
        //else set this.cardValue = cardValue
        this.cardValue = cardValue < 1 || cardValue > 13 ? 1 : cardValue;
        this.cardValueName = this.cardValueNames[this.cardValue - 1];
    }

    /***
     * Card value comparison function
     * @param other Card object to compare to
     * @return boolean this.cardValue < other.cardValue
     */
    public boolean isCardValueLessThan(Card other)
    {
        return this.cardValue < other.cardValue;
    }

    /***
     * Card value comparison function
     * @param other Card object to compare to
     * @return boolean this.cardValue > other.cardValue
     */
    public boolean isCardValueGreaterThan(Card other)
    {
        return this.cardValue > other.cardValue;
    }

    /***
     * Card value comparison function
     * @param other Card object to compare to
     * @return boolean this.cardValue == other.cardValue
     */
    public boolean isCardValueEqualTo(Card other)
    {
        return  this.cardValue == other.cardValue;
    }

    /***
     * Copmares cardValue and suit to determine if it is a duplicate card
     * @param other Card object to compare to
     * @return boolean this.cardValue == other.cardValue && this.suit == other.suit
     */
    public  boolean isCardEqualTo(Card other)
    {
        return this.cardValue == other.cardValue && this.suit == other.suit;
    }
    /***
     * Returns card object cardValueName and suit fields as a String
     * @return String this.cardValueName + " Of " + this.suit
     */
    public String toString(){
        return this.cardValueName + " Of " + this.suit;
    }
}
