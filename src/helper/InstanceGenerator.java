package helper;

import data_structure.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Huang Rong on 2019/6/16.
 *
 * Create Random Instances, each Instance have 3 Points:
 * (px, py, pw)
 * (qx, qy, qw)
 * (rx, ry, yw)
 */
public class InstanceGenerator {
    private final int MAX_LIT;
    private final int MIN_LIT;
    private final Random RAND;

    public InstanceGenerator(int max_lit, int min_lit) {
        MAX_LIT = max_lit;
        MIN_LIT = min_lit;
        RAND = new Random();
    }

    public InstanceGenerator(int max_lit, int min_lit, long seed) {
        MAX_LIT = max_lit;
        MIN_LIT = min_lit;
        RAND = new Random(seed);
    }

    /**
     * This method generates 3 Point as a instance.
     *
     * @return 3 Point
     */
    public List<Point> generateRandom3Points() {
        List<Point> points = new ArrayList<Point>(3);

        for (int i = 1; i <= 3; i++) {
            Point point = new Point();

            point.setX(RAND.nextInt((MAX_LIT - MIN_LIT) + 1) + MIN_LIT);
            point.setY(RAND.nextInt((MAX_LIT - MIN_LIT) + 1) + MIN_LIT);
            // w is not 0
            int w;
            do {
                w = RAND.nextInt((MAX_LIT - MIN_LIT) + 1) + MIN_LIT;
                point.setW(w);
            } while (w == 0);

            points.add(point);
        }

        return points;
    }

    /**
     * This method generates more instances.
     *
     * @return more 3 Points instances
     */
    public List<List<Point>> generateRandomInstances(int numOfInstancs) {
        List<List<Point>> instances = new ArrayList<List<Point>>(numOfInstancs);
        for (int i = 0; i < numOfInstancs; i++) {
            List<Point> points = generateRandom3Points();
            instances.add(points);
        }

        return instances;
    }
}
