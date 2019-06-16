package data_structure;

/**
 * Created by Huang Rong on 2019/6/15.
 */
public class Point {
    private int x, y, w;

    public Point() {
    }

    public Point(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    /**
     * Sets the x
     *
     * @param x x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y
     *
     * @param y y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the w
     *
     * @param w w
     */
    public void setW(int w) {
        this.w = w;
    }
}
