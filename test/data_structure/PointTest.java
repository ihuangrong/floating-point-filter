package data_structure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Huang Rong on 2019/6/15.
 */
public class PointTest {

    @Test
    void test() {
        Point p = new Point(4, 4, 2);
        assertEquals(4, p.getX());
        assertEquals(4, p.getY());
        assertEquals(2, p.getW());
    }
}
