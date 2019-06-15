import java.data_structure.Point;

import static java.algorithm.FloatingPointFilter.orientation;

/**
 * Created by Huang Rong on 2019/6/15.
 */
public class Main {
    public static void main(String[] args) {
        Point p = new Point(0, 0, 2);
        Point q = new Point(4, 4, 2);
        Point r = new Point(1, 2, 2);

        System.out.println(orientation(p, q, r));
    }
}
