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
    private double[][][] pathData;

    public DirectedWeightedGraphAlgoritems_(String jsonFileName) {
        DirectedWeightedGraph g = new DirectedWeightedGraph_(jsonFileName);
        init(g);
    }
    @Override
    public void init(DirectedWeightedGraph g) {
        originalGraph = g;                           //not deep copy
        copiedGraph = new DirectedWeightedGraph_(g); //deep copy
        pathData = new double[g.nodeSize()][g.nodeSize()][2];
        for (int i = 0; i < g.nodeSize(); i++) {
            for (int j = 0; j <g.nodeSize(); j++) {
                pathData[i][j][0] = Integer.MAX_VALUE;
            }
        }
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

    public void printPathData(int key) {
        for (int i = 0; i < pathData[key].length; i++) {
            for (int j = 0; j < pathData[key][i].length; j++) {
                System.out.print(pathData[key][i][j]+" ");
            }
            System.out.println();
        }
    }

    public void calculatePathData(int key) {

        pathData[key][key][0] = 0;
        pathData[key][key][1] = key;

        double minWeight = 0;
        int minID = 0;
        Iterator<NodeData> NodesI = copiedGraph.nodeIter();
        NodeData currNode = copiedGraph.getNode(key);
        int i = 0;
        while (i<5) {
            minWeight = Integer.MAX_VALUE;
            Iterator<EdgeData> outEdgesI = copiedGraph.edgeIter(currNode.getKey());
                while (outEdgesI.hasNext()) {
                    EdgeData currEdge = outEdgesI.next();
                    double currWeight = currEdge.getWeight();
                    int currDest = currEdge.getDest();
                    if (currWeight<pathData[key][currDest][0]) {
                        pathData[key][currDest][0] = currWeight;
                        pathData[key][currDest][1] = key;
                    }
                    if (currWeight<minWeight&&copiedGraph.getNode(currEdge.getDest()).getTag()!=2) {
                        minWeight = currWeight;
                        minID = currEdge.getDest();
                    } else return;
                }
                currNode = copiedGraph.getNode(minID);
                i++;
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
