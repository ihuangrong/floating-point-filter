package algorithm;

import data_structure.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Huang Rong on 2019/6/15.
 */
public class FloatingPointFilterTest {
    private List<Point> points = new ArrayList<>(3);

    @BeforeEach
    void initializeData() {
        points.add(new Point(0, 0, 2));
        points.add(new Point(4, 4, 1));
        points.add(new Point(3, 1, 1));
    }

    @Test
    void test() {
        assertEquals(-1.0, new FloatingPointFilter().orientation(points));
    }
}
