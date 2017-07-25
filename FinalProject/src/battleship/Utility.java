package battleship;

/**
 * Created by Ahmed Al-Hulaibi on 5/21/2017.
 * This class contains reusable utility functions and data
 * @author Ahmed Al-Hulaibi
 * @version 1.0
 * @since 2017-07-21
 */
public class Utility {
    /***
     * Private constructor to prevent instantiation
     */
    private Utility() {
    }

    public static String alphabet = "ABCDEFHIJKLMNOPQRSTUVWXYZ";

    /***
     * This function will clamp a number between min and max exclusive
     * @param value
     * @param min
     * @param max
     * @return clamped integer value
     */
    public static int clamp(int value, int min, int max)
    {
        return Math.max(min, Math.min(max, value));
    }

    /**Returns an unsigned integer between min and max inclusive
     * @param min minimum value
     * @param max maximum value
     * @return int between min and max inclusive*/
    public static int randomInRange(int min, int max) {
        int range = Math.abs(max - min) + 1;
        return (int) (Math.random() * range) + (min < max ? min : max);

        /*Alternatively this function could be written as

        //Take absolute values of min and max
        //store in temp variables to be checked for real min and max
        int absMin = Math.abs(min);
        int absMax = Math.abs(max);

        //set min to minimum
        min = Math.min(absMin,absMax);
        //set max to maximum
        max = Math.max(absMin,absMax);

        //Take absolute value of difference to generate only unsigned values
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
        */
    }
}
