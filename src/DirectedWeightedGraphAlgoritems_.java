import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import java.io.IOException;
import java.util.List;

public class DirectedWeightedGraphAlgoritems_ implements DirectedWeightedGraphAlgorithms  {
    private DirectedWeightedGraph optimizedGraph;


    //If we go with the not extended algorithm,
    // than we need to find solution in Ex2.java or GUI

    @Override
    public void init(DirectedWeightedGraph g) {
        optimizedGraph = g; //maybe deep copy
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return optimizedGraph;
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

        this.optimizedGraph = new DirectedWeightedGraph_(file);
        return true;

    }

}
