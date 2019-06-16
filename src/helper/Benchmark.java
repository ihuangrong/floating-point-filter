package helper;

import algorithm.OrientationAlgorithm;
import data_structure.Point;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * This is a class to measure an algorithm Running Time
 *
 * @author toni
 * Modify by R.Huang on 16.06
 */
public class Benchmark {
    private static final int NUMBER_OF_INSTANCE_RUNS = 30;

    private final Map<List<List<Point>>, Long> RESULTS;
    private final OrientationAlgorithm ALGORITHM;

    public Benchmark(OrientationAlgorithm algorithm) {
        ALGORITHM = algorithm;
        // TreeMap to keep correct order, compares on number of instances
        RESULTS = new TreeMap<List<List<Point>>, Long>(
                new Comparator<List<List<Point>>>() {
                    @Override
                    public int compare(List<List<Point>> i1, List<List<Point>> i2) {
                        return i1.size() - i2.size();
                    }
                }
        );
    }

    /**
     * Adds a result
     *
     * @param instances - instances.size() * 3 Points
     * @param timeNeeded - the time needed to solve the instance
     */
    public void addResult(List<List<Point>> instances, long timeNeeded) {
        RESULTS.put(instances, timeNeeded);
    }

    /**
     * Writes the result of the toString method to a file
     *
     * @param pathToFile - path of the file
     * @throws IOException
     */
    public static void writeResultsToFile(List<Benchmark> benchmarks, String pathToFile) throws IOException {
        StringBuilder b = new StringBuilder();
        b.append("# Automatically generated Benchmark file.\n\n")
                .append("### COMPACT FORMAT ###\n")
                .append("# Each column is for number of instances, each row for one algorithm\n")
                .append("# Each entry in the matrix is a runtime in nanotimes\n")
                .append("# Example\n")
                .append("# ;NumInstances1; NumInstances1; ...\n")
                .append("# AlgorithmName ; TimeInNs1; TimeInNs2; ...\n\n#RESULTS:\n");
        benchmarks.get(0).RESULTS.entrySet().forEach(e -> b.append("; ").append(e.getKey().size()));
        b.append("\n");
        for (Benchmark bm : benchmarks) {
            b.append(bm.ALGORITHM.toString());
            bm.RESULTS.entrySet().forEach(e -> b.append("; ").append(e.getValue()));
            b.append("\n");
        }
        b.append("\n### NICER RESULTS ###\n");
        for (Benchmark bm : benchmarks) {
            b.append(bm.toString());
            b.append("\n");
        }
        System.out.println("Write to file " + pathToFile);
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(pathToFile), "utf-8"));
        writer.write(b.toString());
        writer.close();
    }

    /**
     * Prints the average result for each test in a nice format
     */
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("# Benchmark of Algorithm ")
                .append(ALGORITHM.toString())
                .append(":\n\n# Average Results (time in ns):\n");
        for (Map.Entry<List<List<Point>>, Long> entry : RESULTS.entrySet()) {
            b.append("\nNumber of instances= ")
                    .append(entry.getKey().size())
                    .append(" ; runTime : ")
                    .append(entry.getValue())
                    .append(" ns")
                    .append("\n");
            List<List<Point>> instances = entry.getKey();
            b.append("Each instance contains three points:\n");
            for (int i = 0; i < instances.size(); i++) {
                List<Point> points = instances.get(i);
                for (int j = 0; j < 3; j++) {
                    b.append("(")
                            .append(points.get(j).getX())
                            .append(", ")
                            .append(points.get(j).getY())
                            .append(", ")
                            .append(points.get(j).getW())
                            .append(")");
                }
                b.append(": ")
                        .append(ALGORITHM.orientation(instances.get(i)))
                        .append("; \n");
            }
        }
        return b.toString();
    }

    /**
     * Does a benchmark for all given algorithms, writes results to file.
     *
     * @param nTest  - instances to test the algorithms, n Test, each Test have m Instances, each Instance have 3 Points
     * @param algorithms     - the algorithms we are going to benchmark
     * @param pathOutputFile - path of the output file
     * @throws IOException
     */
    public static void doBenchmark(List<List<List<Point>>> nTest, List<OrientationAlgorithm> algorithms, String pathOutputFile) throws IOException {
        List<Benchmark> benchmarks = new ArrayList<Benchmark>();
        for (OrientationAlgorithm algorithm : algorithms) {
            System.out.println("Test Algorithm: " + algorithm);
            benchmarks.add(doBenchmarkWithAlgorithm(nTest, NUMBER_OF_INSTANCE_RUNS, algorithm));
            // after benchmarking of each size we save results to file
            Benchmark.writeResultsToFile(benchmarks, pathOutputFile);
        }
    }

    /**
     * Does benchmark a of all nTest given, takes avg running time after specified number of runs
     *
     * @param nTest    - list of nTest given
     * @param numberOfRuns - this often is the algorithm on the formula tested
     * @param algorithm    - class of algorithm that is tested
     */
    private static Benchmark doBenchmarkWithAlgorithm(List<List<List<Point>>> nTest, int numberOfRuns, OrientationAlgorithm algorithm) {
        Benchmark b = new Benchmark(algorithm);

        for (List<List<Point>> instance : nTest) {
            long time = 0;
            System.out.println("Doing initial Test...");
            System.out.println("Starting Benchmarks!");
            for (int i = 0; i < numberOfRuns; i++) {
                System.out.println(i + ". Run");
                time = time + runTime(instance, algorithm);
            }
            b.addResult(instance, time / numberOfRuns);
        }
        return b;
    }

    /**
     * Benchmarks the time needed to solve an Instance with method algorithmClass.solve
     * Calculate n instances(n * 3Points) running Time
     *
     * @param instances - 3 Points as an Instance
     * @param algorithm
     * @return Time in MILLISECONDS for the algorithm to solve the formula
     */
    public static long runTime(List<List<Point>> instances, OrientationAlgorithm algorithm) {
        long startTime = System.nanoTime();
        for (int i = 0; i < instances.size(); i++) {
            algorithm.orientation(instances.get(i));
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Finished in " + TimeUnit.NANOSECONDS.convert(totalTime, TimeUnit.NANOSECONDS) + " nanoseconds");

        return totalTime;
    }
}