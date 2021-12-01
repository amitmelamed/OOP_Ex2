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
    private DirectedWeightedGraph transposeGraph;
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
        Iterator<NodeData> NodeI = copiedGraph.nodeIter();
        while (NodeI.hasNext()) calculatePathData(NodeI.next().getKey());

        //transpose();
    }

    public DirectedWeightedGraph getTransposeGraph() {
        return transposeGraph;
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
                if (j==0) System.out.print(i+" "+pathData[key][i][j]+" ");
                else System.out.print((int)pathData[key][i][j]);
            }
            System.out.println();
        }
    }
    public void printPathData() {
        Iterator<NodeData> NodeI = copiedGraph.nodeIter();
        while (NodeI.hasNext()) {
            int key = NodeI.next().getKey();
            System.out.println("TABLE FOR NODE "+key);
            printPathData(key);
            System.out.println();
        }
    }

    public void calculatePathData(int key) {
        pathData[key][key][0] = 0;
        pathData[key][key][1] = key;
        boolean flag = true;
        double minWeight;
        int minID = 0;

        NodeData currNode = copiedGraph.getNode(key);
        while (flag) {
            if (key==8) System.out.println(currNode.getKey());
            Iterator<EdgeData> outEdgesI = copiedGraph.edgeIter(currNode.getKey());
            flag = false;
            minWeight = Integer.MAX_VALUE;
            while (outEdgesI.hasNext()) {
                EdgeData currEdge = outEdgesI.next();

                if (currNode.getKey()==key) {
                    pathData[key][currEdge.getDest()][0] = currEdge.getWeight();
                    pathData[key][currEdge.getDest()][1] = key;
                } else if (pathData[key][currEdge.getDest()][0]>pathData[key][currEdge.getSrc()][0] + currEdge.getWeight()){
                    pathData[key][currEdge.getDest()][0] = pathData[key][currEdge.getSrc()][0] + currEdge.getWeight();
                    pathData[key][currEdge.getDest()][1] = currEdge.getSrc();
                }

                if (copiedGraph.getNode(currEdge.getDest()).getTag()==0) {
                    if (key==8&&currNode.getKey()==3) System.out.println(flag+" "+minID);
                    if (minWeight>currEdge.getWeight()) {

                        minWeight = currEdge.getWeight();
                        minID = currEdge.getDest();
                        flag = true;
                    }
                }


                currNode.setTag(2);
                if (key==8) System.out.println(minID);
                currNode = copiedGraph.getNode(minID);

            }
        }
        Iterator<NodeData> NodesI = copiedGraph.nodeIter();
        while (NodesI.hasNext()) NodesI.next().setTag(0);
    }


    public void transpose(){
        transposeGraph = new DirectedWeightedGraph_(copiedGraph,true);
    }

    @Override
    public boolean isConnected() {
        //create new transposedGraph and every edge change it's dest and src.

        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        //use pathData after we update it in calculatePathData
        return pathData[src][dest][0];
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
