import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectedWeightedGraph_Test {
    DirectedWeightedGraph_ g = new DirectedWeightedGraph_("data/G0.json");

    @Test
    void getNode() {
        NodeData n = g.getNode(0);
        assertEquals(n,g.getNode(0));
    }

    @Test
    void getEdge() {
        EdgeData e = g.getEdge(0);
        EdgeData e0 = g.getEdge(0,1);
        assertEquals(e,g.getEdge(0,1));
        assertEquals(e,g.getEdge(0));
        assertEquals(g.getEdge(0),g.getEdge(0,1));
    }

    @Test
    void getEdgeOut() {
    }

    @Test
    void testGetEdge() {
    }

    @Test
    void addNode() {
        NodeData_ n = new NodeData_(-1,"000,000,000.0");
        g.addNode(n);
        assertEquals(n,g.getNode(-1));
    }

    @Test
    void connect() {
        int source = 0;
        int destination = 4;
        double weight = 999.9;
        g.connect(source,destination,weight);
        EdgeData e = g.getEdge(source,destination);
        assertEquals(e,g.getEdge(source,destination));
        assertEquals(e,g.getNode(source).getOutEdges().get(destination));
        assertEquals(e,g.getNode(destination).getInEdges().get(source));
        int source1 = -1;
        int destination1 = 2;
        double weight1 = 999.9;
        g.connect(source1,destination1,weight1);
        EdgeData e1 = g.getEdge(source1,destination1);
        assertEquals(e1,g.getEdge(source1,destination1));
        assertEquals(e1,g.getNode(source+1).getOutEdges().get(destination1));
        assertEquals(e1,g.getNode(destination1).getInEdges().get(source+1));

    }

    @Test
    void nodeIter() {

    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void testRemoveEdge() {
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }
}