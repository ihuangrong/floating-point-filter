package helper;

import algorithm.FloatingPointFilter;
import data_structure.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Huang Rong on 2019/6/16.
 */
public class InstanceGeneratorTest {

    @Test
    void testGenerateRandomClauses() {
        InstanceGenerator instanceGen = new InstanceGenerator(20, 0, 4);
        List<List<Point>> instances = instanceGen.generateRandomInstances(30);
        assertEquals(instances.size(), 30);
        for (List<Point> p : instances) {
            new FloatingPointFilter().orientation(p);
        }
    }
}
