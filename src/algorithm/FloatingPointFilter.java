package algorithm;

import data_structure.Point;

/**
 * Created by Huang Rong on 2019/6/15.
 */
public class FloatingPointFilter extends OrientationAlgorithm {
    /**
     * To find orientation of three points (p, q, r).
     * The function returns following values:
     * 1 --> Counterclockwise(negative orientation)
     * 0 --> p, q and r are collinear points
     * -1 --> Clockwise(positive orientation)
     */
    @Override
    public double orientation(Point p, Point q, Point r) {
        // convert arguments to double
        double pxd = Double.valueOf(p.getX());
        double pyd = Double.valueOf(p.getY());
        double pwd = Double.valueOf(p.getW());
        double qxd = Double.valueOf(q.getX());
        double qyd = Double.valueOf(q.getY());
        double qwd = Double.valueOf(q.getW());
        double rxd = Double.valueOf(r.getX());
        double ryd = Double.valueOf(r.getY());
        double rwd = Double.valueOf(r.getW());

        // evaluate E with floating point arithmetic
        double E_tilde = (pxd * qwd - qxd * pwd) * (pyd * rwd - ryd * pwd) -
                (pyd * qwd - qyd * pwd) * (pxd * rwd - rxd * pwd);

        // compute mes by replaing all arguments by their absolute
        pxd = Math.abs(pxd);
        pyd = Math.abs(pyd);
        qxd = Math.abs(qxd);
        qyd = Math.abs(qyd);
        rxd = Math.abs(rxd);
        ryd = Math.abs(ryd);

        double mes = (pxd * qwd + qxd * pwd) * (pyd * rwd + ryd * pwd) +
                (pyd * qwd + qyd * pwd) * (pxd * rwd + rxd * pwd);

        double ind = 11.0;
        double eps = Math.pow(2, -53);
        double B = ind * mes * eps;

        if (E_tilde > B) return 1;
        if (E_tilde < -B) return -1;
        if (B < 1) return 0;

        // resort to integer arithmetic
        return Math.signum((p.getX() * q.getW() - q.getX() * p.getW()) * (p.getY() * r.getW() - r.getY() * p.getW()) -
                (p.getY() * q.getW() - q.getY() * p.getW()) * (p.getX() * r.getW() - r.getX() * p.getW()));
    }
}
