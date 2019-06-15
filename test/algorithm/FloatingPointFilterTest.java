package algorithm;

import data_structure.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Huang Rong on 2019/6/15.
 */
public class FloatingPointFilterTest {
    private Point p, q, r;

    @BeforeEach
    void initializeData() {
        p = new Point(0, 0, 2);
        q = new Point(4, 4, 1);
        r = new Point(3, 1, 1);
    }

    @Test
    void test() {
        assertEquals(-1.0, new FloatingPointFilter().orientation(p, q, r));
    }
}
