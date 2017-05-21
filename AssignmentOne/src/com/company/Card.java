package com.company;

/**
 * Created by ahmed on 2017-05-09.
 * This class contains a card object
 * @author Ahmed Al-Hulaibi
 * @version 1.0
 * @since 2017-05-09
 */
public class Card {

    private static String suits[] = new String[]{"Spades","Hearts","Diamonds","Clubs"};

    private static String numberNames[] = new String[]{"Ace",
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
    private String suit;
    private String numberName;
    private int number;

    /**
     * Card constructor
     *
     *
     * */
    public Card() {
        suit = suits[randomInRange(0,suits.length - 1)];
        number = randomInRange(13,1);
        numberName = numberNames[number - 1];
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getNumberName() {
        return numberName;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number < 1 || number > 13 ? 1 : number;
        this.numberName = this.numberNames[this.number - 1];
    }

    public String toString(){
        return this.numberName + " Of " + this.suit;
    }

    /**Returns an unsigned integer between min and max inclusive
     * @param min minimum value
     * @param max maximum value
     * @return A number in between min and max inclusive*/
    private int randomInRange(int min, int max) {
        //Take absolute values of min and max
        // This function only accepts unsigned values
        int tempMin = Math.abs(min);
        int tempMax = Math.abs(max);

        //make sure min is less than max
        //else switch values
        min = Math.min(tempMin,tempMax);
        max = Math.max(tempMin,tempMax);

        //Take absolute value of difference to generate only unsigned values
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;

        /*Alternatively this function could be written as
         * int range = Math.abs(max - min) + 1;
         * return (int) (Math.random() * range) + (min < max ? min : max);

         * */
    }

}
