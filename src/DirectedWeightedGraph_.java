import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;

import java.util.*;
import java.util.function.Consumer;




/**
 * Directed Weight Graph is a class representing graph.
 * A Graph is basically a set of vertex and a set of edges.
 * We used java maps to represent each Set and to access  their components easily.
 * nodes Map key is the ID of each Node.
 * edges Map key is the ID of each Edge (that we created).
 * each Node also have 2 Maps that represents Edges that go to the graph,
 * and OutEdges that go from the Node to other Nodes.
 */
public class DirectedWeightedGraph_ implements DirectedWeightedGraph {

    /** This object holds 2 hashmaps, one for holding the nodes and one for edges
     * edgeID is for listing the amount of edges and mainly for giving a unique
     * ID for each edge.
     */

    private Map<Integer, NodeData> nodes; //Integer is key
    private Map<Integer, EdgeData> edges; //Integer is ID
    private int edgeID = 0;
    private int MC = 0;


    public DirectedWeightedGraph_() {
        nodes = new HashMap<>();
        edges = new HashMap<>();
    }

    /**
     * Deep Copy constructor
     * @param g - the graph to copy
     */
    public DirectedWeightedGraph_(DirectedWeightedGraph g) {
        nodes = new HashMap<>();
        edges = new HashMap<>();

        /** Go through all the nodes in g and create a new node that copies the node in g **/
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

    /**
     * transpose constructor, creates a transposed graph for a given graph g
     * @param g - the graph to transpose
     * @param True - to command the constructor to transpose
     */
    public DirectedWeightedGraph_(DirectedWeightedGraph g,boolean True) {
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
            int src = currEdge.getDest();
            int dest = currEdge.getSrc();
            EdgeData_ T = new EdgeData_(src, dest, currEdge.getWeight(), currEdge.getId());

            /**Three hashmaps that hold edges**/
            nodes.get(T.getSrc()).getOutEdges().put(T.getDest(), T);
            nodes.get(T.getDest()).getInEdges().put(T.getSrc(), T);
            edges.put(T.getId(), T);

        }
    }

