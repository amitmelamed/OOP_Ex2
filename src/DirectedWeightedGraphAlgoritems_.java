import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import java.io.IOException;
import java.util.List;

public class DirectedWeightedGraphAlgoritems_ implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;

    public DirectedWeightedGraphAlgoritems_() {

    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph=g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }


    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {

        this.graph=new DirectedWeightedGraph_(file);
        return true;

    }

}
