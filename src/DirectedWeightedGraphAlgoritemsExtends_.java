import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import java.util.List;

public class DirectedWeightedGraphAlgoritemsExtends_ extends DirectedWeightedGraph_ implements DirectedWeightedGraphAlgorithms  {
    private DirectedWeightedGraph copiedGraph;

    public DirectedWeightedGraphAlgoritemsExtends_(String jsonFileName) {
        super(jsonFileName);
        init(null); //the null is because of the extends. its weird.
        //deep copy the graph into copiedGraph
        //than work on copiedGraph to change there the node's tag (white\gray\black),
        //while BFSing.

    }


    @Override
    public void init(DirectedWeightedGraph g) {
        //build the optimized graph
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return copiedGraph;
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

        this.copiedGraph = new DirectedWeightedGraph_(file);
        return true;

    }

}
