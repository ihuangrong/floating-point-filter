package algorithm;

import data_structure.Point;

import java.util.List;

/**
 * Created by Huang Rong on 2019/6/15.
 * Find Orientation of 3 ordered points
 */
public class Arithmetic extends OrientationAlgorithm{
    /**
     * To find orientation of three points (p, q, r).
     * The function returns following values:
     * -1 --> Counterclockwise(negative orientation)
     * 0 --> p, q and r are collinear points
     * 1 --> Clockwise(positive orientation)
     */
    @Override
    public double orientation(List<Point> points) {
        Point p = points.get(0);
        Point q = points.get(1);
        Point r = points.get(2);

        // for derivation of the formula
        double orient = Math.signum((p.getX() * q.getW() - q.getX() * p.getW()) * (p.getY() * r.getW() - r.getY() * p.getW()) -
                (p.getY() * q.getW() - q.getY() * p.getW()) * (p.getX() * r.getW() - r.getX() * p.getW()));

        return orient;
    }
}
