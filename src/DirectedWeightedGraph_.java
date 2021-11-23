import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import org.w3c.dom.Node;

import java.util.*;

public class DirectedWeightedGraph_ implements DirectedWeightedGraph {
    /*
    private hashmap nodes
    private hashmap edges
    private Iterator<NodeData_> nodes = new HashMap<Integer, NodeData_>();
     */
    private Map<Integer, NodeData> nodes;
    private Map<Integer, EdgeData> edges;

    public DirectedWeightedGraph_() {
        nodes = new HashMap();
        edges = new HashMap();

        /*
        read from json file into nodes and edges
        #######Nodes:########

        NodeData_ f = new NodeData_();
        nodes.put(f.getKey(), f);

        #######Edges:########
        I don't know
         */


            NodeData_ f = new NodeData_();

            nodes.put(f.getKey(), f); //THIS WAY WE PUT IN THE HASHMAP'S INTEGER THE ACTUAL VALUE OF f.key



    }
    @Override
    public NodeData getNode(int key) {
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return null;
    }

    @Override
    public void addNode(NodeData n) {

    }

    @Override
    public void connect(int src, int dest, double w) {

    }

    @Override
    public Iterator<NodeData> nodeIter() {
        Iterator<NodeData> g = nodes.values().iterator();

        return g;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        return null;
    }

    @Override
    public int nodeSize() {
        return 0;
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int getMC() {
        return 0;
    }
}