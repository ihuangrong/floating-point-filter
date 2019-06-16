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
//        List<List<Point>> instances = instanceGen.generateRandomInstances(9000);

//        System.out.println("Arithmetic Finished in " + runTime(new Arithmetic(), instances) + " nanoTime");
//        System.out.println("FloatingPointFilter Finished in " + runTime(new FloatingPointFilter(), instances) + " nanoTime");

        List<Integer> instanceSizes = Arrays.asList(1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000);
        List<List<List<Point>>> lll = createInstances(instanceSizes);
        List<OrientationAlgorithm> algorithms = Arrays.asList(new Arithmetic(), new FloatingPointFilter());
        Benchmark.doBenchmark(lll, algorithms, "results/compareAlgorithms.txt");
    }

    /**
     * Create Random Formulas
     * @param instanceSizes
     * @return
     */
    private static List<List<List<Point>>> createInstances(List<Integer> instanceSizes) {
        final int MAX_LIT = 20;
        final int MIN_LIT = -5;

        InstanceGenerator instanceGen = new InstanceGenerator(MAX_LIT, MIN_LIT, 4);
        List<List<List<Point>>> instances = new ArrayList<>();
        for (Integer i : instanceSizes) {
            instances.add(instanceGen.generateRandomInstances(i));
        }
        return instances;
    }

    private static long runTime(OrientationAlgorithm algorithm, List<List<Point>> instances) {
        long startTime = System.nanoTime();
        for (int i = 0; i < instances.size(); i++) {
            algorithm.orientation(instances.get(i));
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        // System.out.println("Finished in " + TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS) + " seconds");

        return totalTime;
    }
}
