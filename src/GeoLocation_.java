import api.GeoLocation;

/**This class represents a Geographic  location <x,y,z>, (aka Point3D data).
 *in our assignment we will only use it for 2D graphs.
 * distance function will get another Location and calculate their distance.
 */

public class GeoLocation_ implements api.GeoLocation{
    private double x;
    private double y;
    private double z;


    /**
     * constractor for 3D point.
     * getting a Geographic  location <x,y,z>.
     * @param x
     * @param y
     * @param z
     */
    public GeoLocation_(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * constractor for 2D point.
     * getting a Geographic  location <x,y>.
     * @param x
     * @param y
     */
    public GeoLocation_(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0; //work around
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
     * work also on 2D space when Z is equal 0.
     */
    public double distance(GeoLocation g) {
        double calc=Math.pow(this.x-g.x(),2)+Math.pow(this.y-g.y(),2)+Math.pow(this.z-g.z(),2);
        calc=Math.sqrt(calc);
        return calc;
    }


    /**
     * prints the GeoLocation data. X,Y,Z.
     * @return
     */
    @Override
    public String toString() {
        return "GeoLocation_{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
