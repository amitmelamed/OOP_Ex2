import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import java.io.IOException;
import java.util.List;

public class DirectedWeightedGraphAlgoritems_ implements DirectedWeightedGraphAlgorithms  {
    private DirectedWeightedGraph copiedGraph;
    private DirectedWeightedGraph originalGraph;


    public DirectedWeightedGraphAlgoritems_(String jsonFileName) {
        DirectedWeightedGraph g = new DirectedWeightedGraph_(jsonFileName);
        init(g);
    }
    @Override
    public void init(DirectedWeightedGraph g) {
        originalGraph = g; //not deep copy
        copiedGraph = new DirectedWeightedGraph_(g); //deep copy


    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return originalGraph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph copiedGraph = new DirectedWeightedGraph_(originalGraph);

        return copiedGraph;
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

        this.originalGraph = new DirectedWeightedGraph_(file);
        return true;

    }

}
