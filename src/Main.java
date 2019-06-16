import algorithm.Arithmetic;
import algorithm.FloatingPointFilter;
import data_structure.Point;
import helper.InstanceGenerator;

import java.util.List;

/**
 * Created by Huang Rong on 2019/6/15.
 */
public class Main {

    public static void main(String[] args) {
        InstanceGenerator instanceGen = new InstanceGenerator(20, -5, 4);
        List<List<Point>> instances = instanceGen.generateRandomInstances(20);

        long startTime = System.nanoTime();
        for (int i = 0; i < instances.size(); i++) {
            /*
            for (Point point : instances.get(i)) {
                System.out.println("(" + point.getX() + ", " + point.getY() + ", " + point.getW() + ")");
            }
            System.out.println("Orientation: " + new Arithmetic().orientation(instances.get(i)));
            */
            new Arithmetic().orientation(instances.get(i));
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        // System.out.println("Finished in " + TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS) + " seconds");
        System.out.println("Arithmetic Finished in " + totalTime + " nanoTime");

        startTime = System.nanoTime();
        for (int i = 0; i < instances.size(); i++) {
           new FloatingPointFilter().orientation(instances.get(i));
        }
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Finished in " + totalTime + " nanoTime");
    }
}
