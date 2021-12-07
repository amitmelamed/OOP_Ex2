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
    /**
     * This class holds an originalGraph (DirectedWeightedGraph).
     * The advanced functions in this class change and analyze the originalGraph.
     *
     * The class also holds an array double[][][] pathData that holds information (if calculated) about
     * the different paths in the graph.
     */
    private DirectedWeightedGraph originalGraph;
    private DirectedWeightedGraph transposeGraph;
    private double[][][] pathData;
    private boolean isConnected = true;
    private boolean pathCalculated = false;

    /** sets the pathCalculated boolean to the given boolean **/
    public void setPathCalculated(boolean pathCalculated) {
        this.pathCalculated = pathCalculated;
    }

    /** This constructor gets a jsonfile and initiate the GraphAlgo **/
    public DirectedWeightedGraphAlgorithms_(String jsonFileName) {
        DirectedWeightedGraph g = new DirectedWeightedGraph_(jsonFileName);
        init(g);
    }

    /**
     * For a given directedWeightedGraph g, initiate the GraphAlgo.
     * @param g
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        originalGraph = g; //not deep copy - changes in original graph will take place here and in GUI as well
        //copiedGraph = new DirectedWeightedGraph_(g); //deep copy
        pathData = new double[g.nodeSize()][g.nodeSize()][2];
        isConnected = true;
        pathCalculated = false;
        transpose();
    }

    /**
     * returns the transpose graph
     * @return
     */
    public DirectedWeightedGraph getTransposeGraph() {
        return transposeGraph;
    }

    /**
     *return the original graph
     * @return
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return originalGraph;
    }

    /**
     * return the copied graph that may have changed and work on.
     * @return
     */
    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph copiedGraph = new DirectedWeightedGraph_(originalGraph);
        return copiedGraph;
    }

    /**
     * This function performs BFS on the transposed graph.
     * @param key int - starting point for the BFS
     * @param t boolean - this boolean commands the function to perform BFS on the transposed graph.
     */
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

    /**
     * print path data for testing for each node.
     * @param key
     */
    public void printtesting(int key) {
        //PRINTING//
        System.out.println("TABLE FOR "+key);
        for (int i = 0; i < pathData[key].length; i++) {
            if (pathData[key][i][0]>=Integer.MAX_VALUE) System.out.println(i+" "+99999999+" "+pathData[key][i][1]+"TAG IS "+originalGraph.getNode(i).getTag());
            else System.out.println(i+" "+pathData[key][i][0]+" "+pathData[key][i][1]+"TAG IS "+originalGraph.getNode(i).getTag());

        }
        System.out.println();

    }

    /**
     * build the transpose of the original graph.
     */
    public void transpose(){
        transposeGraph = new DirectedWeightedGraph_(originalGraph,true);
    }

    /**
     * the function checking if the graph is connected.
     * connected graph means you have a way to travel from each node to every other node.
     *if we have done this calculation before the graph will be remained the same, and we don't have you do it again.
     * save is isConnected.
     * @return
     */
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

    /**
     * return the shortest path in Distance from node A to node B.
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        //use pathData after we update it in calculatePathData
        if (!pathCalculated) BFS(src);
        if (pathData[src][dest][0]>=Integer.MAX_VALUE) return -1;
        else return pathData[src][dest][0];
    }


    /**
     * return the shortest path from node A to node B.
     * this function return a list of Nodes
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
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

    /**
     * returns the center of the graph.
     * each node in the graph have maximum distance to other point in the graph.
     * the center is the node with the LOWEST maximum distance.
     * @return
     */
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

    /**
     * * the Travel Sales Person function.
     * this function calculate the shortest way you can go to every Node in the copied graph.
     * @param cities
     * @return
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        double countDis=0;

        List<NodeData> tsp=new ArrayList<>();
        boolean [] visited=new boolean[cities.size()];
        for(boolean b:visited){
            b=false;
        }
        NodeData current=cities.get(0);
        visited[0]=true;
        boolean allVisited=false;
        tsp.add(cities.get(0));
        while (!allVisited){
            int nearestIndex=-1;
            double nearestDist=Double.MAX_VALUE;
            for(int i=0;i< visited.length;i++){
                double currentDistToI=shortestPathDist(current.getKey(),i);
                if(current.getKey()!=i&& !visited[i] && currentDistToI<nearestDist){
                    nearestIndex=i;
                    nearestDist=currentDistToI;
                }
            }
            if(nearestIndex!=-1){
                if(copy().getEdge(current.getKey(),nearestIndex)!=null){
                    tsp.add(copy().getNode(nearestIndex));
                    visited[nearestIndex]=true;
                    current=copy().getNode(nearestIndex);
                }else {
                    List<NodeData> pathList=shortestPath(current.getKey(), nearestIndex);
                    for(int i=0;i<pathList.size();i++){
                        tsp.add(pathList.get(i));
                    }

                    visited[nearestIndex]=true;
                    current=copy().getNode(nearestIndex);
                }

            }
            allVisited=true;
            for (boolean b : visited) {
                if (!b)
                    allVisited = false;
            }
        }
        List <NodeData> fromEndToZero=shortestPath (tsp.get(tsp.size()-1).getKey(),0);

        for(int i=0;i<fromEndToZero.size();i++){
            tsp.add(fromEndToZero.get(i));
        }

        for (int i=0;i<tsp.size()-1;i++){
            if(tsp.get(i).getKey()==tsp.get(i+1).getKey()){
                tsp.remove(i);
            }
        }
        return tsp;
    }

    /**
     * Save function will make new json file with the current graph parameters.
     * true will be returned if the file was saved properly.
     * false will be returned other way.
     * @param file - the file name (may include a relative path).
     * @return
     */
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


    /**
     * Load function gets String with file name.
     * if the file contains proper input the graph will be loaded to the graph algorithm.
     * @param file - file name of JSON file
     * @return
     */
    @Override
    public boolean load( String file ) {
        try {
            DirectedWeightedGraph_ weightedGraph = new DirectedWeightedGraph_(file);
            JSONObject jsonObject = parseJSONFile(file);
            JSONArray jsonNodes = jsonObject.getJSONArray("Nodes");
            for (int i = 0; i < jsonNodes.length(); i++) {
                int key = jsonNodes.getJSONObject(i).getInt("id");
                String pos = jsonNodes.getJSONObject(i).getString("pos");
                NodeData_ v = new NodeData_(key, pos);
                weightedGraph.addNode(v);
            }
            int edgeID = 0;
            JSONArray jsonEdges = jsonObject.getJSONArray("Edges");
            for (int i = 0; i < jsonEdges.length(); i++) {
                int src = jsonEdges.getJSONObject(i).getInt("src");
                int dest = jsonEdges.getJSONObject(i).getInt("dest");
                double w = jsonEdges.getJSONObject(i).getDouble("w");
                weightedGraph.connect(src, dest, w);
            }
            init(weightedGraph);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}