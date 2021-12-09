import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import api.DirectedWeightedGraph;


import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

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
        Iterator<NodeData> NodesI = g.nodeIter();
        int i = 0;
        while (NodesI.hasNext()){
            NodeData n = NodesI.next();
            assertEquals(n,g.getNode(i));
            i++;
        }
    }


    @Test
    void edgeIter() {
        Iterator<NodeData> NodesI = g.nodeIter();
        NodeData n;
        int i = 0;
        while (NodesI.hasNext()) {
            n = NodesI.next();
            Iterator<EdgeData> Edges = g.edgeIter();
            while (Edges.hasNext()) {
                EdgeData e = Edges.next();
                assertEquals(e,g.getEdge(i));
                i++;
            }
            i = 0;
        }
    }

    @Test
    void testEdgeIter() {
        Iterator<NodeData> NodesI = g.nodeIter();
        NodeData n;
        int i = 0;
        int j = 0;
        while (NodesI.hasNext()) {
            n = NodesI.next();
            Iterator<EdgeData> OutEdges = g.edgeIter(n.getKey());
            while (OutEdges.hasNext()) {
                EdgeData e = OutEdges.next();
                if (g.getNode(i).getOutEdges().get(j) != null) {
                    assertEquals(e,g.getNode(i).getOutEdges().get(j));
                    j++;
                }
            }
            i = 0;
            i++;
        }
    }
    
    @Test
    void removeNode() {
        Iterator<NodeData> NodesI = g.nodeIter();
        int r = 7;
        int r1 = 2;
        assertNull(g.removeNode(r));
        g.removeNode(r1);
        assertNull(g.removeNode(r1));
        Iterator<NodeData> NodesI1 = g.nodeIter();
        assertNotEquals(NodesI,NodesI1);
        assertNotEquals(NodesI,g.nodeIter());
        NodesI = g.nodeIter();
        while (NodesI.hasNext()){
            NodeData n =  NodesI.next();
            NodeData n1 = NodesI1.next();
            assertEquals(n,n1);
            }
        }



    @Test
    void removeEdge() {
        DirectedWeightedGraph_ copied=new DirectedWeightedGraph_(g);
        copied.removeEdge(0,1);
        assertEquals(14,copied.edgeSize());

    }


    @Test
    void nodeSize() {
        assertEquals(5,g.nodeSize());
    }

    @Test
    void edgeSize() {
        assertEquals(15,g.edgeSize());
    }

    @Test
    void getMC() {
        assertEquals(0,g.getMC());
    }
}