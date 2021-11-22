package api;

/**This class represents a geo location <x,y,z>, (aka Point3D data).
 *in our assigmant we will only use it for 2D graphs.
 * distance funtion will get another Location and calculate their distance.
 */

public class GeoLocation_amit implements api.GeoLocation{
    private double x;
    private double y;
    private double z;

    public GeoLocation_amit(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
    @Override
    /**
     * distance funtion will get another Location and calculate their distance in the 3D space.
     * will work also on 2D space.
     */
    public double distance(GeoLocation g) {
        double calc=Math.pow(this.x-g.x(),2)+Math.pow(this.y-g.y(),2)+Math.pow(this.z-g.z(),2);
        calc=Math.sqrt(calc);
        return calc;
    }

    @Override
    public String toString() {
        return "GeoLocation_amit{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
