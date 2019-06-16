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
    private static final int NUMBER_OF_INSTANCE_RUNS = 3;

    private final Map<List<List<Point>>, Long> RESULTS;
    private final OrientationAlgorithm ALGORITHM;

    public Benchmark(OrientationAlgorithm algorithm) {
        ALGORITHM = algorithm;
        // RESULTS = new HashMap<>();
        // TreeMap to keep correct order, compares on number of clauses
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
     * @param i          - the 3 Points as one instance
     * @param timeNeeded - the time needed to solve the instance
     */
    public void addResult(List<List<Point>> i, long timeNeeded) {
        RESULTS.put(i, timeNeeded);
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
                .append("### COMPACT FORMAT ###")
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
     * Prints the average result for each formula in a nice format
     */
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("# Benchmark of Algorithm ")
                .append(ALGORITHM.toString())
                .append(":\n\n# Average Results (time in ms):\n");
        for (Map.Entry<List<List<Point>>, Long> entry : RESULTS.entrySet()) {
            b.append("number of instances= ")
                    .append(entry.getKey().size())
                    .append(" ; runTime : ")
                    .append(entry.getValue())
                    .append(" ns")
                    .append("\n");
        }
        return b.toString();
    }

    /**
     * Does a benchmark for all given algorithms, writes results to file.
     *
     * @param lll  - instances to test the algorithms on
     * @param algorithms     - the algorithms we are going to benchmark
     * @param pathOutputFile - path of the output file
     * @throws IOException
     */
    public static void doBenchmark(List<List<List<Point>>> lll, List<OrientationAlgorithm> algorithms, String pathOutputFile) throws IOException {
        List<Benchmark> benchmarks = new ArrayList<Benchmark>();
        for (OrientationAlgorithm algorithm : algorithms) {
            System.out.println("Test Algorithm: " + algorithm);
            benchmarks.add(doBenchmarkWithAlgorithm(lll, NUMBER_OF_INSTANCE_RUNS, algorithm));
            // after benchmarking of each size we save results to file
            Benchmark.writeResultsToFile(benchmarks, pathOutputFile);
        }
    }

    /**
     * Does benchmark a of all lll given, takes avg running time after specified number of runs
     *
     * @param lll    - list of lll given
     * @param numberOfRuns - this often is the algorithm on the formula tested
     * @param algorithm    - class of algorithm that is tested
     */
    private static Benchmark doBenchmarkWithAlgorithm(List<List<List<Point>>> lll, int numberOfRuns, OrientationAlgorithm algorithm) {
        Benchmark b = new Benchmark(algorithm);
        long time = 0;
        for (List<List<Point>> instance : lll) {
            System.out.println("Doing initial Test...");
            run(instance, algorithm);
            System.out.println("Starting Benchmarks!");
            for (int i = 0; i < numberOfRuns; i++) {
                System.out.println(i + ". Run");
                time = time + run(instance, algorithm);
            }
            b.addResult(instance, time / numberOfRuns);
        }

        return b;
    }

    /**
     * Benchmarks the time needed to solve an Instance with method algorithmClass.solve
     *
     * @param instances - 3 Points as an Instance
     * @param algorithm
     * @return Time in MILLISECONDS for the algorithm to solve the formula
     */
    private static int run(List<List<Point>> instances, OrientationAlgorithm algorithm) {
        long startTime = System.nanoTime();
        for (int i = 0; i < instances.size(); i++) {
            algorithm.orientation(instances.get(i));
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Finished in " + TimeUnit.NANOSECONDS.convert(totalTime, TimeUnit.NANOSECONDS) + " nanoseconds");
        return (int) TimeUnit.NANOSECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
    }
}