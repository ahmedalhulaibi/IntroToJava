package shapes;

/**
 * Created by squig on 2017-05-12.
 */
public class Main {
    public static void main(String[] args)
    {
        Square aSquare = new Square();
        //aSquare.setLength(1);
        //aSquare.setWidth(2);

        Square anotherSquare = new Square();
        //anotherSquare.setWidth(4);
        //anotherSquare.setLength(5);
        System.out.print(aSquare.getLength() + " " + aSquare.getWidth());
    }
}
