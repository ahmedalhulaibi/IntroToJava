package battleship;

/**
 * Created by Ahmed Al-Hulaibi on 7/12/2017.
 */
public class Ship {

    private int length;
    private char orientation;

    public Ship(int length)
    {
        this.length = length;
        //default orientation to 'N' to denote 'NOT PLACED'
        this.orientation = 'N';
    }
    public int getLength() {
        return length;
    }
    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }
}
