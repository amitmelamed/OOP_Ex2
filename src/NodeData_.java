import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.util.HashMap;
import java.util.Map;

/**
 * this class represents node (vertex) in a (directional) weighted graph
 * each vertex have key(ID), location,weight,info and tag for implements algorithms
 */
public class NodeData_ implements NodeData {


    private int key;
    private GeoLocation location;
    private double weight;
    private String info;
    private int tag;

    private Map<Integer, EdgeData> outEdges; // Integer is the edge's destination
    private Map<Integer, EdgeData> inEdges;  // Integer is the edge's source


    @Override
    public Map<Integer, EdgeData> getOutEdges() {
        return outEdges;
    }
    @Override
    public Map<Integer, EdgeData> getInEdges() {
        return inEdges;
    }

    public NodeData_(NodeData N) {
        this.key = N.getKey();
        this.location = new GeoLocation_(N.getLocation().x(), N.getLocation().y(), N.getLocation().z());
        weight = N.getWeight();
        info = N.getInfo();
        tag = 0;

        outEdges = new HashMap<>();
        inEdges = new HashMap<>();
    }


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