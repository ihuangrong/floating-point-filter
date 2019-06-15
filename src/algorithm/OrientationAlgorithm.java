package algorithm;

import data_structure.Point;

/**
 * Created by Huang Rong on 2019/6/15.
 */
public abstract class OrientationAlgorithm {

    public abstract double orientation(Point p, Point q, Point r);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