    /**
     * for a given jsonfile, create a graph from the information.
     * @param jsonFileName - the json file to read from
     */
    public DirectedWeightedGraph_(String jsonFileName) {
        nodes = new HashMap<>();
        edges = new HashMap<>();

        JSONObject jsonObject = null;
        try {
            jsonObject = lib.parseJSONFile(jsonFileName);
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

    /**
     * For a given key return the Node from the graph
     * @param key - the node_id
     * @return NodeData - the asked node
     */
    @Override
    public NodeData getNode(int key)
    {
        return nodes.get(key);
    }

    /**
     * For a given source and destination return the asked edge
     * @param src - the ID of the source node
     * @param dest - the ID of the destination node
     * @return EdgeData - the asked edge
     */
    @Override
    public EdgeData getEdge(int src, int dest)
    {
        if(nodes.get(dest).getInEdges().containsKey(src)){
            return nodes.get(dest).getInEdges().get(src);
        }
        return null;
    }

    public EdgeData getEdgeOut(int src, int dest)
    {
        return nodes.get(dest).getOutEdges().get(src);
    }

    /**
     * For a given key return the Edge from the graph
     * @param id - the ID of the asked edge
     * @return EdgeData - the asked edge
     */
    public EdgeData getEdge(int id)
    {
        return edges.get(id);
    }

    /**
     * Adds a new node to the graph
     * @param n NodeData - the new node you want to insert
     */
    @Override
    public void addNode(NodeData n) {
        nodes.put(n.getKey(),n);
        MC++;
    }

    /**
     * Connects two given Node with a new edge that you're inserting into the graph with the given weight.
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if(nodes.containsKey(src)&&nodes.containsKey(dest)){
            EdgeData_ edgeData = new EdgeData_(src,dest,w, edgeID);
            edgeID++;
            MC++;
            nodes.get(src).getOutEdges().put(dest,edgeData);
            nodes.get(dest).getInEdges().put(src,edgeData);
            edges.put(edgeData.getId(),edgeData);
        }
    }

    /**
     * Returns the iterator for the node's hashmap.
     * @return Iterator - the iterator itself.
     */
    @Override
    public Iterator<NodeData> nodeIter() {
        return new NodeIter();

    }

    /**
     * Returns the iterator for the edge's hashmap.
     * @return Iterator - the iterator itself.
     */
    @Override
    public Iterator<EdgeData> edgeIter() {
        return new EdgeIter();
    }

    /**
     * Returns the iterator for the out edge's hashmap,
     * this hashmap is stored inside the Node's object.
     * @param node_id - the ID of the node that you want to access it's out edges hashmap.
     * @return Iterator - the iterator itself.
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return new outEdgesIter(node_id); //probably not the solution but maybe it is
    }

    /**
     * This functions removes a given node from the graph.
     * It first recognizes the relevant edges it needs to remove.
     * The first place is remove the edge from the edge's hashmap that is in the graph.
     * The second the third places are from the out edge's hashmap the is in the source node,
     * and from the in edge's hashmap that is in the destination node.
     *
     * The function runs several loops that running on all the out edges\in edges.
     * There for the time complexity where v.degree is the amount of in+out edges, is O(v.degree)
     *
     * lets us note that .keyset function is O(1)
     * @param key - The id of the node we want to remove.
     * @return NodeData - the value of the node we removed. (The node itself) , returns null if no edge was removed.
     */
    @Override //O(v.degree)
    public NodeData removeNode(int key) {

        if (nodes.containsKey(key)) {
            Object[] inedges = nodes.get(key).getInEdges().keySet().toArray();      //keyset is O(1)
            Object[] outedges = nodes.get(key).getOutEdges().keySet().toArray();    //keyset is O(1)
            int[] inIds = new int[inedges.length];
            int[] outIds = new int[outedges.length];

            /**SIZE OF inIds+outIds is equal to v.degree**/
            /** O(3*v.degree)=O(v.degree)**/
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

            nodes.get(key).getOutEdges().clear();       //Clear is O(n) where n is the number of out edges
            nodes.get(key).getInEdges().clear();        //Clear is O(n) where n is the number of in edges

            MC++;
            return nodes.remove(key);
        } else return null;
    }

    /**
     * This functions removes the edge the runs from the given source to a given destination.
     * @param src - The ID of the source of the edge we want to remove.
     * @param dest - the ID of the destination of the edge we want to remove.
     * @return EdgeData - the actual Edge we removed, returns null if no edge was removed.
     */
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

    /**
     * Remove the edge by a given ID.
     * @param id - The ID of the edge we want to remove.
     * @return EdgeData - the actual Edge we removed, returns null if no edge was removed.
     */
    public EdgeData removeEdge(int id) {
        if (edges.containsKey(id)) {
            int src = edges.get(id).getSrc();
            int dest = edges.get(id).getDest();
            return removeEdge(src, dest);
        } else return null;
    }

    /**
     *
     * @returns the amount of nodes in the graph.
     */
    @Override
    public int nodeSize() {
        return nodes.size();
    }

    /**
     *
     * @returns the amount of edges in the graph.
     */
    @Override
    public int edgeSize() {
        return edges.size();
    }

    /**
     * @returns the amount of changes in this graph.
     */
    @Override
    public int getMC() {
        return MC;
    }

    private class NodeIter implements Iterator<NodeData> {

        private NodeData currNode;
        private Iterator<NodeData> Iter;
        public NodeIter() {
            Iter = nodes.values().iterator();
        }


        @Override
        public boolean hasNext() {
            return Iter.hasNext();
        }

        @Override
        public NodeData next() {
            currNode=Iter.next();
            return currNode;
        }

        @Override
        public void remove() {
           NodeData tempNode = Iter.next();
           NodeData nextNode;
           removeNode(currNode.getKey());
           Iter = nodes.values().iterator();
           nextNode = tempNode;
           while (nextNode!=tempNode) {
               nextNode=Iter.next();
           }
           currNode = tempNode;
        }

        @Override
        public void forEachRemaining(Consumer<? super NodeData> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    private class EdgeIter implements Iterator<EdgeData> {

        private EdgeData currEdge;
        private Iterator<EdgeData> Iter;
        public EdgeIter() {
            Iter = edges.values().iterator();
        }

        @Override
        public boolean hasNext() {
            return Iter.hasNext();
        }

        @Override
        public EdgeData next() {
            currEdge = Iter.next();
            return currEdge;
        }

        @Override
        public void remove() {
            EdgeData tempEdge = Iter.next();
            EdgeData nextEdge;
            removeEdge(currEdge.getId());
            Iter = edges.values().iterator();
            nextEdge = tempEdge;
            while (nextEdge!=tempEdge) {
                nextEdge=Iter.next();
            }
            currEdge = tempEdge;
        }

        @Override
        public void forEachRemaining(Consumer<? super EdgeData> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    private class outEdgesIter implements Iterator<EdgeData> {

        private Iterator<EdgeData> Iter;
        private EdgeData currEdge;
        int key;
        public outEdgesIter(int NodeKey) {
            Iter = nodes.get(NodeKey).getOutEdges().values().iterator();
            key = NodeKey;
        }

        @Override
        public boolean hasNext() {
            return Iter.hasNext();
        }

        @Override
        public EdgeData next() {
            currEdge = Iter.next();
            return currEdge;
        }

        @Override
        public void remove() {
            if (!Iter.hasNext()) return;
            EdgeData tempEdge = Iter.next();
            EdgeData nextEdge;
            removeEdge(currEdge.getId());
            Iter = nodes.get(key).getOutEdges().values().iterator();
            nextEdge = tempEdge;
            while (nextEdge!=tempEdge) {
                nextEdge=Iter.next();
            }
            currEdge = tempEdge;
        }

        @Override
        public void forEachRemaining(Consumer<? super EdgeData> action) {
            Iterator.super.forEachRemaining(action);
        }
    }


}


