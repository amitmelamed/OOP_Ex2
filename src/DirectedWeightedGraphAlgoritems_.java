import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.io.IOException;
import java.util.*;

public class DirectedWeightedGraphAlgoritems_ implements DirectedWeightedGraphAlgorithms  {
    private DirectedWeightedGraph copiedGraph;
    private DirectedWeightedGraph originalGraph;
    private DirectedWeightedGraph transposeGraph;
    private double[][][] pathData;
    private boolean isConnected = true;

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

        double minWeight=Integer.MAX_VALUE;
        double currWeight;
        int minID=-1;
        boolean flag=true;

        NodeData currNode = copiedGraph.getNode(key);
        Queue<NodeData> Nodes = new ArrayDeque<>();
        while (flag) {
            minWeight=Integer.MAX_VALUE;
            minID=-1;
            flag = false;
            Iterator<EdgeData> outEdgesI = copiedGraph.edgeIter(currNode.getKey());
            while (outEdgesI.hasNext()) {
                if (currNode.getTag()==0) Nodes.add(currNode);
                EdgeData currEdge = outEdgesI.next();
                currWeight = currEdge.getWeight() + pathData[key][currNode.getKey()][0];
                if (pathData[key][currEdge.getDest()][0] > currWeight) {
                    pathData[key][currEdge.getDest()][0] = currWeight;
                    pathData[key][currEdge.getDest()][1] = currNode.getKey();
                }
                if (minWeight > currWeight && copiedGraph.getNode(currEdge.getDest()).getTag() == 0) {
                    minWeight = currWeight;
                    minID = currEdge.getDest();
                    flag = true;
                }
            }
            currNode.setTag(2);
            currNode = copiedGraph.getNode(minID);

            if (!flag&&!Nodes.isEmpty()) {
                currNode = Nodes.remove();
                flag = true;
            }
        }
        Iterator<NodeData> NodesI = copiedGraph.nodeIter();
        while (NodesI.hasNext()) {
            currNode = NodesI.next();
            currNode.setTag(0);
            if (pathData[key][currNode.getKey()][0]>=Integer.MAX_VALUE) isConnected = false;

        }
    }

    public void transpose(){
        transposeGraph = new DirectedWeightedGraph_(copiedGraph,true);
    }

    @Override
    public boolean isConnected() {
        //create new transposedGraph and every edge change it's dest and src.
        return isConnected;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        //use pathData after we update it in calculatePathData
        if (pathData[src][dest][0]>=Integer.MAX_VALUE) return -1;
        else return pathData[src][dest][0];
    }


    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        //use pathData after we update it in calculatePathData
        if (shortestPathDist(src, dest)==-1) return null;
        List<NodeData> List= new ArrayList<>();
        List.add(copiedGraph.getNode(dest));
        int prev = dest;
        while (prev!=src) {
            List.add(copiedGraph.getNode((int)pathData[src][prev][1]));
            prev=(int)pathData[src][prev][1];
        }
        List<NodeData> List2 = new ArrayList<>();
        for (int i = List.size()-1; i >= 0 ; i--) {
            List2.add(List.get(i));
        }
        return List2;
    }

    @Override
    public NodeData center() {
        int center=-1;
        double minHigh=Double.MAX_VALUE;
        for(int i=0;i<pathData.length;i++){
            double currentNodeMax=-1;
            for(int k=0;k<pathData[i].length;k++){
                if(pathData[i][k][0]>currentNodeMax){
                    currentNodeMax=pathData[i][k][0];
                }
            }
            if(minHigh>currentNodeMax){
                minHigh=currentNodeMax;
                center=i;
            }
        }
        System.out.println(center);
        return copiedGraph.getNode(center);
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

    public static void main(String[] args) {
        DirectedWeightedGraphAlgoritems_ graphAlgoritems=new DirectedWeightedGraphAlgoritems_("data/G1.json");

        graphAlgoritems.center();
    }

}
