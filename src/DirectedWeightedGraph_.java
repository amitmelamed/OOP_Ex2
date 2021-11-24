import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static api.lib.parseJSONFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;



public class DirectedWeightedGraph_ implements DirectedWeightedGraph {

    private Map<Integer, NodeData> nodes; //Integer is key
    private Map<Integer, EdgeData> edges; //Integer is ID


    public DirectedWeightedGraph_(String jsonFileName) throws IOException, JSONException {
        nodes = new HashMap();
        edges = new HashMap<>();

        JSONObject jsonObject = parseJSONFile(jsonFileName);
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


            /**Three hashmaps that hold edges**/
            nodes.get(e.getSrc()).getOutEdges().put(e.getDest(), e);
            nodes.get(e.getDest()).getInEdges().put(e.getSrc(), e);
            edges.put(e.getId(), e);
        }
    }

    @Override
    public NodeData getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return nodes.get(dest).getInEdges().get(src);
    }

    public EdgeData getEdge(int id) {
        return edges.get(id);
    }

    @Override
    public void addNode(NodeData n) {

    }

    @Override
    public void connect(int src, int dest, double w) {

    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return nodes.values().iterator(); //probably not the solution but maybe it is

    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return edges.values().iterator(); //probably not the solution but maybe it is
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return nodes.get(node_id).getOutEdges().values().iterator(); //probably not the solution but maybe it is
    }

    @Override //O(v.degree)
    public NodeData removeNode(int key) {

        Object[] inedges = nodes.get(key).getInEdges().keySet().toArray();      //keyset is O(1)
        Object[] outedges = nodes.get(key).getOutEdges().keySet().toArray();    //keyset is O(1)
        int[] inIds = new int[inedges.length];
        int[] outIds = new int[outedges.length];

        /**(SIZE OF) inIds+outIds = v.degree**/
        for (int i = 0; i < inIds.length; i++) {
            inIds[i] = getEdge((int)inedges[i], key).getId();
        }
        for (int i = 0; i < outIds.length; i++) {
            outIds[i] = getEdge(key, (int)outedges[i]).getId();
        }

        for (int i = 0; i < inIds.length; i++) {
            removeEdge(inIds[i]);
        }
        for (int i = 0; i < outIds.length; i++) {
            removeEdge(outIds[i]);
        }
        /** O(2*v.degree)=O(v.degree)**/
        nodes.get(key).getOutEdges().clear();
        nodes.get(key).getInEdges().clear();

        return nodes.remove(key);
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        int id = nodes.get(dest).getInEdges().get(src).getId();
        edges.remove(id);
        nodes.get(dest).getInEdges().remove(src);
        return nodes.get(src).getOutEdges().remove(dest);

    }

    public void removeEdge(int id) {
        int src = edges.get(id).getSrc();
        int dest = edges.get(id).getDest();
        removeEdge(src, dest);

    }
    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int getMC() {
        return 0;
    }
}