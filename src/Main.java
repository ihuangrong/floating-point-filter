import algorithm.Arithmetic;
import algorithm.FloatingPointFilter;
import data_structure.Point;

/**
 * Created by Huang Rong on 2019/6/15.
 */
public class Main {
    public static void main(String[] args) {
        Point p = new Point(0, 0, 2);
        Point q = new Point(4, 4, 1);
        Point r = new Point(3, 1, 1);

        System.out.println("Arithmetic:" + new Arithmetic().orientation(p, q, r));
        System.out.println("Floating Point Filter:" + new FloatingPointFilter().orientation(p, q, r));
    }
}
