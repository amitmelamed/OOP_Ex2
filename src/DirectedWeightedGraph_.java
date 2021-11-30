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
    private int edgeID = 0;
    private int MC = 0;

    public DirectedWeightedGraph_(DirectedWeightedGraph g) {
        nodes = new HashMap<>();
        edges = new HashMap<>();

        Iterator<NodeData> NodeI = g.nodeIter();
        while(NodeI.hasNext()) {
            NodeData currNode = NodeI.next();


            nodes.put(currNode.getKey(), new NodeData_(currNode));
        }

        Iterator<EdgeData> EdgeI = g.edgeIter();
        while(EdgeI.hasNext()) {
            EdgeData currEdge = EdgeI.next();
            EdgeData_ newEdge = new EdgeData_(currEdge);

            /**Three hashmaps that hold edges**/
            nodes.get(newEdge.getSrc()).getOutEdges().put(newEdge.getDest(), newEdge);
            nodes.get(newEdge.getDest()).getInEdges().put(newEdge.getSrc(), newEdge);
            edges.put(newEdge.getId(), newEdge);

        }
    }
    public DirectedWeightedGraph_(String jsonFileName) {
        nodes = new HashMap<>();
        edges = new HashMap<>();

        JSONObject jsonObject = null;
        try {
            jsonObject = parseJSONFile(jsonFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            EdgeData_ e = new EdgeData_(src, dest, w, edgeID);
            edgeID++;

            /**Three hashmaps that hold edges**/
            nodes.get(e.getSrc()).getOutEdges().put(e.getDest(), e);
            nodes.get(e.getDest()).getInEdges().put(e.getSrc(), e);
            edges.put(e.getId(), e);
        }
    }

    @Override
    public NodeData getNode(int key)
    {
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest)
    {
        return nodes.get(dest).getInEdges().get(src);
    }

    public EdgeData getEdge(int id)
    {
        return edges.get(id);
    }

    @Override
    public void addNode(NodeData n) {
        nodes.put(n.getKey(),n);
        MC++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        if(nodes.containsKey(src)&&nodes.containsKey(dest)){
            EdgeData_ edgeData=new EdgeData_(src,dest,w, edgeID);
            edgeID++;
            MC++;
            nodes.get(src).getOutEdges().put(dest,edgeData);
            nodes.get(dest).getInEdges().put(src,edgeData);
            edges.put(edgeData.getId(),edgeData);
        } //MAYBE ADD SOMETHING THAT CHECKS IF THE EDGE ALREADY EXISTS WITH THE SAME WEIGHT
          //OR MAYBE ITS OKAY TO HAVE DUPLICATES AND WE NEED TO ADD THE EDGE ANYWAY
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

        if (nodes.containsKey(key)) {
            Object[] inedges = nodes.get(key).getInEdges().keySet().toArray();      //keyset is O(1)
            Object[] outedges = nodes.get(key).getOutEdges().keySet().toArray();    //keyset is O(1)
            int[] inIds = new int[inedges.length];
            int[] outIds = new int[outedges.length];

            /**SIZE OF inIds+outIds is equal to v.degree**/
            for (int i = 0; i < inIds.length; i++) {
                inIds[i] = getEdge((int) inedges[i], key).getId();
            }
            for (int i = 0; i < outIds.length; i++) {
                outIds[i] = getEdge(key, (int) outedges[i]).getId();
            }

            for (int i = 0; i < inIds.length; i++) {
                removeEdge(inIds[i]);
            }
            for (int i = 0; i < outIds.length; i++) {
                removeEdge(outIds[i]);
            }
            /** O(2*v.degree)=O(v.degree)**/
            nodes.get(key).getOutEdges().clear();       //Clear is O(n) where n is the number of out edges
            nodes.get(key).getInEdges().clear();        //Clear is O(n) where n is the number of in edges

            MC++;
            return nodes.remove(key);
        } else return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (nodes.containsKey(src)&&nodes.containsKey(dest)&&nodes.get(src).getOutEdges().containsKey(dest)) {

        int id = nodes.get(dest).getInEdges().get(src).getId();
        // could also be nodes.get(src).getOutEdges().get(dest).getId();
        edges.remove(id);
        nodes.get(dest).getInEdges().remove(src);
        MC++;
        return nodes.get(src).getOutEdges().remove(dest);
        } else return null;
    }

    public EdgeData removeEdge(int id) {
        if (edges.containsKey(id)) {
            int src = edges.get(id).getSrc();
            int dest = edges.get(id).getDest();
            return removeEdge(src, dest);
        } else return null;
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
        return MC;
    }
}