import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.util.HashMap;
import java.util.Map;

/**
 * this class represents node (vertex) in a (directional) weighted graph
 * each vertex have key(ID), location,weight,info and tag for implements algorithms
 * the vertex will also contain two Maps that contains all the edges connected to the current Node.
 * outEdges will contain edges that go out from the node, and will map them by the destination.
 * inEdges will contain edges that go in to the node, and will map them by the source.
 */
public class NodeData_ implements NodeData {


    private int key;
    private GeoLocation location;
    private double weight;
    private String info;
    private int tag;

    private Map<Integer, EdgeData> outEdges; // Integer is the edge's destination
    private Map<Integer, EdgeData> inEdges;  // Integer is the edge's source


    /**
     * deep copy constructor
     * @param N
     */
    public NodeData_(NodeData N) {
        this.key = N.getKey();
        this.location = new GeoLocation_(N.getLocation().x(), N.getLocation().y(), N.getLocation().z());
        weight = N.getWeight();
        info = N.getInfo();
        tag = 0;

        outEdges = new HashMap<>();
        inEdges = new HashMap<>();
    }


    /**
     * build a new node.
     * gets a key and geographic location.
     * sets weight,tag and info to default (0,0," ").
     * sets the in and out edges Maps (default is empty).
     * @param key
     * @param pos
     */
    public NodeData_(int key, String pos) {
        this.key = key;
        String xyz[] = pos.split(",");
        double x = Double.parseDouble(xyz[0]);
        double y = Double.parseDouble(xyz[1]);
        double z = Double.parseDouble(xyz[2]);

        location = new GeoLocation_(x, y, z);
        weight = 0;
        info = "";
        tag = 0;

        outEdges = new HashMap<>();
        inEdges = new HashMap<>();
    }


    /**
     * getters setters and toString methods.
     * @return
     */
    @Override
    public Map<Integer, EdgeData> getOutEdges() {
        return outEdges;
    }
    @Override
    public Map<Integer, EdgeData> getInEdges() {
        return inEdges;
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
        return this.info;
    }

    @Override
    public void setInfo(String info) {
        this.info =info;
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
        return "NodeData_{" +
                "key=" + key +
                ", location=" + location +
                ", weight=" + weight +
                ", Info='" + info + '\'' +
                ", tag=" + tag +
                '}';
    }
}