package algorithm;

import data_structure.Point;

import java.util.List;

/**
 * Created by Huang Rong on 2019/6/15.
 */
public abstract class OrientationAlgorithm {

    public abstract double orientation(List<Point> points);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
