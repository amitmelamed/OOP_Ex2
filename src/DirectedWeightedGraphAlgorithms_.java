import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static api.lib.parseJSONFile;

public class DirectedWeightedGraphAlgorithms_ implements DirectedWeightedGraphAlgorithms  {
    private DirectedWeightedGraph originalGraph;
    //private DirectedWeightedGraph copiedGraph;
    private DirectedWeightedGraph transposeGraph;
    private double[][][] pathData;
    private boolean isConnected = true;
    boolean pathCalculated = false;

    public void setPathCalculated(boolean pathCalculated) {
        this.pathCalculated = pathCalculated;
    }

    public DirectedWeightedGraphAlgorithms_(String jsonFileName) {
        DirectedWeightedGraph g = new DirectedWeightedGraph_(jsonFileName);
        init(g);
    }
    @Override
    public void init(DirectedWeightedGraph g) {
        originalGraph = g; //not deep copy - changes in original graph will take place here and in GUI as well
        //copiedGraph = new DirectedWeightedGraph_(g); //deep copy
        pathData = new double[g.nodeSize()][g.nodeSize()][2];
        isConnected = true;
        pathCalculated = false;
        transpose();
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

    public void BFS(int key, boolean t) {

        Iterator<NodeData> NodesI = transposeGraph.nodeIter();
        NodeData currNode;
        while (NodesI.hasNext()) {
            currNode = NodesI.next();
            if (key!=currNode.getKey()) pathData[key][currNode.getKey()][0] = Integer.MAX_VALUE;
        }

        currNode = transposeGraph.getNode(key);
        Queue<NodeData> Q = new LinkedList<>();
        Q.add(currNode);


        while(!Q.isEmpty()) {
            currNode = Q.poll();
            currNode.setTag(2);
            Iterator<EdgeData> OutEdges = transposeGraph.edgeIter(currNode.getKey());
            while (OutEdges.hasNext()) {
                EdgeData currEdge = OutEdges.next();
                if (transposeGraph.getNode(currEdge.getDest()).getTag()==0) {
                    Q.add(transposeGraph.getNode(currEdge.getDest()));
                    transposeGraph.getNode(currEdge.getDest()).setTag(1);
                    pathData[key][currEdge.getDest()][0] = currEdge.getWeight()+pathData[key][currNode.getKey()][0];
                    pathData[key][currEdge.getDest()][1] = currNode.getKey();
                }
                else if (pathData[key][currEdge.getDest()][0] > currEdge.getWeight()+pathData[key][currNode.getKey()][0]){
                    pathData[key][currEdge.getDest()][0] = currEdge.getWeight()+pathData[key][currNode.getKey()][0];
                    pathData[key][currEdge.getDest()][1] = currNode.getKey();

                }
            }
        }

        NodesI = transposeGraph.nodeIter();
        while (NodesI.hasNext()) {
            currNode = NodesI.next();
            currNode.setTag(0);
            if (pathData[key][currNode.getKey()][0]>=Integer.MAX_VALUE) isConnected = false;
        }
    }

    public void BFS(int key) {

        Iterator<NodeData> NodesI = originalGraph.nodeIter();
        NodeData currNode;
        while (NodesI.hasNext()) {
            currNode = NodesI.next();
            if (key!=currNode.getKey())
                pathData[key][currNode.getKey()][0] = Integer.MAX_VALUE;
        }

        currNode = originalGraph.getNode(key);
        Queue<NodeData> Q = new LinkedList<>();
        Q.add(currNode);


        while(!Q.isEmpty()) {
            currNode = Q.poll();
            currNode.setTag(2);
            Iterator<EdgeData> OutEdges = originalGraph.edgeIter(currNode.getKey());
            while (OutEdges.hasNext()) {
                EdgeData currEdge = OutEdges.next();
                if (originalGraph.getNode(currEdge.getDest()).getTag()==0) {
                    Q.add(originalGraph.getNode(currEdge.getDest()));
                    originalGraph.getNode(currEdge.getDest()).setTag(1);
                    pathData[key][currEdge.getDest()][0] = currEdge.getWeight()+pathData[key][currNode.getKey()][0];
                    pathData[key][currEdge.getDest()][1] = currNode.getKey();
                }
                else if (pathData[key][currEdge.getDest()][0] > currEdge.getWeight()+pathData[key][currNode.getKey()][0]){
                    pathData[key][currEdge.getDest()][0] = currEdge.getWeight()+pathData[key][currNode.getKey()][0];
                    pathData[key][currEdge.getDest()][1] = currNode.getKey();

                }
            }
        }

        NodesI = originalGraph.nodeIter();
        while (NodesI.hasNext()) {
            currNode = NodesI.next();
            currNode.setTag(0);
            if (pathData[key][currNode.getKey()][0]>=Integer.MAX_VALUE) isConnected = false;
        }
    }

    public void printtesting(int key) {
        //PRINTING//
        System.out.println("TABLE FOR "+key);
        for (int i = 0; i < pathData[key].length; i++) {
            if (pathData[key][i][0]>=Integer.MAX_VALUE) System.out.println(i+" "+99999999+" "+pathData[key][i][1]+"TAG IS "+originalGraph.getNode(i).getTag());
            else System.out.println(i+" "+pathData[key][i][0]+" "+pathData[key][i][1]+"TAG IS "+originalGraph.getNode(i).getTag());

        }
        System.out.println();

    }

    public void transpose(){
        transposeGraph = new DirectedWeightedGraph_(originalGraph,true);
    }

    @Override
    public boolean isConnected() {
        if (originalGraph.nodeSize()==0||originalGraph.nodeSize()==1) return true;
        if (pathCalculated)
            return isConnected;
        else {
            BFS(1);
            BFS(1, true);

            return isConnected;

        }
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        //use pathData after we update it in calculatePathData
        if (!pathCalculated) BFS(src);
        if (pathData[src][dest][0]>=Integer.MAX_VALUE) return -1;
        else return pathData[src][dest][0];
    }


    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        //use pathData after we update it in calculatePathData
        if (!pathCalculated) BFS(src);
        if (shortestPathDist(src, dest)==-1) return null;
        List<NodeData> List= new ArrayList<>();
        List.add(originalGraph.getNode(dest));
        int prev = dest;
        while (prev!=src) {
            List.add(originalGraph.getNode((int)pathData[src][prev][1]));
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

            if (!isConnected) return null;
            if (!pathCalculated) {
                Iterator<NodeData> I = getGraph().nodeIter();
                I.forEachRemaining(nodeData -> BFS(nodeData.getKey()));
                pathCalculated = true;
            }

            int center = -1;
            double minHigh=Double.MAX_VALUE;

            Iterator<NodeData> J;
            Iterator<NodeData>I = getGraph().nodeIter();
            while (I.hasNext()) {
                NodeData NI = I.next();

                double currentNodeMax = -1;
                J = getGraph().nodeIter();
                while (J.hasNext()) {
                    NodeData NJ = J.next();

                    if (pathData[NI.getKey()][NJ.getKey()][0]>currentNodeMax) {
                        currentNodeMax = pathData[NI.getKey()][NJ.getKey()][0];
                    }
                }
                if(minHigh>currentNodeMax){
                    minHigh=currentNodeMax;
                    center=NI.getKey();
                }
            }

        return originalGraph.getNode(center);
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        JSONObject json = new JSONObject();
        JSONArray e = new JSONArray();
        JSONArray v = new JSONArray();


        Iterator<NodeData> NI = originalGraph.nodeIter();
        while (NI.hasNext()) {
            NodeData currNode = NI.next();
            JSONObject jsonNode = new JSONObject();
            jsonNode.put("pos", "" + currNode.getLocation().x() + "," + currNode.getLocation().y() + "," + currNode.getLocation().z());
            jsonNode.put("id", currNode.getKey());
            v.put(jsonNode);
        }
        Iterator<EdgeData> NJ = originalGraph.edgeIter();
        while (NJ.hasNext()) {
            EdgeData currEdge = NJ.next();
            JSONObject jsonEdge = new JSONObject();
            jsonEdge.put("src", currEdge.getSrc());
            jsonEdge.put("w", currEdge.getWeight());
            jsonEdge.put("dest", currEdge.getDest());
            e.put(jsonEdge);
        }
        json.put("Edges", e);
        json.put("Nodes", v);

        try {
            FileWriter Files = new FileWriter(file);
            Files.write(json.toString());
            Files.close();
        }catch (IOException f) {
            f.printStackTrace();
            return false;
        }
        return true;

    }

//    @Override
//    public boolean load(String file) {
//        try {
//            DirectedWeightedGraph_ weightedGraph = new DirectedWeightedGraph_(file);
//            JSONObject jsonObject = parseJSONFile(file);
//            JSONArray jsonNodes = jsonObject.getJSONArray("Nodes");
//            for(int i=0;i<jsonNodes.length();i++){
//                int key=jsonNodes.getJSONObject(i).getInt("id");
//                String pos=jsonNodes.getJSONObject(i).getString("pos");
//                NodeData_ v = new NodeData_(key, pos);
//                weightedGraph.addNode(v);
//            }
//
//            int edgeID=0;
//            JSONArray jsonEdges = jsonObject.getJSONArray("Edges");
//            for (int i = 0; i < jsonEdges.length(); i++) {
//                int src = jsonEdges.getJSONObject(i).getInt("src");
//                int dest = jsonEdges.getJSONObject(i).getInt("dest");
//                double w = jsonEdges.getJSONObject(i).getDouble("w");
//                weightedGraph.connect(src,dest,w);
//
//            }
//
//
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//
//
//    }
    @Override
    public boolean load(String file) {
        originalGraph = new DirectedWeightedGraph_(file);
        init(originalGraph);
        return true;

    }

}
