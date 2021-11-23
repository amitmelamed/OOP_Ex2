import api.GeoLocation;
import api.NodeData;

/**
 * this class represents node (vertex) in a (directional) weighted graph
 * each vertex have key(ID), location,weight,info and tag for implements algorithms
 */
public class NodeData_ implements NodeData {
    private int key;
    private GeoLocation location;
    private double weight;
    private String Info;
    private int tag;

    static int id = 0;
    static int x = 0;

    public NodeData_(int key, GeoLocation location, double weight) {
        this.key = key;
        this.location = location;
        this.weight = weight;
        Info = "key: "+key;
        this.tag = tag;


    }

    public NodeData_() {
        this.key = id++;
        this.location = new GeoLocation_(x++,2,3);
        this.weight = 3;
        Info = "key: "+key;
        this.tag = 0;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return location;
    }

    @Override
    public void setLocation(GeoLocation point) {
        this.location=point;

    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight=weight;

    }

    @Override
    public String getInfo() {
        return this.Info;
    }

    @Override
    public void setInfo(String info) {
        this.Info=info;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;

    }

    @Override
    public String toString() {
        return "NodeData_amit{" +
                "key=" + key +
                ", location=" + location +
                ", weight=" + weight +
                ", Info='" + Info + '\'' +
                ", tag=" + tag +
                '}';
    }
}