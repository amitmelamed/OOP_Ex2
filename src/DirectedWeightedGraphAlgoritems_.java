import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DirectedWeightedGraphAlgoritems_ implements DirectedWeightedGraphAlgorithms  {
    private DirectedWeightedGraph copiedGraph;
    private DirectedWeightedGraph originalGraph;
    private int[][][] pathData;

    public DirectedWeightedGraphAlgoritems_(String jsonFileName) {
        DirectedWeightedGraph g = new DirectedWeightedGraph_(jsonFileName);
        init(g);
    }
    @Override
    public void init(DirectedWeightedGraph g) {
        originalGraph = g;                           //not deep copy
        copiedGraph = new DirectedWeightedGraph_(g); //deep copy
        pathData = new int[g.nodeSize()][g.nodeSize()][2];
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

    public void calculatePathData(int key) {

        pathData[key][key][0] = 0;
        pathData[key][key][1] = key;

        Iterator<EdgeData> outEdgesI = copiedGraph.edgeIter(key);
        while (outEdgesI.hasNext()) {
            EdgeData_ currEdge = (EdgeData_) outEdgesI.next();
            double currWeight = currEdge.getWeight();
            int currDest = currEdge.getDest();
            if (currWeight<pathData[key][currDest][0]) {
                //update pathData[key][currDest][0] with new minimal weight
                //also update the "prev node" in pathData[key][currDest][1];
            }
            //pathData[key][currDest][0]

        }


    }

    @Override
    public boolean isConnected() {
        //create new transposedGraph and every edge change it's dest and src.

        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        //use pathData after we update it in calculatePathData
        return 0;
    }


    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        //use pathData after we update it in calculatePathData
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
