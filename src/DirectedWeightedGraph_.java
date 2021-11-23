import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import org.w3c.dom.Node;

import java.util.*;

public class DirectedWeightedGraph_ implements DirectedWeightedGraph {
    /*
    private hashmap nodes
    private hashmap edges
    private Iterator<NodeData_> nodes = new HashMap<Integer, NodeData_>();
     */
    private Map<Integer, NodeData> nodes;
    private static int sizeOfE;
    private ArrayList<EdgeData> test = new ArrayList(); // WE NEED TO HOLD THE EDGES
                                                        // IN A HASHMAP AND REMOVE
                                                        // THE HASHMAPS FROM THE NODES
                                                        // BECAUSE ITS STUPID.



    public DirectedWeightedGraph_(String jsonFileName) throws IOException, JSONException {
        nodes = new HashMap();
        JSONObject jsonObject = Ex2.parseJSONFile(jsonFileName);
        JSONArray jsonNodes = jsonObject.getJSONArray("Nodes");
        for(int i=0;i<jsonNodes.length();i++){
            int key=jsonNodes.getJSONObject(i).getInt("id");
            String pos=jsonNodes.getJSONObject(i).getString("pos");
            NodeData_ v = new NodeData_(key, pos);
            nodes.put(v.getKey(), v);
        }

        JSONArray jsonEdges = jsonObject.getJSONArray("Edges");
        for (int i = 0; i < jsonEdges.length(); i++) {
            int src = jsonEdges.getJSONObject(i).getInt("src");
            int dest = jsonEdges.getJSONObject(i).getInt("dest");
            double w = jsonEdges.getJSONObject(i).getDouble("w");

            EdgeData_ e = new EdgeData_(src, dest, w);
           // System.out.println(e.toString());

            nodes.get(e.getSrc()).getOutEdges().put(e.getDest(), e);
            nodes.get(e.getDest()).getInEdges().put(e.getSrc(), e);
            test.add(e);
            sizeOfE++;
        }
        /**
       #######Edges:########
                for each Edge:
                    EdgeData_ e = new EdgeData(src, dest, weight);
                    nodes.get(e.src).outEdges.put(e.dest, e);
                    nodes.get(e.dest).inEdges.put(e.src, e);
                    sizeOfE++;


        Now we have all the nodes ready to receive the edges into their hashmaps.
        For each Edge we have src and dst,
        the edge goes into two nodes.
        The src node put the edge into its outEdges (something like outEdges.put(e.getDest(), e);)
        The dst node put the edge into its inEdges                 (inEdges.put(e.getSource, e);)

        For each edge we do sizeOfE++;

         */


    }



    @Override
    public NodeData getNode(int key) {
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return null;
    }
    public EdgeData getEdge2(int src, int dest) {
        return nodes.get(src).getOutEdges().get(dest);
    }

    @Override
    public void addNode(NodeData n) {

    }

    @Override
    public void connect(int src, int dest, double w) {

    }

    @Override
    public Iterator<NodeData> nodeIter() {
        Iterator<NodeData> g = nodes.values().iterator();

        return g;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        //size of e minus minus;
        return nodes.get(src).getOutEdges().remove(dest);
    }

    @Override
    public int nodeSize() {
        return 0;
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int getMC() {
        return 0;
    }
}