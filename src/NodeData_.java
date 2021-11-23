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
    private String Info;
    private int tag;

    private Map<Integer, EdgeData> outEdges;
    private Map<Integer, EdgeData> inEdges;


    @Override
    public Map<Integer, EdgeData> getOutEdges() {
        return outEdges;
    }
    @Override
    public Map<Integer, EdgeData> getInEdges() {
        return inEdges;
    }
//each node would contain a hashmap for it's out edges,
    //the hashmap is names outEdges and contains all the edges
    //that goes out to it's neibgourhs.
    //the hashed value is the destination itself.
    // e is now an edge, e.destination is it's destination id
    //outEdges.put(e.getDest(), e);

    //each node would also contain a hashmap for it's in edges,
    // the hashmap is named inEdges and contains all the edges
    // that goes in to itself from it's neihbors.
    //the hashed value is the source of the edge.
    // e is now an edge, e.sorce is it's sorce id
    //inEdges.put(e.getSource, e);

    public NodeData_(int key, String pos) {
        this.key = key;
        String xyz[] = pos.split(",");
        double x = Double.parseDouble(xyz[0]);
        double y = Double.parseDouble(xyz[1]);
        double z = Double.parseDouble(xyz[2]);

        location = new GeoLocation_(x, y, z);
        weight = 0;
        Info = "";
        tag = 0;

        outEdges = new HashMap<>();
        inEdges = new HashMap<>();
    }

//    public NodeData_() {
//        this.key = id++;
//        this.location = new GeoLocation_(x++,2,3);
//        this.weight = 3;
//        Info = "key: "+key;
//        this.tag = 0;
//    } THIS IS AN EMPTY CONSTRUCTOR FOR EARLY TESTINGS

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