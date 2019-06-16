import algorithm.Arithmetic;
import algorithm.FloatingPointFilter;
import algorithm.OrientationAlgorithm;
import data_structure.Point;
import helper.Benchmark;
import helper.InstanceGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Huang Rong on 2019/6/15.
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        InstanceGenerator instanceGen = new InstanceGenerator(20, -5, 4);
//        List<List<Point>> instances = instanceGen.generateRandomInstances(1);
//
//        System.out.println("Arithmetic Finished in " + Benchmark.runTime(instances, new Arithmetic()) + " nanoTime");
//        System.out.println("FloatingPointFilter Finished in " + Benchmark.runTime(instances, new FloatingPointFilter()) + " nanoTime");

        List<Integer> instanceSizes = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
        List<List<List<Point>>> lll = createInstances(instanceSizes);
        List<OrientationAlgorithm> algorithms = Arrays.asList(new Arithmetic(), new FloatingPointFilter());
        Benchmark.doBenchmark(lll, algorithms, "results/compareAlgorithms.txt");
    }

    /**
     * Create n Test, each test have m Instances
     * because 1 instance running too fast, we use m Instances as a test
     *
     * @param instanceSizes
     * @return
     */
    private static List<List<List<Point>>> createInstances(List<Integer> instanceSizes) {
        final int MAX_LIT = 50;
        final int MIN_LIT = -20;

        InstanceGenerator instanceGen = new InstanceGenerator(MAX_LIT, MIN_LIT, 4);
        List<List<List<Point>>> instances = new ArrayList<>();
        for (Integer i : instanceSizes) {
            instances.add(instanceGen.generateRandomInstances(i));
        }
        return instances;
    }
}
