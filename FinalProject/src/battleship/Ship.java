package battleship;

/**
 * Created by Ahmed Al-Hulaibi on 7/12/2017.
 * This class describes a Ship object
 */
public class Ship {

    private int length;
    private char orientation;

    /***
     * Ship constructor
     * @param length
     */
    public Ship(int length)
    {
        this.length = length;
        //default orientation to 'N' to denote 'NOT PLACED'
        this.orientation = 'N';
    }

    /***
     * Returns the length of the ship
     * @return int length
     */
    public int getLength() {
        return length;
    }

    /***
     * Returns the orientation char
     * @return char oreintation
     */
    public char getOrientation() {
        return orientation;
    }

    /***
     * Sets the orientation
     * @param orientation
     */
    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }
}
